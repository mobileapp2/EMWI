package com.imuons.shopntrips.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.imuons.shopntrips.R;
import com.imuons.shopntrips.fragments.RoyalityIncomeReportFragment;
import com.imuons.shopntrips.model.RoyalityIncomeReportRecordModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RoyalityIncomeReportAdapter extends RecyclerView.Adapter<RoyalityIncomeReportAdapter.AwardReportAdapterHolder> {
    private List<RoyalityIncomeReportRecordModel> airList = new ArrayList<>();
    private Context context;
    private FragmentManager fragmentManager;
    private RoyalityIncomeReportFragment awardIncomeReportFragment;
    private String wdatefromurl, cdatefromurl;
    private Date datec, datew;
    private static int currentPosition = 0;
    RoyalityIncomeReportRecordModel royalityIncomeReportRecordModel;

    public RoyalityIncomeReportAdapter(Context context, List<RoyalityIncomeReportRecordModel> airList) {
        this.airList = airList;

        this.context = context;
        Log.e("list: ", airList.size() + "");
    }

    @Override
    public RoyalityIncomeReportAdapter.AwardReportAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_royality_income_report, parent, false);
        return new RoyalityIncomeReportAdapter.AwardReportAdapterHolder(v);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(final RoyalityIncomeReportAdapter.AwardReportAdapterHolder holder, final int position) {

        royalityIncomeReportRecordModel = airList.get(position);

        holder.hiddenlayout.setVisibility(View.GONE);
        wdatefromurl = royalityIncomeReportRecordModel.getEntryTime();
        if(wdatefromurl != null) {
            SimpleDateFormat simpleDateFormatw = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            try {
                datew = simpleDateFormatw.parse(wdatefromurl);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            DateFormat dateFormatw = new SimpleDateFormat("yyyy/MM/dd");
            String wcdate = dateFormatw.format(datew);
        holder.date.setText(wcdate);
        }else{
            holder.date.setText("-");
        }

//        cdatefromurl = withdrawHistoryRecordModel.getCompleteDate();
//        if(cdatefromurl != null) {
//        SimpleDateFormat simpleDateFormatc = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        try {
//        datew = simpleDateFormatc.parse(wdatefromurl);
//        } catch (ParseException e) {
//        e.printStackTrace();
//        }
//        DateFormat dateFormatc = new SimpleDateFormat("yyyy/MM/dd");
//        String ccdate = dateFormatc.format(datew);
//        holder.compledate.setText(ccdate);
//        }else {
//        holder.compledate.setText("-");
//        }


//        if (position % 2 == 0) {
//        holder.llmain.setBackgroundColor(Color.parseColor("#f5f4f4"));
//        } else {
//        holder.llmain.setBackgroundColor(Color.parseColor("#c9caca"));
//
//        }


        holder.srno.setText(String.valueOf(position + 1));

        holder.rank.setText(royalityIncomeReportRecordModel.getRank());


        holder.quser.setText(String.valueOf(royalityIncomeReportRecordModel.getQualifiedUsers()));

        holder.percentage.setText(String.valueOf(royalityIncomeReportRecordModel.getPercentage())+"%");
        int amt = royalityIncomeReportRecordModel.getAmount();
        holder.amount.setText(String.valueOf(amt));
        int onamt = royalityIncomeReportRecordModel.getOnAmount();
        holder.onamount.setText(String.valueOf(onamt));




        //if the position is equals to the item position which is to be expanded
        if (currentPosition == position) {
            //  holder.eye.setImageResource(R.drawable.closeeye);
            //creating an animation
            Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down);

            //toggling visibility
            holder.hiddenlayout.setVisibility(View.VISIBLE);

            //adding sliding effect
            holder.hiddenlayout.startAnimation(slideDown);
        } else {
            // holder.eye.setImageResource(R.drawable.oprneye);
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
        return airList.size();
    }

    public class AwardReportAdapterHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.srno)
        TextView srno;

        @BindView(R.id.onamount)
        TextView onamount;
        @BindView(R.id.percentage)
        TextView percentage;
        @BindView(R.id.quser)
        TextView quser;
        @BindView(R.id.rank)
        TextView rank;
        @BindView(R.id.amount)
        TextView amount;

        @BindView(R.id.date)
        TextView date;


        @BindView(R.id.llmain)
        LinearLayout llmain;
        @BindView(R.id.hiddenlayout)
        LinearLayout hiddenlayout;
        View container;


        public AwardReportAdapterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            container = itemView;
        }
    }

}

