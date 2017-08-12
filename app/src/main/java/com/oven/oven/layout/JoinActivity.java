package com.oven.oven.layout;

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

public class JoinActivity extends AppCompatActivity {

    private EditText et_mail, et_pw;
    private Button btn_ok;
    private String mail, pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        et_mail = (EditText) findViewById(R.id.et_join_mail);
        et_pw = (EditText) findViewById(R.id.et_join_pw);
        btn_ok = (Button) findViewById(R.id.btn_join_ok);

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
                mail = et_mail.getText().toString();
                pw = et_pw.getText().toString();
                Toast.makeText(JoinActivity.this, mail+" / "+pw, Toast.LENGTH_LONG).show();

                postJoin(mail, pw);
            }
        };

        btn_ok.setOnClickListener(listener);

    }

    public void postJoin(String mail, String pw){
        LoginService service = network.buildRetrofit().create(LoginService.class);
        Call<LoginRes> joinCall = service.postJoin(mail, pw, "testUname", "01012345678", "testcName", "TestAddress");

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
