package com.imuons.shopntrips.adapters;

import android.app.FragmentManager;
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

import androidx.recyclerview.widget.RecyclerView;

import com.imuons.shopntrips.R;
import com.imuons.shopntrips.fragments.EPinProductDetailFragment;
import com.imuons.shopntrips.model.EPinProductDetailDatumModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EPinProductDetailAdapter extends RecyclerView.Adapter<EPinProductDetailAdapter.EPinProductDetailAdapterHolder> {
    EPinProductDetailFragment ePinProductDetailFragment;
    private List<EPinProductDetailDatumModel> epdList = new ArrayList<>();
    private Context context;

    private static int currentPosition = 0;
    private FragmentManager fragmentManager;
    public EPinProductDetailAdapter(EPinProductDetailFragment ePinProductDetailFragment, List<EPinProductDetailDatumModel> epdList) {
        this.epdList = epdList;
        this.ePinProductDetailFragment = ePinProductDetailFragment;
        Log.e("list: ", epdList.size() + "");
    }

    @Override
    public EPinProductDetailAdapter.EPinProductDetailAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(ePinProductDetailFragment.getContext()).inflate(R.layout.adapter_epin_product_detail, parent, false);
        return new EPinProductDetailAdapter.EPinProductDetailAdapterHolder(v);
    }

    @Override
    public void onBindViewHolder(final EPinProductDetailAdapter.EPinProductDetailAdapterHolder holder, final int position) {

        final EPinProductDetailDatumModel ePinProductDetailDataModel = epdList.get(position);

        holder.hiddenlayout.setVisibility(View.GONE);
//
//        if (position % 2 == 0) {
//            holder.llmain.setBackgroundColor(Color.parseColor("#f5f4f4"));
//        } else {
//            holder.llmain.setBackgroundColor(Color.parseColor("#c9caca"));
//
//        }
        holder.srno.setText(String.valueOf(position + 1));


        holder.productname.setText(ePinProductDetailDataModel.getProductName());

        holder.price.setText(ePinProductDetailDataModel.getProductPrice());
        int reqqty = ePinProductDetailDataModel.getRequestQuantity();
        holder.qty.setText(String.valueOf(reqqty));
        holder.total.setText(ePinProductDetailDataModel.getTotalPrice());
        //if the position is equals to the item position which is to be expanded
        if (currentPosition == position) {
          //  holder.eye.setImageResource(R.drawable.oprneye);
            //creating an animation
            Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down);

            //toggling visibility
            holder.hiddenlayout.setVisibility(View.VISIBLE);

            //adding sliding effect
            holder.hiddenlayout.startAnimation(slideDown);
        } else {
           // holder.eye.setImageResource(R.drawable.closeeye);
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
        return epdList.size();
    }

    public class EPinProductDetailAdapterHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.srno)
        TextView srno;
        @BindView(R.id.productname)
        TextView productname;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.qty)
        TextView qty;
        @BindView(R.id.total)
        TextView total;




        @BindView(R.id.llmain)
        LinearLayout llmain;
        @BindView(R.id.hiddenlayout)
        LinearLayout hiddenlayout;
        View container;


        public EPinProductDetailAdapterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            container = itemView;
        }
    }

}
