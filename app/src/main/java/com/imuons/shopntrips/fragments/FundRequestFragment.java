package com.imuons.shopntrips.fragments;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.imuons.shopntrips.R;
import com.imuons.shopntrips.model.FundRequestResponseModel;
import com.imuons.shopntrips.model.GetBalanceReponseModel;
import com.imuons.shopntrips.model.UserProfileDataModel;
import com.imuons.shopntrips.model.UserProfileResponseModel;
import com.imuons.shopntrips.retrofit.ApiHandler;
import com.imuons.shopntrips.retrofit.ShopNTrips;
import com.imuons.shopntrips.utils.Constants;
import com.imuons.shopntrips.utils.SharedPreferenceUtils;
import com.imuons.shopntrips.utils.Utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MultipartBody;
import pl.droidsonroids.gif.GifImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static android.widget.Toast.LENGTH_SHORT;

/**
 * A simple {@link Fragment} subclass.
 */
public class FundRequestFragment extends Fragment {
    @BindView(R.id.topupbal)
    EditText topupbal;
    @BindView(R.id.userid)
    EditText userid;
    @BindView(R.id.fullname)
    EditText fullname;
    @BindView(R.id.amount)
    EditText amount;
    @BindView(R.id.submit)
    Button submit;
    static final Integer READ_EXST = 0x4;
    String m_selectedPath,amountenterd,filenamestr;
    private static final int SELECT_PICTURE = 100;

    private static final String TAG = "SelectImageActivity";
    @BindView(R.id.choosefiles)
    ImageView choosefiles;
    @BindView(R.id.filename)
    TextView filename;
    private FragmentManager fragmentManager;
    @BindView(R.id.gif)
    GifImageView gif;
    private UserProfileResponseModel profileModel;
    public FundRequestFragment() {
        // Required empty public constructor
    }

    public static FundRequestFragment newInstance() {
        FundRequestFragment fragment = new FundRequestFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fund_request, container, false);
        ButterKnife.bind(this, view);

