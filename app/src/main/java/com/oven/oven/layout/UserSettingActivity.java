package com.oven.oven.layout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.oven.oven.R;

import org.w3c.dom.Text;

public class UserSettingActivity extends AppCompatActivity {

    private Button btn_user, btn_getClause, btn_getPersonalClause;

    private String userName, userMail;
    private TextView tv_uname, tv_email;

    public static final int MODIFYREQUESTCODE = 90;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);

        btn_user = (Button)findViewById(R.id.btn_setting_userInfo);
        btn_getClause = (Button)findViewById(R.id.btn_setting_getClause);
        btn_getPersonalClause = (Button)findViewById(R.id.btn_setting_getPersonalClause);

        tv_uname = (TextView)findViewById(R.id.tv_userId_setting);
        tv_email = (TextView)findViewById(R.id.tv_userMail_setting);

        userName = getIntent().getStringExtra("userId");
        userMail = getIntent().getStringExtra("userMail");

        tv_uname.setText(userName);
        tv_email.setText(userMail);

        btn_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserSettingActivity.this, ModifyUserInfoActivity.class);
                startActivityForResult(intent, MODIFYREQUESTCODE);
            }
        });

        btn_getClause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserSettingActivity.this, GetClauseActivity.class);
                startActivity(intent);
            }
        });

        btn_getPersonalClause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserSettingActivity.this, GetPersonalClauseActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case MODIFYREQUESTCODE:
                if (resultCode == RESULT_OK) {
                    userName = getIntent().getStringExtra("userId");
                    userMail = getIntent().getStringExtra("userMail");

                    tv_uname.setText(userName);
                    tv_email.setText(userMail);

                    //Log.d("testResult : ", "Okay!!");
                }
                break;
        }
    }
}
