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

public class EPinAmtDepAdapter extends RecyclerView.Adapter<EPinAmtDepAdapter.EPinAmtDepAdapterHolder> {
    private List<PinRequestReportRecordModel> prList = new ArrayList<>();
    private Context context;
    private BinaryIncomeFragment roiIncomeFragment;
    private String wdatefromurl,cdatefromurl;
    private Date datec,datew;
    private static int currentPosition = 0;
    private FragmentManager fragmentManager;
    PinRequestReportRecordModel pinRequestReportRecordModel;
    public EPinAmtDepAdapter(Context context, List<PinRequestReportRecordModel> prList) {
        this.prList = prList;

        this.context = context;
        Log.e("list: ", prList.size() + "");
    }

    @Override
    public EPinAmtDepAdapter.EPinAmtDepAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_amt_dep_report, parent, false);
        return new EPinAmtDepAdapter.EPinAmtDepAdapterHolder(v);
    }

    @Override
    public void onBindViewHolder(final EPinAmtDepAdapter.EPinAmtDepAdapterHolder holder, final int position) {

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
        holder.depositetype.setText(pinRequestReportRecordModel.getDepositeType());
        if(pinRequestReportRecordModel.getBankName() != null){
            holder.bankname.setText(pinRequestReportRecordModel.getBankName());
        }else{
            holder.bankname.setText(" ");
        }
        if(pinRequestReportRecordModel.getChequeNo() != null){
            holder.chequeno.setText(String.valueOf(pinRequestReportRecordModel.getChequeNo()));
        }else{
            holder.chequeno.setText(" ");
        }
        holder.receiptno.setText((CharSequence) pinRequestReportRecordModel.getReceiptNo());
         holder.refno.setText(pinRequestReportRecordModel.getRefNo());
         holder.amt.setText(String.valueOf(pinRequestReportRecordModel.getAmountDeposited()));
holder.des.setText(pinRequestReportRecordModel.getDescription());
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

    public class EPinAmtDepAdapterHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.srno)
        TextView srno;

        @BindView(R.id.depositetype)
        TextView depositetype;
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.bankname)
        TextView bankname;
        @BindView(R.id.chequeno)
        TextView chequeno;
        @BindView(R.id.receiptno)
        TextView receiptno;
        @BindView(R.id.refno)
        TextView refno;
        @BindView(R.id.amt)
        TextView amt;
        @BindView(R.id.des)
        TextView des;




        @BindView(R.id.llmain)
        LinearLayout llmain;
        @BindView(R.id.hiddenlayout)
        LinearLayout hiddenlayout;
        View container;


        public EPinAmtDepAdapterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            container = itemView;
        }
    }

}

