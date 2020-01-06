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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.imuons.shopntrips.R;
import com.imuons.shopntrips.model.GetCityDatumModel;
import com.imuons.shopntrips.model.GetCityResponseModel;
import com.imuons.shopntrips.model.GetStateDatumModel;
import com.imuons.shopntrips.model.GetStateResponseModel;
import com.imuons.shopntrips.model.UpdateProfileResponseModel;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactInfoActivity extends AppCompatActivity {
    private UserProfileResponseModel model;
    @BindView(R.id.btn_Update)
    Button mbtnSubmit;
    @BindView(R.id.txt_postalAddress)
    EditText mTextpostalAddress;
    @BindView(R.id.txt_pincode)
    EditText mTextPincode;
    @BindView(R.id.edit_state)
    EditText edit_state;
    @BindView(R.id.edit_city)
    EditText edit_city;
    ListPopupWindow statelistPopupWindow,citylistPopupWindow;
    String strstate,strcity,strpincode;
    List<String> listStateName = new ArrayList<>();
    private List<GetStateDatumModel> statelist = new ArrayList<>();

    List<String> listCityName = new ArrayList<>();
    private List<GetCityDatumModel> citylist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);
        ButterKnife.bind(this);
        Gson gS = new Gson();
        String target = getIntent().getStringExtra("object");
        model = gS.fromJson(target, UserProfileResponseModel.class);
        if (model != null && model.getData().getUserId() != null) {
            displayData(model);
        } else {
            Toast.makeText(ContactInfoActivity.this, "No Data found!", Toast.LENGTH_SHORT).show();
        }
        getTopUp();
        setEditViewFalse();
        getStatebycountry();

        statelistPopupWindow = new ListPopupWindow(ContactInfoActivity.this);
        citylistPopupWindow = new ListPopupWindow(ContactInfoActivity.this);

        mbtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    UpdateProfile();

            }
        });

        statelistPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                edit_state.setText(statelist.get(i).getName());
                strstate = edit_state.getText().toString();
                getcitybystate(strstate);
                statelistPopupWindow.dismiss();
            }
        });
        citylistPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                edit_city.setText(citylist.get(i).getName());
                strcity = edit_city.getText().toString();
                citylistPopupWindow.dismiss();
            }
        });

        edit_state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statelistPopupWindow.setAdapter(new ArrayAdapter(
                        ContactInfoActivity.this,
                        R.layout.check_list_item, listStateName));
                statelistPopupWindow.setAnchorView(edit_state);
                statelistPopupWindow.setWidth(800);
                statelistPopupWindow.setHeight(800);
                statelistPopupWindow.setModal(true);
                statelistPopupWindow.show();
            }
        });
        edit_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                citylistPopupWindow.setAdapter(new ArrayAdapter(
                        ContactInfoActivity.this,
                        R.layout.check_list_item, listCityName));
                citylistPopupWindow.setAnchorView(edit_city);
                citylistPopupWindow.setWidth(800);
                citylistPopupWindow.setHeight(800);
                citylistPopupWindow.setModal(true);
                citylistPopupWindow.show();
            }
        });
    }

    private void setEditViewFalse() {
        mTextPincode.setEnabled(false);
        mTextpostalAddress.setEnabled(false);
    }

    private void displayData(UserProfileResponseModel data) {
        mTextpostalAddress.setText(data.getData().getAddress());
        mTextPincode.setText((CharSequence) data.getData().getPincode());
        edit_city.setText(data.getData().getCity());
        edit_state.setText(data.getData().getState());
    }

    private void getcitybystate(String strstate) {
        final ProgressDialog pd = ViewUtils.getProgressBar(ContactInfoActivity.this, "Loading...", "Please wait..!");

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
                        Toast.makeText(ContactInfoActivity.this, getCityResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetCityResponseModel> call,
                                  Throwable t) {
                pd.hide();
                Toast.makeText(ContactInfoActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getcitybyname(List<GetCityDatumModel> data) {

        for (int i = 0; i < data.size(); i++) {
            listCityName.add(data.get(i).getName());
        }
    }

    private void UpdateProfile() {
        final ProgressDialog pd = ViewUtils.getProgressBar(ContactInfoActivity.this, "Loading...", "Please wait..!");
        String postaladdress = mTextpostalAddress.getText().toString();
        String pincode = mTextPincode.getText().toString();


        Map<String, String> roiMap = new HashMap<>();
        roiMap.put("country", "IN");
        roiMap.put("state", strstate);
        roiMap.put("city", strcity);
        roiMap.put("address", postaladdress);
        roiMap.put("pincode", pincode);


        Emwi apiService = ApiHandler.getApiService();

        final Call<UpdateProfileResponseModel> loginCall = apiService.wsUpdateProfile("Bearer "
                + SharedPreferenceUtils.getLoginObject(
                ContactInfoActivity.this).getData().getAccess_token(), roiMap);

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
                        Toast.makeText(ContactInfoActivity.this, "Information Updated", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ContactInfoActivity.this, DashboardActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(ContactInfoActivity.this, profileResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<UpdateProfileResponseModel> call,
                                  Throwable t) {
                pd.hide();
                Toast.makeText(ContactInfoActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void getStatebycountry() {
        final ProgressDialog pd = ViewUtils.getProgressBar(ContactInfoActivity.this, "Loading...", "Please wait..!");

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
                        Toast.makeText(ContactInfoActivity.this, getStateResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetStateResponseModel> call,
                                  Throwable t) {
                pd.hide();
                Toast.makeText(ContactInfoActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getstatebyname(List<GetStateDatumModel> data) {

        for (int i = 0; i < data.size(); i++) {
            listStateName.add(data.get(i).getName());
        }
    }
    private void getTopUp() {
        final ProgressDialog pd = ViewUtils.getProgressBar(ContactInfoActivity.this, "Loading...", "Please wait..!");
        Map<String, String> roiMap = new HashMap<>();

        roiMap.put("start", String.valueOf(0));

        Emwi apiService = ApiHandler.getApiService();

        final Call<UserTopUpResponse> loginCall = apiService.wsTopUP("Bearer "
                + SharedPreferenceUtils.getLoginObject(
                ContactInfoActivity.this).getData().getAccess_token());

        loginCall.enqueue(new Callback<UserTopUpResponse>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<UserTopUpResponse> call,
                                   Response<UserTopUpResponse> response) {
                pd.hide();
                if (response.isSuccessful()) {
                    UserTopUpResponse userTopUpResponse = response.body();
                    if (userTopUpResponse.getCode() == Constants.RESPONSE_ERRORS &&
                            userTopUpResponse.getStatus().equals("Not Found")) {

                        mbtnSubmit.setVisibility(View.VISIBLE);
                        doEditNow();
                    } else {
                        mbtnSubmit.setVisibility(View.VISIBLE);
                        doEditNow();
                    }
                }
            }

            @Override
            public void onFailure(Call<UserTopUpResponse> call,
                                  Throwable t) {
                pd.hide();
                Toast.makeText(ContactInfoActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void doEditNow() {
        mTextpostalAddress.setEnabled(true);
        mTextPincode.setEnabled(true);
    }

}
