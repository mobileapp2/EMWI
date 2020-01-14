package com.imuons.shopntrips.fragments;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.imuons.shopntrips.R;
import com.imuons.shopntrips.model.UserPhotosDataModel;
import com.imuons.shopntrips.model.UserPhotosResponseModel;
import com.imuons.shopntrips.model.UserProfileDataModel;
import com.imuons.shopntrips.model.UserProfileResponseModel;
import com.imuons.shopntrips.model.UserTopUpResponse;
import com.imuons.shopntrips.retrofit.ApiHandler;
import com.imuons.shopntrips.retrofit.Emwi;
import com.imuons.shopntrips.utils.Constants;
import com.imuons.shopntrips.utils.SharedPreferenceUtils;
import com.imuons.shopntrips.utils.Utils;
import com.imuons.shopntrips.utils.ViewUtils;
import com.imuons.shopntrips.views.AboutActivity;
import com.imuons.shopntrips.views.BankDetailsActivity;
import com.imuons.shopntrips.views.ContactInfoActivity;
import com.imuons.shopntrips.views.KYCActivity;
import com.imuons.shopntrips.views.ProfileInfoActivity;
import com.imuons.shopntrips.views.UpdateSecurityActivity;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment implements View.OnClickListener {
    private UserProfileResponseModel profileModel;
    private UserTopUpResponse profileModel2;
    @BindView(R.id.text_user_name)
    TextView mTextUserName;
    @BindView(R.id.text_email_id)
    TextView mTextEmailId;
    @BindView(R.id.text_mobile_number)
    TextView mTextMobileNumber;
    @BindView(R.id.image_profile)
    ImageView mImageUser;

    @BindView(R.id.txt_userId)
    TextView mTextUserID;

    @BindView(R.id.txt_mobileNo)
    TextView mTextMobileNumbers;

    @BindView(R.id.txt_sponsorId)
    TextView mTextSponsorID;

    @BindView(R.id.txt_Email)
    TextView mTextEmail;

    @BindView(R.id.txt_name)
    TextView mTextName;


    @BindView(R.id.btn_Information)
    ImageView mBtnInformation;
    @BindView(R.id.btn_Security)
    ImageView mBtnSecurity;

    @BindView(R.id.btn_ContactInfo)
    TextView mBtnContactInfo;
    @BindView(R.id.btn_BankDetails)
    ImageView mBtnBankDetails;
    @BindView(R.id.btn_About)
    ImageView mBtnAbout;
    @BindView(R.id.btn_Kyc)
    ImageView mBtnKyc;
    @BindView(R.id.txt_About)
    TextView mEdtAbout;
    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        registerListeners();
        getUserPhotos();
        getUserProfileInfo();
        mBtnKyc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent = new Intent(ProfileFragment.this.getContext(), KYCActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }


    private void registerListeners() {

        mBtnInformation.setOnClickListener(this);
        mBtnSecurity.setOnClickListener(this);
        mBtnBankDetails.setOnClickListener(this);
        mBtnContactInfo.setOnClickListener(this);
        mBtnAbout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        Gson gS = new Gson();

        switch (v.getId()) {
            case R.id.btn_Information:
                String object = gS.toJson(profileModel);
                intent = new Intent(ProfileFragment.this.getContext(),
                        ProfileInfoActivity.class);
                intent.putExtra("object", object);
                startActivity(intent);
                break;
            case R.id.btn_Security:
                object = gS.toJson(profileModel);
                intent = new Intent(ProfileFragment.this.getContext(),
                        UpdateSecurityActivity.class);
                intent.putExtra("object", object);
                startActivity(intent);
                break;
            case R.id.btn_BankDetails:
                object = gS.toJson(profileModel);
                intent = new Intent(ProfileFragment.this.getContext(),
                        BankDetailsActivity.class);
                intent.putExtra("object", object);
                startActivity(intent);
                break;
            case R.id.btn_ContactInfo:
                object = gS.toJson(profileModel);
                intent = new Intent(ProfileFragment.this.getContext(),
                        ContactInfoActivity.class);
                intent.putExtra("object", object);
                startActivity(intent);
                break;
            case R.id.btn_About:
                object = gS.toJson(profileModel);
                intent = new Intent(ProfileFragment.this.getContext(),
                        AboutActivity.class);
                intent.putExtra("object", object);
                startActivity(intent);
                break;

            default:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Utils.checkInternetConnection(ProfileFragment.this.getContext())) {
            getUserPhotos();
            getUserProfileInfo();
        } else {
            Toast.makeText(ProfileFragment.this.getContext(),
                    getString(R.string.no_internet_connection_message), Toast.LENGTH_SHORT).show();
        }
    }

    private void getUserPhotos() {
        final ProgressDialog pd = ViewUtils.getProgressBar(ProfileFragment.this.getContext(), "Loading...", "Please wait..!");

        Emwi apiService = ApiHandler.getApiService();
        final Call<UserPhotosResponseModel> loginCall = apiService.wsUserPhotos(
                "Bearer " + SharedPreferenceUtils.getLoginObject(
                        ProfileFragment.this.getContext()).getData().getAccess_token());
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
                        Toast.makeText(ProfileFragment.this.getContext(), loginModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<UserPhotosResponseModel> call,
                                  Throwable t) {
                pd.hide();
                Toast.makeText(ProfileFragment.this.getContext(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUserDataPhoto(UserPhotosDataModel data) {
        if (data.getPhoto() != null) {
            Picasso.get().load(String.valueOf(data.getPhoto())).into(mImageUser);
        }
    }

    private void getUserProfileInfo() {
        final ProgressDialog pd = ViewUtils.getProgressBar(ProfileFragment.this.getContext(),
                "Loading...", "Please wait..!");

        Emwi apiService = ApiHandler.getApiService();
        final Call<UserProfileResponseModel> loginCall = apiService.wsUserProfileInfo(
                "Bearer " + SharedPreferenceUtils.getLoginObject(
                        ProfileFragment.this.getContext()).getData().getAccess_token());
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
                        Toast.makeText(ProfileFragment.this.getContext(), profileModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<UserProfileResponseModel> call,
                                  Throwable t) {
                pd.hide();
                Toast.makeText(ProfileFragment.this.getContext(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUserData(UserProfileDataModel data) {

        mTextUserName.setText(data.getFullname());
        mTextEmailId.setText(data.getEmail());
        mTextMobileNumber.setText(data.getMobile());
        mTextName.setText(data.getFullname());
        mTextEmail.setText(data.getEmail());
        mTextMobileNumbers.setText(data.getMobile());
        mTextSponsorID.setText(data.getSponserId());
        mTextUserID.setText(data.getUserId());
        mEdtAbout.setText(data.getAboutUs());

    }


}