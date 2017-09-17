package com.oven.oven.layout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.oven.oven.MainActivity;
import com.oven.oven.R;

//이용약관 액티비티

public class ClauseActivity extends AppCompatActivity {

    private CheckBox chk1, chk2;
    private Button btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clause);

        chk1 = (CheckBox) findViewById(R.id.cb_clause1);
        chk2 = (CheckBox) findViewById(R.id.cb_clause2);
        btn_next = (Button) findViewById(R.id.btn_clause_next);

        Button.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chk1.isChecked() && chk2.isChecked()){
                    Intent intent = new Intent(ClauseActivity.this, JoinActivity.class);
                    startActivity(intent);
                    return;
                }
                else if(!chk1.isChecked()){
                    Toast.makeText(ClauseActivity.this, "이용약관에 동의해주세요.", Toast.LENGTH_LONG).show();
                    return;
                }
                else if(!chk2.isChecked()){
                    Toast.makeText(ClauseActivity.this, "개인정보취급에 동의해주세요.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        };
        btn_next.setOnClickListener(listener);
    }
}
