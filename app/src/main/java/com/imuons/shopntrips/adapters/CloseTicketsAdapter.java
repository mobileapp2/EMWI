package com.imuons.shopntrips.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.imuons.shopntrips.R;
import com.imuons.shopntrips.model.AllTicketsDataModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CloseTicketsAdapter extends RecyclerView.Adapter<CloseTicketsAdapter.OpenTicketAdapderHolder> {
    private List<AllTicketsDataModel> teList = new ArrayList<>();
    private Context context;

    private static int currentPosition = 0;
    AllTicketsDataModel transferEpinReportRecordModel;
    ClosedTicketsRecyclerViewClickListener listener;

    public CloseTicketsAdapter(Context context, List<AllTicketsDataModel> teList, ClosedTicketsRecyclerViewClickListener listener) {
        this.teList = teList;
        this.context = context;
        this.listener = listener;
        Log.e("list: ", teList.size() + "");
    }

    @Override
    public CloseTicketsAdapter.OpenTicketAdapderHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_closetickets, parent, false);
        return new CloseTicketsAdapter.OpenTicketAdapderHolder(v);
    }

    @Override
    public void onBindViewHolder(final CloseTicketsAdapter.OpenTicketAdapderHolder holder, final int position) {

        transferEpinReportRecordModel = teList.get(position);

        if (transferEpinReportRecordModel.getStatus().equals("Close")) {
            holder.userId.setText(transferEpinReportRecordModel.getTicketNo());
            holder.close.setVisibility(View.VISIBLE);
        } else {

            holder.close.setVisibility(View.GONE);
        }
        if (transferEpinReportRecordModel.getStatus().equals("Open")) {

            holder.close.setVisibility(View.GONE);
        } else {
            holder.userId.setText(transferEpinReportRecordModel.getTicketNo());
            holder.close.setVisibility(View.VISIBLE);
        }



        /*holder.userId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transferEpinReportRecordModel = teList.get(position);
                listener.onClickCallClosebacks(view,position, String.valueOf(transferEpinReportRecordModel.getId()));

            }
        });*/
    }

    @Override
    public int getItemCount() {
        return teList.size();
    }

    public class OpenTicketAdapderHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.userId)
        TextView userId;
        @BindView(R.id.close)
        CardView close;
        View container;


        public OpenTicketAdapderHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            container = itemView;
        }
    }

    public interface ClosedTicketsRecyclerViewClickListener {

        void onClickCallClosebacks(View view, int position, String url);

    }

}

