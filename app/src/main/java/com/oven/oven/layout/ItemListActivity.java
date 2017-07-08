package com.oven.oven.layout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.oven.oven.R;
import com.oven.oven.adapter.itemListAdapter;

public class ItemListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private itemListAdapter adapter;
    private String[] name = {"바나나", "커피", "빵", "요거트", "음료수", "물", "껌"};
    private String[] num = {"500원 /개 ","500원 /개 ","500원 /개 ","500원 /개 ","500원 /개 ","500원 /개 ","500원 /개 "};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_item_list);
        adapter = new itemListAdapter(this, name, num);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }
}
