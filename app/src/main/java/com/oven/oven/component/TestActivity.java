package com.oven.oven.component;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.oven.oven.MainActivity;
import com.oven.oven.R;
import com.oven.oven.adapter.ItemListAdapter;
import com.oven.oven.layout.ItemListMainActivity;
import com.oven.oven.layout.LikeListActivity;
import com.oven.oven.model.ProductRes;
import com.oven.oven.model.ProductResList;
import com.oven.oven.model.SideUserInfo;
import com.oven.oven.service.LoginService;
import com.oven.oven.service.ProductService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private LinearLayout goLike;
    private RecyclerView recyclerView;
    private ItemListAdapter adapter;
    private List<ProductRes> productList;
    private TextView tv_uname, tv_email, tv_delivery, tv_favorite, tv_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        goLike = (LinearLayout)navigationView.getHeaderView(0).findViewById(R.id.btn_like_drawer);
        tv_delivery = (TextView)navigationView.getHeaderView(0).findViewById(R.id.tv_num_today_delivery_main);
        tv_email = (TextView)navigationView.getHeaderView(0).findViewById(R.id.tv_userMail_main);
        tv_favorite = (TextView)navigationView.getHeaderView(0).findViewById(R.id.tv_num_zzim_main);
        tv_uname = (TextView)navigationView.getHeaderView(0).findViewById(R.id.tv_userId_main);
        tv_view = (TextView)navigationView.getHeaderView(0).findViewById(R.id.tv_num_clicked_main);

        //goLike = (LinearLayout) findViewById(R.id.btn_like_drawer);
        goLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(TestActivity.this, LikeListActivity.class);
                    startActivity(intent);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler_item_list);
        adapter = new ItemListAdapter(this, productList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        getProductList();
        postSidemenu();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void onClickLogout(){
        UserManagement.requestLogout(new LogoutResponseCallback() {
            @Override
            public void onCompleteLogout() {
                SharedPreferences.Editor editor = MainActivity.pref.edit();
                editor.putInt("autoLogin", 0);
                editor.commit();

                Intent intent = new Intent(TestActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            SharedPreferences.Editor editor = MainActivity.pref.edit();
            editor.putInt("autoLogin", 0);
            editor.commit();

            Intent intent = new Intent(TestActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_gallery) {
            onClickLogout();
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void getProductList(){
        ProductService service = network.buildRetrofit().create(ProductService.class);
        Call<ProductResList> call = service.getProductList(MainActivity.UID);
        call.enqueue(new Callback<ProductResList>() {
            @Override
            public void onResponse(Call<ProductResList> call, final Response<ProductResList> response) {
                if(response.isSuccessful()) {
                    if(response.body()!=null){
                        productList = response.body().getProduct_list();
                        adapter = new ItemListAdapter(TestActivity.this, productList);
                        recyclerView.setAdapter(adapter);
                    }
                    return;
                }
                Toast.makeText(getApplicationContext(), "err " + response.code() + " : " + response.message(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<ProductResList> call, Throwable t) {
                Log.d("TEST", "err msg : " + t.getMessage().toString());
            }
        });
    }

    public void postSidemenu(){
        LoginService service = network.buildRetrofit().create(LoginService.class);
        Call<SideUserInfo> call = service.postSidemenu(MainActivity.UID);
        call.enqueue(new Callback<SideUserInfo>() {
            @Override
            public void onResponse(Call<SideUserInfo> call, final Response<SideUserInfo> response) {
                if(response.isSuccessful()) {
                    if(response.body()!=null){
                        if(response.body().getCode()==200){
                            String userName = response.body().getUname();
                            String email = response.body().getEmail();
                            String delivery_cnt = String.valueOf(response.body().getDelivery_cnt());
                            String favorite_cnt = String.valueOf(response.body().getFavorite_cnt());
                            String view_cnt = String.valueOf(response.body().getView_cnt());
                            if(userName!=null)
                                Log.d("uname", userName);
                            if(email!=null)
                                Log.d("email", email);
                            Log.d("d, f, v : ", delivery_cnt+", "+favorite_cnt+", "+view_cnt);
                            tv_delivery.setText(delivery_cnt);
                            tv_view.setText(view_cnt);
                            tv_uname.setText(userName);
                            tv_favorite.setText(favorite_cnt);
                            tv_email.setText(email);
                            return;
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "유저 정보를 불러오는데 실패했습니다. " + response.code() + " : " + response.message(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }
                Toast.makeText(getApplicationContext(), "err " + response.code() + " : " + response.message(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<SideUserInfo> call, Throwable t) {
                Log.d("TEST", "err msg : " + t.getMessage().toString());
            }
        });
    }
}

