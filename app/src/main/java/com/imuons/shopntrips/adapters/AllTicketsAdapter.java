package com.imuons.shopntrips.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.imuons.shopntrips.R;
import com.imuons.shopntrips.model.AllTicketsDataModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllTicketsAdapter extends RecyclerView.Adapter<AllTicketsAdapter.TransferEpinReportAdapterHolder> {
    private List<AllTicketsDataModel> teList = new ArrayList<>();
    private Context context;

    private static int currentPosition = 0;
    AllTicketsDataModel transferEpinReportRecordModel;
    AllTicketsRecyclerViewClickListener listener;

    public AllTicketsAdapter(Context context, List<AllTicketsDataModel> teList, AllTicketsRecyclerViewClickListener listener) {
        this.teList = teList;
        this.context = context;
        this.listener = listener;
        Log.e("list: ", teList.size() + "");
    }

    @Override
    public AllTicketsAdapter.TransferEpinReportAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_alltickets, parent, false);
        return new AllTicketsAdapter.TransferEpinReportAdapterHolder(v);
    }

    @Override
    public void onBindViewHolder(final AllTicketsAdapter.TransferEpinReportAdapterHolder holder, final int position) {

        transferEpinReportRecordModel = teList.get(position);

        holder.userId.setText(transferEpinReportRecordModel.getTicketNo());

        holder.btn_Chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transferEpinReportRecordModel = teList.get(position);


                listener.onClickCallbacks(view,position, String.valueOf(transferEpinReportRecordModel.getId()));

            }
        });

    }


    @Override
    public int getItemCount() {
        return teList.size();
    }

    public class TransferEpinReportAdapterHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.userId)
        TextView userId;
        @BindView(R.id.btn_Chat)
        TextView btn_Chat;


        View container;


        public TransferEpinReportAdapterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            container = itemView;
        }
    }

    public interface AllTicketsRecyclerViewClickListener {

        void onClickCallbacks(View view, int position, String url);


    }

}

