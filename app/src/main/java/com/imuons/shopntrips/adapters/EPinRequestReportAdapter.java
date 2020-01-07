package com.imuons.shopntrips.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.imuons.shopntrips.R;
import com.imuons.shopntrips.fragments.BinaryIncomeFragment;

import com.imuons.shopntrips.fragments.EPinAmtDepositedFragment;
import com.imuons.shopntrips.fragments.EPinProductDetailFragment;
import com.imuons.shopntrips.model.BinaryIncomeReportRecordModel;
import com.imuons.shopntrips.model.PinRequestReportRecordModel;
import com.imuons.shopntrips.utils.SharedPreferenceUtils;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EPinRequestReportAdapter extends RecyclerView.Adapter<EPinRequestReportAdapter.EPinRequestReportAdapterHolder> {
    private List<PinRequestReportRecordModel> prList = new ArrayList<>();
    private Context context;
    private BinaryIncomeFragment roiIncomeFragment;
    private String wdatefromurl,cdatefromurl;
    private Date datec,datew;
    private static int currentPosition = 0;
    private FragmentManager fragmentManager;
    PinRequestReportRecordModel pinRequestReportRecordModel;
    public EPinRequestReportAdapter(Context context, List<PinRequestReportRecordModel> prList) {
        this.prList = prList;

        this.context = context;
        Log.e("list: ", prList.size() + "");
    }

    @Override
    public EPinRequestReportAdapter.EPinRequestReportAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_epin_request_report, parent, false);
        return new EPinRequestReportAdapter.EPinRequestReportAdapterHolder(v);
    }

    @Override
    public void onBindViewHolder(final EPinRequestReportAdapter.EPinRequestReportAdapterHolder holder, final int position) {

        pinRequestReportRecordModel  = prList.get(position);

        holder.hiddenlayout.setVisibility(View.GONE);
        wdatefromurl = pinRequestReportRecordModel.getEntryTime();
        if(wdatefromurl != null) {
            SimpleDateFormat simpleDateFormatw = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
            try {
                datew = simpleDateFormatw.parse(wdatefromurl);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            DateFormat dateFormatw = new SimpleDateFormat("yyyy/MM/dd");
            String wcdate = dateFormatw.format(datew);
            holder.reqdate.setText(wcdate);
        }else{
            holder.reqdate.setText("-");
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
        holder.reqty.setText(String.valueOf(pinRequestReportRecordModel.getRequestQuantity()));
        holder.userid.setText(pinRequestReportRecordModel.getUserId());
        holder.product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pinRequestReportRecordModel  = prList.get(position);
                int requestid = pinRequestReportRecordModel.getId();

                SharedPreferenceUtils.storeReqid(context, String.valueOf(requestid));

                fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, EPinProductDetailFragment.newInstance()).commit();
                ((AppCompatActivity)view.getContext()).getSupportActionBar().setTitle("E-Pin Product Detail");
            }
        });
        holder.amtdep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int getid = pinRequestReportRecordModel.getId();
                SharedPreferenceUtils.storeId(context, String.valueOf(getid));

                fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, EPinAmtDepositedFragment.newInstance()).commit();
                ((AppCompatActivity)view.getContext()).getSupportActionBar().setTitle("E-Pin Amount Deposited");
            }
        });
        holder.status.setText(pinRequestReportRecordModel.getStatus());
        if(pinRequestReportRecordModel.getAttachment() != null){
        if(pinRequestReportRecordModel.getAttachment().endsWith(".png")){
            Picasso.get().load(pinRequestReportRecordModel.getAttachment()).into(holder.attachment);

        }else{
            Picasso.get().load(R.drawable.applogo).into(holder.attachment);
        }}else{
            Picasso.get().load(R.drawable.applogo).into(holder.attachment);
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

    @Override
    public int getItemCount() {
        return prList.size();
    }

    public class EPinRequestReportAdapterHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.srno)
        TextView srno;

        @BindView(R.id.reqdate)
        TextView reqdate;
        @BindView(R.id.reqty)
        TextView reqty;
        @BindView(R.id.userid)
        TextView userid;
        @BindView(R.id.product)
        TextView product;
        @BindView(R.id.amtdep)
        TextView amtdep;
        @BindView(R.id.status)
        TextView status;
        @BindView(R.id.attachment)
        ImageView attachment;





        @BindView(R.id.llmain)
        LinearLayout llmain;
        @BindView(R.id.hiddenlayout)
        LinearLayout hiddenlayout;
        View container;


        public EPinRequestReportAdapterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            container = itemView;
        }
    }

}
