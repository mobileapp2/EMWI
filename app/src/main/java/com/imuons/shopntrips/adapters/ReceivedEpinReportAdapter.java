package com.imuons.shopntrips.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.imuons.shopntrips.R;
import com.imuons.shopntrips.fragments.ReceivedEPinReportFragment;
import com.imuons.shopntrips.fragments.TransferEPinReportFragment;
import com.imuons.shopntrips.fragments.TransferEpinsFragment;
import com.imuons.shopntrips.model.ReceivedPinRecordModel;
import com.imuons.shopntrips.model.TransferEpinReportRecordModel;
import com.imuons.shopntrips.utils.SharedPreferenceUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReceivedEpinReportAdapter extends RecyclerView.Adapter<ReceivedEpinReportAdapter.ReceivedEpinReportAdapterHolder> {
    private List<ReceivedPinRecordModel> teList = new ArrayList<>();
    private Context context;
    private FragmentManager fragmentManager;
    private ReceivedEPinReportFragment receivedEPinReportFragment;
    private String wdatefromurl, cdatefromurl;
    private Date datec, datew;
    private static int currentPosition = 0;
    ReceivedPinRecordModel receivedPinRecordModel;

    public ReceivedEpinReportAdapter(Context context, List<ReceivedPinRecordModel> teList) {
        this.teList = teList;

        this.context = context;
        Log.e("list: ", teList.size() + "");
    }

    @Override
    public ReceivedEpinReportAdapter.ReceivedEpinReportAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_receive_epin_report, parent, false);
        return new ReceivedEpinReportAdapter.ReceivedEpinReportAdapterHolder(v);
    }

    @Override
    public void onBindViewHolder(final ReceivedEpinReportAdapter.ReceivedEpinReportAdapterHolder holder, final int position) {

        receivedPinRecordModel = teList.get(position);

        holder.hiddenlayout.setVisibility(View.GONE);
        wdatefromurl = receivedPinRecordModel.getEntryTime();
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

        holder.fromfullname.setText(receivedPinRecordModel.getFullname());


        holder.fromuserid.setText(receivedPinRecordModel.getUserId());

        holder.epincount.setText(String.valueOf(receivedPinRecordModel.getNoofpin()));

        holder.productname.setText(receivedPinRecordModel.getName());



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

    public class ReceivedEpinReportAdapterHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.srno)
        TextView srno;

        @BindView(R.id.fromuserid)
        TextView fromuserid;
        @BindView(R.id.fromfullname)
        TextView fromfullname;
        @BindView(R.id.epincount)
        TextView epincount;

        @BindView(R.id.productname)
        TextView productname;

        @BindView(R.id.date)
        TextView date;


        @BindView(R.id.llmain)
        LinearLayout llmain;
        @BindView(R.id.hiddenlayout)
        LinearLayout hiddenlayout;
        View container;


        public ReceivedEpinReportAdapterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            container = itemView;
        }
    }

}

