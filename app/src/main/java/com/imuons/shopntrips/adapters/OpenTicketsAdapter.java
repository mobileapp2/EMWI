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

public class OpenTicketsAdapter extends RecyclerView.Adapter<OpenTicketsAdapter.OpenTicketAdapderHolder> {
    private List<AllTicketsDataModel> teList = new ArrayList<>();
    private Context context;

    private static int currentPosition = 0;
    AllTicketsDataModel transferEpinReportRecordModel;
    OpenTicketsRecyclerViewClickListener listener;

    public OpenTicketsAdapter(Context context, List<AllTicketsDataModel> teList, OpenTicketsRecyclerViewClickListener listener) {
        this.teList = teList;
        this.context = context;
        this.listener = listener;
        Log.e("list: ", teList.size() + "");
    }

    @Override
    public OpenTicketsAdapter.OpenTicketAdapderHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_opentickets, parent, false);
        return new OpenTicketsAdapter.OpenTicketAdapderHolder(v);
    }

    @Override
    public void onBindViewHolder(final OpenTicketsAdapter.OpenTicketAdapderHolder holder, final int position) {

        transferEpinReportRecordModel = teList.get(position);

        if (transferEpinReportRecordModel.getStatus().equals("Open")) {
            holder.userId.setText(transferEpinReportRecordModel.getTicketNo());
            holder.open.setVisibility(View.VISIBLE);
        } else {

            holder.open.setVisibility(View.GONE);
        }

        if (transferEpinReportRecordModel.getStatus().equals("Close")) {

            holder.open.setVisibility(View.GONE);
        }

        holder.btn_Chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transferEpinReportRecordModel = teList.get(position);

                listener.onClickCallOpenbacks(view, position, String.valueOf(transferEpinReportRecordModel.getId()));

            }
        });


    }


    @Override
    public int getItemCount() {
        return teList.size();
    }

    public class OpenTicketAdapderHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.userId)
        TextView userId;
        @BindView(R.id.btn_Chat)
        TextView btn_Chat;
        @BindView(R.id.open)
        CardView open;
        View container;


        public OpenTicketAdapderHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            container = itemView;
        }
    }

    public interface OpenTicketsRecyclerViewClickListener {

        void onClickCallOpenbacks(View view, int position, String url);

    }

}

