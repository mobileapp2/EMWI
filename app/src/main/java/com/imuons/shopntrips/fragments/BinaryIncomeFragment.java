package com.imuons.shopntrips.fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListPopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.imuons.shopntrips.R;
import com.imuons.shopntrips.adapters.BinaryIncomeReportAdapter;
import com.imuons.shopntrips.model.BinaryIncomeReportRecordModel;
import com.imuons.shopntrips.model.BinaryIncomeReportResponseModel;
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
import pl.droidsonroids.gif.GifImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class BinaryIncomeFragment extends Fragment {

    @BindView(R.id.recycler_binary_income_report)
    RecyclerView recycler_binary_income_report;
    @BindView(R.id.searchbyid)
    EditText searchbyid;
    @BindView(R.id.gif)
    GifImageView gif;
    @BindView(R.id.getselectedentry)
    TextView getselectedentry;
    @BindView(R.id.dropdoenentry)
    View dropdoenentry;
    String mStringUserId;
    BinaryIncomeReportAdapter binaryIncomeReportAdapter;
    private List<BinaryIncomeReportRecordModel> rorList = new ArrayList<>();
    String countselected = "10";
    private FragmentManager fragmentManager;
    String entry[] ={"10","50","100","500","1000","5000","10000"};
    ListPopupWindow entrypopupwindow;

    public BinaryIncomeFragment() {
        // Required empty public constructor
    }


    public static BinaryIncomeFragment newInstance() {
        BinaryIncomeFragment fragment = new BinaryIncomeFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_binaryincome, container, false);
        ButterKnife.bind(this, view);
        fragmentManager = getFragmentManager();

        entrypopupwindow = new ListPopupWindow(
                BinaryIncomeFragment.this.getContext());
        recycler_binary_income_report.setHasFixedSize(true);
        recycler_binary_income_report.setLayoutManager(new LinearLayoutManager(BinaryIncomeFragment.this.getContext(),LinearLayoutManager.VERTICAL,false));
        getselectedentry.setText(countselected);
        dropdoenentry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                entrypopupwindow.setAdapter(new ArrayAdapter(
                        BinaryIncomeFragment.this.getContext(),
                        R.layout.check_list_item, entry));
                entrypopupwindow.setAnchorView(dropdoenentry);
                entrypopupwindow.setWidth(170);
                entrypopupwindow.setHeight(500);
                entrypopupwindow.setModal(true);
                entrypopupwindow.show();
            }
        });
        entrypopupwindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                getselectedentry.setText(entry[i]);
                countselected = getselectedentry.getText().toString();
                rorList .clear();
                mStringUserId="";
                getData(mStringUserId);
                entrypopupwindow.dismiss();
            }
        });
        searchbyid.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    rorList.clear();
                    // if (validateUserId()) {
                    mStringUserId = searchbyid.getText().toString().trim();
                    getData(mStringUserId);
//                    } else {
//                        mStringUserId = "";
//                        getdata(mStringUserId);
//                        Toast.makeText(GetDonationReportFragment.this.getContext(),
//                                getString(R.string.invalid_user_id), Toast.LENGTH_SHORT).show();
//                    }

                    return true;
                }
                return false;
            }
        });
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        if (Utils.checkInternetConnection(BinaryIncomeFragment.this.getContext())) {
            recycler_binary_income_report.setHasFixedSize(true);
            recycler_binary_income_report.setLayoutManager(new LinearLayoutManager(BinaryIncomeFragment.this.getContext(),LinearLayoutManager.VERTICAL,false));
            rorList.clear();
            mStringUserId="";
            getData(mStringUserId);

        } else {
            Toast.makeText(BinaryIncomeFragment.this.getContext(),
                    getString(R.string.no_internet_connection_message), Toast.LENGTH_SHORT).show();
        }
    }
    private void getData(String mStringUserId) {
        //final ProgressDialog pd = ViewUtils.getProgressBar(UserProfileFragment.this.getContext(), "Loading...", "Please wait..!");
        gif.setVisibility(View.VISIBLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        Map<String, String> roiMap = new HashMap<>();

        roiMap.put("start", String.valueOf(0));
        roiMap.put("length", countselected);
        roiMap.put("search[value]",mStringUserId);

        Emwi apiService = ApiHandler.getApiService();
        final Call<BinaryIncomeReportResponseModel> loginCall = apiService.wsBinaryIncomeReport(
                "Bearer " + SharedPreferenceUtils.getAccesstoken(BinaryIncomeFragment.this.getContext()),roiMap);
        loginCall.enqueue(new Callback<BinaryIncomeReportResponseModel>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<BinaryIncomeReportResponseModel> call,
                                   Response<BinaryIncomeReportResponseModel> response) {
//                pd.hide();
                gif.setVisibility(View.GONE);
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                if (response.isSuccessful()) {
                    BinaryIncomeReportResponseModel roiIncomeReportResponseModel = response.body();
                    if (roiIncomeReportResponseModel.getCode() == Constants.RESPONSE_CODE_OK &&
                            roiIncomeReportResponseModel.getStatus().equals("OK")) {
                        rorList.addAll(roiIncomeReportResponseModel.getData().getRecords());
                        if(rorList.size() > 0) {
                            binaryIncomeReportAdapter = new BinaryIncomeReportAdapter(BinaryIncomeFragment.this.getContext(), rorList);
                            recycler_binary_income_report.setAdapter(binaryIncomeReportAdapter);
                        }else{
                            Toast.makeText(BinaryIncomeFragment.this.getContext(), "No data available in table", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(BinaryIncomeFragment.this.getContext(), roiIncomeReportResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<BinaryIncomeReportResponseModel> call,
                                  Throwable t) {
//                pd.hide();
                gif.setVisibility(View.GONE);
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                Toast.makeText(BinaryIncomeFragment.this.getContext(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
