package com.imuons.shopntrips.adapters;

import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.imuons.shopntrips.R;
import com.imuons.shopntrips.fragments.TransferEpinsFragment;
import com.imuons.shopntrips.model.TransferPinDetailDataModel;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransferEpinsDetailsAdapter extends RecyclerView.Adapter<TransferEpinsDetailsAdapter.TransferEpinsDetailsAdapterHolder> {
    TransferEpinsFragment transferEpinsFragment;
    private List<TransferPinDetailDataModel> tpList = new ArrayList<>();
    private Context context;
    String datefromurl;
    private static final int SELECT_PICTURE = 100;
    private FragmentManager fragmentManager;
    Date date;
    private static int currentPosition = 0;

    public TransferEpinsDetailsAdapter(TransferEpinsFragment transferEpinsFragment, List<TransferPinDetailDataModel> tpList) {
        this.tpList = tpList;
        this.transferEpinsFragment = transferEpinsFragment;
        Log.e("list: ", tpList.size() + "");
    }

    @Override
    public TransferEpinsDetailsAdapter.TransferEpinsDetailsAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(transferEpinsFragment.getContext()).inflate(R.layout.adapter_transfer_epins_details, parent, false);
        return new TransferEpinsDetailsAdapter.TransferEpinsDetailsAdapterHolder(v);
    }

    @Override
    public void onBindViewHolder(final  TransferEpinsDetailsAdapter.TransferEpinsDetailsAdapterHolder holder, final int position) {

        final TransferPinDetailDataModel transferPinDetailDataModel = tpList.get(position);



        holder.srno.setText(String.valueOf(position + 1));


        holder.epins.setText(transferPinDetailDataModel.getPin());
        //if the position is equals to the item position which is to be expanded

    }

    @Override
    public int getItemCount() {
        return tpList.size();
    }

    public class TransferEpinsDetailsAdapterHolder extends RecyclerView.ViewHolder {
//        @BindView(R.id.touserid)
//        TextView touserid;
//        @BindView(R.id.tofullname)
//        TextView tofullname;
//        @BindView(R.id.epincount)
//        TextView epincount;
        @BindView(R.id.epins)
        TextView epins;
//        @BindView(R.id.productname)
//        TextView productname;
//        @BindView(R.id.date)
//        TextView date;
        @BindView(R.id.srno)
        TextView srno;
//        @BindView(R.id.eye)
//        ImageView eye;


//        @BindView(R.id.llmain)
//        LinearLayout llmain;
//        @BindView(R.id.hiddenlayout)
//        LinearLayout hiddenlayout;
        View container;


        public TransferEpinsDetailsAdapterHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            container = itemView;
        }
    }

}
