package com.imuons.shopntrips.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListPopupWindow;
import android.widget.TextView;
import android.widget.Toast;


import com.imuons.shopntrips.R;
import com.imuons.shopntrips.model.CheckUserExistResponseModel;
import com.imuons.shopntrips.model.GetLevelResponseModel;
import com.imuons.shopntrips.model.GetProductFroPinTransferDataModel;
import com.imuons.shopntrips.model.GetProductFroPinTransferResponseModel;
import com.imuons.shopntrips.model.ProductDatumModel;
import com.imuons.shopntrips.model.TransferPinResponseModel;
import com.imuons.shopntrips.retrofit.ApiHandler;
import com.imuons.shopntrips.retrofit.Emwi;
import com.imuons.shopntrips.utils.Constants;
import com.imuons.shopntrips.utils.SharedPreferenceUtils;
import com.imuons.shopntrips.utils.Utils;
import com.imuons.shopntrips.views.Registration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TransferEpinFragment extends Fragment {
    @BindView(R.id.availableepins)
    EditText availableepins;
    @BindView(R.id.transuserid)
    EditText transuserid;
    @BindView(R.id.nopfepins)
    EditText nopfepins;
    @BindView(R.id.selectproduct)
    EditText selectproduct;
    @BindView(R.id.submit)
    Button submit;
    private FragmentManager fragmentManager;
    @BindView(R.id.gif)
    GifImageView gif;
    Integer intnoofpins;
    boolean sid = false;
    List<String> productnamelist = new ArrayList<>();

    List<String> listProductName = new ArrayList<>();
    String getproductid,strnoofpins,gettxttransferid,strselectedproduct,strentrnoofpins,strusedid;
    ListPopupWindow selectproductpopupwindow;

    private List<GetProductFroPinTransferDataModel> productlist = new ArrayList<>();
    public TransferEpinFragment() {
        // Required empty public constructor
    }


    public static TransferEpinFragment newInstance() {
        TransferEpinFragment fragment = new TransferEpinFragment();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transfer_epin, container, false);
        ButterKnife.bind(this, view);
        fragmentManager = getFragmentManager();

        selectproductpopupwindow = new ListPopupWindow(
                TransferEpinFragment.this.getContext());
        selectproduct.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                selectproductpopupwindow.setAdapter(new ArrayAdapter(
                        TransferEpinFragment.this.getContext(),
                        R.layout.product_list_item, productnamelist));
                selectproductpopupwindow.setAnchorView(selectproduct);
                selectproductpopupwindow.setWidth(900);
                selectproductpopupwindow.setHeight(410);
                selectproductpopupwindow.setModal(true);
                selectproductpopupwindow.show();
            }
        });


        selectproductpopupwindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int no = productlist.get(i).getNoOfPinsAvailable();
                String test = String.valueOf(no);
                selectproduct.setText(productlist.get(i).getName());
                getproductid = String.valueOf(productlist.get(i).getId());
                selectproductpopupwindow.dismiss();
            }
        });

        transuserid.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TextView text = (TextView) TransferEpinFragment.this.getActivity().getCurrentFocus();

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

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strselectedproduct = selectproduct.getText().toString();
                strentrnoofpins = nopfepins.getText().toString();
                strusedid = transuserid.getText().toString();
                if(sid == true && !strusedid.isEmpty() && !strselectedproduct.isEmpty() && !strentrnoofpins.isEmpty()){
                    transferpin(strselectedproduct,strentrnoofpins,strusedid);
                }else{
                    Toast.makeText(TransferEpinFragment.this.getContext(),"All fields are mandetory",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;

    }

    private void transferpin(String strselectedproduct, String strentrnoofpins,String strusedid) {
        gif.setVisibility(View.VISIBLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        Map<String, String> tpMap = new HashMap<>();
        tpMap.put("pins",strentrnoofpins);
        tpMap.put("product",getproductid);
        tpMap.put("transfer_to",strusedid);

        Emwi apiService = ApiHandler.getApiService();
        final Call<TransferPinResponseModel> loginCall = apiService.wsTransferPins(
                "Bearer " + SharedPreferenceUtils.getAccesstoken(TransferEpinFragment.this.getContext()),tpMap);
        loginCall.enqueue(new Callback<TransferPinResponseModel>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<TransferPinResponseModel> call,
                                   Response<TransferPinResponseModel> response) {
                gif.setVisibility(View.GONE);
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                if (response.isSuccessful()) {
                    TransferPinResponseModel transferPinResponseModel = response.body();
                    if (transferPinResponseModel.getCode() == Constants.RESPONSE_CODE_OK &&
                            transferPinResponseModel.getStatus().equals("OK")) {
                        Toast.makeText(TransferEpinFragment.this.getContext(), transferPinResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                        fragmentManager.beginTransaction().replace(R.id.content_frame, TransferEPinReportFragment.newInstance()).commit();
                        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Transfer E-Pin Report");

                    } if(transferPinResponseModel.getCode() == 404){
                        Toast.makeText(TransferEpinFragment.this.getContext(), transferPinResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(TransferEpinFragment.this.getContext(), transferPinResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<TransferPinResponseModel> call,
                                  Throwable t) {
                gif.setVisibility(View.GONE);
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                Toast.makeText(TransferEpinFragment.this.getContext(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Utils.checkInternetConnection(TransferEpinFragment.this.getContext())) {



            getProducts();
        } else {
            Toast.makeText(TransferEpinFragment.this.getContext(),
                    getString(R.string.no_internet_connection_message), Toast.LENGTH_SHORT).show();
        }
    }

    private void getProducts() {
        gif.setVisibility(View.VISIBLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);



        Emwi apiService = ApiHandler.getApiService();
        final Call<GetProductFroPinTransferResponseModel> loginCall = apiService.wsPinTransfer(
                "Bearer " + SharedPreferenceUtils.getAccesstoken(TransferEpinFragment.this.getContext()));
        loginCall.enqueue(new Callback<GetProductFroPinTransferResponseModel>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<GetProductFroPinTransferResponseModel> call,
                                   Response<GetProductFroPinTransferResponseModel> response) {
                gif.setVisibility(View.GONE);
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                if (response.isSuccessful()) {
                    GetProductFroPinTransferResponseModel getProductFroPinTransferResponseModel = response.body();
                    if (getProductFroPinTransferResponseModel.getCode() == Constants.RESPONSE_CODE_OK &&
                            getProductFroPinTransferResponseModel.getStatus().equals("OK")) {
                        productlist.addAll(getProductFroPinTransferResponseModel.getData());
                        listProductName.clear();
                        getproductname(getProductFroPinTransferResponseModel.getData());
                    } else {
                        Toast.makeText(TransferEpinFragment.this.getContext(), getProductFroPinTransferResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetProductFroPinTransferResponseModel> call,
                                  Throwable t) {
                gif.setVisibility(View.GONE);
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                Toast.makeText(TransferEpinFragment.this.getContext(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getproductname(List<GetProductFroPinTransferDataModel> data) {
        intnoofpins = 0;
        for (int i = 0; i < data.size(); i++) {
            String  t = data.get(i).getName()+ "  (No of Pins available:"+String.valueOf(data.get(i).getNoOfPinsAvailable())+")";
            productnamelist.add(t);
            intnoofpins = intnoofpins + data.get(i).getNoOfPinsAvailable();
        }
        strnoofpins = String.valueOf(intnoofpins);
        availableepins.setText(strnoofpins);
    }

    private void checkSponsorAvailability() {


        Map<String, String> loginMap = new HashMap<>();
        final String userName;

        userName = transuserid.getText().toString().trim();

        loginMap.put("user_id", userName);

        Emwi apiService = ApiHandler.getApiService();
        final Call<CheckUserExistResponseModel> loginCall = apiService.wsCheckUser(loginMap);
        loginCall.enqueue(new Callback<CheckUserExistResponseModel>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<CheckUserExistResponseModel> call,
                                   Response<CheckUserExistResponseModel> response) {

                if (response.isSuccessful()) {
                    CheckUserExistResponseModel checkUserExistResponseModel = response.body();
                    if (checkUserExistResponseModel.getCode() == 404) {
                        sid = false;
                        Toast.makeText(TransferEpinFragment.this.getContext(), checkUserExistResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        sid = true;
                        Toast.makeText(TransferEpinFragment.this.getContext(), checkUserExistResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<CheckUserExistResponseModel> call,
                                  Throwable t) {

                Toast.makeText(TransferEpinFragment.this.getContext(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
