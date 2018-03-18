package com.oven.oven.layout;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.oven.oven.MainActivity;
import com.oven.oven.R;
import com.oven.oven.component.JusoNetwork;
import com.oven.oven.component.network;
import com.oven.oven.model.AddressSearchResult;
import com.oven.oven.model.SettingUserInfo;
import com.oven.oven.service.JusoService;
import com.oven.oven.service.SettingService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModifyUserInfoActivity extends AppCompatActivity {

    static final int PICK_CONTACT_REQUEST = 0;
    private EditText et_mail, et_pw1, et_pw2, et_name, et_phone, et_addr, et_cname, et_zipcode, et_detailed_addr;
    private Button btn_setting, btn_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_user_info);

        et_mail = (EditText)findViewById(R.id.et_setting_mail);
        et_pw1 = (EditText)findViewById(R.id.et_setting_pw);
        et_pw2 = (EditText)findViewById(R.id.et_setting_pw2);
        et_name = (EditText)findViewById(R.id.et_setting_uname);
        et_phone = (EditText)findViewById(R.id.et_setting_hp);
        et_addr = (EditText)findViewById(R.id.et_setting_address);
        et_cname = (EditText)findViewById(R.id.et_setting_cname);
        et_zipcode = (EditText)findViewById(R.id.et_setting_zipcode);
        et_detailed_addr = (EditText)findViewById(R.id.et_setting_detailed_address);

        btn_setting = (Button)findViewById(R.id.btn_setting_ok);
        btn_search = (Button)findViewById(R.id.btn_search_address_modify_user_info);

        postGetUserInfo();

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ModifyUserInfoActivity.this, ItemListMainActivity.class);
                startActivityForResult(intent, PICK_CONTACT_REQUEST);
            }
        });

        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_mail.getText().toString().length()==0){
                    Toast.makeText(ModifyUserInfoActivity.this, "이메일 주소를 입력해 주세요.", Toast.LENGTH_LONG).show();
                    return;
                }
                if(et_pw1.getText().toString().length()==0){
                    Toast.makeText(ModifyUserInfoActivity.this, "비밀번호를 입력해 주세요.", Toast.LENGTH_LONG).show();
                    return;
                }
                if(et_pw2.getText().toString().length()==0){
                    Toast.makeText(ModifyUserInfoActivity.this, "비밀번호(확인)을 입력해 주세요.", Toast.LENGTH_LONG).show();
                    return;
                }
                if(et_cname.getText().toString().length()==0){
                    Toast.makeText(ModifyUserInfoActivity.this, "업체명을 입력해 주세요.", Toast.LENGTH_LONG).show();
                    return;
                }
                if(et_name.getText().toString().length()==0){
                    Toast.makeText(ModifyUserInfoActivity.this, "사용자 이름을 입력해 주세요.", Toast.LENGTH_LONG).show();
                    return;
                }
                if(et_addr.getText().toString().length()==0){
                    Toast.makeText(ModifyUserInfoActivity.this, "주소를 입력해 주세요.", Toast.LENGTH_LONG).show();
                    return;
                }
                if(et_zipcode.getText().toString().length()==0){
                    Toast.makeText(ModifyUserInfoActivity.this, "우편번호를 입력해 주세요.", Toast.LENGTH_LONG).show();
                    return;
                }
                if(et_phone.getText().toString().length()==0){
                    Toast.makeText(ModifyUserInfoActivity.this, "휴대전화번호를 입력해 주세요.", Toast.LENGTH_LONG).show();
                    return;
                }

                String mail = et_mail.getText().toString();
                String pw = et_pw1.getText().toString();
                String pw2 = et_pw2.getText().toString();
                String uname = et_name.getText().toString();
                String cname = et_cname.getText().toString();
                String hp = et_phone.getText().toString();
                String addr = et_addr.getText().toString();
                String zipcode = et_zipcode.getText().toString();
                String detailed_addr = et_detailed_addr.getText().toString();

                if(pw.compareTo(pw2)!=0){
                    Toast.makeText(ModifyUserInfoActivity.this, "비밀번호를 다시 확인해주세요.(같지 않음)", Toast.LENGTH_LONG).show();
                    return;
                }

                postModifyUserInfo(pw, uname, hp, cname, addr, detailed_addr, zipcode);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==PICK_CONTACT_REQUEST){
            if(resultCode==RESULT_OK){
                et_addr.setText(data.getStringExtra("roadAddress"));
                et_zipcode.setText(data.getStringExtra("zipCode"));
            }
        }
    }

    public void postGetUserInfo(){
        SettingService service = network.buildRetrofit().create(SettingService.class);
        Call<SettingUserInfo> joinCall = service.postGetUserInfo(MainActivity.UID);

        joinCall.enqueue(new Callback<SettingUserInfo>() {
            @Override
            public void onResponse(Call<SettingUserInfo> call, Response<SettingUserInfo> response) {
                if(response.isSuccessful()){
                    SettingUserInfo userInfo = response.body();

                    if(userInfo.getCode()==200){
                        et_mail.setText(userInfo.getUser_info().get(0).getEmail());
                        et_name.setText(userInfo.getUser_info().get(0).getUname());
                        et_phone.setText(userInfo.getUser_info().get(0).getHp());
                        et_addr.setText(userInfo.getUser_info().get(0).getAddress());
                        et_cname.setText(userInfo.getUser_info().get(0).getCname());
                        et_zipcode.setText(userInfo.getUser_info().get(0).getZipcode());
                        et_detailed_addr.setText(userInfo.getUser_info().get(0).getDe_addr());
                        return;
                    }
                    else {
                        Toast.makeText(ModifyUserInfoActivity.this, "Failed to Access", Toast.LENGTH_LONG).show();
                        Log.d("TEST", "code : " + userInfo.getCode() + " msg : " + userInfo.getMsg());
                        return;
                    }
                }
                int code = response.code();
                Log.d("TEST", "err code : " + code);
            }

            @Override
            public void onFailure(Call<SettingUserInfo> call, Throwable t) {
                Toast.makeText(ModifyUserInfoActivity.this, "Failed to Access", Toast.LENGTH_LONG).show();
                Log.i("TEST","err : "+ t.getMessage());
            }
        });
    }

    public void postModifyUserInfo(String pw, String uname, String hp, String cname, String addr, String de_addr, String zipcode){
        SettingService service = network.buildRetrofit().create(SettingService.class);
        Call<SettingUserInfo> joinCall = service.postUpdateUserInfo(MainActivity.UID, pw, uname, hp, cname, addr, de_addr, zipcode);

        joinCall.enqueue(new Callback<SettingUserInfo>() {
            @Override
            public void onResponse(Call<SettingUserInfo> call, Response<SettingUserInfo> response) {
                if(response.isSuccessful()){
                    SettingUserInfo userInfo = response.body();

                    if(userInfo.getCode()==200){
                        Toast.makeText(ModifyUserInfoActivity.this, "회원정보 수정이 완료되었습니다.", Toast.LENGTH_LONG).show();
                        setResult(RESULT_OK);
                        finish();
                        return;
                    }
                    else {
                        Toast.makeText(ModifyUserInfoActivity.this, "Failed to Access", Toast.LENGTH_LONG).show();
                        Log.d("TEST", "code : " + userInfo.getCode() + " msg : " + userInfo.getMsg());
                        return;
                    }
                }
                int code = response.code();
                Log.d("TEST", "err code : " + code);
            }

            @Override
            public void onFailure(Call<SettingUserInfo> call, Throwable t) {
                Toast.makeText(ModifyUserInfoActivity.this, "Failed to Access", Toast.LENGTH_LONG).show();
                Log.i("TEST","err : "+ t.getMessage());
            }
        });
    }

    public void getJusoInfo(){
        JusoService service = network.buildRetrofit().create(JusoService.class);
        Call<AddressSearchResult> joinCall = service.getJuso("능동");

        joinCall.enqueue(new Callback<AddressSearchResult>() {
            @Override
            public void onResponse(Call<AddressSearchResult> call, Response<AddressSearchResult> response) {
                if(response.isSuccessful()){
                    AddressSearchResult juso = response.body();
                    Log.d("Code", "Code : " + juso.getCode());
                    if(juso.getCode()==200){
                        //Log.d("TotalCount", "TotalCount : " + juso.getAddress().getMeta().getTotal_count());
                        //Log.d("PagableCount", "PagableCount : " + juso.getAddress().getMeta().getPageable_count());
                        //Log.d("RoadAddress", juso.getAddress().getDocuments().get(0).getRoad_address().getAddress_name());
                        Log.d("Address", juso.getAddress().getDocuments().get(0).getAddress().getAddress_name());
                        //Log.d("ZipCode", "ZipCode : " + juso.getAddress().getDocuments().get(0).getAddress().getZip_code());
                    }


                }

            }

            @Override
            public void onFailure(Call<AddressSearchResult> call, Throwable t) {
                Toast.makeText(ModifyUserInfoActivity.this, "Failed to Access AddressSearch", Toast.LENGTH_LONG).show();
                Log.i("TEST","err : "+ t.getMessage());
            }
        });
    }


}
