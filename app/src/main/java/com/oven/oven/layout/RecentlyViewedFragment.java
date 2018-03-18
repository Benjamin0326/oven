package com.oven.oven.layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class RecentlyViewedFragment extends Fragment {

    private RecyclerView recyclerView;
    private ItemListAdapter adapter;
    private List<ProductRes> productList;

    public RecentlyViewedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_recently_viewed, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_item_view_product);
        adapter = new ItemListAdapter(getContext(), productList);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        getProductList();

        return rootView;
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
                        adapter = new ItemListAdapter(getContext(), productList);
                        recyclerView.setAdapter(adapter);
                    }
                    return;
                }
                Toast.makeText(getContext(), "err " + response.code() + " : " + response.message(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<ProductResList> call, Throwable t) {
                Log.d("TEST", "err msg : " + t.getMessage().toString());
            }
        });
    }
}
