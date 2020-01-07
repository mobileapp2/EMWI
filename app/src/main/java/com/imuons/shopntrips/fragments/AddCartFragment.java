package com.imuons.shopntrips.fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListPopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.imuons.shopntrips.R;
import com.imuons.shopntrips.adapters.AddToCartAdapter;
import com.imuons.shopntrips.model.AddToCartResponseModel;
import com.imuons.shopntrips.model.RemoveProductResponseModel;
import com.imuons.shopntrips.model.UserCartDataModel;
import com.imuons.shopntrips.model.UserCartResponseModel;
import com.imuons.shopntrips.retrofit.ApiHandler;
import com.imuons.shopntrips.retrofit.Emwi;
import com.imuons.shopntrips.utils.Constants;
import com.imuons.shopntrips.utils.SharedPreferenceUtils;
import com.imuons.shopntrips.utils.Utils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddCartFragment extends Fragment {
    @BindView(R.id.selectdepositetype)
    TextView selectdepositetype;
    @BindView(R.id.recycler_view_addtocart)
    RecyclerView recycler_view_addtocart;
String strselectdepositetype,getid,getqty,strtotalprise,strqty;


    private AddToCartAdapter addToCartAdapter;

    ListPopupWindow selectdepositetypepw;
    private FragmentManager fragmentManager;
    String depositetype[] ={"Cash","Bank","Cheque","Credit Card"};
    private List<UserCartDataModel> acList = new ArrayList<>();

    public AddCartFragment() {
        // Required empty public constructor
    }


    public static AddCartFragment newInstance() {
        AddCartFragment fragment = new AddCartFragment();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_cart, container, false);
        ButterKnife.bind(this, view);
//        getid  = SharedPreferenceUtils.getProductId(AddCartFragment.this.getContext());
//        getqty = SharedPreferenceUtils.getCartnumber(AddCartFragment.this.getContext());
        fragmentManager = getFragmentManager();
        selectdepositetypepw = new ListPopupWindow(
                AddCartFragment.this.getContext());

        recycler_view_addtocart.setHasFixedSize(true);
        recycler_view_addtocart.setLayoutManager(new LinearLayoutManager(AddCartFragment.this.getContext(),LinearLayoutManager.VERTICAL,false));

        selectdepositetype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectdepositetypepw.setAdapter(new ArrayAdapter(
                        AddCartFragment.this.getContext(),
                        R.layout.check_list_item, depositetype));
                selectdepositetypepw.setAnchorView(selectdepositetype);
                selectdepositetypepw.setWidth(500);
                selectdepositetypepw.setHeight(500);
                selectdepositetypepw.setModal(true);
                selectdepositetypepw.show();
            }
        });
        selectdepositetypepw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectdepositetype.setText(depositetype[i]);
                strselectdepositetype = selectdepositetype.getText().toString();
                  if(strselectdepositetype.equals("Cash")){
                      fragmentManager.beginTransaction().replace(R.id.content_frame, CashFragment.newInstance()).commit();
                      ((AppCompatActivity)view.getContext()).getSupportActionBar().setTitle("Cash");
                  }
                else if(strselectdepositetype.equals("Bank")){
                      fragmentManager.beginTransaction().replace(R.id.content_frame, BankFragment.newInstance()).commit();
                      ((AppCompatActivity)view.getContext()).getSupportActionBar().setTitle("Bank");
                  }else if(strselectdepositetype.equals("Cheque")){
                      fragmentManager.beginTransaction().replace(R.id.content_frame, ChequeFragment.newInstance()).commit();
                      ((AppCompatActivity)view.getContext()).getSupportActionBar().setTitle("Cheque");
                  }else if(strselectdepositetype.equals("Credit Card")){
                      fragmentManager.beginTransaction().replace(R.id.content_frame, CreditCardFragment.newInstance()).commit();
                      ((AppCompatActivity)view.getContext()).getSupportActionBar().setTitle("Credit Card");
                  }
                selectdepositetypepw.dismiss();
            }
        });
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        if (Utils.checkInternetConnection(AddCartFragment.this.getContext())) {

            recycler_view_addtocart.setHasFixedSize(true);
            recycler_view_addtocart.setLayoutManager(new LinearLayoutManager(AddCartFragment.this.getContext(),LinearLayoutManager.VERTICAL,false));

            acList.clear();
            getusercartitem();
            //getdata();
        } else {
            Toast.makeText(AddCartFragment.this.getContext(),
                    getString(R.string.no_internet_connection_message), Toast.LENGTH_SHORT).show();
        }
    }

    private void getdata() {
        Map<String, String> edMap = new HashMap<>();

        edMap.put("id",getid);
        edMap.put("quantity", getqty);


        Emwi apiService = ApiHandler.getApiService();
        final Call<AddToCartResponseModel> loginCall = apiService.wsAddToCart(
                "Bearer " + SharedPreferenceUtils.getAccesstoken(AddCartFragment.this.getContext()),edMap);
        loginCall.enqueue(new Callback<AddToCartResponseModel>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<AddToCartResponseModel> call,
                                   Response<AddToCartResponseModel> response) {

                if (response.isSuccessful()) {
                    AddToCartResponseModel addToCartResponseModel = response.body();
                    if (addToCartResponseModel.getCode() == Constants.RESPONSE_CODE_OK &&
                            addToCartResponseModel.getStatus().equals("OK")) {

                        getusercartitem();
//                         acList.add(addToCartResponseModel.getData());
//                        if(acList.size() > 0) {
//                            addToCartAdapter = new AddToCartAdapter(AddCartFragment.this, acList);
//                            recycler_view_addtocart.setAdapter(addToCartAdapter);
//                        }else{
//                            Toast.makeText(AddCartFragment.this.getContext(), "No data available in table", Toast.LENGTH_SHORT).show();
//                        }
                    } else {
                        fragmentManager.beginTransaction().replace(R.id.content_frame, SendEpinRequestFragment.newInstance()).commit();
                        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Add Product To Cart");
                        Toast.makeText(AddCartFragment.this.getContext(), addToCartResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<AddToCartResponseModel> call,
                                  Throwable t) {
                Toast.makeText(AddCartFragment.this.getContext(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getusercartitem() {
        Emwi apiService = ApiHandler.getApiService();
        final Call<UserCartResponseModel> loginCall = apiService.wsGetUserCart(
                "Bearer " + SharedPreferenceUtils.getAccesstoken(AddCartFragment.this.getContext()));
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
                            addToCartAdapter = new AddToCartAdapter(AddCartFragment.this, acList);
                            recycler_view_addtocart.setAdapter(addToCartAdapter);

                           gettotalprise(userCartResponseModel.getData());
                        }else{

                            acList.clear();
                            Toast.makeText(AddCartFragment.this.getContext(), "No data available in cart", Toast.LENGTH_SHORT).show();
                            fragmentManager.beginTransaction().replace(R.id.content_frame, SendEpinRequestFragment.newInstance()).commit();
                            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Add Product To Cart");

                        }
                    } else {
                        Toast.makeText(AddCartFragment.this.getContext(), userCartResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<UserCartResponseModel> call,
                                  Throwable t) {
                Toast.makeText(AddCartFragment.this.getContext(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void gettotalprise(List<UserCartDataModel> data) {
        strtotalprise = data.get(0).getSumTotalPrice();
    }


    public  void removeitem(String strorderid){

        Map<String, String> riMap = new HashMap<>();

        riMap.put("order_id",strorderid);



        Emwi apiService = ApiHandler.getApiService();
        final Call<RemoveProductResponseModel> loginCall = apiService.wsRemoveProduct(
                "Bearer " + SharedPreferenceUtils.getAccesstoken(AddCartFragment.this.getContext()),riMap);
        loginCall.enqueue(new Callback<RemoveProductResponseModel>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<RemoveProductResponseModel> call,
                                   Response<RemoveProductResponseModel> response) {

                if (response.isSuccessful()) {
                    RemoveProductResponseModel removeProductResponseModel = response.body();
                    if (removeProductResponseModel.getCode() == Constants.RESPONSE_CODE_OK &&
                            removeProductResponseModel.getStatus().equals("OK")) {
                   acList.clear();
                        getusercartitem();
//                         acList.add(addToCartResponseModel.getData());
//                        if(acList.size() > 0) {
//                            addToCartAdapter = new AddToCartAdapter(AddCartFragment.this, acList);
//                            recycler_view_addtocart.setAdapter(addToCartAdapter);
//                        }else{
//                            Toast.makeText(AddCartFragment.this.getContext(), "No data available in table", Toast.LENGTH_SHORT).show();
//                        }
                    } else if(removeProductResponseModel.getCode() == 404){
                        acList.clear();
                    }
                    else {
                        Toast.makeText(AddCartFragment.this.getContext(), removeProductResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else{
                   // Toast.makeText(AddCartFragment.this.getContext(), removeProductResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RemoveProductResponseModel> call,
                                  Throwable t) {
                Toast.makeText(AddCartFragment.this.getContext(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
