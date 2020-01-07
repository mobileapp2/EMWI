package com.imuons.shopntrips.fragments;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.imuons.shopntrips.R;
import com.imuons.shopntrips.model.SendMultiplePinResponseModel;
import com.imuons.shopntrips.model.UserCartDataModel;
import com.imuons.shopntrips.model.UserCartResponseModel;
import com.imuons.shopntrips.retrofit.ApiHandler;
import com.imuons.shopntrips.retrofit.Emwi;
import com.imuons.shopntrips.utils.Constants;
import com.imuons.shopntrips.utils.SharedPreferenceUtils;
import com.imuons.shopntrips.utils.Utils;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

import static android.widget.Toast.LENGTH_SHORT;


public class CreditCardFragment extends Fragment {
    @BindView(R.id.date)
    EditText etdate;
//    @BindView(R.id.bankname)
//    EditText bankname;
//    @BindView(R.id.receiptno)
//    EditText receiptno;
//    @BindView(R.id.chequeno)
//    EditText chequeno;
    @BindView(R.id.amount)
    EditText amount;
    @BindView(R.id.des)
    EditText des;
    @BindView(R.id.cf)
    ImageView cf;
    @BindView(R.id.nfc)
    TextView nfc;
    @BindView(R.id.submit)
    Button submit;
    private static final int SELECT_PICTURE = 100;
    String strdate,stramout,strrn,strdes,strtotalprise,m_selectedPath,filenamestr,strqty,strbank,strcheque;
    final Calendar myCalendar = Calendar.getInstance();
    static final Integer READ_EXST = 0x4;
    private List<UserCartDataModel> acList =new ArrayList<>();

    private FragmentManager fragmentManager;


    public CreditCardFragment() {
        // Required empty public constructor
    }


    public static CreditCardFragment newInstance( ) {
        CreditCardFragment fragment = new CreditCardFragment();

        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_creditcard, container, false);
        ButterKnife.bind(this, view);
        fragmentManager = getFragmentManager();
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE|WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        etdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(CreditCardFragment.this.getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateAmount() && validatedate()  &&  validatedes()){
                    callsubmit();
                }
            }
        });
        cf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                askForPermission(Manifest.permission.READ_EXTERNAL_STORAGE,READ_EXST);
                openImageChooser();
            }
        });
        return view;
    }

