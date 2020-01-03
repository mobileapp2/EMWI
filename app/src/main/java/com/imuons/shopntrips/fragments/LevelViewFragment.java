package com.imuons.shopntrips.fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
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

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.imuons.shopntrips.R;
import com.imuons.shopntrips.adapters.LevelViewAdapter;
import com.imuons.shopntrips.model.GetLevelDatumModel;
import com.imuons.shopntrips.model.GetLevelResponseModel;
import com.imuons.shopntrips.model.LevelViewRecordModel;
import com.imuons.shopntrips.model.LevelViewResponseModel;
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
public class LevelViewFragment extends Fragment {
    @BindView(R.id.recycler_level_view_report)
    RecyclerView recycler_level_view_report;
    @BindView(R.id.gif)
    GifImageView gif;
    String mStringUserId;
    @BindView(R.id.searchbyid)
    EditText searchbyid;
    @BindView(R.id.getselectedentry)
    TextView getselectedentry;
    @BindView(R.id.dropdoenentry)
    View dropdoenentry;
    @BindView(R.id.getselectedlevel)
            TextView getselectedlevel;
    @BindView(R.id.dropdownlevel)
            View dropdownlevel;

    LevelViewAdapter levelViewAdapter;
    List<String> levelnamelist = new ArrayList<>();
    private List<LevelViewRecordModel> lwList = new ArrayList<>();
  private List<GetLevelDatumModel> ldList = new ArrayList<>();
    String countselected = "10";
    String levelselected = "1";
    private FragmentManager fragmentManager;
    String entry[] ={"10","50","100","500","1000","5000","10000"};
    String level[] = {"1","2","3","4"};
    ListPopupWindow entrypopupwindow,levelpopupwindow;

