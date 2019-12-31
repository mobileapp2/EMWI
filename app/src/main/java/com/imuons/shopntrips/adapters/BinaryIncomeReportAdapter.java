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
import com.imuons.shopntrips.model.BinaryIncomeReportRecordModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BinaryIncomeReportAdapter extends RecyclerView.Adapter<BinaryIncomeReportAdapter.RoiIncomeReportAdapterHolder> {
private List<BinaryIncomeReportRecordModel> rorList = new ArrayList<>();
private Context context;
private BinaryIncomeFragment roiIncomeFragment;
private String wdatefromurl,cdatefromurl;
private Date datec,datew;
private static int currentPosition = 0;

public BinaryIncomeReportAdapter(Context context, List<BinaryIncomeReportRecordModel> rorList) {
        this.rorList = rorList;

        this.context = context;
        Log.e("list: ", rorList.size() + "");
        }

@Override
public BinaryIncomeReportAdapter.RoiIncomeReportAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_binary_income_report, parent, false);
        return new BinaryIncomeReportAdapter.RoiIncomeReportAdapterHolder(v);
        }

@Override
public void onBindViewHolder(final BinaryIncomeReportAdapter.RoiIncomeReportAdapterHolder holder, final int position) {

final BinaryIncomeReportRecordModel binaryIncomeReportRecordModel = rorList.get(position);

        holder.hiddenlayout.setVisibility(View.GONE);
               wdatefromurl = binaryIncomeReportRecordModel.getEntryTime();
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

        holder.payoutno.setText(String.valueOf(binaryIncomeReportRecordModel.getPayoutNo()));


        holder.leftbv.setText(String.valueOf(binaryIncomeReportRecordModel.getLeftBv()));

        holder.rightbv.setText(String.valueOf(binaryIncomeReportRecordModel.getRightBv()));


    holder.carryleftbv.setText(String.valueOf(binaryIncomeReportRecordModel.getLeftBvCarry()));

    holder.carryrightbv.setText(String.valueOf(binaryIncomeReportRecordModel.getRightBvCarry()));

    holder.amount.setText(binaryIncomeReportRecordModel.getAmount());

    holder.capping.setText(String.valueOf(binaryIncomeReportRecordModel.getCapping()));

    holder.lapsamount.setText(String.valueOf(binaryIncomeReportRecordModel.getLapsBv()));

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
        return rorList.size();
        }

public class RoiIncomeReportAdapterHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.srno)
    TextView srno;

    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.carryrightbv)
    TextView carryrightbv;
    @BindView(R.id.amount)
    TextView amount;
    @BindView(R.id.lapsamount)
    TextView lapsamount;
    @BindView(R.id.capping)
    TextView capping;
    @BindView(R.id.carryleftbv)
    TextView carryleftbv;
    @BindView(R.id.rightbv)
    TextView rightbv;
    @BindView(R.id.leftbv)
    TextView leftbv;
    @BindView(R.id.payoutno)
    TextView payoutno;




    @BindView(R.id.llmain)
    LinearLayout llmain;
    @BindView(R.id.hiddenlayout)
    LinearLayout hiddenlayout;
    View container;


    public RoiIncomeReportAdapterHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        container = itemView;
    }
}

}
