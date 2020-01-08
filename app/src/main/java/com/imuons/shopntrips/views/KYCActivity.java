package com.imuons.shopntrips.views;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.imuons.shopntrips.R;
import com.imuons.shopntrips.model.UploadPhotosResponseModel;
import com.imuons.shopntrips.model.UserPhotosDataModel;
import com.imuons.shopntrips.model.UserPhotosResponseModel;
import com.imuons.shopntrips.retrofit.ApiHandler;
import com.imuons.shopntrips.retrofit.Emwi;
import com.imuons.shopntrips.utils.Constants;
import com.imuons.shopntrips.utils.SharedPreferenceUtils;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.LENGTH_SHORT;

public class KYCActivity extends AppCompatActivity {
    @BindView(R.id.cfpp)
    ImageView cfpp;
    @BindView(R.id.cfpc)
    ImageView cfpc;
    @BindView(R.id.cfac)
    ImageView cfac;
    @BindView(R.id.cfap)
    ImageView cfap;

    @BindView(R.id.upp)
    Button upp;
    @BindView(R.id.upc)
    Button upc;
    @BindView(R.id.uac)
    Button uac;
    @BindView(R.id.uap)
    Button uap;

    @BindView(R.id.txtpp)
    TextView txtpp;
    @BindView(R.id.txtpc)
    TextView txtpc;
    @BindView(R.id.txtac)
    TextView txtac;
    @BindView(R.id.txtap)
    TextView txtap;

