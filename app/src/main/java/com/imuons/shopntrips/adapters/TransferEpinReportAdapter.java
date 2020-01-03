package com.imuons.shopntrips.adapters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.imuons.shopntrips.R;
import com.imuons.shopntrips.fragments.RoyalityIncomeReportFragment;
import com.imuons.shopntrips.fragments.TransferEPinReportFragment;
import com.imuons.shopntrips.fragments.TransferEpinsFragment;
import com.imuons.shopntrips.model.AwardReportGetResponse;
import com.imuons.shopntrips.model.RoyalityIncomeReportRecordModel;
import com.imuons.shopntrips.model.TransferEpinReportRecordModel;
import com.imuons.shopntrips.retrofit.ApiHandler;
import com.imuons.shopntrips.retrofit.Emwi;
import com.imuons.shopntrips.utils.Constants;
import com.imuons.shopntrips.utils.SharedPreferenceUtils;
import com.imuons.shopntrips.utils.ViewUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransferEpinReportAdapter  extends RecyclerView.Adapter<TransferEpinReportAdapter.TransferEpinReportAdapterHolder> {
    private List<TransferEpinReportRecordModel> teList = new ArrayList<>();
    private Context context;
    private FragmentManager fragmentManager;
    private TransferEPinReportFragment transferEPinReportFragment;
    private String wdatefromurl, cdatefromurl;
    private Date datec, datew;
    private static int currentPosition = 0;
    TransferEpinReportRecordModel transferEpinReportRecordModel;

    public TransferEpinReportAdapter(Context context, List<TransferEpinReportRecordModel> teList) {
        this.teList = teList;

        this.context = context;
        Log.e("list: ", teList.size() + "");
    }

    @Override
    public TransferEpinReportAdapter.TransferEpinReportAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_transfer_epin_report, parent, false);
        return new TransferEpinReportAdapter.TransferEpinReportAdapterHolder(v);
    }

    @Override
    public void onBindViewHolder(final TransferEpinReportAdapter.TransferEpinReportAdapterHolder holder, final int position) {

        transferEpinReportRecordModel = teList.get(position);

        holder.hiddenlayout.setVisibility(View.GONE);
        wdatefromurl = transferEpinReportRecordModel.getEntryTime();
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

        holder.touserid.setText(transferEpinReportRecordModel.getUserId());


        holder.fromuserid.setText(transferEpinReportRecordModel.getFullname());

        holder.epincount.setText(String.valueOf(transferEpinReportRecordModel.getNoofpin()));

        holder.productname.setText(transferEpinReportRecordModel.getName());


        holder.epin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int historyid = transferEpinReportRecordModel.getHistoryId();
                int touserid = transferEpinReportRecordModel.getToUserId();

                SharedPreferenceUtils.storeHistoryId(context, String.valueOf(historyid));
                SharedPreferenceUtils.storeToUserId(context,String.valueOf(touserid));

                fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, TransferEpinsFragment.newInstance()).commit();
                ((AppCompatActivity)view.getContext()).getSupportActionBar().setTitle("Transfer E-Pin Report");
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




    @Override
    public int getItemCount() {
        return teList.size();
    }

    public class TransferEpinReportAdapterHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.srno)
        TextView srno;

        @BindView(R.id.touserid)
        TextView touserid;
        @BindView(R.id.fromuserid)
        TextView fromuserid;
        @BindView(R.id.epincount)
        TextView epincount;
        @BindView(R.id.epin)
        TextView epin;
        @BindView(R.id.productname)
        TextView productname;

        @BindView(R.id.date)
        TextView date;


        @BindView(R.id.llmain)
        LinearLayout llmain;
        @BindView(R.id.hiddenlayout)
        LinearLayout hiddenlayout;
        View container;


        public TransferEpinReportAdapterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            container = itemView;
        }
    }

}

