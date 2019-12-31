package com.imuons.shopntrips.views;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.imuons.shopntrips.R;
import com.imuons.shopntrips.model.UpdateProfileResponseModel;
import com.imuons.shopntrips.model.UserProfileResponseModel;
import com.imuons.shopntrips.model.UserTopUpResponse;
import com.imuons.shopntrips.retrofit.ApiHandler;
import com.imuons.shopntrips.retrofit.Emwi;
import com.imuons.shopntrips.utils.Constants;
import com.imuons.shopntrips.utils.SharedPreferenceUtils;
import com.imuons.shopntrips.utils.ViewUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AboutActivity extends AppCompatActivity {
    private UserProfileResponseModel model;
    @BindView(R.id.text_user_name)
    TextView mTextUserName;
    @BindView(R.id.text_email_id)
    TextView mTextEmailIds;
    @BindView(R.id.text_mobile_number)
    TextView mTextMobileNumber;
    @BindView(R.id.btn_Edit)
    Button mbtnUpdate;
    @BindView(R.id.txt_About)
    EditText mEdtAbout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);

        getTopUp();
        Gson gS = new Gson();
        String target = getIntent().getStringExtra("object");
        model = gS.fromJson(target, UserProfileResponseModel.class);
        if (model != null && model.getData().getUserId() != null) {
            displayData(model);
        } else {
            Toast.makeText(AboutActivity.this, "No Data found!", Toast.LENGTH_SHORT).show();
        }
        mbtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateAbout();
            }
        });
    }

    private void getTopUp() {
        mbtnUpdate.setVisibility(View.GONE);
        final ProgressDialog pd = ViewUtils.getProgressBar(AboutActivity.this, "Loading...", "Please wait..!");

        Emwi apiService = ApiHandler.getApiService();

        final Call<UserTopUpResponse> loginCall = apiService.wsTopUP("Bearer "
                + SharedPreferenceUtils.getLoginObject(
                AboutActivity.this).getData().getAccess_token());

        loginCall.enqueue(new Callback<UserTopUpResponse>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<UserTopUpResponse> call,
                                   Response<UserTopUpResponse> response) {
                pd.hide();
                if (response.isSuccessful()) {
                    UserTopUpResponse userTopUpResponse = response.body();
                    if (userTopUpResponse.getCode() == 404 &&
                            userTopUpResponse.getStatus().equals("Not Found")) {
                        mbtnUpdate.setVisibility(View.VISIBLE);
                    }else {
                        mbtnUpdate.setVisibility(View.GONE);
                        Toast.makeText(AboutActivity.this, "You Cannot Update", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<UserTopUpResponse> call,
                                  Throwable t) {
                pd.hide();
                Toast.makeText(AboutActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void UpdateAbout() {
        final ProgressDialog pd = ViewUtils.getProgressBar(AboutActivity.this, "Loading...", "Please wait..!");
        String name = mEdtAbout.getText().toString();
        Map<String, String> roiMap = new HashMap<>();
        roiMap.put("about_us", name);
        Emwi apiService = ApiHandler.getApiService();

        final Call<UpdateProfileResponseModel> loginCall = apiService.wsUpdateProfile("Bearer "
                + SharedPreferenceUtils.getLoginObject(
                AboutActivity.this).getData().getAccess_token(), roiMap);

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
                        Toast.makeText(AboutActivity.this, "Information Updated", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AboutActivity.this, DashboardActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(AboutActivity.this, profileResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<UpdateProfileResponseModel> call,
                                  Throwable t) {
                pd.hide();
                Toast.makeText(AboutActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayData(UserProfileResponseModel data) {
        mTextUserName.setText(data.getData().getFullname());
        mTextEmailIds.setText(data.getData().getEmail());
        mTextMobileNumber.setText(data.getData().getMobile());
        mEdtAbout.setText((CharSequence) data.getData().getAboutUs());
    }
}
