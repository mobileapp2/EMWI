package com.imuons.shopntrips.views;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Spinner;
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
import com.imuons.shopntrips.retrofit.Emwi;
import com.imuons.shopntrips.utils.Constants;
import com.imuons.shopntrips.utils.SharedPreferenceUtils;
import com.imuons.shopntrips.utils.ViewUtils;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private UserProfileResponseModel model;

    @BindView(R.id.txt_userId)
    EditText mTextUserID;

    @BindView(R.id.txt_mobileNo)
    EditText mTextMobileNumbers;

    @BindView(R.id.txt_sponsorId)
    EditText mTextSponsorID;

    @BindView(R.id.txt_Email)
    EditText mTextEmail;
    @BindView(R.id.txt_NomineeName)
    EditText mNomineeName;
    @BindView(R.id.txt_DOB)
    EditText mTextDOB;

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

    @BindView(R.id.txt_NomineeRelation)
    Spinner mNomineeRelation;
    @BindView(R.id.nomineerelation)
    TextView nomineerelation;

    @BindView(R.id.btn_Edit)
    Button mbtnSubmit;

    String myString = "Select Nominee Relation";
    
    int sp_position;
    String selected, spinner_item, strnomineeselect;

    private DatePickerDialog fromDatePickerDialog;
    private SimpleDateFormat dateFormatter;

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

        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        mTextDOB.setInputType(InputType.TYPE_NULL);
        mTextDOB.requestFocus();

        getTopUp();
        getUserPhotos();
        setDateTimeField();
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter.createFromResource(this, R.array.brew_array,
                android.R.layout.simple_spinner_item);

        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_position = staticAdapter.getPosition(myString);
        mNomineeRelation.setAdapter(staticAdapter);


        mNomineeRelation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
                selected = mNomineeRelation.getSelectedItem().toString();
                if (!selected.equals("Select Nominee Relation"))
                    spinner_item = selected;
                System.out.println(selected);
                setid();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
        mbtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateMobileNo()) {
                    UpdateProfile();
                }
            }
        });
        nomineerelation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(ProfileInfoActivity.this, nomineerelation);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        strnomineeselect = String.valueOf(item.getTitle());
                        nomineerelation.setText(strnomineeselect);
                        return true;
                    }
                });

                popup.show();//showing popup menu

            }
        });

    }

    private void setDateTimeField() {
        mTextDOB.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                mTextDOB.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void setid() {
        mNomineeRelation.setSelection(sp_position);
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
        String nominee_name = mNomineeName.getText().toString();
        String relation = mNomineeRelation.getSelectedItem().toString();
        String dob = mTextDOB.getText().toString();
        Map<String, String> roiMap = new HashMap<>();
        roiMap.put("fullname", name);
        roiMap.put("mobile", mobile);
        roiMap.put("nominee_name", nominee_name);
        roiMap.put("relation", strnomineeselect);
        roiMap.put("email", email);
        roiMap.put("dob", dob);

        Emwi apiService = ApiHandler.getApiService();

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

        Emwi apiService = ApiHandler.getApiService();

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
                            userTopUpResponse.getStatus().equals("Purchase")) {

                        mbtnSubmit.setVisibility(View.VISIBLE);
                    } else {
                        mbtnSubmit.setVisibility(View.VISIBLE);
                        mTextUserID.setFocusable(false);
                        mTextUserID.setFocusableInTouchMode(false);
                        mTextUserID.setClickable(false);

                        mTextSponsorID.setClickable(false);
                        mTextSponsorID.setFocusable(false);
                        mTextSponsorID.setFocusableInTouchMode(false);

                      /*  mTextName.setClickable(false);
                        mTextName.setFocusable(false);
                        mTextName.setFocusableInTouchMode(false);
*/
                        mTextMobileNumbers.setClickable(false);
                        mTextMobileNumbers.setFocusable(false);
                        mTextMobileNumbers.setFocusableInTouchMode(false);

                     /*   mTextEmail.setClickable(false);
                        mTextEmail.setFocusable(false);
                        mTextEmail.setFocusableInTouchMode(false);*/

                        mTextDateOfJoining.setClickable(false);
                        mTextDateOfJoining.setFocusable(false);
                        mTextDateOfJoining.setFocusableInTouchMode(false);

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

        Emwi apiService = ApiHandler.getApiService();
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
        nomineerelation.setText(data.getData().getRelation());
        mNomineeName.setText(data.getData().getNomineeName());
        mTextDOB.setText(data.getData().getDob());
    }


    @Override
    public void onClick(View view) {
        if(view == mTextDOB) {
            fromDatePickerDialog.show();
        }
    }
}
