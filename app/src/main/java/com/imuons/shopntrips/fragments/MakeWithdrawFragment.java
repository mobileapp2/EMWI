package com.imuons.shopntrips.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.imuons.shopntrips.R;
import com.imuons.shopntrips.model.FundTransferResponseModel;
import com.imuons.shopntrips.model.GetBalanceReponseModel;
import com.imuons.shopntrips.model.MakeWthdrawReponseModel;
import com.imuons.shopntrips.retrofit.ApiHandler;
import com.imuons.shopntrips.retrofit.Emwi;
import com.imuons.shopntrips.utils.Constants;
import com.imuons.shopntrips.utils.SharedPreferenceUtils;
import com.imuons.shopntrips.utils.Utils;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.LENGTH_SHORT;


public class MakeWithdrawFragment extends Fragment {

    @BindView(R.id.gif)
    GifImageView gif;
@BindView(R.id.bal)
    EditText bal;
@BindView(R.id.amount)
EditText amount;
@BindView(R.id.submit)
Button submit;
@BindView(R.id.txt)
    TextView txt;
String amountenterd;
Calendar calendar = Calendar.getInstance();
    private FragmentManager fragmentManager;
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
        fragmentManager = getFragmentManager();
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
            submit.setVisibility(View.VISIBLE);
            txt.setVisibility(View.GONE);
        }else{
            txt.setVisibility(View.VISIBLE);
        submit.setVisibility(View.GONE);
        }


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( validateAmount() ) {

                    callsubmit();


                }
            }
        });
        return view;

    }

    private void callsubmit() {
        gif.setVisibility(View.VISIBLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        Map<String, String> submitmap = new HashMap<>();


        submitmap.put("amount",amountenterd);

        Emwi apiService = ApiHandler.getApiService();
        final Call<MakeWthdrawReponseModel> loginCall = apiService.wsMakeWithdraw(
                "Bearer " + SharedPreferenceUtils.getAccesstoken(MakeWithdrawFragment.this.getContext()),submitmap);
        loginCall.enqueue(new Callback<MakeWthdrawReponseModel>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<MakeWthdrawReponseModel> call,
                                   Response<MakeWthdrawReponseModel> response) {
//                pd.hide();
                gif.setVisibility(View.GONE);
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                if (response.isSuccessful()) {
                    MakeWthdrawReponseModel makeWthdrawReponseModel = response.body();
                    if (makeWthdrawReponseModel.getCode() == Constants.RESPONSE_CODE_OK &&
                            makeWthdrawReponseModel.getStatus().equals("OK")) {
                        //call top_up Report
                        fragmentManager.beginTransaction().replace(R.id.content_frame, WithdrawRequestReportFragment.newInstance()).commit();
                        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Withdraw Request");

                        Toast.makeText(MakeWithdrawFragment.this.getContext(), makeWthdrawReponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MakeWithdrawFragment.this.getContext(), makeWthdrawReponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<MakeWthdrawReponseModel> call,
                                  Throwable t) {
//                pd.hide();
                gif.setVisibility(View.GONE);
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                Toast.makeText(MakeWithdrawFragment.this.getContext(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private boolean validateAmount() {
        amountenterd = amount.getText().toString();
        int checkvalue = Integer.parseInt(amountenterd);
        if (amountenterd.isEmpty() || checkvalue <= 499) {
            Toast.makeText(MakeWithdrawFragment.this.getContext(), "The amount field must be 500 or more.",
                    LENGTH_SHORT).show();
            return false;
        }
        return true;
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
