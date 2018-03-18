package com.oven.oven.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.oven.oven.R;
import com.oven.oven.model.CartProduct;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sung9 on 2017-07-08.
 */

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder>{

    private Context context;
    private List<CartProduct> cartProducts;

    public CartListAdapter(Context _context, List<CartProduct> _cartProducts){
        context = _context;
        cartProducts = _cartProducts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_list, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final int pos = position;
        /*
        ImageView.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int data = list_img_festival[pos];
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Fragment fr = new FestivalInfoFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, fr).commit();

            }
        };
        */

        holder.tv_name.setText(cartProducts.get(pos).getPname());
        holder.tv_min.setText("최소주문수량 "+String.valueOf(cartProducts.get(pos).getMin())+" 개");

        holder.tv_max.setText("최대주문수량 " + String.valueOf(cartProducts.get(pos).getMax())+" 개");

        holder.tv_price.setText(String.valueOf(cartProducts.get(pos).getTotal_price())+" 원");

        holder.tv_num.setText(String.valueOf(cartProducts.get(pos).getCpcnt())+" 개");

        holder.tv_date.setText(cartProducts.get(pos).getArrival_date());
        Picasso.with(context).load(cartProducts.get(pos).getImage()).resize(250,250).centerInside().into(holder.img_item);
        //holder.img_festival.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        if(cartProducts==null)
            return 0;
        return cartProducts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView img_item;
        private TextView tv_name, tv_num, tv_price, tv_min, tv_max, tv_date;
        public ViewHolder(View view){
            super(view);
            img_item = (ImageView) view.findViewById(R.id.img_cart_item);
            tv_date = (TextView) view.findViewById(R.id.tv_cart_date);
            tv_name = (TextView) view.findViewById(R.id.tv_cart_name);
            tv_num = (TextView) view.findViewById(R.id.tv_cart_num);
            tv_price = (TextView) view.findViewById(R.id.tv_cart_price);
            tv_min = (TextView) view.findViewById(R.id.tv_cart_min);
            tv_max = (TextView) view.findViewById(R.id.tv_cart_max);
        }
    }
}
