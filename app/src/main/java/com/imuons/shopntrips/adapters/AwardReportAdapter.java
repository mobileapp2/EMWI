package com.imuons.shopntrips.adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.imuons.shopntrips.R;
import com.imuons.shopntrips.fragments.AwardIncomeReportFragment;
import com.imuons.shopntrips.fragments.FundTransferFragment;
import com.imuons.shopntrips.fragments.ROIIncomeFragment;
import com.imuons.shopntrips.fragments.TeamViewFragment;
import com.imuons.shopntrips.model.AwardReportGetResponse;
import com.imuons.shopntrips.model.AwardReportRecordModel;
import com.imuons.shopntrips.model.RoiIncomeReportRecordModel;
import com.imuons.shopntrips.model.TeamViewResponseModel;
import com.imuons.shopntrips.model.UpdateProfileResponseModel;
import com.imuons.shopntrips.retrofit.ApiHandler;
import com.imuons.shopntrips.retrofit.ShopNTrips;
import com.imuons.shopntrips.utils.Constants;
import com.imuons.shopntrips.utils.SharedPreferenceUtils;
import com.imuons.shopntrips.utils.ViewUtils;
import com.imuons.shopntrips.views.AboutActivity;
import com.imuons.shopntrips.views.DashboardActivity;

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

public class AwardReportAdapter extends RecyclerView.Adapter<AwardReportAdapter.AwardReportAdapterHolder> {
    private List<AwardReportRecordModel> airList = new ArrayList<>();
    private Context context;
    private FragmentManager fragmentManager;
    private AwardIncomeReportFragment awardIncomeReportFragment;
    private String wdatefromurl, cdatefromurl;
    private Date datec, datew;
    private static int currentPosition = 0;
    AwardReportRecordModel awardReportRecordModel;

    public AwardReportAdapter(Context context, List<AwardReportRecordModel> airList) {
        this.airList = airList;

        this.context = context;
        Log.e("list: ", airList.size() + "");
    }

    @Override
    public AwardReportAdapter.AwardReportAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_award_income_report, parent, false);
        return new AwardReportAdapter.AwardReportAdapterHolder(v);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(final AwardReportAdapter.AwardReportAdapterHolder holder, final int position) {

        awardReportRecordModel = airList.get(position);

        holder.hiddenlayout.setVisibility(View.GONE);
//        wdatefromurl = awardReportRecordModel.getWonOnDate();
//        if(wdatefromurl != null) {
//            SimpleDateFormat simpleDateFormatw = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
//            try {
//                datew = simpleDateFormatw.parse(wdatefromurl);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//            DateFormat dateFormatw = new SimpleDateFormat("yyyy/MM/dd");
//            String wcdate = dateFormatw.format(datew);
        holder.date.setText(awardReportRecordModel.getWonOnDate());
//        }else{
//            holder.date.setText("-");
//        }

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

        holder.leftbv.setText(awardReportRecordModel.getLeftBvForAward());


        holder.userid.setText(awardReportRecordModel.getUserId());

        holder.award.setText(awardReportRecordModel.getAward());
        int amt = awardReportRecordModel.getAmount();
        holder.amount.setText(String.valueOf(amt));
        int rightbv = awardReportRecordModel.getRightBvForAward();
        holder.rightbv.setText(String.valueOf(rightbv));
        int statint = awardReportRecordModel.getStatus();

        if (statint == 0) {
            holder.status.setClickable(true);
            holder.status.setBackgroundColor(R.color.green);
            holder.status.setText("GET");

            holder.status.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    awardReportRecordModel = airList.get(position);
                    int intwinid = awardReportRecordModel.getWinId();
                    String strwinid = String.valueOf(intwinid);
                    showDialouge(strwinid,v);
                }
            });

        } else {
            holder.status.setClickable(false);
            holder.status.setText("RECEIVED");
        }


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

    private void showDialouge(String strwinid, View v) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setTitle("Exit");
        builder1.setMessage("Are you sure You want to receive this award ?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                        callsubmit(strwinid,v);
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private void callsubmit(String strwinid, View v) {

        final ProgressDialog pd = ViewUtils.getProgressBar(context, "Loading...", "Please wait..!");

        Map<String, String> roiMap = new HashMap<>();
        roiMap.put("id", strwinid);
        ShopNTrips apiService = ApiHandler.getApiService();

        final Call<AwardReportGetResponse> loginCall = apiService.wsGetAward("Bearer "
                + SharedPreferenceUtils.getLoginObject(
                context).getData().getAccess_token(), roiMap);

        loginCall.enqueue(new Callback<AwardReportGetResponse>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<AwardReportGetResponse> call,
                                   Response<AwardReportGetResponse> response) {
                pd.hide();
                if (response.isSuccessful()) {
                    AwardReportGetResponse awardReportGetResponse = response.body();
                    if (awardReportGetResponse.getCode() == Constants.RESPONSE_CODE_OK &&
                            awardReportGetResponse.getStatus().equals("OK")) {
                        Toast.makeText(context, awardReportGetResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.content_frame, AwardIncomeReportFragment.newInstance()).commit();
                        ((AppCompatActivity)v.getContext()).getSupportActionBar().setTitle("Award Income Report");
                    } else {
                        Toast.makeText(context, awardReportGetResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<AwardReportGetResponse> call,
                                  Throwable t) {
                pd.hide();
                //Toast.makeText(context, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
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

        @BindView(R.id.userid)
        TextView userid;
        @BindView(R.id.award)
        TextView award;
        @BindView(R.id.leftbv)
        TextView leftbv;
        @BindView(R.id.rightbv)
        TextView rightbv;
        @BindView(R.id.amount)
        TextView amount;
        @BindView(R.id.status)
        TextView status;
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