    public static LevelViewFragment newInstance() {
        LevelViewFragment fragment = new LevelViewFragment();
        return fragment;
    }
    public LevelViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_level_view, container, false);
        ButterKnife.bind(this, view);
        fragmentManager = getFragmentManager();

        entrypopupwindow = new ListPopupWindow(
                LevelViewFragment.this.getContext());
        levelpopupwindow = new ListPopupWindow(
                LevelViewFragment.this.getContext());
        recycler_level_view_report.setHasFixedSize(true);
        recycler_level_view_report.setLayoutManager(new LinearLayoutManager(LevelViewFragment.this.getContext(), LinearLayoutManager.VERTICAL,false));
        getselectedentry.setText(countselected);
      //  getselectedlevel.setText(levelselected);
        dropdoenentry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                entrypopupwindow.setAdapter(new ArrayAdapter(
                        LevelViewFragment.this.getContext(),
                        R.layout.check_list_item, entry));
                entrypopupwindow.setAnchorView(dropdoenentry);
                entrypopupwindow.setWidth(170);
                entrypopupwindow.setHeight(500);
                entrypopupwindow.setModal(true);
                entrypopupwindow.show();
            }
        });

        dropdownlevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                levelpopupwindow.setAdapter(new ArrayAdapter(
                        LevelViewFragment.this.getContext(),
                        R.layout.check_list_item_level, levelnamelist));
                levelpopupwindow.setAnchorView(dropdownlevel);
                levelpopupwindow.setWidth(350);
                levelpopupwindow.setHeight(500);
                levelpopupwindow.setModal(true);
                levelpopupwindow.show();
            }
        });

        entrypopupwindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                getselectedentry.setText(entry[i]);
                countselected = getselectedentry.getText().toString();
                lwList .clear();
                ldList.clear();
                mStringUserId="";
                getdata(mStringUserId);
                getLevel();
                entrypopupwindow.dismiss();
            }
        });

        levelpopupwindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                getselectedlevel.setText(ldList.get(i).getLevelName());
                levelselected = String.valueOf(ldList.get(i).getLevelId());
                lwList .clear();
                ldList.clear();
                mStringUserId="";
                getdata(mStringUserId);
                getLevel();
               levelpopupwindow.dismiss();
            }
        });

        searchbyid.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    lwList.clear();
                    ldList.clear();
                    // if (validateUserId()) {
                    mStringUserId = searchbyid.getText().toString().trim();
                    getdata(mStringUserId);
                    getLevel();
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
        if (Utils.checkInternetConnection(LevelViewFragment.this.getContext())) {

            recycler_level_view_report.setHasFixedSize(true);
            recycler_level_view_report.setLayoutManager(new LinearLayoutManager(LevelViewFragment.this.getContext(), LinearLayoutManager.VERTICAL,false));
            lwList .clear();
            ldList.clear();
            mStringUserId="";
            getdata(mStringUserId);
            getLevel();
        } else {
            Toast.makeText(LevelViewFragment.this.getContext(),
                    getString(R.string.no_internet_connection_message), Toast.LENGTH_SHORT).show();
        }
    }

    private void getLevel() {
        gif.setVisibility(View.VISIBLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);



        Emwi apiService = ApiHandler.getApiService();
        final Call<GetLevelResponseModel> loginCall = apiService.wsGetLevel(
                "Bearer " + SharedPreferenceUtils.getAccesstoken(LevelViewFragment.this.getContext()));
        loginCall.enqueue(new Callback<GetLevelResponseModel>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<GetLevelResponseModel> call,
                                   Response<GetLevelResponseModel> response) {
                gif.setVisibility(View.GONE);
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                if (response.isSuccessful()) {
                    GetLevelResponseModel getLevelResponseModel = response.body();
                    if (getLevelResponseModel.getCode() == Constants.RESPONSE_CODE_OK &&
                            getLevelResponseModel.getStatus().equals("OK")) {
                        ldList.addAll(getLevelResponseModel.getData());
                        levelnamelist.clear();
                        getlevelname(getLevelResponseModel.getData());
                    } else {
                        Toast.makeText(LevelViewFragment.this.getContext(), getLevelResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetLevelResponseModel> call,
                                  Throwable t) {
                gif.setVisibility(View.GONE);
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                Toast.makeText(LevelViewFragment.this.getContext(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getlevelname(List<GetLevelDatumModel> data) {
        for (int i = 0; i < data.size(); i++) {
            levelnamelist.add(data.get(i).getLevelName());
        }
    }
    private void getdata(String mStringUserId) {
        gif.setVisibility(View.VISIBLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        Map<String, String> drMap = new HashMap<>();

        drMap.put("start", String.valueOf(0));
        drMap.put("length", countselected);
        drMap.put("search[value]",mStringUserId);
        drMap.put("level_id",levelselected);


        Emwi apiService = ApiHandler.getApiService();
        final Call<LevelViewResponseModel> loginCall = apiService.wsLevelViewReport(
                "Bearer " + SharedPreferenceUtils.getAccesstoken(LevelViewFragment.this.getContext()),drMap);
        loginCall.enqueue(new Callback<LevelViewResponseModel>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<LevelViewResponseModel> call,
                                   Response<LevelViewResponseModel> response) {
                gif.setVisibility(View.GONE);
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                if (response.isSuccessful()) {
                    LevelViewResponseModel levelViewResponseModel = response.body();
                    if (levelViewResponseModel.getCode() == Constants.RESPONSE_CODE_OK &&
                            levelViewResponseModel.getStatus().equals("OK")) {
                        lwList.addAll(levelViewResponseModel.getData().getRecords());
                        if(lwList.size() > 0) {
                            levelViewAdapter = new LevelViewAdapter(getContext(), lwList);
                            recycler_level_view_report.setAdapter(levelViewAdapter);
                        }else{
                            Toast.makeText(LevelViewFragment.this.getContext(), "No data available in table", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LevelViewFragment.this.getContext(), levelViewResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<LevelViewResponseModel> call,
                                  Throwable t) {
                gif.setVisibility(View.GONE);
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                Toast.makeText(LevelViewFragment.this.getContext(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
