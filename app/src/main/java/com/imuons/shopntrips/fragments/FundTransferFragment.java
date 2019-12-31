package com.imuons.shopntrips.fragments;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.imuons.shopntrips.R;
import com.imuons.shopntrips.model.CheckDownlineUserResponseModel;
import com.imuons.shopntrips.model.FundTransferResponseModel;
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

import static android.widget.Toast.LENGTH_SHORT;

/**
 * A simple {@link Fragment} subclass.
 */
public class FundTransferFragment extends Fragment {
    @BindView(R.id.topupbal)
    EditText topupbal;
    @BindView(R.id.userid)
    EditText userid;
    @BindView(R.id.fullname)
    EditText fullname;
    @BindView(R.id.amount)
    EditText amount;
    @BindView(R.id.submit)
    Button submit;
    private FragmentManager fragmentManager;
    @BindView(R.id.gif)
    GifImageView gif;
    boolean mSponsorAvailable;
    String strproduct,productid,fullName,amountenterd,struserid,strbal;
    public FundTransferFragment() {
        // Required empty public constructor
    }

    public static FundTransferFragment newInstance() {
        FundTransferFragment fragment = new FundTransferFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fund_transfer, container, false);
        ButterKnife.bind(this, view);
        fragmentManager = getFragmentManager();
        userid.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TextView text = (TextView) FundTransferFragment.this.getActivity().getCurrentFocus();

                if (text != null && text.length() > 0) {
//                    View next = text.focusSearch(View.FOCUS_RIGHT); // or FOCUS_FORWARD
//                    if (next != null)
//                        next.requestFocus();

                    checkSponsorAvailability(); // Or whatever
                }
            }

            // afterTextChanged
            @Override
            public void afterTextChanged(Editable s) {
            }

            // beforeTextChanged
            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFullname() && validateAmount() && validateSponserId()  && validateBal()) {

                    showDialouge();


                }
            }
        });

        return view;
    }

    private void showDialouge() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(FundTransferFragment.this.getContext());
        builder1.setTitle("Exit");
        builder1.setMessage("Are you sure You want to transfer the wallet ?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                        callsubmit();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private void callsubmit() {
        gif.setVisibility(View.VISIBLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        Map<String, String> submitmap = new HashMap<>();


        submitmap.put("amount",amountenterd);
        submitmap.put("to_user_id",struserid);
        submitmap.put("type","topup_to_topup");
        Emwi apiService = ApiHandler.getApiService();
        final Call<FundTransferResponseModel> loginCall = apiService.wsFundTransfer(
                "Bearer " + SharedPreferenceUtils.getAccesstoken(FundTransferFragment.this.getContext()),submitmap);
        loginCall.enqueue(new Callback<FundTransferResponseModel>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<FundTransferResponseModel> call,
                                   Response<FundTransferResponseModel> response) {
//                pd.hide();
                gif.setVisibility(View.GONE);
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                if (response.isSuccessful()) {
                    FundTransferResponseModel fundTransferResponseModel = response.body();
                    if (fundTransferResponseModel.getCode() == Constants.RESPONSE_CODE_OK &&
                            fundTransferResponseModel.getStatus().equals("OK")) {
                        //call top_up Report
                        fragmentManager.beginTransaction().replace(R.id.content_frame, FundTransferReportFragment.newInstance()).commit();
                        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Fund Transfer Report");

                        Toast.makeText(FundTransferFragment.this.getContext(), fundTransferResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(FundTransferFragment.this.getContext(), fundTransferResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<FundTransferResponseModel> call,
                                  Throwable t) {
//                pd.hide();
                gif.setVisibility(View.GONE);
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                Toast.makeText(FundTransferFragment.this.getContext(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private boolean validateAmount() {
        amountenterd = amount.getText().toString();
        int checkvalue = Integer.parseInt(amountenterd);
        if (amountenterd.isEmpty() || checkvalue <= 0) {
            Toast.makeText(FundTransferFragment.this.getContext(), getString(R.string.enter_amount),
                    LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean validateBal() {
        strbal = topupbal.getText().toString().trim();
        if (strbal.isEmpty() ) {
            Toast.makeText(FundTransferFragment.this.getContext(),
                    "Balance is empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private boolean validateSponserId() {
        struserid= userid.getText().toString().trim();
        if (struserid.isEmpty()) {
            Toast.makeText(FundTransferFragment.this.getContext(), "Enter valid User Id", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private boolean validateFullname() {
        fullName =  fullname.getText().toString().trim();
        if (fullName.isEmpty()) {
            Toast.makeText(FundTransferFragment.this.getContext(),
                    getString(R.string.invalid_full_name_message), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Utils.checkInternetConnection(FundTransferFragment.this.getContext())) {


            getTopUpBal();


        } else {
            Toast.makeText(FundTransferFragment.this.getContext(),
                    getString(R.string.no_internet_connection_message), Toast.LENGTH_SHORT).show();
        }
    }

    private void getTopUpBal() {
        gif.setVisibility(View.VISIBLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        Map<String, String> topmap = new HashMap<>();

        topmap.put("wallet", "topup");
        Emwi apiService = ApiHandler.getApiService();
        final Call<GetBalanceReponseModel> loginCall = apiService.wsTopUpBalance(
                "Bearer " + SharedPreferenceUtils.getAccesstoken(FundTransferFragment.this.getContext()),topmap);
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
                        topupbal.setText(String.valueOf(avabal));

                    } else {
                        Toast.makeText(FundTransferFragment.this.getContext(), getBalanceReponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetBalanceReponseModel> call,
                                  Throwable t) {
//                pd.hide();
                gif.setVisibility(View.GONE);
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                Toast.makeText(FundTransferFragment.this.getContext(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void checkSponsorAvailability() {



        final String user;

        user = userid.getText().toString().trim();

        Map<String, String> submitmap = new HashMap<>();


        submitmap.put("user_id",user);

        Emwi apiService = ApiHandler.getApiService();
        final Call<CheckDownlineUserResponseModel> loginCall = apiService.wsCheckDownlineUser(
                "Bearer " + SharedPreferenceUtils.getAccesstoken(FundTransferFragment.this.getContext()),submitmap);
        loginCall.enqueue(new Callback<CheckDownlineUserResponseModel>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<CheckDownlineUserResponseModel> call,
                                   Response<CheckDownlineUserResponseModel> response) {

                if (response.isSuccessful()) {
                    CheckDownlineUserResponseModel loginModel = response.body();
                    //   sponsorname = loginModel.getData().getFullname();
                    fullname.setText(loginModel.getData().getFullname());
                    if (loginModel.getCode() == 404) {
                        mSponsorAvailable = true;

                        Toast.makeText(FundTransferFragment.this.getContext(), loginModel.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        mSponsorAvailable = false;
                        // Toast.makeText(Registration.this, loginModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<CheckDownlineUserResponseModel> call,
                                  Throwable t) {

                Toast.makeText(FundTransferFragment.this.getContext(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
