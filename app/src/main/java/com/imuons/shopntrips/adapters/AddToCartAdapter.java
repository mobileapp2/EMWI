package com.imuons.shopntrips.adapters;

import android.content.Context;
import android.graphics.Color;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.imuons.shopntrips.R;
import com.imuons.shopntrips.fragments.AddCartFragment;
import com.imuons.shopntrips.model.UserCartDataModel;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddToCartAdapter extends RecyclerView.Adapter<AddToCartAdapter.AddToCartAdapterHolder> {
    AddCartFragment addCartFragment;
    private List<UserCartDataModel> acList = new ArrayList<>();
    private Context context;
    String datefromurl;
    private static final int SELECT_PICTURE = 100;
    private FragmentManager fragmentManager;
    private static int currentPosition = 0;
    public AddToCartAdapter(AddCartFragment addCartFragment   , List<UserCartDataModel> acList) {
        this.acList = acList;
        this.addCartFragment = addCartFragment;
        Log.e("list: ", acList.size() + "");
    }

    @Override       //adapter_add_to_cart
    public AddToCartAdapter.AddToCartAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_add_to_cart, parent, false);
        return new AddToCartAdapter.AddToCartAdapterHolder(v);
    }

    @Override
    public void onBindViewHolder(final AddToCartAdapter.AddToCartAdapterHolder holder, final int position) {

        final UserCartDataModel userCartDataModel = acList.get(position);

        holder.hiddenlayout.setVisibility(View.GONE);

        if (position % 2 == 0) {
            holder.llmain.setBackgroundColor(Color.parseColor("#f5f4f4"));
        } else {
            holder.llmain.setBackgroundColor(Color.parseColor("#c9caca"));

        }

        holder.srno.setText(String.valueOf(position + 1));
        holder.productname.setText(userCartDataModel.getProductName());
        holder.price.setText(userCartDataModel.getProductPrice());
        int intqty = userCartDataModel.getRequestQuantity();
        holder.qty.setText(String.valueOf(intqty));
        holder.total.setText(userCartDataModel.getTotalPrice());
        holder.action.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {

                   String strorderid = String.valueOf(userCartDataModel.getSrno());
            addCartFragment.removeitem(strorderid);
               }
           });

        //if the position is equals to the item position which is to be expanded
        if (currentPosition == position) {
           // holder.eye.setImageResource(R.drawable.oprneye);
            //creating an animation
            Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down);

            //toggling visibility
            holder.hiddenlayout.setVisibility(View.VISIBLE);

            //adding sliding effect
            holder.hiddenlayout.startAnimation(slideDown);
        } else {
            //holder.eye.setImageResource(R.drawable.closeeye);
        }

        holder.llmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //getting the position of the item to expand it
                currentPosition = position;
                //reloding the list
                notifyDataSetChanged();
            }
        });





    }

    @Override
    public int getItemCount() {
        return acList.size();
    }

    public class AddToCartAdapterHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.srno)
        TextView srno;
        @BindView(R.id.productname)
        TextView productname;
        @BindView(R.id.price)
        TextView price;


        @BindView(R.id.qty)
        TextView  qty;
        @BindView(R.id.total)
        TextView total;
        @BindView(R.id.action)
        ImageView action;

        @BindView(R.id.eye)
        ImageView eye;


        @BindView(R.id.llmain)
        LinearLayout llmain;
        @BindView(R.id.hiddenlayout)
        LinearLayout hiddenlayout;
        View container;


        public AddToCartAdapterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            container = itemView;
        }
    }
}
