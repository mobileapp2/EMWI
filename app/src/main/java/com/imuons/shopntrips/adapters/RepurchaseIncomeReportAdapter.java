package com.imuons.shopntrips.adapters;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.imuons.shopntrips.R;
import com.imuons.shopntrips.fragments.BinaryIncomeFragment;
import com.imuons.shopntrips.model.RepurchaseIncomeReportRecordModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepurchaseIncomeReportAdapter extends  RecyclerView.Adapter<RepurchaseIncomeReportAdapter.BinaryRoiIncomeReportAdapterHolder> {
    private List<RepurchaseIncomeReportRecordModel> brorList = new ArrayList<>();
    private Context context;
    private BinaryIncomeFragment roiIncomeFragment;
    private String wdatefromurl,cdatefromurl;
    private Date datec,datew;
    private static int currentPosition = 0;

    public RepurchaseIncomeReportAdapter(Context context, List<RepurchaseIncomeReportRecordModel> brorList) {
        this.brorList = brorList;

        this.context = context;
        Log.e("list: ", brorList.size() + "");
    }

    @Override
    public RepurchaseIncomeReportAdapter.BinaryRoiIncomeReportAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_repurchase_income_report_adapter, parent, false);
        return new RepurchaseIncomeReportAdapter.BinaryRoiIncomeReportAdapterHolder(v);
    }

    @Override
    public void onBindViewHolder(final RepurchaseIncomeReportAdapter.BinaryRoiIncomeReportAdapterHolder holder, final int position) {

        final RepurchaseIncomeReportRecordModel repurchaseIncomeReportRecordModel = brorList.get(position);

        holder.hiddenlayout.setVisibility(View.GONE);
        wdatefromurl = repurchaseIncomeReportRecordModel.getEntryTime();
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

        holder.leftbv.setText(String.valueOf(repurchaseIncomeReportRecordModel.getLeftBv()));


        holder.rightbv.setText(String.valueOf(repurchaseIncomeReportRecordModel.getRightBv()));

        holder.matchbv.setText(String.valueOf(repurchaseIncomeReportRecordModel.getMatchBv()));

        holder.carryleftbv.setText(String.valueOf(repurchaseIncomeReportRecordModel.getLeftBvCarry()));
        holder.carryrightbv.setText(String.valueOf(repurchaseIncomeReportRecordModel.getRightBvCarry()));

        holder.amount.setText(repurchaseIncomeReportRecordModel.getAmount());






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
        return brorList.size();
    }

    public class BinaryRoiIncomeReportAdapterHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.srno)
        TextView srno;

        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.leftbv)
        TextView leftbv;
        @BindView(R.id.amount)
        TextView amount;
        @BindView(R.id.rightbv)
        TextView rightbv;

        @BindView(R.id.matchbv)
        TextView matchbv;
        @BindView(R.id.carryleftbv)
        TextView carryleftbv;
        @BindView(R.id.carryrightbv)
        TextView carryrightbv;








        @BindView(R.id.llmain)
        LinearLayout llmain;
        @BindView(R.id.hiddenlayout)
        LinearLayout hiddenlayout;
        View container;


        public BinaryRoiIncomeReportAdapterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            container = itemView;
        }
    }

}
