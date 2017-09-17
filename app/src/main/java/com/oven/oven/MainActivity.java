package com.oven.oven;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;
import com.oven.oven.component.network;
import com.oven.oven.layout.ItemListActivity;
import com.oven.oven.layout.JoinActivity;
import com.oven.oven.layout.KakaoSignupActivity;
import com.oven.oven.layout.SplashActivity;
import com.oven.oven.model.LoginRes;
import com.oven.oven.service.LoginService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    public static final String PREF = "YF_PREF";
    public static SharedPreferences pref;
    public static int UID;

    private Button btn_login, btn_join;
    private EditText edit_id, edit_pw;
    private SessionCallback callback;
    private CheckBox auto_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getPreferences();

        UID = pref.getInt("uid",-1);
        if(UID!=-1){
            Intent intent = new Intent(MainActivity.this, ItemListActivity.class);
            startActivity(intent);
            //finish();
        }

        Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
        startActivity(intent);

        btn_login = (Button) findViewById(R.id.btn_login);
        btn_join = (Button) findViewById(R.id.btn_join);

        auto_login = (CheckBox) findViewById(R.id.cb_auto_login);

        edit_id = (EditText) findViewById(R.id.edit_login_id);
        edit_pw = (EditText) findViewById(R.id.edit_login_pw);

        Button.OnClickListener btn_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edit_id.getText().toString().length()==0){
                    Toast.makeText(MainActivity.this, "이메일 주소를 입력해 주세요.", Toast.LENGTH_LONG).show();
                    return;
                }
                if(edit_pw.getText().toString().length()==0){
                    Toast.makeText(MainActivity.this, "비밀번호를 입력해 주세요.", Toast.LENGTH_LONG).show();
                    return;
                }
                String id, pw;
                id = edit_id.getText().toString();
                pw = edit_pw.getText().toString();
                //Toast.makeText(MainActivity.this, mail+" / "+pw, Toast.LENGTH_LONG).show();
                postLogin(id, pw);
            }
        };

        Button.OnClickListener btn_join_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, JoinActivity.class);
                startActivity(intent);
            }
        };

        btn_login.setOnClickListener(btn_listener);
        btn_join.setOnClickListener(btn_join_listener);

        callback = new SessionCallback();
        Session.getCurrentSession().addCallback(callback);
        Session.getCurrentSession().checkAndImplicitOpen();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(callback);
    }

    private class SessionCallback implements ISessionCallback {

        @Override
        public void onSessionOpened() {
            redirectSignupActivity();  // 세션 연결성공 시 redirectSignupActivity() 호출
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if(exception != null) {
                Logger.e(exception);
            }
            setContentView(R.layout.activity_main); // 세션 연결이 실패했을때
        }                                            // 로그인화면을 다시 불러옴
    }

    protected void redirectSignupActivity() {       //세션 연결 성공 시 SignupActivity로 넘김
        final Intent intent = new Intent(this, KakaoSignupActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }

    public void postLogin(final String mail, final String pw){
        LoginService service = network.buildRetrofit().create(LoginService.class);
        Call<LoginRes> loginCall = service.postLogin(mail, pw);

        loginCall.enqueue(new Callback<LoginRes>() {
            @Override
            public void onResponse(Call<LoginRes> call, Response<LoginRes> response) {
                if(response.isSuccessful()){
                    LoginRes testRes = response.body();

                    Log.d("Login Info : ", testRes.getCode()+"\n"+testRes.getMsg());
                    //for(int i=0;i<festivalLists.get(position).getVideo().length;i++){
                    //    Log.d("#Test :", festivalLists.get(position).getVideo()[i]);
                    //}
                    //Picasso.with(getActivity()).load(festivalLists.get(position).getImg()[1]).into(img);
                    if(testRes.getCode()==200) {
                        Toast.makeText(MainActivity.this, "UID : "+testRes.getUid(), Toast.LENGTH_SHORT).show();

                        if(auto_login.isChecked()){
                            SharedPreferences.Editor editor = MainActivity.pref.edit();
                            editor.putInt("uid", testRes.getUid());
                            editor.putString("email", mail);
                            editor.putString("pw", pw);
                            editor.commit();
                        }

                        Intent intent = new Intent(MainActivity.this, ItemListActivity.class);
                        startActivity(intent);
                        //finish();
                    }
                    return;
                }
                int code = response.code();
                Log.d("TEST", "err code : " + code);
            }

            @Override
            public void onFailure(Call<LoginRes> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to Access", Toast.LENGTH_LONG).show();
                Log.i("TEST","err : "+ t.getMessage());
            }
        });
    }

    private void getPreferences(){
        pref = getSharedPreferences(PREF, MODE_PRIVATE);
    }

}
