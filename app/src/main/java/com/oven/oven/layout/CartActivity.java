package com.oven.oven.layout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.oven.oven.MainActivity;
import com.oven.oven.R;
import com.oven.oven.adapter.CartListAdapter;
import com.oven.oven.adapter.ItemListAdapter;
import com.oven.oven.component.network;
import com.oven.oven.model.CartProduct;
import com.oven.oven.model.CartProducts;
import com.oven.oven.model.ProductResList;
import com.oven.oven.service.ProductService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CartListAdapter adapter;
    private List<CartProduct> cartProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_cart_list);
        adapter = new CartListAdapter(this, cartProducts);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        getProductList();
    }

    public void getProductList(){
        ProductService service = network.buildRetrofit().create(ProductService.class);
        Call<CartProducts> call = service.postCartProduct(MainActivity.UID);
        call.enqueue(new Callback<CartProducts>() {
            @Override
            public void onResponse(Call<CartProducts> call, final Response<CartProducts> response) {
                if(response.isSuccessful()) {
                    if(response.body()!=null){
                        cartProducts = response.body().getProduct_list();
                        adapter = new CartListAdapter(CartActivity.this, cartProducts);
                        recyclerView.setAdapter(adapter);
                    }
                    return;
                }
                Toast.makeText(getApplicationContext(), "err " + response.code() + " : " + response.message(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<CartProducts> call, Throwable t) {
                Log.d("TEST", "err msg : " + t.getMessage().toString());
            }
        });
    }
}
