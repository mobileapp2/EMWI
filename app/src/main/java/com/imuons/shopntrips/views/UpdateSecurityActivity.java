package com.imuons.shopntrips.views;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.imuons.shopntrips.R;
import com.imuons.shopntrips.model.ChangePasswordResponseModel;
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

public class UpdateSecurityActivity extends AppCompatActivity {


    @BindView(R.id.old_pass)
    EditText mEditOldPassword;
    @BindView(R.id.new_trs_pass)
    EditText mEditNewPassword;
    @BindView(R.id.retype_pass)
    EditText mEditConfirmPassword;
    @BindView(R.id.updatetrans)
    Button updatetrans;
    String oldpass, newtranspass, retypepass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_security);
        ButterKnife.bind(this);

        updatetrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateOldPassword() && validateNewPassword() && validateConfirmPassword()) {
                    changePassword();
                }
            }
        });
    }


    private void changePassword() {
        Map<String, String> passwordmap = new HashMap<>();
        final String oldpass, newpass, compass;


        oldpass = mEditOldPassword.getText().toString().trim();
        newpass = mEditNewPassword.getText().toString().trim();
        compass = mEditConfirmPassword.getText().toString().trim();


        passwordmap.put("old_password", oldpass);
        passwordmap.put("new_password", newpass);
        passwordmap.put("retype_password", compass);


        Emwi apiService = ApiHandler.getApiService();
        final Call<ChangePasswordResponseModel> loginCall = apiService.wsChangePassword("Bearer " + SharedPreferenceUtils.getAccesstoken(UpdateSecurityActivity.this), passwordmap);
        loginCall.enqueue(new Callback<ChangePasswordResponseModel>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<ChangePasswordResponseModel> call,
                                   Response<ChangePasswordResponseModel> response) {
                if (response.isSuccessful()) {
                    ChangePasswordResponseModel changePasswordResponseModel = response.body();
                    if (changePasswordResponseModel.getCode() == Constants.RESPONSE_CODE_OK &&
                            changePasswordResponseModel.getStatus().equals("OK")) {
                        mEditOldPassword.setText("");
                        mEditNewPassword.setText("");
                        mEditConfirmPassword.setText("");
                        Toast.makeText(UpdateSecurityActivity.this, "Password Successfully Updated ", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(UpdateSecurityActivity.this, changePasswordResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(UpdateSecurityActivity.this, "Check username or password", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<ChangePasswordResponseModel> call,
                                  Throwable t) {


                Toast.makeText(UpdateSecurityActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private boolean validateConfirmPassword() {
        String password = mEditNewPassword.getText().toString().trim();
        String confirmPassword = mEditConfirmPassword.getText().toString().trim();
        if (!confirmPassword.equals(password)) {
            Toast.makeText(UpdateSecurityActivity.this, getString(R.string.invalid_confirm_password_message), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean validateOldPassword() {
        String oldPassword = mEditOldPassword.getText().toString().trim();
        if (oldPassword.isEmpty()) {
            Toast.makeText(UpdateSecurityActivity.this, getString(R.string.empty_password), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean validateNewPassword() {
        String password = mEditNewPassword.getText().toString().trim();
        if (password.isEmpty()) {
            Toast.makeText(UpdateSecurityActivity.this, getString(R.string.invalid_new_password_message), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
