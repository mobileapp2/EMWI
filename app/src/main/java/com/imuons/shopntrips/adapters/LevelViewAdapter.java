package com.imuons.shopntrips.adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.imuons.shopntrips.R;
import com.imuons.shopntrips.fragments.LevelViewFragment;
import com.imuons.shopntrips.model.LevelViewRecordModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LevelViewAdapter extends RecyclerView.Adapter<LevelViewAdapter.LevelViewAdapterHolder> {
    private List<LevelViewRecordModel> lwList = new ArrayList<>();
    private Context context;
    private LevelViewFragment levelViewFragment;
    private String cdatefromurl,edatefromurl,adatefromurl;
    private Date datec,datee,datea;
    private static int currentPosition = 0;

    public LevelViewAdapter(Context context, List<LevelViewRecordModel> lwList) {
        this.lwList = lwList;

        this.context = context;
        Log.e("list: ", lwList.size() + "");
    }

    @Override
    public LevelViewAdapter.LevelViewAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_level_view, parent, false);
        return new LevelViewAdapter.LevelViewAdapterHolder(v);
    }

    @Override
    public void onBindViewHolder(final LevelViewAdapter.LevelViewAdapterHolder holder, final int position) {

        final LevelViewRecordModel levelViewRecordModel = lwList.get(position);

        holder.hiddenlayout.setVisibility(View.GONE);
        cdatefromurl = levelViewRecordModel.getEntryTime();
        if(cdatefromurl != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            try {
                datec = simpleDateFormat.parse(cdatefromurl);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String cdate = dateFormat.format(datec);
            holder.date.setText(cdate);
        }else{
            holder.date.setText("-");
        }





//        if (position % 2 == 0) {
//            holder.llmain.setBackgroundColor(Color.parseColor("#f5f4f4"));
//        } else {
//            holder.llmain.setBackgroundColor(Color.parseColor("#c9caca"));
//
//        }

        holder.srno.setText(String.valueOf(position + 1));
        holder.sponserid.setText(levelViewRecordModel.getUserId());
        holder.userid.setText(levelViewRecordModel.getDownUserId());
        int levelint = levelViewRecordModel.getLevel();
        holder.level.setText(String.valueOf(levelint));
        holder.name.setText(levelViewRecordModel.getFullname());






        //if the position is equals to the item position which is to be expanded
        if (currentPosition == position) {

            //creating an animation
            Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down);

            //toggling visibility
            holder.hiddenlayout.setVisibility(View.VISIBLE);

            //adding sliding effect
            holder.hiddenlayout.startAnimation(slideDown);
        } else {

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
        return lwList.size();
    }

    public class LevelViewAdapterHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.srno)
        TextView srno;
        @BindView(R.id.sponserid)
        TextView sponserid;
        @BindView(R.id.level)
        TextView level;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.userid)
        TextView userid;
        @BindView(R.id.date)
        TextView date;




        @BindView(R.id.llmain)
        LinearLayout llmain;
        @BindView(R.id.hiddenlayout)
        LinearLayout hiddenlayout;
        View container;


        public LevelViewAdapterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            container = itemView;
        }
    }

}
