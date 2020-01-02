package com.imuons.shopntrips.adapters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
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
import com.imuons.shopntrips.model.AwardReportRecordModel;
import com.imuons.shopntrips.model.UsedEpinReportRecordModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UsedEpinReportAdapter extends RecyclerView.Adapter<UsedEpinReportAdapter.UsedEpinReportAdapterHolder> {
    private List<UsedEpinReportRecordModel> ueList = new ArrayList<>();
    private Context context;
    private BinaryIncomeFragment roiIncomeFragment;
    private String wdatefromurl,cdatefromurl;
    private Date datec,datew;
    private static int currentPosition = 0;

    public UsedEpinReportAdapter(Context context, List<UsedEpinReportRecordModel> ueList) {
        this.ueList = ueList;

        this.context = context;
        Log.e("list: ", ueList.size() + "");
    }

    @Override
    public UsedEpinReportAdapter.UsedEpinReportAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_used_epin_report, parent, false);
        return new UsedEpinReportAdapter.UsedEpinReportAdapterHolder(v);
    }

    @Override
    public void onBindViewHolder(final UsedEpinReportAdapter.UsedEpinReportAdapterHolder holder, final int position) {

        final UsedEpinReportRecordModel usedEpinReportRecordModel = ueList.get(position);

        holder.hiddenlayout.setVisibility(View.GONE);
        wdatefromurl = usedEpinReportRecordModel.getUsedDate();
        if(wdatefromurl != null) {
            SimpleDateFormat simpleDateFormatw = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
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

        holder.epin.setText(usedEpinReportRecordModel.getPin());
        holder.status.setText(String.valueOf(usedEpinReportRecordModel.getStatus()));

        holder.productname.setText(String.valueOf(usedEpinReportRecordModel.getProductName()));


        holder.usedby.setText(String.valueOf(usedEpinReportRecordModel.getUsedByUserId()));


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
        return ueList.size();
    }

    public class UsedEpinReportAdapterHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.srno)
        TextView srno;

        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.usedby)
        TextView usedby;
        @BindView(R.id.status)
        TextView status;
        @BindView(R.id.productname)
        TextView productname;
        @BindView(R.id.epin)
        TextView epin;



        @BindView(R.id.llmain)
        LinearLayout llmain;
        @BindView(R.id.hiddenlayout)
        LinearLayout hiddenlayout;
        View container;


        public UsedEpinReportAdapterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            container = itemView;
        }
    }

}
