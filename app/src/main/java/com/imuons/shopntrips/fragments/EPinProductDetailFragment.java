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
import android.widget.TextView;
import android.widget.Toast;

import com.imuons.shopntrips.R;
import com.imuons.shopntrips.adapters.EPinProductDetailAdapter;
import com.imuons.shopntrips.model.EPinProductDetailDatumModel;
import com.imuons.shopntrips.model.EPinProductDetailResponseModel;
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

public class EPinProductDetailFragment extends Fragment {
    @BindView(R.id.qty)
    TextView qty;
    @BindView(R.id.total)
    TextView total;
    @BindView(R.id.recycler_view_epinproductdetails)
    RecyclerView recycler_view_epinproductdetails;
    EPinProductDetailAdapter ePinProductDetailAdapter;
    private List<EPinProductDetailDatumModel> epdList = new ArrayList<>();
    String totalpricre;
    int intqty;
    int totalqty= 0;


    public EPinProductDetailFragment() {
        // Required empty public constructor
    }


    public static EPinProductDetailFragment newInstance() {
        EPinProductDetailFragment fragment = new EPinProductDetailFragment();

        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_epin_product_detail, container, false);

        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Utils.checkInternetConnection(EPinProductDetailFragment.this.getContext())) {


            recycler_view_epinproductdetails.setHasFixedSize(true);
            recycler_view_epinproductdetails.setLayoutManager(new LinearLayoutManager(EPinProductDetailFragment.this.getContext(),LinearLayoutManager.VERTICAL,false));

            epdList.clear();
            getdata();
        } else {
            Toast.makeText(EPinProductDetailFragment.this.getContext(),
                    getString(R.string.no_internet_connection_message), Toast.LENGTH_SHORT).show();
        }
    }

    private void getdata() {
        Map<String, String> epdMap = new HashMap<>();
        epdMap.put("request_id", SharedPreferenceUtils.getReqid(EPinProductDetailFragment.this.getContext()));

        Emwi apiService = ApiHandler.getApiService();
        final Call<EPinProductDetailResponseModel> loginCall = apiService.wsEPinProductDetail(
                "Bearer " + SharedPreferenceUtils.getAccesstoken(EPinProductDetailFragment.this.getContext()),epdMap);
        loginCall.enqueue(new Callback<EPinProductDetailResponseModel>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<EPinProductDetailResponseModel> call,
                                   Response<EPinProductDetailResponseModel> response) {

                if (response.isSuccessful()) {
                    EPinProductDetailResponseModel ePinProductDetailResponseModel = response.body();
                    if (ePinProductDetailResponseModel.getCode() == Constants.RESPONSE_CODE_OK &&
                            ePinProductDetailResponseModel.getStatus().equals("OK")) {
                        epdList.addAll(ePinProductDetailResponseModel.getData());
                        totalpricre =  ePinProductDetailResponseModel.getData().get(0).getSumTotalPrice();
                       String strqty = ePinProductDetailResponseModel.getData().get(0).getSumRequestQuantity();
                        qty.setText(strqty);
                        total.setText(totalpricre);
                        if(epdList.size() > 0) {
                            ePinProductDetailAdapter = new EPinProductDetailAdapter(EPinProductDetailFragment.this, epdList);
                            recycler_view_epinproductdetails.setAdapter(ePinProductDetailAdapter);
                        }else{
                            Toast.makeText(EPinProductDetailFragment.this.getContext(), "No data available in table", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(EPinProductDetailFragment.this.getContext(), ePinProductDetailResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<EPinProductDetailResponseModel> call,
                                  Throwable t) {
                Toast.makeText(EPinProductDetailFragment.this.getContext(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }
}