        fragmentManager = getFragmentManager();
        //handlePermission();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            askForPermission(Manifest.permission.READ_EXTERNAL_STORAGE,READ_EXST);
        }
        choosefiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    askForPermission(Manifest.permission.READ_EXTERNAL_STORAGE,READ_EXST);
                }
                openImageChooser();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateAmount() && validateFileName()) {  //
                    callsubmit();
                }


            }
        });
        return view;
    }

    private void callsubmit() {
        gif.setVisibility(View.VISIBLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        int at = Integer.parseInt(amountenterd);
        //   RequestBody amount = RequestBody.create(MediaType.parse("text/plain"),at);
        File file = new File(m_selectedPath);
        long length = file.length() / 1024;
        if (length < 1000) {

            okhttp3.RequestBody requestFile = okhttp3.RequestBody.create(okhttp3.MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part body = null;
            body = MultipartBody.Part.createFormData("payment", file.getName().replace(" ", "_"), requestFile);
            HashMap<String, Integer> map = new HashMap<>();
            // map.put("file",body);
            map.put("amount", at);
            ShopNTrips apiService = ApiHandler.getApiService();
            Call<FundRequestResponseModel> call = apiService.wsMakeFundRequest("Bearer " + SharedPreferenceUtils.getAccesstoken(FundRequestFragment.this.getContext()), map, body);
            call.enqueue(new Callback<FundRequestResponseModel>() {
                @Override
                public void onResponse(Call<FundRequestResponseModel> call, Response<FundRequestResponseModel> response) {
//                pd.hide();
                    gif.setVisibility(View.GONE);
                    getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    if (response.isSuccessful()) {
                        // tasks available
                        FundRequestResponseModel fundRequestResponseModel = response.body();
                        if (fundRequestResponseModel.getCode() == Constants.RESPONSE_CODE_OK) {
                            Toast.makeText(FundRequestFragment.this.getContext(),
                                    fundRequestResponseModel.getMessage(), Toast.LENGTH_LONG).show();
                            fragmentManager.beginTransaction().replace(R.id.content_frame, FundRequestReportFragment.newInstance()).commit();
                            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Fund Request Report");
                        } else {
                            //  Log.v("Error code 400",response.errorBody().toString());
                            Toast.makeText(FundRequestFragment.this.getContext(),
                                    fundRequestResponseModel.getMessage(), LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(FundRequestFragment.this.getContext(),
                                "not uploading", LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<FundRequestResponseModel> call, Throwable t) {
                    //                pd.hide();
                    gif.setVisibility(View.GONE);
                    getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    // something went completely south (like no internet connection)
                    Toast.makeText(FundRequestFragment.this.getContext(),
                            t.toString(), LENGTH_SHORT).show();
                }
            });
            gif.setVisibility(View.GONE);
        } else {
            Toast.makeText(FundRequestFragment.this.getContext(), "Image Size Should Not Greater Than 1MB", LENGTH_SHORT).show();
        }
    }
    private void getTopUpBal() {
        gif.setVisibility(View.VISIBLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        Map<String, String> topmap = new HashMap<>();

        topmap.put("wallet", "topup");
        ShopNTrips apiService = ApiHandler.getApiService();
        final Call<GetBalanceReponseModel> loginCall = apiService.wsTopUpBalance(
                "Bearer " + SharedPreferenceUtils.getAccesstoken(FundRequestFragment.this.getContext()),topmap);
        loginCall.enqueue(new Callback<GetBalanceReponseModel>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<GetBalanceReponseModel> call,
                                   Response<GetBalanceReponseModel> response) {
//                pd.hide();
                gif.setVisibility(View.GONE);
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                if (response.isSuccessful()) {
                    GetBalanceReponseModel getBalanceReponseModel = response.body();
                    if (getBalanceReponseModel.getCode() == Constants.RESPONSE_CODE_OK &&
                            getBalanceReponseModel.getStatus().equals("OK")) {
                        int avabal = getBalanceReponseModel.getData().getTopup();
                        topupbal.setText(String.valueOf(avabal));

                    } else {
                        Toast.makeText(FundRequestFragment.this.getContext(), getBalanceReponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetBalanceReponseModel> call,
                                  Throwable t) {
//                pd.hide();
                gif.setVisibility(View.GONE);
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                Toast.makeText(FundRequestFragment.this.getContext(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Utils.checkInternetConnection(FundRequestFragment.this.getContext())) {

            getProfile();
            getTopUpBal();


        } else {
            Toast.makeText(FundRequestFragment.this.getContext(),
                    getString(R.string.no_internet_connection_message), Toast.LENGTH_SHORT).show();
        }
    }

    private void getProfile() {
        ShopNTrips apiService = ApiHandler.getApiService();
        final Call<UserProfileResponseModel> loginCall = apiService.wsUserProfileInfo(
                "Bearer " + SharedPreferenceUtils.getLoginObject(
                        FundRequestFragment.this.getContext()).getData().getAccess_token());
        loginCall.enqueue(new Callback<UserProfileResponseModel>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<UserProfileResponseModel> call,
                                   Response<UserProfileResponseModel> response) {

                if (response.isSuccessful()) {
                    profileModel = response.body();
                    if (profileModel.getCode() == Constants.RESPONSE_CODE_OK &&
                            profileModel.getStatus().equals("OK")) {
                        setUserData(profileModel.getData());
                    } else {
                        Toast.makeText(FundRequestFragment.this.getContext(), profileModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<UserProfileResponseModel> call,
                                  Throwable t) {

                Toast.makeText(FundRequestFragment.this.getContext(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUserData(UserProfileDataModel data) {
        userid.setText(data.getUserId());
        fullname.setText(data.getFullname());
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void askForPermission(String permission, Integer requestCode) {
        if (getActivity().checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions( new String[]{permission}, requestCode);
        } else {
            //  Toast.makeText(MakeFundRequestFragment.this.getContext(), "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateAmount() {
        amountenterd = amount.getText().toString();
        int checkvalue = Integer.parseInt(amountenterd);
        if (amountenterd.isEmpty() || checkvalue <= 0) {
            Toast.makeText(FundRequestFragment.this.getContext(), getString(R.string.enter_amount),
                    LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private boolean validateFileName() {
        filenamestr = filename.getText().toString();
        if (filenamestr.isEmpty() || filenamestr.equals("No File Choosen")) {
            Toast.makeText(FundRequestFragment.this.getContext(), getString(R.string.upload_slip),
                    LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

















    private void openImageChooser() {
        // Create the Intent for Image Gallery.
        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start new activity with the LOAD_IMAGE_RESULTS to handle back the results when image is picked from the Image Gallery.
        startActivityForResult(i,SELECT_PICTURE); //LOAD_IMAGE_RESULTS


//        Intent choosePictureIntent = new Intent(Intent.ACTION_GET_CONTENT);
//        choosePictureIntent.setType("image/*");
//        startActivityForResult(choosePictureIntent, SELECT_PICTURE);


    }
    public void onActivityResult( int requestCode,  int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SELECT_PICTURE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            // Get the url from data
            Uri selectedImageUri = data.getData();
            if (null != selectedImageUri) {
                // Get the path from the Uri
                String path = getPathFromURI(selectedImageUri);
                m_selectedPath = path;

                Log.i(TAG, "Image Path : " + path);
                filenamestr = path.substring(path.lastIndexOf("/")+1);
                filename.setText(filenamestr);

            }
        }else{
            Toast.makeText(FundRequestFragment.this.getContext(), "Select Slip",
                    LENGTH_SHORT).show();
        }
    }






    /* Get the real path from the URI */
    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().getApplicationContext().getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }
    private void handlePermission() {
        ActivityCompat.requestPermissions((Activity) FundRequestFragment.this.getContext(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, SELECT_PICTURE);
        if (ContextCompat.checkSelfPermission(FundRequestFragment.this.getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {


        } else {
            Toast.makeText(FundRequestFragment.this.getContext(), "Permission Required",
                    LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case SELECT_PICTURE:
                for (int i = 0; i < permissions.length; i++) {
                    String permission = permissions[i];
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        boolean showRationale = ActivityCompat.shouldShowRequestPermissionRationale((Activity) FundRequestFragment.this.getContext(), permission);
                        if (showRationale) {
                            //  Show your own message here

                            Toast.makeText(FundRequestFragment.this.getContext(),"Permission Required",
                                    Toast.LENGTH_SHORT).show();

                        } else {
                            // showSettingsAlert();
                        }
                    }
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


}
