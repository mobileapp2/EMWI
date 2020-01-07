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

public class OpenTicketsAdapter extends RecyclerView.Adapter<OpenTicketsAdapter.TransferEpinReportAdapterHolder> {
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
    public OpenTicketsAdapter.TransferEpinReportAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_opentickets, parent, false);
        return new OpenTicketsAdapter.TransferEpinReportAdapterHolder(v);
    }

    @Override
    public void onBindViewHolder(final OpenTicketsAdapter.TransferEpinReportAdapterHolder holder, final int position) {

        transferEpinReportRecordModel = teList.get(position);


            holder.userId.setText(transferEpinReportRecordModel.getTicketNo());



    }


    @Override
    public int getItemCount() {
        return teList.size();
    }

    public class TransferEpinReportAdapterHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.userId)
        TextView userId;


        View container;


        public TransferEpinReportAdapterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            container = itemView;
        }
    }

    public interface OpenTicketsRecyclerViewClickListener {

        void onClickCallbacks(View view, int position, String url);


    }

}

