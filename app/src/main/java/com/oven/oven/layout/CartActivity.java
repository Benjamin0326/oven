package com.oven.oven.layout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.oven.oven.R;
import com.oven.oven.adapter.CartListAdapter;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CartListAdapter adapter;
    private String[] name = {"바나나", "커피", "빵", "요거트", "음료수", "물", "껌"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_cart_list);
        adapter = new CartListAdapter(this, name);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }
}
