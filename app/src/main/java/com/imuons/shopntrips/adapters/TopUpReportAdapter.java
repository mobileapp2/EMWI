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
import com.imuons.shopntrips.fragments.TopupReportFragment;
import com.imuons.shopntrips.model.TopUpReportRecordModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopUpReportAdapter extends RecyclerView.Adapter<TopUpReportAdapter.TopUpReportAdapterHolder> {
    private List<TopUpReportRecordModel> trList = new ArrayList<>();
    private Context context;
    private TopupReportFragment topupFragment;
    private String wdatefromurl,cdatefromurl;
    private Date datec,datew;
    private static int currentPosition = 0;

    public TopUpReportAdapter(Context context, List<TopUpReportRecordModel> trList) {
        this.trList = trList;

        this.context = context;
        Log.e("list: ", trList.size() + "");
    }

    @Override
    public TopUpReportAdapter.TopUpReportAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_top_up_report, parent, false);
        return new TopUpReportAdapter.TopUpReportAdapterHolder(v);
    }

    @Override
    public void onBindViewHolder(final TopUpReportAdapter.TopUpReportAdapterHolder holder, final int position) {

        final TopUpReportRecordModel topUpReportRecordModel = trList.get(position);

        holder.hiddenlayout.setVisibility(View.GONE);
        wdatefromurl = topUpReportRecordModel.getEntryTime();
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

        holder.productname.setText(topUpReportRecordModel.getProduct());


        holder.pin.setText(topUpReportRecordModel.getPin());
int topint = topUpReportRecordModel.getTopupNo();
        holder.topup.setText(String.valueOf(topint));
        int amt = topUpReportRecordModel.getAmount();
        holder.amount.setText(String.valueOf(amt));







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
        return trList.size();
    }

    public class TopUpReportAdapterHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.srno)
        TextView srno;


        @BindView(R.id.epin)
        TextView pin;
        @BindView(R.id.productname)
        TextView productname;
        @BindView(R.id.amount)
        TextView amount;
        @BindView(R.id.topup)
        TextView topup;
        @BindView(R.id.date)
        TextView date;





        @BindView(R.id.llmain)
        LinearLayout llmain;
        @BindView(R.id.hiddenlayout)
        LinearLayout hiddenlayout;
        View container;


        public TopUpReportAdapterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            container = itemView;
        }
    }

}
