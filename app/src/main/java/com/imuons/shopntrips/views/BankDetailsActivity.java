package com.imuons.shopntrips.views;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.imuons.shopntrips.R;
import com.imuons.shopntrips.model.OTPResponseModel;
import com.imuons.shopntrips.model.UpdateProfileResponseModel;
import com.imuons.shopntrips.model.UserProfileResponseModel;
import com.imuons.shopntrips.model.VerifyOTPResponseModel;
import com.imuons.shopntrips.retrofit.ApiHandler;
import com.imuons.shopntrips.retrofit.Emwi;
import com.imuons.shopntrips.utils.Constants;
import com.imuons.shopntrips.utils.SharedPreferenceUtils;
import com.imuons.shopntrips.utils.ViewUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BankDetailsActivity extends AppCompatActivity {
    private UserProfileResponseModel model;
    @BindView(R.id.btn_Edit)
    Button mbtnEdit;
    @BindView(R.id.btn_Submit)
    Button mbtnSubmit;
    @BindView(R.id.txt_bankAccountNo)
    EditText mEditBankAccountNo;
    @BindView(R.id.txt_bankBranchName)
    EditText mEditBranchName;
    @BindView(R.id.txt_acHolderName)
    EditText mEditAcHolderName;
    @BindView(R.id.txt_panNo)
    EditText mEditPanNo;
    @BindView(R.id.txt_bankName)
            
    EditText mEditBankName;
    @BindView(R.id.txt_bankIFSCCode)
    EditText mEditIFSCCode;
    String  pancard,sponsorname,sponsorid,userid,password,cpassword;
    Pattern pattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_details);
        ButterKnife.bind(this);
        setEditViewFalse();
        Gson gS = new Gson();
        String target = getIntent().getStringExtra("object");
        model = gS.fromJson(target, UserProfileResponseModel.class);
        if (model != null && model.getData().getUserId() != null) {
            displayData(model);
        } else {
            Toast.makeText(BankDetailsActivity.this, "No Data found!", Toast.LENGTH_SHORT).show();
        }


        mbtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getOTP();
            }
        });

        mbtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( validationpancard()){
                    UpdateData();
                }

            }
        });

    }

    private boolean validationpancard() {
        pancard= mEditPanNo.getText().toString().trim();
        Matcher matcher = pattern.matcher(pancard);
        if (matcher.matches()) {

            return true;
        }else {
            Toast.makeText(BankDetailsActivity.this, getString(R.string.invalid_pan_card_number), Toast.LENGTH_SHORT).show();
            return false;
        }

    }
    private void UpdateData() {
        final ProgressDialog pd = ViewUtils.getProgressBar(BankDetailsActivity.this, "Loading...", "Please wait..!");
        String accountNo = mEditBankAccountNo.getText().toString();
        String branchName = mEditBranchName.getText().toString();
        String holderName = mEditAcHolderName.getText().toString();
        String panNo = mEditPanNo.getText().toString();
        String bankName = mEditBankName.getText().toString();
        String ifscCode = mEditIFSCCode.getText().toString();


        Map<String, String> roiMap = new HashMap<>();
        roiMap.put("account_no", accountNo);
        roiMap.put("branch_name", branchName);
        roiMap.put("holder_name", holderName);
        roiMap.put("pan_no", panNo);
        roiMap.put("bank_name", bankName);
        roiMap.put("ifsc_code", ifscCode);

        Emwi apiService = ApiHandler.getApiService();

        final Call<UpdateProfileResponseModel> loginCall = apiService.wsUpdateProfile("Bearer "
                + SharedPreferenceUtils.getLoginObject(
                BankDetailsActivity.this).getData().getAccess_token(), roiMap);

        loginCall.enqueue(new Callback<UpdateProfileResponseModel>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<UpdateProfileResponseModel> call,
                                   Response<UpdateProfileResponseModel> response) {
                pd.hide();
                if (response.isSuccessful()) {
                    UpdateProfileResponseModel profileResponseModel = response.body();
                    if (profileResponseModel.getCode() == Constants.RESPONSE_CODE_OK &&
                            profileResponseModel.getStatus().equals("OK")) {
                        Toast.makeText(BankDetailsActivity.this, "Information Updated", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BankDetailsActivity.this, DashboardActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BankDetailsActivity.this, profileResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<UpdateProfileResponseModel> call,
                                  Throwable t) {
                pd.hide();
                Toast.makeText(BankDetailsActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getOTP() {
        final ProgressDialog pd = ViewUtils.getProgressBar(BankDetailsActivity.this, "Loading...", "Please wait..!");
        Map<String, String> roiMap = new HashMap<>();

        roiMap.put("start", String.valueOf(0));

        Emwi apiService = ApiHandler.getApiService();

        final Call<OTPResponseModel> loginCall = apiService.wsSendOTP("Bearer "
                + SharedPreferenceUtils.getLoginObject(
                BankDetailsActivity.this).getData().getAccess_token(), roiMap);

        loginCall.enqueue(new Callback<OTPResponseModel>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<OTPResponseModel> call,
                                   Response<OTPResponseModel> response) {
                pd.hide();
                if (response.isSuccessful()) {
                    OTPResponseModel OTPResponseModel = response.body();
                    if (OTPResponseModel.getCode() == Constants.RESPONSE_CODE_OK &&
                            OTPResponseModel.getStatus().equals("OK")) {
                        //setSpinnerAdapter(OTPResponseModel.getData());
                        openAlertBox();
                        Toast.makeText(BankDetailsActivity.this, "OTP Send Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(BankDetailsActivity.this, OTPResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<OTPResponseModel> call,
                                  Throwable t) {
                pd.hide();
                Toast.makeText(BankDetailsActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openAlertBox() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final EditText edittext = new EditText(getApplication());
        alert.setMessage("Enter OTP here");
        alert.setTitle("OTP Send Successfully");
        alert.setView(edittext);

        alert.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                String YouEditTextValue = edittext.getText().toString();
                verifyOTP(YouEditTextValue);
            }
        });

        alert.show();
    }

    private void verifyOTP(String youEditTextValue) {
        final ProgressDialog pd = ViewUtils.getProgressBar(BankDetailsActivity.this, "Loading...", "Please wait..!");
        Map<String, String> roiMap = new HashMap<>();

        roiMap.put("otp", youEditTextValue);

        Emwi apiService = ApiHandler.getApiService();

        final Call<VerifyOTPResponseModel> loginCall = apiService.wsVeryfiedOTP("Bearer "
                + SharedPreferenceUtils.getLoginObject(
                BankDetailsActivity.this).getData().getAccess_token(), roiMap);

        loginCall.enqueue(new Callback<VerifyOTPResponseModel>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<VerifyOTPResponseModel> call,
                                   Response<VerifyOTPResponseModel> response) {
                pd.hide();
                if (response.isSuccessful()) {
                    VerifyOTPResponseModel VerifyOTPResponseModel = response.body();
                    if (VerifyOTPResponseModel.getCode() == Constants.RESPONSE_CODE_OK &&
                            VerifyOTPResponseModel.getStatus().equals("OK")) {
                        Toast.makeText(BankDetailsActivity.this, "OTP Verified", Toast.LENGTH_SHORT).show();
                        doEditNow();
                    } else {
                        Toast.makeText(BankDetailsActivity.this, VerifyOTPResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<VerifyOTPResponseModel> call,
                                  Throwable t) {
                pd.hide();
                Toast.makeText(BankDetailsActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void doEditNow() {
        mEditBankAccountNo.setEnabled(true);
        mEditBranchName.setEnabled(true);
        mEditAcHolderName.setEnabled(true);
        mEditPanNo.setEnabled(true);
        mEditBankName.setEnabled(true);
        mEditIFSCCode.setEnabled(true);
        mbtnEdit.setVisibility(View.VISIBLE);
        mbtnSubmit.setVisibility(View.VISIBLE);

    }

    private void setEditViewFalse() {
        mEditBankAccountNo.setEnabled(false);
        mEditBranchName.setEnabled(false);
        mEditAcHolderName.setEnabled(false);
        mEditPanNo.setEnabled(false);
        mEditBankName.setEnabled(false);
        mEditIFSCCode.setEnabled(false);
        mbtnEdit.setVisibility(View.VISIBLE);
    }

    private void displayData(UserProfileResponseModel data) {
        mEditBankAccountNo.setText(data.getData().getAccountNo());
        mEditBranchName.setText(data.getData().getBranchName());
        mEditAcHolderName.setText(data.getData().getHolderName());
        mEditPanNo.setText(data.getData().getPanNo());
        mEditBankName.setText(data.getData().getBankName());
        mEditIFSCCode.setText(data.getData().getIfscCode());
    }
}
