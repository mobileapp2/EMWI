package com.imuons.shopntrips.views;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.imuons.shopntrips.R;
import com.imuons.shopntrips.model.UpdateProfileResponseModel;
import com.imuons.shopntrips.model.UserPhotosDataModel;
import com.imuons.shopntrips.model.UserPhotosResponseModel;
import com.imuons.shopntrips.model.UserProfileResponseModel;
import com.imuons.shopntrips.model.UserTopUpResponse;
import com.imuons.shopntrips.retrofit.ApiHandler;
import com.imuons.shopntrips.retrofit.ShopNTrips;
import com.imuons.shopntrips.utils.Constants;
import com.imuons.shopntrips.utils.SharedPreferenceUtils;
import com.imuons.shopntrips.utils.ViewUtils;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileInfoActivity extends AppCompatActivity {

    private UserProfileResponseModel model;

    @BindView(R.id.txt_userId)
    EditText mTextUserID;

    @BindView(R.id.txt_mobileNo)
    EditText mTextMobileNumbers;

    @BindView(R.id.txt_sponsorId)
    EditText mTextSponsorID;

    @BindView(R.id.txt_Email)
    EditText mTextEmail;

    @BindView(R.id.txt_name)
    EditText mTextName;

    @BindView(R.id.text_user_name)
    TextView mTextUserName;
    @BindView(R.id.txt_DateOfJoining)
    TextView mTextDateOfJoining;
    @BindView(R.id.text_email_id)
    TextView mTextEmailIds;
    @BindView(R.id.text_mobile_number)
    TextView mTextMobileNumber;
    @BindView(R.id.image_profile)
    ImageView mImageUser;

    @BindView(R.id.btn_Edit)
    Button mbtnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_info);
        ButterKnife.bind(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.profile);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        Gson gS = new Gson();
        String target = getIntent().getStringExtra("object");
        model = gS.fromJson(target, UserProfileResponseModel.class);
        if (model != null && model.getData().getUserId() != null) {
            displayData(model);
        } else {
            Toast.makeText(ProfileInfoActivity.this, "No Data found!", Toast.LENGTH_SHORT).show();
        }
        getTopUp();
        getUserPhotos();

        mbtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateMobileNo()){
                    UpdateProfile();
                }

            }
        });
    }
    private boolean validateMobileNo() {
        String mobile = mTextMobileNumbers.getText().toString().trim();
        if (mobile.isEmpty() || mobile.length() < 10) {
            Toast.makeText(ProfileInfoActivity.this,
                    getString(R.string.invalid_mobile_number_message), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void UpdateProfile() {
        final ProgressDialog pd = ViewUtils.getProgressBar(ProfileInfoActivity.this, "Loading...", "Please wait..!");
        String name = mTextName.getText().toString();
        String email = mTextEmail.getText().toString();
        String mobile = mTextMobileNumbers.getText().toString();

        Map<String, String> roiMap = new HashMap<>();
        roiMap.put("fullname", name);
        roiMap.put("mobile", mobile);
        roiMap.put("email", email);


        ShopNTrips apiService = ApiHandler.getApiService();

        final Call<UpdateProfileResponseModel> loginCall = apiService.wsUpdateProfile("Bearer "
                + SharedPreferenceUtils.getLoginObject(
                ProfileInfoActivity.this).getData().getAccess_token(), roiMap);

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
                        Toast.makeText(ProfileInfoActivity.this, "Information Updated", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ProfileInfoActivity.this, DashboardActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(ProfileInfoActivity.this, profileResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<UpdateProfileResponseModel> call,
                                  Throwable t) {
                pd.hide();
                Toast.makeText(ProfileInfoActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getTopUp() {
        final ProgressDialog pd = ViewUtils.getProgressBar(ProfileInfoActivity.this, "Loading...", "Please wait..!");
        Map<String, String> roiMap = new HashMap<>();

        roiMap.put("start", String.valueOf(0));

        ShopNTrips apiService = ApiHandler.getApiService();

        final Call<UserTopUpResponse> loginCall = apiService.wsTopUP("Bearer "
                + SharedPreferenceUtils.getLoginObject(
                ProfileInfoActivity.this).getData().getAccess_token());

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
                    } else {
                        mbtnSubmit.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<UserTopUpResponse> call,
                                  Throwable t) {
                pd.hide();
                Toast.makeText(ProfileInfoActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void getUserPhotos() {
        final ProgressDialog pd = ViewUtils.getProgressBar(ProfileInfoActivity.this, "Loading...", "Please wait..!");

        ShopNTrips apiService = ApiHandler.getApiService();
        final Call<UserPhotosResponseModel> loginCall = apiService.wsUserPhotos(
                "Bearer " + SharedPreferenceUtils.getLoginObject(
                        ProfileInfoActivity.this).getData().getAccess_token());
        loginCall.enqueue(new Callback<UserPhotosResponseModel>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<UserPhotosResponseModel> call,
                                   Response<UserPhotosResponseModel> response) {
                pd.hide();
                if (response.isSuccessful()) {
                    UserPhotosResponseModel loginModel = response.body();
                    if (loginModel.getCode() == Constants.RESPONSE_CODE_OK &&
                            loginModel.getStatus().equals("OK")) {
                        setUserDataPhoto(loginModel.getData());
                    } else {
                        Toast.makeText(ProfileInfoActivity.this, loginModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<UserPhotosResponseModel> call,
                                  Throwable t) {
                pd.hide();
                Toast.makeText(ProfileInfoActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUserDataPhoto(UserPhotosDataModel data) {
        if (data.getPhoto() != null) {
            Picasso.get().load(String.valueOf(data.getPhoto())).into(mImageUser);
        }
    }

    private void displayData(UserProfileResponseModel data) {
        mTextName.setText(data.getData().getFullname());
        mTextEmail.setText(data.getData().getEmail());
        mTextMobileNumbers.setText(data.getData().getMobile());
        mTextSponsorID.setText(data.getData().getSponserId());
        mTextUserID.setText(data.getData().getUserId());
        mTextUserName.setText(data.getData().getFullname());
        mTextEmailIds.setText(data.getData().getEmail());
        mTextMobileNumber.setText(data.getData().getMobile());
        mTextDateOfJoining.setText(data.getData().getEntryTime());
    }

}
