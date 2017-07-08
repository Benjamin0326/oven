package com.oven.oven.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.oven.oven.R;

/**
 * Created by sung9 on 2017-07-08.
 */

public class cartListAdapter extends RecyclerView.Adapter<cartListAdapter.ViewHolder>{

    private String[] name;
    private Context context;

    public cartListAdapter(Context _context, String[] _name){
        context = _context;
        name = _name;
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
        holder.tv_name.setText(name[pos]);
        //holder.img_festival.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        if(name==null)
            return 0;
        return name.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        //private ImageView img_item;
        private TextView tv_name;
        public ViewHolder(View view){
            super(view);
            //img_item = (ImageView) view.findViewById(R.id.img_item);
            tv_name = (TextView) view.findViewById(R.id.tv_cart_name);
            //tv_num = (TextView) view.findViewById(R.id.tv_item_num);
        }
    }
}
