package com.imuons.shopntrips.views;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.imuons.shopntrips.R;
import com.imuons.shopntrips.model.CheckUserExistResponseModel;
import com.imuons.shopntrips.retrofit.ApiHandler;
import com.imuons.shopntrips.retrofit.Emwi;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registration extends AppCompatActivity {
    @BindView(R.id.next)
    Button next;
    @BindView(R.id.edit_sponsorid)
    EditText edit_sponsorid;
    @BindView(R.id.edit_sponsorname)
    EditText edit_sponsorname;
    @BindView(R.id.edit_userid)
    EditText edit_userid;
    @BindView(R.id. edit_password)
    EditText  edit_password;
    @BindView(R.id. edit_cpassword)
    EditText edit_cpassword;
    @BindView(R.id. edit_position)
    EditText edit_position;
    @BindView(R.id.leftradio)
    RadioButton leftradio;
    @BindView(R.id.rightradio)
    RadioButton rightradio;
    @BindView(R.id.login)
    TextView login;
    @BindView(R.id.radiogroup)
    RadioGroup radiogroup;
    String  selectedradio,sponsorname,sponsorid,userid,password,cpassword;
    private boolean mSponsorAvailable = false, mUserAvailable = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);

        next = findViewById(R.id.next);
        getRandomNumber();
        if (savedInstanceState != null) {
            // Restore value of members from saved state
            edit_sponsorid.setText(savedInstanceState.getString("sponsorid"));
            edit_sponsorname.setText(savedInstanceState.getString("sponsorname"));
            edit_userid.setText(savedInstanceState.getString("userid"));
            edit_password.setText(savedInstanceState.getString("password"));
            edit_cpassword.setText(savedInstanceState.getString("cpassword"));
            edit_position.setText(savedInstanceState.getString("selectedradio"));
        } else {
            // Probably initialize members with default values for a new instance
        }
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkSponsorAvailability();
                if (validateSponsorName()  && validateSponserId() && validateUserId() && validateRadio()  && validateConfirmPassword() && validatePassword() && comfirmPassword() ){

                    Intent contact = new Intent(Registration.this,SignUp.class);
                    contact.putExtra("sponsorname",sponsorname);
                    contact.putExtra("sponsorid",sponsorid);
                    contact.putExtra("userid",userid);
                    contact.putExtra("password",password);
                    contact.putExtra("cpassword",cpassword);
                    contact.putExtra("selectedradio",selectedradio);
                    startActivity(contact);

                }

            }
        });
        edit_sponsorid.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    checkSponsorAvailability();
                    return true;
                }
                return false;
            }
        });
        edit_sponsorid.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TextView text = (TextView) getCurrentFocus();

                if (text != null && text.length() > 0) {
//                    View next = text.focusSearch(View.FOCUS_RIGHT); // or FOCUS_FORWARD
//                    if (next != null)
//                        next.requestFocus();

                    checkSponsorAvailability(); // Or whatever
                }
            }

            // afterTextChanged
            @Override
            public void afterTextChanged(Editable s) {
            }

            // beforeTextChanged
            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(Registration.this,LoginActivity.class);
                startActivity(login);
            }
        });

    }
    @Override
    public void onBackPressed() {

    }

    private void getRandomNumber() {
        Random rnd = new Random();
        int n = 100000 + rnd.nextInt(90000000);
        userid = String.valueOf(n);
        edit_userid.setText(userid);
    }

    private void checkSponsorAvailability() {


        Map<String, String> loginMap = new HashMap<>();
        final String userName;

        userName = edit_sponsorid.getText().toString().trim();

        loginMap.put("user_id", userName);

        Emwi apiService = ApiHandler.getApiService();
        final Call<CheckUserExistResponseModel> loginCall = apiService.wsCheckUser(loginMap);
        loginCall.enqueue(new Callback<CheckUserExistResponseModel>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<CheckUserExistResponseModel> call,
                                   Response<CheckUserExistResponseModel> response) {

                if (response.isSuccessful()) {
                    CheckUserExistResponseModel loginModel = response.body();
                    sponsorname = loginModel.getData().getFullname();
                    edit_sponsorname.setText(loginModel.getData().getFullname());
                    if (loginModel.getCode() == 404) {
                        mSponsorAvailable = true;

                        Toast.makeText(Registration.this, loginModel.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        mSponsorAvailable = false;
                        // Toast.makeText(Registration.this, loginModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<CheckUserExistResponseModel> call,
                                  Throwable t) {

                Toast.makeText(Registration.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putString("sponsorname",sponsorname);
        savedInstanceState.putString("sponsorid",sponsorid);
        savedInstanceState.putString("userid",userid);
        savedInstanceState.putString("password",password);
        savedInstanceState.putString("cpassword",cpassword);
        savedInstanceState.putString("selectedradio",selectedradio);
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.
        edit_sponsorid.setText(savedInstanceState.getString("sponsorid"));
        edit_sponsorname.setText(savedInstanceState.getString("sponsorname"));
        edit_userid.setText(savedInstanceState.getString("userid"));
//       edit_password.setText(savedInstanceState.getString("password"));
//       edit_confirmpassword.setText(savedInstanceState.getString("cpassword"));
        edit_position.setText(savedInstanceState.getString("selectedradio"));
    }


    private boolean validateRadio() {
        if(radiogroup.getCheckedRadioButtonId()==-1)
        {
            Toast.makeText(getApplicationContext(), "Please select Position", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(leftradio.isChecked())
        {
//            // get selected radio button from radioGroup
//            int selectedId = radiogroup.getCheckedRadioButtonId();
//            // find the radiobutton by returned id
//            leftradio = (RadioButton)findViewById(selectedId);
            selectedradio = "1";
        }else if(rightradio.isChecked()){
            selectedradio = "2";
        }
        return true;
    }


    private boolean validatePassword() {
        password  = edit_password.getText().toString().trim();
        if (password.isEmpty() && password.length() > 5) {
            Toast.makeText(Registration.this, getString(R.string.empty_password), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private boolean validateConfirmPassword() {
        password = edit_password.getText().toString().trim();
        cpassword = edit_cpassword.getText().toString().trim();
        if (!cpassword.equals(password)) {
            Toast.makeText(Registration.this, getString(R.string.invalid_confirm_password_message), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean comfirmPassword() {
        cpassword = edit_cpassword.getText().toString().trim();
        if (password.isEmpty() && cpassword.length() > 5) {
            Toast.makeText(Registration.this, getString(R.string.empty_password), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private boolean validateSponsorName() {
        sponsorname = edit_sponsorname.getText().toString().trim();
        if (sponsorname.isEmpty()) {
//            Toast.makeText(Registration.this,
//                    getString(R.string.invalid_full_name_message), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private boolean validateSponserId() {
        sponsorid= edit_sponsorid.getText().toString().trim();
        if (sponsorid.isEmpty()) {
            Toast.makeText(Registration.this, getString(R.string.empty_sponsor_id), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private boolean validateUserId() {
        userid = edit_userid.getText().toString().trim();
        if (userid.isEmpty()) {
            Toast.makeText(Registration.this, getString(R.string.empty_user_id), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}
