package com.oven.oven.layout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.oven.oven.MainActivity;
import com.oven.oven.R;
import com.oven.oven.adapter.ItemListAdapter;
import com.oven.oven.component.network;
import com.oven.oven.model.ProductRes;
import com.oven.oven.model.ProductResList;
import com.oven.oven.service.ProductService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VIewProductActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemListAdapter adapter;
    private List<ProductRes> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_item_view_product);
        adapter = new ItemListAdapter(this, productList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        getProductList();
    }

    public void getProductList(){
        ProductService service = network.buildRetrofit().create(ProductService.class);
        Call<ProductResList> call = service.postProductVIewList(MainActivity.UID);
        call.enqueue(new Callback<ProductResList>() {
            @Override
            public void onResponse(Call<ProductResList> call, final Response<ProductResList> response) {
                if(response.isSuccessful()) {
                    if(response.body()!=null){
                        productList = response.body().getProduct_list();
                        adapter = new ItemListAdapter(VIewProductActivity.this, productList);
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

}
