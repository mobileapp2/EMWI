package com.imuons.shopntrips.adapters;

import androidx.recyclerview.widget.RecyclerView;

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

import com.imuons.shopntrips.R;
import com.imuons.shopntrips.fragments.BinaryIncomeReportFragment;
import com.imuons.shopntrips.model.BinaryIncomeReportRecordModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BinaryIncomeReportAdapter extends RecyclerView.Adapter<BinaryIncomeReportAdapter.BinaryIncomeReportAdapterHolder> {
private List<BinaryIncomeReportRecordModel> birList = new ArrayList<>();
private Context context;
private BinaryIncomeReportFragment binaryIncomeReportFragment;
private String wdatefromurl,cdatefromurl;
private Date datec,datew;
private static int currentPosition = 0;

public BinaryIncomeReportAdapter(Context context, List<BinaryIncomeReportRecordModel> birList) {
        this.birList = birList;

        this.context = context;
        Log.e("list: ", birList.size() + "");
        }

@Override
public BinaryIncomeReportAdapter.BinaryIncomeReportAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_binary_income_report, parent, false);
        return new BinaryIncomeReportAdapter.BinaryIncomeReportAdapterHolder(v);
        }

@Override
public void onBindViewHolder(final BinaryIncomeReportAdapter.BinaryIncomeReportAdapterHolder holder, final int position) {

final BinaryIncomeReportRecordModel binaryIncomeReportRecordModel = birList.get(position);

        holder.hiddenlayout.setVisibility(View.GONE);
 //       wdatefromurl = binaryIncomeReportRecordModel.getWithdrawDate();
//        if(wdatefromurl != null) {
//        SimpleDateFormat simpleDateFormatw = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        try {
//        datew = simpleDateFormatw.parse(wdatefromurl);
//        } catch (ParseException e) {
//        e.printStackTrace();
//        }
//        DateFormat dateFormatw = new SimpleDateFormat("yyyy/MM/dd");
//        String wcdate = dateFormatw.format(datew);
//        holder.withdrawdate.setText(wcdate);
//        }else{
//        holder.withdrawdate.setText("-");
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
        int payno = binaryIncomeReportRecordModel.getPayoutNo();
        holder.payoutno.setText(String.valueOf(payno));

        int leftbvint = binaryIncomeReportRecordModel.getLeftBv();
        holder.leftbv.setText(String.valueOf(leftbvint));
    int rightbvint = binaryIncomeReportRecordModel.getRightBv();
    holder.rightbv.setText(String.valueOf(rightbvint));

    int carryrightbvint = binaryIncomeReportRecordModel.getRightBvCarry();
    holder.carryrighbv.setText(String.valueOf(carryrightbvint));
    int carryleftbvint = binaryIncomeReportRecordModel.getLeftBvCarry();
    holder.carryleftbv.setText(String.valueOf(carryleftbvint));

      double lapsint = binaryIncomeReportRecordModel.getLapsBv();
      holder.lapsamt.setText(String.valueOf(lapsint));


        holder.amount.setText(binaryIncomeReportRecordModel.getAmount());

        holder.pin.setText(binaryIncomeReportRecordModel.getPin());
        holder.roi.setText(binaryIncomeReportRecordModel.getBinaryRoi());







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
        return birList.size();
        }

public class BinaryIncomeReportAdapterHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.srno)
    TextView srno;
    @BindView(R.id.payoutno)
    TextView payoutno;
    @BindView(R.id.leftbv)
    TextView leftbv;
    @BindView(R.id.rightbv)
    TextView rightbv;
    @BindView(R.id.carryleftbv)
    TextView carryleftbv;
    @BindView(R.id.carryrighbv)
    TextView carryrighbv;
@BindView(R.id.lapsamt)
TextView lapsamt;
@BindView(R.id.amount)
    TextView amount;
@BindView(R.id.pin)
    TextView pin;
@BindView(R.id.roi)
    TextView roi;





    @BindView(R.id.llmain)
    LinearLayout llmain;
    @BindView(R.id.hiddenlayout)
    LinearLayout hiddenlayout;
    View container;


    public BinaryIncomeReportAdapterHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        container = itemView;
    }
}

}
