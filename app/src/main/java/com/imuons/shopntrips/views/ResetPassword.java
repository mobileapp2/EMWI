package com.imuons.shopntrips.views;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.imuons.shopntrips.R;
import com.imuons.shopntrips.model.ResetPasswordResponseModel;
import com.imuons.shopntrips.retrofit.ApiHandler;
import com.imuons.shopntrips.retrofit.Emwi;
import com.imuons.shopntrips.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPassword extends AppCompatActivity {
    @BindView(R.id.edit_userid)
    EditText edit_userid;
    @BindView(R.id.button_submit)
    Button button_submit;
    @BindView(R.id.button_login)
    Button button_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        ButterKnife.bind(this);

        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateUserName() ) {
                    resetpassword();
                }
            }
        });
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent (ResetPassword.this,LoginActivity.class);
                startActivity(login);

            }
        });
    }

    private void resetpassword() {
        final String userName;
        userName = edit_userid.getText().toString().trim();

        Emwi apiService = ApiHandler.getApiService();

        final Call<ResetPasswordResponseModel> loginCall = apiService.wsResetPassword(userName);

        loginCall.enqueue(new Callback<ResetPasswordResponseModel>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<ResetPasswordResponseModel> call,
                                   Response<ResetPasswordResponseModel> response) {

                if (response.isSuccessful()) {
                    ResetPasswordResponseModel loginModel = response.body();
                    if (loginModel.getCode() == Constants.RESPONSE_CODE_OK &&
                            loginModel.getStatus().equals("OK")) {


                        Toast.makeText(ResetPassword.this, loginModel.getMessage(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ResetPassword.this, LoginActivity.class));
                        finish();
                    } else {
                        Toast.makeText(ResetPassword.this, loginModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResetPasswordResponseModel> call,
                                  Throwable t) {

                Toast.makeText(ResetPassword.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validateUserName() {
        String userName = edit_userid.getText().toString();
        if (userName.isEmpty()) {
            Toast.makeText(ResetPassword.this, getString(R.string.invalid_username_message),
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
