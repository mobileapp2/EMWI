package com.imuons.shopntrips.views;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListPopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.imuons.shopntrips.R;
import com.imuons.shopntrips.model.GetCityDatumModel;
import com.imuons.shopntrips.model.GetCityResponseModel;
import com.imuons.shopntrips.model.GetStateDatumModel;
import com.imuons.shopntrips.model.GetStateResponseModel;
import com.imuons.shopntrips.model.RegisterResponseModel;
import com.imuons.shopntrips.model.UserProfileDataModel;
import com.imuons.shopntrips.model.UserProfileResponseModel;
import com.imuons.shopntrips.model.UserTopUpResponse;
import com.imuons.shopntrips.retrofit.ApiHandler;
import com.imuons.shopntrips.retrofit.Emwi;
import com.imuons.shopntrips.utils.Constants;
import com.imuons.shopntrips.utils.SharedPreferenceUtils;
import com.imuons.shopntrips.utils.ViewUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotPresentActivity extends AppCompatActivity {
    private UserProfileResponseModel profileModel;
    private UserTopUpResponse profileModel2;

    @BindView(R.id.edt_SponsorId)
    EditText mTextSponsorId;
    @BindView(R.id.edit_SponsorName)
    EditText mTextSponsorName;
    @BindView(R.id.edt_Mobileno)
    EditText mTextMobileNumbers;

    @BindView(R.id.edit_Password)
    EditText mTextPassword;
    @BindView(R.id.txt_ConfirmPassword)
    EditText mTextConfirmPassword;
    @BindView(R.id.edt_Name)
    EditText mTextName;
    @BindView(R.id.txt_UserId)
    TextView mEditUserID;

    @BindView(R.id.edt_Email)
    EditText mTextEmail;
    @BindView(R.id.edt_country)
    EditText mTextCountry;
    @BindView(R.id.edt_State)
    EditText mTextState;
    @BindView(R.id.edt_City)
    EditText mTextCity;
    @BindView(R.id.edt_Address)
    EditText mTextAddress;
    @BindView(R.id.edt_Pincode)
    EditText mTextPincode;
    @BindView(R.id.radiogroup)
    RadioGroup radiogroup;
    @BindView(R.id.leftradio)
    RadioButton leftradio;
    @BindView(R.id.rightradio)
    RadioButton rightradio;
    @BindView(R.id.btn_Submit)
    Button mBtnSubmit;
    ListPopupWindow statelistPopupWindow, citylistPopupWindow;
    String selectedradio, sponsorname, refUserID, sponsorid, userid, password, cpassword, email, mobile, fullName,
            strstate, straddress, strcity, strpincode;
    List<String> listStateName = new ArrayList<>();
    private List<GetStateDatumModel> statelist = new ArrayList<>();
    List<String> listCityName = new ArrayList<>();
    private List<GetCityDatumModel> citylist = new ArrayList<>();
    int randomNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_present);
        ButterKnife.bind(this);
        getStatebycountry();
        getUserProfileInfo();
        statelistPopupWindow = new ListPopupWindow(NotPresentActivity.this);
        citylistPopupWindow = new ListPopupWindow(NotPresentActivity.this);
        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateEmail() && validateMobileNo() && validateFullName() && validateCity() && validateState() && validateRadio() && validatePincode() && validateAddress()) {


                    register();
                }

            }
        });
        statelistPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mTextState.setText(statelist.get(i).getName());
                strstate = mTextState.getText().toString();
                getcitybystate(strstate);
                statelistPopupWindow.dismiss();
            }
        });

        citylistPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mTextCity.setText(citylist.get(i).getName());
                strcity = mTextCity.getText().toString();

                citylistPopupWindow.dismiss();
            }
        });

        mTextState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statelistPopupWindow.setAdapter(new ArrayAdapter(
                        NotPresentActivity.this,
                        R.layout.check_list_item, listStateName));
                statelistPopupWindow.setAnchorView(mTextState);
                statelistPopupWindow.setWidth(800);
                statelistPopupWindow.setHeight(800);
                statelistPopupWindow.setModal(true);
                statelistPopupWindow.show();
            }
        });

        mTextCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                citylistPopupWindow.setAdapter(new ArrayAdapter(
                        NotPresentActivity.this,
                        R.layout.check_list_item, listCityName));
                citylistPopupWindow.setAnchorView(mTextCity);
                citylistPopupWindow.setWidth(800);
                citylistPopupWindow.setHeight(800);
                citylistPopupWindow.setModal(true);
                citylistPopupWindow.show();
            }
        });


    }

    private void getUserProfileInfo() {
        final ProgressDialog pd = ViewUtils.getProgressBar(NotPresentActivity.this,
                "Loading...", "Please wait..!");

        Emwi apiService = ApiHandler.getApiService();
        final Call<UserProfileResponseModel> loginCall = apiService.wsUserProfileInfo(
                "Bearer " + SharedPreferenceUtils.getLoginObject(
                        NotPresentActivity.this).getData().getAccess_token());
        loginCall.enqueue(new Callback<UserProfileResponseModel>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<UserProfileResponseModel> call,
                                   Response<UserProfileResponseModel> response) {
                pd.hide();
                if (response.isSuccessful()) {
                    profileModel = response.body();
                    if (profileModel.getCode() == Constants.RESPONSE_CODE_OK &&
                            profileModel.getStatus().equals("OK")) {
                        setUserData(profileModel.getData());
                    } else {
                        Toast.makeText(NotPresentActivity.this, profileModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<UserProfileResponseModel> call,
                                  Throwable t) {
                pd.hide();
                Toast.makeText(NotPresentActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getStatebycountry() {
        final ProgressDialog pd = ViewUtils.getProgressBar(NotPresentActivity.this, "Loading...", "Please wait..!");

        Map<String, String> loginMap = new HashMap<>();
        loginMap.put("country", "IN");

        Emwi apiService = ApiHandler.getApiService();
        final Call<GetStateResponseModel> loginCall = apiService.wsGetStateByCountry(loginMap);

        loginCall.enqueue(new Callback<GetStateResponseModel>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<GetStateResponseModel> call,
                                   Response<GetStateResponseModel> response) {
                pd.hide();
                if (response.isSuccessful()) {
                    GetStateResponseModel getStateResponseModel = response.body();
                    if (getStateResponseModel.getCode() == Constants.RESPONSE_CODE_OK &&
                            getStateResponseModel.getStatus().equals("OK")) {
                        statelist.addAll(getStateResponseModel.getData());
                        listStateName.clear();
                        getstatebyname(getStateResponseModel.getData());
                    } else {
                        Toast.makeText(NotPresentActivity.this, getStateResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetStateResponseModel> call,
                                  Throwable t) {
                pd.hide();
                Toast.makeText(NotPresentActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getcitybystate(String strstate) {
        final ProgressDialog pd = ViewUtils.getProgressBar(NotPresentActivity.this, "Loading...", "Please wait..!");

        Map<String, String> loginMap = new HashMap<>();
        loginMap.put("state", strstate);

        Emwi apiService = ApiHandler.getApiService();
        final Call<GetCityResponseModel> loginCall = apiService.wsGetCityByState(loginMap);

        loginCall.enqueue(new Callback<GetCityResponseModel>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<GetCityResponseModel> call,
                                   Response<GetCityResponseModel> response) {
                pd.hide();
                if (response.isSuccessful()) {
                    GetCityResponseModel getCityResponseModel = response.body();
                    if (getCityResponseModel.getCode() == Constants.RESPONSE_CODE_OK &&
                            getCityResponseModel.getStatus().equals("OK")) {
                        citylist.addAll(getCityResponseModel.getData());
                        listCityName.clear();
                        getcitybyname(getCityResponseModel.getData());
                    } else {
                        Toast.makeText(NotPresentActivity.this, getCityResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetCityResponseModel> call,
                                  Throwable t) {
                pd.hide();
                Toast.makeText(NotPresentActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getcitybyname(List<GetCityDatumModel> data) {

        for (int i = 0; i < data.size(); i++) {
            listCityName.add(data.get(i).getName());
        }

    }

    private void register() {
        final ProgressDialog pd = ViewUtils.getProgressBar(NotPresentActivity.this, "Loading...", "Please wait..!");


        password = mTextPassword.getText().toString().trim();

        Map<String, String> registerMap = new HashMap<>();
        registerMap.put("fullname", fullName);
        registerMap.put("email", email);
        registerMap.put("user_id", String.valueOf(randomNo));
        registerMap.put("password", password);
        //   registerMap.put("placement_user_id", "");
        registerMap.put("ref_user_id", refUserID);
        registerMap.put("position", selectedradio);
        registerMap.put("sponsor_name", sponsorname);
        registerMap.put("mobile", mobile);
        registerMap.put("country", "IN");
        registerMap.put("state", strstate);
        registerMap.put("address", straddress);
        registerMap.put("city", strcity);
        registerMap.put("pincode", strpincode);


        Emwi apiService = ApiHandler.getApiService();
        final Call<RegisterResponseModel> registerCall = apiService.wsRegister(registerMap);
        registerCall.enqueue(new Callback<RegisterResponseModel>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<RegisterResponseModel> call,
                                   Response<RegisterResponseModel> response) {
                pd.hide();
                if (response.isSuccessful()) {
                    RegisterResponseModel registerResponseModel = response.body();
                    if (registerResponseModel.getCode() == Constants.RESPONSE_CODE_OK &&
                            registerResponseModel.getStatus().equals("OK")) {
                        String useridtosend = String.valueOf(registerResponseModel.getData().getUserid());
                        String userpassword = registerResponseModel.getData().getPassword();
                        Intent showpassword = new Intent(NotPresentActivity.this, DashboardActivity.class);

                        startActivity(showpassword);
                        Toast.makeText(NotPresentActivity.this, registerResponseModel.getMessage(), Toast.LENGTH_SHORT).show();


                    } else {
                        pd.hide();
                        Toast.makeText(NotPresentActivity.this, registerResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    pd.hide();
                    Toast.makeText(NotPresentActivity.this, "Check username or password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponseModel> call,
                                  Throwable t) {
                pd.hide();
                Toast.makeText(NotPresentActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getstatebyname(List<GetStateDatumModel> data) {

        for (int i = 0; i < data.size(); i++) {
            listStateName.add(data.get(i).getName());
        }
    }

    private void setUserData(UserProfileDataModel data) {
        mTextSponsorId.setText(data.getUserId());
        mTextSponsorName.setText(data.getFullname());
        sponsorname = data.getFullname();
        refUserID = data.getUserId();
        setRandomNo();
        //mTextMobileNumbers.setText(data.getMobile());
    }

    private boolean validateEmail() {
        email = mTextEmail.getText().toString().trim();
        if (email.isEmpty() || !email.matches(Constants.EMAIL_REGEX)) {
            Toast.makeText(NotPresentActivity.this,
                    getString(R.string.invalid_email_message), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean validateFullName() {
        fullName = mTextName.getText().toString().trim();
        if (fullName.isEmpty()) {
            Toast.makeText(NotPresentActivity.this,
                    getString(R.string.invalid_full_name_message), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean validateMobileNo() {
        mobile = mTextMobileNumbers.getText().toString().trim();
        if (mobile.isEmpty() || mobile.length() < 10) {
            Toast.makeText(NotPresentActivity.this,
                    getString(R.string.invalid_mobile_number_message), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean validatePincode() {
        strpincode = mTextPincode.getText().toString().trim();
        if (strpincode.isEmpty() || strpincode.length() < 6) {
            Toast.makeText(NotPresentActivity.this,
                    "Enter Pincode", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean validateCity() {
        strcity = mTextCity.getText().toString().trim();
        if (strcity.isEmpty() || strcity.equals("Select City")) {
            Toast.makeText(NotPresentActivity.this,
                    "Enter City", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean validateState() {
        if (strstate.isEmpty() || strcity.equals("Select State")) {
            Toast.makeText(NotPresentActivity.this,
                    "Select State", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean validateAddress() {
        straddress = mTextAddress.getText().toString().trim();
        if (straddress.isEmpty()) {
            Toast.makeText(NotPresentActivity.this,
                    "Enter Address", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean validateRadio() {
        if (radiogroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(getApplicationContext(), "Please select Position", Toast.LENGTH_SHORT).show();
            return false;
        } else if (leftradio.isChecked()) {
//            // get selected radio button from radioGroup
//            int selectedId = radiogroup.getCheckedRadioButtonId();
//            // find the radiobutton by returned id
//            leftradio = (RadioButton)findViewById(selectedId);
            selectedradio = "1";
        } else if (rightradio.isChecked()) {
            selectedradio = "2";
        }
        return true;
    }

    private void setRandomNo() {
        Random rnd = new Random();
        randomNo = 10000000 + rnd.nextInt(90000000);
        mEditUserID.setText(Integer.toString(randomNo));

    }
}
