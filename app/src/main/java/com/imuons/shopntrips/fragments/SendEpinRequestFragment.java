package com.imuons.shopntrips.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.imuons.shopntrips.R;
import com.imuons.shopntrips.adapters.SendPinRequestAdapter;
import com.imuons.shopntrips.model.GetProductsPinRequestDataModel;
import com.imuons.shopntrips.model.GetProductsPinRequestResponseModel;
import com.imuons.shopntrips.retrofit.ApiHandler;
import com.imuons.shopntrips.retrofit.Emwi;
import com.imuons.shopntrips.utils.Constants;
import com.imuons.shopntrips.utils.SharedPreferenceUtils;
import com.imuons.shopntrips.utils.Utils;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SendEpinRequestFragment extends Fragment {
    @BindView(R.id.recycler_view_senepinrequest)
    RecyclerView recycler_view_senepinrequest;
    @BindView(R.id.loader_view)
    View loaderView;
    @BindView(R.id.checkout)
    TextView checkout;
    private FragmentManager fragmentManager;
    SendPinRequestAdapter sendPinRequestAdapter;
    private List<GetProductsPinRequestDataModel> spList = new ArrayList<>();

    public SendEpinRequestFragment() {
        // Required empty public constructor
    }


    public static SendEpinRequestFragment newInstance() {
        SendEpinRequestFragment fragment = new SendEpinRequestFragment();


        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_send_epin_request, container, false);
        ButterKnife.bind(this, view);
        recycler_view_senepinrequest.setHasFixedSize(true);
        recycler_view_senepinrequest.setLayoutManager(new LinearLayoutManager(SendEpinRequestFragment.this.getContext(),LinearLayoutManager.VERTICAL,false));
        fragmentManager = getFragmentManager();
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragmentManager.beginTransaction().replace(R.id.content_frame,AddCartFragment.newInstance()).commit();
                ((AppCompatActivity)view.getContext()).getSupportActionBar().setTitle("Send E-Pin Request");

            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Utils.checkInternetConnection(SendEpinRequestFragment.this.getContext())) {

            recycler_view_senepinrequest.setHasFixedSize(true);
            recycler_view_senepinrequest.setLayoutManager(new LinearLayoutManager(SendEpinRequestFragment.this.getContext(),LinearLayoutManager.VERTICAL,false));

            spList.clear();
            getdata();
        } else {
            Toast.makeText(SendEpinRequestFragment.this.getContext(),
                    getString(R.string.no_internet_connection_message), Toast.LENGTH_SHORT).show();
        }
    }
    private void getdata() {
        loaderView.setVisibility(View.VISIBLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        Emwi apiService = ApiHandler.getApiService();
        final Call<GetProductsPinRequestResponseModel> loginCall = apiService.wsGetProductsForPinRequest(
                "Bearer " + SharedPreferenceUtils.getAccesstoken(SendEpinRequestFragment.this.getContext()));
        loginCall.enqueue(new Callback<GetProductsPinRequestResponseModel>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<GetProductsPinRequestResponseModel> call,
                                   Response<GetProductsPinRequestResponseModel> response) {
                loaderView.setVisibility(View.GONE);
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                if (response.isSuccessful()) {
                    GetProductsPinRequestResponseModel getProductsPinRequestResponseModel = response.body();
                    if (getProductsPinRequestResponseModel.getCode() == Constants.RESPONSE_CODE_OK &&
                            getProductsPinRequestResponseModel.getStatus().equals("OK")) {
                        spList.addAll(getProductsPinRequestResponseModel.getData());
                        if(spList.size() > 0) {
                            sendPinRequestAdapter = new SendPinRequestAdapter(SendEpinRequestFragment.this, spList);
                            recycler_view_senepinrequest.setAdapter(sendPinRequestAdapter);
                        }else{
                            Toast.makeText(SendEpinRequestFragment.this.getContext(), "No data available in table", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SendEpinRequestFragment.this.getContext(), getProductsPinRequestResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetProductsPinRequestResponseModel> call,
                                  Throwable t) {
                loaderView.setVisibility(View.GONE);
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                Toast.makeText(SendEpinRequestFragment.this.getContext(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
