package com.oven.oven.layout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.kakao.auth.ErrorCode;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.helper.log.Logger;
import com.oven.oven.MainActivity;
import com.oven.oven.R;
import com.oven.oven.component.TestActivity;
import com.oven.oven.component.network;
import com.oven.oven.model.LoginRes;
import com.oven.oven.service.LoginService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KakaoSignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestMe();
    }


    protected void requestMe() { //유저의 정보를 받아오는 함수

        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                String message = "failed to get user info. msg=" + errorResult;
                Logger.d(message);

                ErrorCode result = ErrorCode.valueOf(errorResult.getErrorCode());
                if (result == ErrorCode.CLIENT_ERROR_CODE) {
                    finish();
                } else {
                    redirectLoginActivity();
                }
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Log.d("KakaoError", "SessionClosed");
                redirectLoginActivity();
            }

            @Override
            public void onNotSignedUp() {} // 카카오톡 회원이 아닐 시 showSignup(); 호출해야함

            @Override
            public void onSuccess(UserProfile userProfile) {  //성공 시 userProfile 형태로 반환
                Logger.d("UserProfile : " + userProfile);
                String kakaoID = userProfile.getEmail(); // userProfile에서 ID값을 가져옴
                String kakaoNickname = userProfile.getNickname();     // Nickname 값을 가져옴
                Log.d("UserInfo :", kakaoID+" / "+kakaoNickname);
                redirectMainActivity(kakaoID, kakaoNickname); // 로그인 성공시 MainActivity로
            }
        });
    }
/*
    private void requestMe() {
        List<String> propertyKeys = new ArrayList<String>();
        propertyKeys.add("kaccount_email");
        propertyKeys.add("nickname");
        propertyKeys.add("profile_image");
        propertyKeys.add("thumbnail_image");

        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                String message = "failed to get user info. msg=" + errorResult;
                Logger.d(message);

                redirectLoginActivity();
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                redirectLoginActivity();
            }

            @Override
            public void onSuccess(UserProfile userProfile) {
                Logger.d("UserProfile : " + userProfile);
                redirectMainActivity();
            }

            @Override
            public void onNotSignedUp() {
                //redirectSignupActivity(self);
            }
        }, propertyKeys, false);
    }
*/
    private void redirectMainActivity(String email, String nickname) {
        postKakaoLogin(email, nickname);
    }
    protected void redirectLoginActivity() {
        final Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }



    public void postKakaoLogin(final String mail, final String name){
        LoginService service = network.buildRetrofit().create(LoginService.class);
        Call<LoginRes> loginCall = service.postKakaoLogin(mail, name);

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
                            Toast.makeText(KakaoSignupActivity.this, "Login UID : "+testRes.getUid(), Toast.LENGTH_SHORT).show();
                            SharedPreferences.Editor editor = MainActivity.pref.edit();
                            editor.putInt("autoLogin", 0);
                            editor.putInt("uid", testRes.getUid());
                            editor.putString("email", mail);
                            editor.putString("name", name);
                            //editor.putString("pw", pw);
                            editor.commit();

/*
SharedPreferences.Editor editor = MainActivity.pref.edit();
            editor.putInt("uid", -1);
            editor.commit();
 */
                        Intent intent = new Intent(KakaoSignupActivity.this, TestActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        postKakaoJoin(mail, name);
                    }
                    return;
                }
                int code = response.code();
                Log.d("TEST", "err code : " + code);
            }

            @Override
            public void onFailure(Call<LoginRes> call, Throwable t) {
                Toast.makeText(KakaoSignupActivity.this, "Failed to Access", Toast.LENGTH_LONG).show();
                Log.i("TEST","err : "+ t.getMessage());
            }
        });
    }

    public void postKakaoJoin(final String mail, final String name){
        LoginService service = network.buildRetrofit().create(LoginService.class);
        Call<LoginRes> joinCall = service.postKakaoJoin(mail, name);

        joinCall.enqueue(new Callback<LoginRes>() {
            @Override
            public void onResponse(Call<LoginRes> call, Response<LoginRes> response) {
                if(response.isSuccessful()){
                    LoginRes testRes = response.body();

                    Log.d("Join Info : ", testRes.getCode()+"\n"+testRes.getMsg());
                    Toast.makeText(KakaoSignupActivity.this, "Join UID : "+testRes.getUid(), Toast.LENGTH_SHORT).show();
                    //for(int i=0;i<festivalLists.get(position).getVideo().length;i++){
                    //    Log.d("#Test :", festivalLists.get(position).getVideo()[i]);
                    //}
                    //Picasso.with(getActivity()).load(festivalLists.get(position).getImg()[1]).into(img);
                    SharedPreferences.Editor editor = MainActivity.pref.edit();
                    editor.putInt("autoLogin", 0);
                    editor.putInt("uid", testRes.getUid());
                    editor.putString("email", mail);
                    editor.putString("name", name);
                    //editor.putString("pw", pw);
                    editor.commit();

/*
SharedPreferences.Editor editor = MainActivity.pref.edit();
            editor.putInt("uid", -1);
            editor.commit();
 */
                    Intent intent = new Intent(KakaoSignupActivity.this, TestActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }
                int code = response.code();
                Log.d("TEST", "err code : " + code);
            }

            @Override
            public void onFailure(Call<LoginRes> call, Throwable t) {
                Toast.makeText(KakaoSignupActivity.this, "Failed to Access", Toast.LENGTH_LONG).show();
                Log.i("TEST","err : "+ t.getMessage());
            }
        });
    }

}
