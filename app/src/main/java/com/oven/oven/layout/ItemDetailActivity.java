package com.oven.oven.layout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.oven.oven.R;
import com.oven.oven.adapter.ItemListAdapter;
import com.oven.oven.component.network;
import com.oven.oven.model.ProductDetail;
import com.oven.oven.model.ProductDetailList;
import com.oven.oven.model.ProductResList;
import com.oven.oven.service.ProductService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDetailActivity extends AppCompatActivity {

    private Button btn_select_date, btn_cart;
    private CalendarView calendar;
    private ProductDetailList productDetail;
    private ImageView img_item_detail;
    private TextView tv_item_name, tv_item_des;
    private int pid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        img_item_detail = (ImageView) findViewById(R.id.img_detail);

        tv_item_name = (TextView) findViewById(R.id.text_item_name_detail);
        tv_item_des = (TextView) findViewById(R.id.text_item_description_detail);

        pid = getIntent().getExtras().getInt("pid");

        postProductDetail(pid);

        btn_select_date = (Button) findViewById(R.id.btn_select_date);
        btn_cart = (Button) findViewById(R.id.btn_cart);
        calendar = (CalendarView) findViewById(R.id.calendar_item_detail);
        calendar.setVisibility(View.INVISIBLE);

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
                Intent intent = new Intent(ItemDetailActivity.this, CartActivity.class);
                startActivity(intent);
            }
        };

        btn_select_date.setOnClickListener(date_listener);
        btn_cart.setOnClickListener(cart_listener);

    }

    public void postProductDetail(int id){

        ProductService service = network.buildRetrofit().create(ProductService.class);
        Call<ProductDetailList> call = service.postProductDetail(id);
        call.enqueue(new Callback<ProductDetailList>() {
            @Override
            public void onResponse(Call<ProductDetailList> call, final Response<ProductDetailList> response) {
                if(response.isSuccessful()) {
                    if(response.body()!=null){
                        productDetail = response.body();
                        if(productDetail.getCode()==200){
                            Picasso.with(ItemDetailActivity.this).load(productDetail.getProduct_list().get(0).getImage()).resize(500,500).centerCrop().into(img_item_detail);
                            tv_item_name.setText(productDetail.getProduct_list().get(0).getPname());
                            String testText = "";
                            testText+=("가격 : "+productDetail.getProduct_list().get(0).getPrice());
                            testText+=("\n최소주문 수량 : "+productDetail.getProduct_list().get(0).getMin());
                            testText+=("\n배송예정일 : "+productDetail.getProduct_list().get(0).getArrival_date());
                            testText+=("\n유통기한 : "+productDetail.getProduct_list().get(0).getExpire_date());
                            testText+=("\n보관방법 : "+productDetail.getProduct_list().get(0).getStorage());
                            testText+=("\n주의사항 : "+productDetail.getProduct_list().get(0).getNotice());
                            testText+=("\n상세설명 : "+productDetail.getProduct_list().get(0).getDescription());
                            tv_item_des.setText(testText);
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

}
