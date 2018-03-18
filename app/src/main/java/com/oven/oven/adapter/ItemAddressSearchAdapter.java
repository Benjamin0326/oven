package com.oven.oven.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.oven.oven.R;
import com.oven.oven.layout.ItemDetailActivity;
import com.oven.oven.model.AddressDocument;
import com.oven.oven.model.ProductRes;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sung9 on 2018-01-29.
 */

public class ItemAddressSearchAdapter extends RecyclerView.Adapter<ItemAddressSearchAdapter.ViewHolder>{

    private List<AddressDocument> addressList;
    private Context context;

    public ItemAddressSearchAdapter(Context _context, List<AddressDocument> _addressList){
        context = _context;
        addressList = _addressList;
    }

    @Override
    public ItemAddressSearchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_address_search, parent, false);
        ItemAddressSearchAdapter.ViewHolder holder = new ItemAddressSearchAdapter.ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ItemAddressSearchAdapter.ViewHolder holder, int position) {
        final int pos = position;

        holder.tv_addr.setText(addressList.get(pos).getAddress_name());
        holder.tv_addr.setTag(pos);
    }

    @Override
    public int getItemCount() {
        if(addressList==null)
            return 0;
        return addressList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_addr;
        public ViewHolder(View view){
            super(view);
            tv_addr = (TextView) view.findViewById(R.id.text_address_search_item);
        }
    }
}
