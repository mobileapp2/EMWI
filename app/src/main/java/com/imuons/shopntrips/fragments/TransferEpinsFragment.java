package com.imuons.shopntrips.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.imuons.shopntrips.R;
import com.imuons.shopntrips.adapters.TransferEpinsDetailsAdapter;
import com.imuons.shopntrips.model.TransferPinDetailDataModel;
import com.imuons.shopntrips.model.TransferPinDetailResponseModel;
import com.imuons.shopntrips.retrofit.ApiHandler;
import com.imuons.shopntrips.retrofit.Emwi;
import com.imuons.shopntrips.utils.Constants;
import com.imuons.shopntrips.utils.SharedPreferenceUtils;
import com.imuons.shopntrips.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TransferEpinsFragment extends Fragment {

    @BindView(R.id.recycler_view_transferepins)
    RecyclerView recycler_view_transferepins;
    TransferEpinsDetailsAdapter transferEpinsDetailsAdapter;
    private List<TransferPinDetailDataModel> tpList = new ArrayList<>();
    public TransferEpinsFragment() {
        // Required empty public constructor
    }

    public static TransferEpinsFragment newInstance() {
        TransferEpinsFragment fragment = new TransferEpinsFragment();

        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transfer_epins, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Utils.checkInternetConnection(TransferEpinsFragment.this.getContext())) {

            recycler_view_transferepins.setHasFixedSize(true);
            recycler_view_transferepins.setLayoutManager(new LinearLayoutManager(TransferEpinsFragment.this.getContext(),LinearLayoutManager.VERTICAL,false));

            tpList.clear();
            getdata();
        } else {
            Toast.makeText(TransferEpinsFragment.this.getContext(),
                    getString(R.string.no_internet_connection_message), Toast.LENGTH_SHORT).show();
        }
    }

    private void getdata() {
        Map<String, String> tpMap = new HashMap<>();


        tpMap.put("history_id", SharedPreferenceUtils.getHistoryId(TransferEpinsFragment.this.getContext()));
        tpMap.put("touser_id",SharedPreferenceUtils.getToUserId(TransferEpinsFragment.this.getContext()));


        Emwi apiService = ApiHandler.getApiService();
        final Call<TransferPinDetailResponseModel> loginCall = apiService.wsGetTransferPins(
                "Bearer " + SharedPreferenceUtils.getAccesstoken(TransferEpinsFragment.this.getContext()),tpMap);
        loginCall.enqueue(new Callback<TransferPinDetailResponseModel>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<TransferPinDetailResponseModel> call,
                                   Response<TransferPinDetailResponseModel> response) {

                if (response.isSuccessful()) {
                    TransferPinDetailResponseModel transferPinDetailResponseModel = response.body();
                    if (transferPinDetailResponseModel.getCode() == Constants.RESPONSE_CODE_OK &&
                            transferPinDetailResponseModel.getStatus().equals("OK")) {
                        tpList.addAll(transferPinDetailResponseModel.getData());
                        if(tpList.size() > 0) {
                            transferEpinsDetailsAdapter = new TransferEpinsDetailsAdapter(TransferEpinsFragment.this, tpList);
                            recycler_view_transferepins.setAdapter(transferEpinsDetailsAdapter);
                        }else{
                            Toast.makeText(TransferEpinsFragment.this.getContext(), "No data available in table", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(TransferEpinsFragment.this.getContext(), transferPinDetailResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<TransferPinDetailResponseModel> call,
                                  Throwable t) {
                Toast.makeText(TransferEpinsFragment.this.getContext(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
