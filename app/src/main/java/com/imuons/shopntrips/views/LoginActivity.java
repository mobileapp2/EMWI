package com.imuons.shopntrips.views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.imuons.shopntrips.R;
import com.imuons.shopntrips.model.LoginResponseModel;
import com.imuons.shopntrips.retrofit.ApiHandler;
import com.imuons.shopntrips.retrofit.Emwi;
import com.imuons.shopntrips.utils.Constants;
import com.imuons.shopntrips.utils.SharedPreferenceUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {


    @BindView(R.id.mEditUserName)
    EditText mEditUserName;
    @BindView(R.id.mEditPassword)
    EditText mEditPassword;
    @BindView(R.id.mTextForgotPassword)
    TextView mTextForgotPassword;
    @BindView(R.id.btn_RegisterNow)
    TextView registerhere;
    @BindView(R.id.mButtonLogin)
    TextView mButtonLogin;

    @BindView(R.id.loader_view)
    View loaderView;

//    @BindView(R.id.checkbox)
//    CheckBox saveLoginCheckBox;

    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();
        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true) {
            mEditUserName.setText(loginPreferences.getString("username", ""));
            mEditPassword.setText(loginPreferences.getString("password", ""));
          //  saveLoginCheckBox.setChecked(true);
        }
        registerhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent si = new Intent(LoginActivity.this, Registration.class);
                startActivity(si);
            }
        });

        mTextForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent forgotpass = new Intent(LoginActivity.this, ResetPassword.class);
                startActivity(forgotpass);
            }
        });
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateUserName() && validatePassword()) {
                    login();
                }
            }
        });
    }

    private void login() {

        loaderView.setVisibility(View.VISIBLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        Map<String, String> loginMap = new HashMap<>();
        final String userName, password;
        userName = mEditUserName.getText().toString().trim();
        password = mEditPassword.getText().toString().trim();

//        if (saveLoginCheckBox.isChecked()) {
//            loginPrefsEditor.putBoolean("saveLogin", true);
//            loginPrefsEditor.putString("username", userName);
//            loginPrefsEditor.putString("password", password);
//            loginPrefsEditor.commit();
//        } else {
//            loginPrefsEditor.clear();
//            loginPrefsEditor.commit();
//        }

        loginMap.put("user_id", userName);
        loginMap.put("password", password);

        Emwi apiService = ApiHandler.getApiService();
        final Call<LoginResponseModel> loginCall = apiService.wsLogin(loginMap);
        loginCall.enqueue(new Callback<LoginResponseModel>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<LoginResponseModel> call,
                                   Response<LoginResponseModel> response) {

                if (response.isSuccessful()) {
                    loaderView.setVisibility(View.GONE);
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                    LoginResponseModel loginModel = response.body();
                    if (loginModel.getCode() == Constants.RESPONSE_CODE_OK &&
                            loginModel.getStatus().equals("OK")) {
                        SharedPreferenceUtils.storeLoginObject(loginModel, LoginActivity.this);
                        SharedPreferenceUtils.storeUserName(LoginActivity.this, userName);
                        SharedPreferenceUtils.storePassword(LoginActivity.this, password);
                        SharedPreferenceUtils.storeAccessToken(LoginActivity.this, loginModel.getData().getAccess_token());
                        startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, loginModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponseModel> call,
                                  Throwable t) {
                loaderView.setVisibility(View.GONE);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                Toast.makeText(LoginActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private boolean validatePassword() {
        String password = mEditPassword.getText().toString();
        if (password.isEmpty()) {
            Toast.makeText(LoginActivity.this, getString(R.string.invalid_password_message),
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean validateUserName() {
        String userName = mEditUserName.getText().toString();
        if (userName.isEmpty()) {
            Toast.makeText(LoginActivity.this, getString(R.string.invalid_username_message),
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
