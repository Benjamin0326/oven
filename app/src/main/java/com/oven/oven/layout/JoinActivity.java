package com.oven.oven.layout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.oven.oven.R;
import com.oven.oven.component.network;
import com.oven.oven.model.LoginRes;
import com.oven.oven.service.LoginService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.oven.oven.layout.ModifyUserInfoActivity.PICK_CONTACT_REQUEST;

public class JoinActivity extends AppCompatActivity {

    private EditText et_mail, et_pw, et_pw2, et_uname, et_cname, et_hp, et_addr, et_zipcode, et_detailed_addr;
    private Button btn_ok, btn_search;
    private String mail, pw, pw2, uname, cname, hp, addr, detailed_addr, zipcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        et_mail = (EditText) findViewById(R.id.et_join_mail);
        et_pw = (EditText) findViewById(R.id.et_join_pw);
        et_pw2 = (EditText) findViewById(R.id.et_join_pw2);
        et_uname = (EditText) findViewById(R.id.et_join_uname);
        et_cname = (EditText) findViewById(R.id.et_join_cname);
        et_hp = (EditText) findViewById(R.id.et_join_hp);
        et_addr = (EditText) findViewById(R.id.et_join_address);
        et_zipcode = (EditText) findViewById(R.id.et_join_zipcode);
        et_detailed_addr = (EditText) findViewById(R.id.et_join_detailed_address);

        btn_ok = (Button) findViewById(R.id.btn_join_ok);
        btn_search = (Button)findViewById(R.id.btn_search_address_join);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JoinActivity.this, ItemListMainActivity.class);
                startActivityForResult(intent, PICK_CONTACT_REQUEST);
            }
        });

        Button.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_mail.getText().toString().length()==0){
                    Toast.makeText(JoinActivity.this, "이메일 주소를 입력해 주세요.", Toast.LENGTH_LONG).show();
                    return;
                }
                if(et_pw.getText().toString().length()==0){
                    Toast.makeText(JoinActivity.this, "비밀번호를 입력해 주세요.", Toast.LENGTH_LONG).show();
                    return;
                }
                if(et_pw2.getText().toString().length()==0){
                    Toast.makeText(JoinActivity.this, "비밀번호(확인)을 입력해 주세요.", Toast.LENGTH_LONG).show();
                    return;
                }
                if(et_cname.getText().toString().length()==0){
                    Toast.makeText(JoinActivity.this, "업체명을 입력해 주세요.", Toast.LENGTH_LONG).show();
                    return;
                }
                if(et_uname.getText().toString().length()==0){
                    Toast.makeText(JoinActivity.this, "사용자 이름을 입력해 주세요.", Toast.LENGTH_LONG).show();
                    return;
                }

                if(et_addr.getText().toString().length()==0){
                    Toast.makeText(JoinActivity.this, "주소를 입력해 주세요.", Toast.LENGTH_LONG).show();
                    return;
                }
                if(et_zipcode.getText().toString().length()==0){
                    Toast.makeText(JoinActivity.this, "우편번호를 입력해 주세요.", Toast.LENGTH_LONG).show();
                    return;
                }
                if(et_hp.getText().toString().length()==0){
                    Toast.makeText(JoinActivity.this, "휴대전화번호를 입력해 주세요.", Toast.LENGTH_LONG).show();
                    return;
                }

                mail = et_mail.getText().toString();
                pw = et_pw.getText().toString();
                pw2 = et_pw2.getText().toString();
                uname = et_uname.getText().toString();
                cname = et_cname.getText().toString();
                hp = et_hp.getText().toString();
                addr = et_addr.getText().toString();
                detailed_addr = et_detailed_addr.getText().toString();
                zipcode = et_zipcode.getText().toString();

                if(pw.compareTo(pw2)!=0){
                    Toast.makeText(JoinActivity.this, "비밀번호를 다시 확인해주세요.(같지 않음)", Toast.LENGTH_LONG).show();
                    return;
                }

                Toast.makeText(JoinActivity.this, mail+" / "+pw, Toast.LENGTH_LONG).show();

                postJoin(mail, pw, uname, hp, cname, addr, detailed_addr, zipcode);
            }
        };

        btn_ok.setOnClickListener(listener);

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

    public void postJoin(String mail, String pw, String uname, String hp, String cname, String addr, String de_addr, String zipcode){
        LoginService service = network.buildRetrofit().create(LoginService.class);
        Call<LoginRes> joinCall = service.postJoin(mail, pw, uname, hp, cname, addr, de_addr, zipcode);

        joinCall.enqueue(new Callback<LoginRes>() {
            @Override
            public void onResponse(Call<LoginRes> call, Response<LoginRes> response) {
                if(response.isSuccessful()){
                    LoginRes testRes = response.body();

                    Log.d("Join Info : ", testRes.getCode()+"\n"+testRes.getMsg());
                    //for(int i=0;i<festivalLists.get(position).getVideo().length;i++){
                    //    Log.d("#Test :", festivalLists.get(position).getVideo()[i]);
                    //}
                    //Picasso.with(getActivity()).load(festivalLists.get(position).getImg()[1]).into(img);
                    finish();
                    return;
                }
                int code = response.code();
                Log.d("TEST", "err code : " + code);
            }

            @Override
            public void onFailure(Call<LoginRes> call, Throwable t) {
                Toast.makeText(JoinActivity.this, "Failed to Access", Toast.LENGTH_LONG).show();
                Log.i("TEST","err : "+ t.getMessage());
            }
        });
    }

}
