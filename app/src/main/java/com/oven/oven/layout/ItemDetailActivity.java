package com.oven.oven.layout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.oven.oven.MainActivity;
import com.oven.oven.R;
import com.oven.oven.adapter.ItemListAdapter;
import com.oven.oven.component.network;
import com.oven.oven.model.DefaultModel;
import com.oven.oven.model.ProductDetail;
import com.oven.oven.model.ProductDetailList;
import com.oven.oven.model.ProductFavorite;
import com.oven.oven.model.ProductResList;
import com.oven.oven.service.ProductService;
import com.squareup.picasso.Picasso;
import com.squareup.timessquare.CalendarPickerView;

import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDetailActivity extends AppCompatActivity {

    private Button btn_select_date, btn_cart;
    private ImageButton btn_like;
    private CalendarPickerView calendar;
    private ProductDetailList productDetail;
    private ImageView img_item_detail;
    private TextView tv_item_name, tv_item_des;
    private int pid;
    private EditText edit_num_product;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        img_item_detail = (ImageView) findViewById(R.id.img_detail);

        tv_item_name = (TextView) findViewById(R.id.text_item_name_detail);
        tv_item_des = (TextView) findViewById(R.id.text_item_description_detail);



        pid = getIntent().getExtras().getInt("pid");

        postProductDetail(pid);

        edit_num_product = (EditText)findViewById(R.id.edit_item_num_detail);

        btn_select_date = (Button) findViewById(R.id.btn_select_date);
        btn_cart = (Button) findViewById(R.id.btn_cart);
        btn_like = (ImageButton) findViewById(R.id.btn_like_detail);
        calendar = (CalendarPickerView) findViewById(R.id.calendar_item_detail);
        calendar.setVisibility(View.INVISIBLE);

        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);

        Date today = new Date();
        calendar.init(today, nextYear.getTime()).withSelectedDate(today);

        calendar.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                Toast.makeText(ItemDetailActivity.this, ""+date.toString(), Toast.LENGTH_LONG).show();
                calendar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onDateUnselected(Date date) {

            }
        });

        ImageButton.OnClickListener like_listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postProductFavorite(MainActivity.UID, pid);
            }
        };

        Button.OnClickListener date_listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(calendar.getVisibility()==View.VISIBLE){
                    calendar.setVisibility(View.INVISIBLE);
                }
                else if(calendar.getVisibility()==View.INVISIBLE){
                    calendar.setVisibility(View.VISIBLE);
                }
            }
        };

        Button.OnClickListener cart_listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edit_num_product.getText().toString().length()==0) {
                    Toast.makeText(ItemDetailActivity.this, "수량을 입력해 주세요.", Toast.LENGTH_LONG).show();
                    return;
                }
                int num = Integer.parseInt(edit_num_product.getText().toString());
                if(num<productDetail.getMin()){
                    Toast.makeText(ItemDetailActivity.this, "최소 수량보다 적습니다.", Toast.LENGTH_LONG).show();
                    return;
                }

                postAddCart(pid, num);
            }
        };

        btn_select_date.setOnClickListener(date_listener);
        btn_cart.setOnClickListener(cart_listener);
        btn_like.setOnClickListener(like_listener);

    }

    public void postProductDetail(int id){

        ProductService service = network.buildRetrofit().create(ProductService.class);
        Call<ProductDetailList> call = service.postProductDetail(id, MainActivity.UID);
        call.enqueue(new Callback<ProductDetailList>() {
            @Override
            public void onResponse(Call<ProductDetailList> call, final Response<ProductDetailList> response) {
                if(response.isSuccessful()) {
                    if(response.body()!=null){
                        productDetail = response.body();
                        if(productDetail.getCode()==200){
                            Picasso.with(ItemDetailActivity.this).load(productDetail.getImage()).resize(500,500).centerInside().into(img_item_detail);
                            tv_item_name.setText(productDetail.getPname());
                            String testText = "";
                            testText+=("가격 : "+productDetail.getPrice());
                            testText+=("\n최소주문 수량 : "+productDetail.getMin());
                            testText+=("\n배송예정일 : "+productDetail.getArrival_date());
                            //testText+=("\n유통기한 : "+productDetail.getProduct_detail().get(0).getExpire_date());
                            //testText+=("\n보관방법 : "+productDetail.getProduct_detail().get(0).getStorage());
                            testText+=("\n주의사항 : "+productDetail.getNotice());
                            testText+=("\n상세설명 : "+productDetail.getDescription());
                            tv_item_des.setText(testText);
                            if(productDetail.getToggle()==1)
                                btn_like.setImageResource(R.mipmap.icon_item_action_like_selected);
                            else if(productDetail.getToggle()==0)
                                btn_like.setImageResource(R.mipmap.icon_item_action_like_deselected);
                        }
                    }
                    return;
                }

                Toast.makeText(getApplicationContext(), "err " + response.code() + " : " + response.message(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<ProductDetailList> call, Throwable t) {
                Log.d("TEST", "err msg : " + t.getMessage().toString());
            }
        });
    }

    public void postAddCart(int pid, int cnt){
        Log.d("NUM : ", String.valueOf(cnt));
        ProductService service = network.buildRetrofit().create(ProductService.class);
        Call<DefaultModel> call = service.postAddCart(pid, MainActivity.UID, cnt);
        call.enqueue(new Callback<DefaultModel>() {
            @Override
            public void onResponse(Call<DefaultModel> call, final Response<DefaultModel> response) {
                if(response.isSuccessful()) {
                    if(response.body()!=null){
                        DefaultModel res = response.body();
                        Log.d("cart res : ", String.valueOf(res.getCode()) + " " + res.getMsg());

                        if(res.getCode()==200){
                            Toast.makeText(ItemDetailActivity.this, "장바구니에 추가되었습니다.", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(ItemDetailActivity.this, "장바구니 추가에 실패했습니다.", Toast.LENGTH_LONG).show();
                        }
                    }
                    return;
                }

                Toast.makeText(getApplicationContext(), "err " + response.code() + " : " + response.message(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<DefaultModel> call, Throwable t) {
                Log.d("TEST", "err msg : " + t.getMessage().toString());
            }
        });
    }

    public void postProductFavorite(int uid, int pid){

        ProductService service = network.buildRetrofit().create(ProductService.class);
        Call<ProductFavorite> call = service.postProductFavorite(uid, pid);
        call.enqueue(new Callback<ProductFavorite>() {
            @Override
            public void onResponse(Call<ProductFavorite> call, final Response<ProductFavorite> response) {
                if(response.isSuccessful()) {
                    if(response.body()!=null){
                        if(response.body().getCode()==200){
                            if(response.body().getToggle()==1){
                                btn_like.setImageResource(R.mipmap.icon_item_action_like_selected);
                                Toast.makeText(ItemDetailActivity.this, "좋아요가 반영되었습니다.", Toast.LENGTH_LONG).show();
                            }
                            else if(response.body().getToggle()==0){
                                btn_like.setImageResource(R.mipmap.icon_item_action_like_deselected);
                                Toast.makeText(ItemDetailActivity.this, "좋아요가 취소되었습니다.", Toast.LENGTH_LONG).show();
                            }

                        }
                    }
                    return;
                }

                Toast.makeText(getApplicationContext(), "err " + response.code() + " : " + response.message(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<ProductFavorite> call, Throwable t) {
                Log.d("TEST", "err msg : " + t.getMessage().toString());
            }
        });
    }


}
