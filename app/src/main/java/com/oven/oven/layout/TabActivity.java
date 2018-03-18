package com.oven.oven.layout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.oven.oven.R;

public class TabActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private TextView userId, userMail;
    private String id, mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        userId = (TextView)findViewById(R.id.tv_userId_tab);
        userMail = (TextView)findViewById(R.id.tv_userMail_tab);

        id = getIntent().getStringExtra("userId");
        mail = getIntent().getStringExtra("userMail");

        userId.setText(id);
        userMail.setText(mail);

        int id = getIntent().getIntExtra("pageNumber", 0);

        Bundle bundle = new Bundle();
        bundle.putInt("pageNumber", id);

        fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = new UserProductTabFragment();
        fragment.setArguments(bundle);
        fragmentTransaction.add(R.id.container_fragment, fragment);
        fragmentTransaction.commit();
    }
}
