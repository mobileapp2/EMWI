package com.imuons.shopntrips.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;


import com.imuons.shopntrips.R;
import com.imuons.shopntrips.model.GetBalanceReponseModel;
import com.imuons.shopntrips.retrofit.ApiHandler;
import com.imuons.shopntrips.retrofit.Emwi;
import com.imuons.shopntrips.utils.Constants;
import com.imuons.shopntrips.utils.SharedPreferenceUtils;
import com.imuons.shopntrips.utils.Utils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MakeWithdrawFragment extends Fragment {

    @BindView(R.id.gif)
    GifImageView gif;
@BindView(R.id.bal)
    EditText bal;
@BindView(R.id.amount)
EditText amount;
    public MakeWithdrawFragment() {
        // Required empty public constructor
    }


    public static MakeWithdrawFragment newInstance() {
        MakeWithdrawFragment fragment = new MakeWithdrawFragment();

        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_make_withdraw, container, false);
        ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        if (Utils.checkInternetConnection(MakeWithdrawFragment.this.getContext())) {


            getTopUpBal();

        } else {
            Toast.makeText(MakeWithdrawFragment.this.getContext(),
                    getString(R.string.no_internet_connection_message), Toast.LENGTH_SHORT).show();
        }
    }

    private void getTopUpBal() {
        gif.setVisibility(View.VISIBLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        Map<String, String> topmap = new HashMap<>();

        topmap.put("wallet", "working");
        Emwi apiService = ApiHandler.getApiService();
        final Call<GetBalanceReponseModel> loginCall = apiService.wsTopUpBalance(
                "Bearer " + SharedPreferenceUtils.getAccesstoken(MakeWithdrawFragment.this.getContext()),topmap);
        loginCall.enqueue(new Callback<GetBalanceReponseModel>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<GetBalanceReponseModel> call,
                                   Response<GetBalanceReponseModel> response) {
//                pd.hide();
                gif.setVisibility(View.GONE);
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                if (response.isSuccessful()) {
                    GetBalanceReponseModel getBalanceReponseModel = response.body();
                    if (getBalanceReponseModel.getCode() == Constants.RESPONSE_CODE_OK &&
                            getBalanceReponseModel.getStatus().equals("OK")) {
                        int avabal = getBalanceReponseModel.getData().getTopup();
                        bal.setText(String.valueOf(avabal));

                    } else {
                        Toast.makeText(MakeWithdrawFragment.this.getContext(), getBalanceReponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetBalanceReponseModel> call,
                                  Throwable t) {
//                pd.hide();
                gif.setVisibility(View.GONE);
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                Toast.makeText(MakeWithdrawFragment.this.getContext(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