    @BindView(R.id.uploadedpp)
    ImageView uploadedpp;
    @BindView(R.id.uploadedpc)
    ImageView uploadedpc;
    @BindView(R.id.uploadedac)
    ImageView uploadedac;
    @BindView(R.id.uploadedap)
    ImageView uploadedap;
    static final Integer READ_EXST = 0x4;
    String m_selectedPath,filenamestr,selectedcf;
    private static final int SELECT_PICTURE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kyc);
        ButterKnife.bind(this);
        askForPermission(Manifest.permission.READ_EXTERNAL_STORAGE,READ_EXST);
        cfpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedcf = "photo";
                askForPermission(Manifest.permission.READ_EXTERNAL_STORAGE,READ_EXST);
                openImageChooser();
            }
        });
        cfpc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedcf = "pan";
                askForPermission(Manifest.permission.READ_EXTERNAL_STORAGE,READ_EXST);
                openImageChooser();
            }
        });
        cfac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedcf = "adhar";
                askForPermission(Manifest.permission.READ_EXTERNAL_STORAGE,READ_EXST);
                openImageChooser();
            }
        });
        cfap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedcf = "address";
                askForPermission(Manifest.permission.READ_EXTERNAL_STORAGE,READ_EXST);
                openImageChooser();
            }
        });


        upp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(m_selectedPath != null){
                    uploadimage(m_selectedPath,selectedcf);
                }else{
                    Toast.makeText(KYCActivity.this,"Please Select Image",LENGTH_SHORT).show();
                }
            }
        });
        upc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(m_selectedPath != null){
                    uploadimage(m_selectedPath,selectedcf);
                }else{
                    Toast.makeText(KYCActivity.this,"Please Select Image",LENGTH_SHORT).show();
                }
            }
        });
        uac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(m_selectedPath != null){
                    uploadimage(m_selectedPath,selectedcf);
                }else{
                    Toast.makeText(KYCActivity.this,"Please Select Image",LENGTH_SHORT).show();
                }
            }
        });
        uap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(m_selectedPath != null){
                    uploadimage(m_selectedPath,selectedcf);
                }else{
                    Toast.makeText(KYCActivity.this,"Please Select Image",LENGTH_SHORT).show();
                }
            }
        });
    }

    private void uploadimage(String m_selectedPath, String selectedcf) {

        File file = new File(m_selectedPath);
        long length = file.length() / 1024;
        if(length < 1000) {
            //  MultipartBody.Part body = null;
            okhttp3.RequestBody name = okhttp3.RequestBody.create(okhttp3.MediaType.parse("text/plain"), selectedcf);

            okhttp3.RequestBody requestFile = okhttp3.RequestBody.create(okhttp3.MediaType.parse("multipart/form-data"), file);      //

            MultipartBody.Part body = MultipartBody.Part.createFormData("photo", file.getName().replace(" ", "_"), requestFile);
            HashMap<String, RequestBody> map = new HashMap<>();
            // map.put("file",body);
            map.put("name", name);
            Emwi apiService = ApiHandler.getApiService();
            Call<UploadPhotosResponseModel> call = apiService.wsUploadPhotos("Bearer " + SharedPreferenceUtils.getAccesstoken(KYCActivity.this), map, body);
            call.enqueue(new Callback<UploadPhotosResponseModel>() {
                @Override
                public void onResponse(Call<UploadPhotosResponseModel> call, Response<UploadPhotosResponseModel> response) {
                    if (response.isSuccessful()) {
                        // tasks available
                        UploadPhotosResponseModel uploadPhotosResponseModel = response.body();
                        if (uploadPhotosResponseModel.getCode() == Constants.RESPONSE_CODE_OK) {
                            Toast.makeText(KYCActivity.this,
                                    uploadPhotosResponseModel.getMessage(), Toast.LENGTH_LONG).show();
                            getdata();
                        } else {
                            Toast.makeText(KYCActivity.this,
                                    uploadPhotosResponseModel.getMessage(), LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(KYCActivity.this,
                                " photo not uploading", LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<UploadPhotosResponseModel> call, Throwable t) {
                    Toast.makeText(KYCActivity.this,
                            t.toString(), LENGTH_SHORT).show();
                }
            });
        }else{
            Toast.makeText(KYCActivity.this, "Image Size Should Not Greater Than 1MB", LENGTH_SHORT).show();
        }
    }

    private void getdata() {
        Emwi apiService = ApiHandler.getApiService();
        final Call<UserPhotosResponseModel> loginCall = apiService.wsUserPhotos(
                "Bearer " + SharedPreferenceUtils.getAccesstoken(KYCActivity.this));
        loginCall.enqueue(new Callback<UserPhotosResponseModel>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<UserPhotosResponseModel> call,
                                   Response<UserPhotosResponseModel> response) {



                if (response.isSuccessful()) {
                    UserPhotosResponseModel userPhotosResponseModel = response.body();
                    if (userPhotosResponseModel.getCode() == Constants.RESPONSE_CODE_OK &&
                            userPhotosResponseModel.getStatus().equals("OK")) {
                        setUserPhoto(userPhotosResponseModel.getData());
                    } else {
                        Toast.makeText(KYCActivity.this, userPhotosResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<UserPhotosResponseModel> call,
                                  Throwable t) {


                Toast.makeText(KYCActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setUserPhoto(UserPhotosDataModel data) {
        if (data.getAddress() != null) {
            Picasso.get().load(String.valueOf(data.getAddress())).into(uploadedap);
        } else {
            Picasso.get().load(R.drawable.noimageavailable).into(uploadedap);
        }

        
        if (data.getPancard() != null) {
            Picasso.get().load(String.valueOf(data.getPancard())).into(uploadedpc);
        } else {
            Picasso.get().load(R.drawable.noimageavailable).into(uploadedpc);
        }
        if (data.getPhoto() != null) {
            Picasso.get().load(String.valueOf(data.getPhoto())).into(uploadedpp);
        } else {
            Picasso.get().load(R.drawable.noimageavailable).into(uploadedpp);
        }
    }


    private void openImageChooser() {
        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i,SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_PICTURE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            // Get the url from data
            Uri selectedImageUri = data.getData();
            if (null != selectedImageUri) {
                // Get the path from the Uri
                String path = getPathFromURI(selectedImageUri);
                m_selectedPath = path;


              //  Log.i(TAG, "Image Path : " + path);
                if (path != null) {
                    filenamestr = path.substring(path.lastIndexOf("/") + 1);
                    if (selectedcf.equals("photo")) {
                        txtpp.setText(filenamestr);
                    } else if (selectedcf.equals("pan")) {
                        txtpc.setText(filenamestr);
                    } else if (selectedcf.equals("adhar")) {
                        txtac.setText(filenamestr);
                    } else if (selectedcf.equals("address")) {
                        txtap.setText(filenamestr);
                    }
                } else {
                    Toast.makeText(KYCActivity.this, "Select Image", LENGTH_SHORT).show();
                }

            }
        } else {
            Toast.makeText(KYCActivity.this, "Select Slip",
                    LENGTH_SHORT).show();
        }
    }

    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getApplicationContext().getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }


    public void askForPermission(String permission, Integer requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getApplicationContext().checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions( new String[]{permission}, requestCode);
            } else {
                //  Toast.makeText(MakeFundRequestFragment.this.getContext(), "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
