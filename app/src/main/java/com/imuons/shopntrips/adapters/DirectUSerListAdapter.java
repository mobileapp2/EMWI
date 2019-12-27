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

import androidx.recyclerview.widget.RecyclerView;

import com.imuons.shopntrips.R;
import com.imuons.shopntrips.fragments.DirectUserListFragment;
import com.imuons.shopntrips.model.DirectUserListRecordModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DirectUSerListAdapter extends RecyclerView.Adapter<DirectUSerListAdapter.DirectUSerListAdapterHolder> {
    private List<DirectUserListRecordModel> duList = new ArrayList<>();
private Context context;
private DirectUserListFragment directUserListFragment;
private String wdatefromurl,cdatefromurl;
private Date datec,datew;
private static int currentPosition = 0;

public DirectUSerListAdapter(Context context, List<DirectUserListRecordModel> drorList) {
        this.duList = drorList;

        this.context = context;
        Log.e("list: ", drorList.size() + "");
        }

@Override
public DirectUSerListAdapter.DirectUSerListAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_direct_user_list_report, parent, false);
        return new DirectUSerListAdapter.DirectUSerListAdapterHolder(v);
        }

@Override
public void onBindViewHolder(final DirectUSerListAdapter.DirectUSerListAdapterHolder holder, final int position) {

final DirectUserListRecordModel directRoiReportRecordModel = duList.get(position);

        holder.hiddenlayout.setVisibility(View.GONE);
        wdatefromurl = directRoiReportRecordModel.getEntryTime();
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

        holder.userid.setText(directRoiReportRecordModel.getUserId());

        holder.fullname.setText(directRoiReportRecordModel.getFullname());

        holder.mobile.setText(directRoiReportRecordModel.getMobile());


        holder.position.setText(directRoiReportRecordModel.getPosition());








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
        return duList.size();
        }

public class DirectUSerListAdapterHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.srno)
    TextView srno;
    @BindView(R.id.userid)
    TextView userid;
    @BindView(R.id.fullname)
    TextView fullname;


    @BindView(R.id.mobile)
    TextView mobile;
    @BindView(R.id.date)
    TextView date;

    @BindView(R.id.position)
    TextView position;






    @BindView(R.id.llmain)
    LinearLayout llmain;
    @BindView(R.id.hiddenlayout)
    LinearLayout hiddenlayout;
    View container;


    public DirectUSerListAdapterHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        container = itemView;
    }
}

}
