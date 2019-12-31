package com.imuons.shopntrips.views;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListPopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.imuons.shopntrips.R;
import com.imuons.shopntrips.model.GetCityDatumModel;
import com.imuons.shopntrips.model.GetCityResponseModel;
import com.imuons.shopntrips.model.GetStateDatumModel;
import com.imuons.shopntrips.model.GetStateResponseModel;
import com.imuons.shopntrips.model.RegisterResponseModel;
import com.imuons.shopntrips.retrofit.ApiHandler;
import com.imuons.shopntrips.retrofit.Emwi;
import com.imuons.shopntrips.utils.Constants;
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

public class SignUp extends AppCompatActivity {

    @BindView(R.id.edit_name)
    EditText edit_name;
    @BindView(R.id.edit_mobile)
    EditText edit_mobile;
    @BindView(R.id.edit_email)
    EditText edit_email;
    @BindView(R.id.edit_state)
    EditText edit_state;
    @BindView(R.id.edit_city)
    EditText edit_city;
    @BindView(R.id.edit_address)
    EditText edit_address;
    @BindView(R.id.edit_pincode)
    EditText edit_pincode;
    @BindView(R.id.submit)
    Button submit;
    @BindView(R.id.login)
    TextView login;
    @BindView(R.id.checkterm)
    CheckBox checkterm;
    ListPopupWindow statelistPopupWindow,citylistPopupWindow;
    String  selectedradio,sponsorname,sponsorid,userid,password,cpassword,email,mobile,fullName,
            strstate,straddress,strcity,strpincode;


    List<String> listStateName = new ArrayList<>();
    private List<GetStateDatumModel> statelist = new ArrayList<>();

    List<String> listCityName = new ArrayList<>();
    private List<GetCityDatumModel> citylist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        getStatebycountry();
        selectedradio = getIntent().getStringExtra("selectedradio");
        sponsorname = getIntent().getStringExtra("sponsorname");
        sponsorid = getIntent().getStringExtra("sponsorid");
        userid = getIntent().getStringExtra("userid");
        password = getIntent().getStringExtra("password");
        cpassword = getIntent().getStringExtra("cpassword");

        statelistPopupWindow = new ListPopupWindow(SignUp.this);
        citylistPopupWindow = new ListPopupWindow(SignUp.this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateEmail() && validateMobileNo() && validateFullName() && validateCity() && validateState() && validatePincode() && validateAddress()) {


                  register();
                }

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
                        SignUp.this,
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
                        SignUp.this,
                        R.layout.check_list_item, listCityName));
                citylistPopupWindow.setAnchorView(edit_city);
                citylistPopupWindow.setWidth(800);
                citylistPopupWindow.setHeight(800);
                citylistPopupWindow.setModal(true);
                citylistPopupWindow.show();
            }
        });
    }

    private void getcitybystate(String strstate) {
        final ProgressDialog pd = ViewUtils.getProgressBar(SignUp.this, "Loading...", "Please wait..!");

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
                        Toast.makeText(SignUp.this, getCityResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetCityResponseModel> call,
                                  Throwable t) {
                pd.hide();
                Toast.makeText(SignUp.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }

        private void getcitybyname(List<GetCityDatumModel> data) {

            for (int i = 0; i < data.size(); i++) {
                listCityName.add(data.get(i).getName());
            }

        }

    private void register() {

        Map<String, String> registerMap = new HashMap<>();
        registerMap.put("confirm_password", cpassword);
        registerMap.put("email", email);
        registerMap.put("fullname", fullName);
        registerMap.put("mobile", mobile);
        registerMap.put("password", password);
        registerMap.put("ref_user_id", sponsorid);
        registerMap.put("sponsor_name", sponsorname);
        registerMap.put("user_id", userid);
        registerMap.put("position", selectedradio);
        registerMap.put("country","IN");
        registerMap.put("state",strstate);
        registerMap.put("address",straddress);
        registerMap.put("city",strcity);
        registerMap.put("pincode",strpincode);



        Emwi apiService = ApiHandler.getApiService();
        final Call<RegisterResponseModel> registerCall = apiService.wsRegister(registerMap);
        registerCall.enqueue(new Callback<RegisterResponseModel>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<RegisterResponseModel> call,
                                   Response<RegisterResponseModel> response) {

                if (response.isSuccessful()) {
                    RegisterResponseModel registerResponseModel = response.body();
                    if (registerResponseModel.getCode() == Constants.RESPONSE_CODE_OK &&
                            registerResponseModel.getStatus().equals("OK")) {
                        String useridtosend = String.valueOf(registerResponseModel.getData().getUserid());
                        String userpassword = registerResponseModel.getData().getPassword();
                        Intent showpassword = new  Intent (SignUp.this,ShowPassword.class);
                        showpassword.putExtra("userid", useridtosend);
                        showpassword.putExtra("password", userpassword);
                        startActivity(showpassword);


                    } else {
                        Toast.makeText(SignUp.this, registerResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(SignUp.this, "Check username or password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponseModel> call,
                                  Throwable t) {

                Toast.makeText(SignUp.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private boolean validateEmail() {
        email = edit_email.getText().toString().trim();
        if (email.isEmpty() || !email.matches(Constants.EMAIL_REGEX)) {
            Toast.makeText(SignUp.this,
                    getString(R.string.invalid_email_message), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {

    }



    private boolean validateFullName() {
        fullName =  edit_name.getText().toString().trim();
        if (fullName.isEmpty()) {
            Toast.makeText(SignUp.this,
                    getString(R.string.invalid_full_name_message), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void getStatebycountry() {
        final ProgressDialog pd = ViewUtils.getProgressBar(SignUp.this, "Loading...", "Please wait..!");

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
                        Toast.makeText(SignUp.this, getStateResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetStateResponseModel> call,
                                  Throwable t) {
                pd.hide();
                Toast.makeText(SignUp.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getstatebyname(List<GetStateDatumModel> data) {

        for (int i = 0; i < data.size(); i++) {
            listStateName.add(data.get(i).getName());
        }
    }
    private boolean validateMobileNo() {
        mobile = edit_mobile.getText().toString().trim();
        if (mobile.isEmpty() || mobile.length() < 10) {
            Toast.makeText(SignUp.this,
                    getString(R.string.invalid_mobile_number_message), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private boolean validatePincode() {
        strpincode = edit_pincode.getText().toString().trim();
        if (strpincode.isEmpty() || strpincode.length() < 6) {
            Toast.makeText(SignUp.this,
                    "Enter Pincode", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean validateCity() {
        strcity = edit_city.getText().toString().trim();
        if (strcity.isEmpty() || strcity.equals("Select City")) {
            Toast.makeText(SignUp.this,
                    "Enter City", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean validateState() {
        if (strstate.isEmpty() || strcity.equals("Select State")) {
            Toast.makeText(SignUp.this,
                    "Select State", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private boolean validateAddress() {
        straddress = edit_address.getText().toString().trim();
        if (straddress.isEmpty()) {
            Toast.makeText(SignUp.this,
                    "Enter Address", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