//    private boolean validateCheeque() {
//        strcheque = chequeno.getText().toString().trim();
//        if (strcheque.isEmpty() ) {
//            Toast.makeText(CreditCardFragment.this.getContext(), "Enter Valid Cheque Number", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        return true;
//    }
//
//    private boolean validateBank() {
//        strbank = bankname.getText().toString().trim();
//        if (strbank.isEmpty() ) {
//            Toast.makeText(CreditCardFragment.this.getContext(), "Enter Valid Bank Name", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        return true;
//    }

    public void onActivityResult( int requestCode,  int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SELECT_PICTURE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            // Get the url from data
            Uri selectedImageUri = data.getData();
            if (null != selectedImageUri) {
                // Get the path from the Uri
                String path = getPathFromURI(selectedImageUri);
                m_selectedPath = path;



              //  Log.i(TAG, "Image Path : " + path);
                filenamestr = path.substring(path.lastIndexOf("/")+1);

                nfc.setText(filenamestr);



            }
        }else{
            Toast.makeText(CreditCardFragment.this.getContext(), "Select Payment Slip",
                    LENGTH_SHORT).show();
        }
    }
    private void openImageChooser() {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i,SELECT_PICTURE);
    }
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
    private void askForPermission(String permission, Integer requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getActivity().checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions( new String[]{permission}, requestCode);
            } else {
                //  Toast.makeText(MakeFundRequestFragment.this.getContext(), "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void callsubmit() {
        long length = 0;
        okhttp3.MultipartBody.Part body = null;
        File file;
        int getqty = acList.size();
        strqty = String.valueOf(getqty);
        if(m_selectedPath != null){
            file = new File(m_selectedPath);
            length = file.length() / 1024; // Size in KB
        }else{
            file = new File(" ");
            length = file.length() / 1024; // Size in KB
        }
        if(length < 800){
            okhttp3.RequestBody dt = okhttp3.RequestBody.create(okhttp3.MediaType.parse("text/plain"),"credit");
            okhttp3.RequestBody transactiondate = okhttp3.RequestBody.create(okhttp3.MediaType.parse("text/plain"),strdate);
           // RequestBody recept = RequestBody.create(MediaType.parse("text/plain"),strrn);
            okhttp3.RequestBody amt = okhttp3.RequestBody.create(okhttp3.MediaType.parse("text/plain"),stramout);
            okhttp3.RequestBody remark = okhttp3.RequestBody.create(okhttp3.MediaType.parse("text/plain"),strdes);
            okhttp3.RequestBody totalamt = okhttp3.RequestBody.create(okhttp3.MediaType.parse("text/plain"),strtotalprise);
            okhttp3.RequestBody quantity = okhttp3.RequestBody.create(okhttp3.MediaType.parse("text/plain"),strqty);
//            RequestBody bankname = RequestBody.create(MediaType.parse("text/plain"),strbank);
//            RequestBody chequeno = RequestBody.create(MediaType.parse("text/plain"),strcheque);
//            okhttp3.RequestBody requestFile = okhttp3.RequestBody.create(okhttp3.MediaType.parse("multipart/form-data"), file);
//            body = MultipartBody.Part.createFormData("file", file.getName().replace(" ", "_"), requestFile);

            if(m_selectedPath != null){
                okhttp3.RequestBody requestFile = okhttp3.RequestBody.create(okhttp3.MediaType.parse("multipart/form-data"), file);
                body = okhttp3.MultipartBody.Part.createFormData("file", file.getName().replace(" ", "_"), requestFile);
            }else{
                okhttp3.RequestBody attachmentEmpty = okhttp3.RequestBody.create(okhttp3.MediaType.parse("text/plain"), "");
                body = okhttp3.MultipartBody.Part.createFormData("file", file.getName().replace(" ", "_"), attachmentEmpty);
            }

            HashMap<String, okhttp3.RequestBody> map = new HashMap<>();
            // map.put("file",body);
            map.put("deposite_type",dt);
            map.put("transactiondate",transactiondate);
         //   map.put("receipt_no",recept);
            map.put("cash_amount",amt);
            map.put("remark",remark);
            map.put("total_amount",totalamt);
            map.put("quantity",quantity);
//            map.put("bank_name",bankname);
//            map.put("cheque_no",chequeno);

            Emwi apiService = ApiHandler.getApiService();
            Call<SendMultiplePinResponseModel> call = apiService.wsSendPinRequest("Bearer " + SharedPreferenceUtils.getAccesstoken(CreditCardFragment.this.getContext()),map,body);
            call.enqueue(new Callback<SendMultiplePinResponseModel>() {
                @Override
                public void onResponse(Call<SendMultiplePinResponseModel> call, Response<SendMultiplePinResponseModel> response) {
                    if (response.isSuccessful()) {
                        // tasks available
                        SendMultiplePinResponseModel sendMultiplePinResponseModel = response.body();
                        if (sendMultiplePinResponseModel.getCode() == Constants.RESPONSE_CODE_OK) {
                            Toast.makeText(CreditCardFragment.this.getContext(),
                                    sendMultiplePinResponseModel.getMessage(), Toast.LENGTH_LONG).show();
                            fragmentManager.beginTransaction().replace(R.id.content_frame, EPinRequestReportFragment.newInstance()).commit();
                            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("E-Pin Request Report");
                        } else {
                            Toast.makeText(CreditCardFragment.this.getContext(),
                                    sendMultiplePinResponseModel.getMessage(), LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(CreditCardFragment.this.getContext(),
                                "not uploading", LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<SendMultiplePinResponseModel> call, Throwable t) {
                    Toast.makeText(CreditCardFragment.this.getContext(),
                            t.toString(), LENGTH_SHORT).show();
                }
            });
        }else{
            Toast.makeText(CreditCardFragment.this.getContext(), "Max Photo Size: 800 K.B.", LENGTH_SHORT).show();
        }

    }

    private boolean validatedes() {
        strdes = des.getText().toString().trim();
        if (strdes.isEmpty() ) {
            Toast.makeText(CreditCardFragment.this.getContext(), "Enter Description", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

//    private boolean validateRn() {
//        strrn = receiptno.getText().toString().trim();
//        if (strrn.isEmpty() ) {
//            Toast.makeText(CreditCardFragment.this.getContext(), "Enter Receipt No", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        return true;
//    }

    private boolean validatedate() {
        strdate = etdate.getText().toString().trim();
        if (strdate.isEmpty() ) {
            Toast.makeText(CreditCardFragment.this.getContext(), "Enter Valid Date", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean validateAmount() {
        stramout = amount.getText().toString().trim();
        if (stramout.isEmpty() ) {
            Toast.makeText(CreditCardFragment.this.getContext(), getString(R.string.enter_amount), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;

    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    private void updateLabel() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        etdate.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Utils.checkInternetConnection(CreditCardFragment.this.getContext())) {
            acList.clear();
            getusercartitem();
        } else {
            Toast.makeText(CreditCardFragment.this.getContext(),
                    getString(R.string.no_internet_connection_message), Toast.LENGTH_SHORT).show();
        }
    }
    private void getusercartitem() {
        Emwi apiService = ApiHandler.getApiService();
        final Call<UserCartResponseModel> loginCall = apiService.wsGetUserCart(
                "Bearer " + SharedPreferenceUtils.getAccesstoken(CreditCardFragment.this.getContext()));
        loginCall.enqueue(new Callback<UserCartResponseModel>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<UserCartResponseModel> call,
                                   Response<UserCartResponseModel> response) {

                if (response.isSuccessful()) {
                    UserCartResponseModel userCartResponseModel = response.body();
                    if (userCartResponseModel.getCode() == Constants.RESPONSE_CODE_OK &&
                            userCartResponseModel.getStatus().equals("OK")) {

                        acList.addAll(userCartResponseModel.getData());


                        if(acList.size() > 0) {
//                            addToCartAdapter = new AddToCartAdapter(AddCartFragment.this, acList);
//                            recycler_view_addtocart.setAdapter(addToCartAdapter);

                            gettotalprise(userCartResponseModel.getData());
                        }else{
                            Toast.makeText(CreditCardFragment.this.getContext(), "No data available in table", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(CreditCardFragment.this.getContext(), userCartResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<UserCartResponseModel> call,
                                  Throwable t) {
                Toast.makeText(CreditCardFragment.this.getContext(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void gettotalprise(List<UserCartDataModel> data) {
        strtotalprise = data.get(0).getSumTotalPrice();
    }





}








