package com.imuons.shopntrips.adapters;

import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.imuons.shopntrips.R;
import com.imuons.shopntrips.fragments.WithdrawRequestReportFragment;
import com.imuons.shopntrips.model.WithdrawRequestReportRecordModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WithdrawRequestReportAdapter extends RecyclerView.Adapter<WithdrawRequestReportAdapter.WithdrawRequestReportAdapterHolder> {
    private List<WithdrawRequestReportRecordModel> wrList = new ArrayList<>();
    private Context context;
    private WithdrawRequestReportFragment withdrawReportFragment;
    private String wdatefromurl,cdatefromurl;
    private Date datec,datew;
    private static int currentPosition = 0;

    public WithdrawRequestReportAdapter(Context context, List<WithdrawRequestReportRecordModel> wrList) {
        this.wrList = wrList;

        this.context = context;
        Log.e("list: ", wrList.size() + "");
    }

    @Override
    public WithdrawRequestReportAdapter.WithdrawRequestReportAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_withdraw_request_report, parent, false);
        return new WithdrawRequestReportAdapter.WithdrawRequestReportAdapterHolder(v);
    }

    @Override
    public void onBindViewHolder(final WithdrawRequestReportAdapter.WithdrawRequestReportAdapterHolder holder, final int position) {

        final WithdrawRequestReportRecordModel withdrawRequestReportRecordModel = wrList.get(position);

        holder.hiddenlayout.setVisibility(View.GONE);
        wdatefromurl = withdrawRequestReportRecordModel.getRecDate();
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

        double amtint = withdrawRequestReportRecordModel.getAmount();
        holder.amount.setText(String.valueOf(amtint));

        double tdsint = withdrawRequestReportRecordModel.getTds();
        holder.tds.setText(String.valueOf(tdsint));

        double adminchar = withdrawRequestReportRecordModel.getAmtPin();
        holder.admincharges.setText(String.valueOf(adminchar));

        double netamtint = withdrawRequestReportRecordModel.getNetAmount();
        holder.netamt.setText(String.valueOf(netamtint));

        int statusint = withdrawRequestReportRecordModel.getStatus();
        if(statusint == 0){
            holder.status.setText("Unpaid");
        }else if (statusint == 1){
            holder.status.setText("Paid");
        }



        holder.details.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        showDetails(withdrawRequestReportRecordModel);
    }
});





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
    private void showDetails(WithdrawRequestReportRecordModel withdrawRequestReportRecordModel) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.layout_bank_detail_withdraw_report);

        TextView accountnumber = (TextView) dialog.findViewById(R.id.accountnumber);
        TextView holdername = (TextView) dialog.findViewById(R.id.holdername);
        TextView bankname = (TextView) dialog.findViewById(R.id.bankname);
        TextView branchname = (TextView) dialog.findViewById(R.id.branchname);
        TextView ifsccode = (TextView) dialog.findViewById(R.id.ifsccode);

        ImageView imageClose = dialog.findViewById(R.id.image_close);

        try {
            int amtin = withdrawRequestReportRecordModel.getAccountNo();
            accountnumber.setText(String.valueOf(amtin));
            holdername.setText(withdrawRequestReportRecordModel.getHolderName());
            bankname.setText(withdrawRequestReportRecordModel.getBankName());
            branchname.setText(withdrawRequestReportRecordModel.getBranchName());
            ifsccode.setText(withdrawRequestReportRecordModel.getIfscCode());

        } catch (Exception e) {
            e.printStackTrace();
        }
        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
    @Override
    public int getItemCount() {
        return wrList.size();
    }

    public class WithdrawRequestReportAdapterHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.srno)
        TextView srno;

        @BindView(R.id.amount)
        TextView amount;
        @BindView(R.id.tds)
        TextView tds;

        @BindView(R.id.admincharges)
        TextView admincharges;
        @BindView(R.id.netamt)
        TextView netamt;
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.status)
        TextView status;
        @BindView(R.id.deatils)
        TextView details;





        @BindView(R.id.llmain)
        LinearLayout llmain;
        @BindView(R.id.hiddenlayout)
        LinearLayout hiddenlayout;
        View container;


        public WithdrawRequestReportAdapterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            container = itemView;
        }
    }

}
