package com.oven.oven.layout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import com.oven.oven.R;

public class ItemDetailActivity extends AppCompatActivity {

    private Button btn_select_date, btn_cart;
    private CalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        btn_select_date = (Button) findViewById(R.id.btn_select_date);
        btn_cart = (Button) findViewById(R.id.btn_cart);
        calendar = (CalendarView) findViewById(R.id.calendar_item_detail);

        Button.OnClickListener date_listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar.setVisibility(View.VISIBLE);
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
}
