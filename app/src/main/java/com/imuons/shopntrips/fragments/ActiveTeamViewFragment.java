package com.imuons.shopntrips.fragments;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListPopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.imuons.shopntrips.R;
import com.imuons.shopntrips.adapters.ActiveTeamViewAdapter;
import com.imuons.shopntrips.model.ActiveTeamViewRecordModel;
import com.imuons.shopntrips.model.ActiveTeamViewResponseModel;
import com.imuons.shopntrips.retrofit.ApiHandler;
import com.imuons.shopntrips.retrofit.Emwi;
import com.imuons.shopntrips.utils.Constants;
import com.imuons.shopntrips.utils.SharedPreferenceUtils;
import com.imuons.shopntrips.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
public class ActiveTeamViewFragment extends Fragment {

    @BindView(R.id.edit_from_date)
    EditText fromdate;
    @BindView(R.id.edit_to_date)
    EditText todate;
    @BindView(R.id.selectteam)
    TextView selectteam;
    @BindView(R.id.edit_user_id)
    EditText edit_user_id;








    @BindView(R.id.searchbyid)
    EditText searchbyid;
    @BindView(R.id.getselectedentry)
    TextView getselectedentry;
    @BindView(R.id.dropdoenentry)
    View dropdoenentry;
    String mStringUserId;
    @BindView(R.id.gif)
    GifImageView gif;
    @BindView(R.id.btn_search)
    TextView search;
    @BindView(R.id.btn_reset)
    TextView clear;
    boolean sid = false;

    @BindView(R.id.recycler_active_view_team_view)
    RecyclerView recycler_active_view_team_view;
    private ActiveTeamViewAdapter activeTeamViewAdapter;
    String countselected = "10";


    private FragmentManager fragmentManager;
                            private List<ActiveTeamViewRecordModel> activeteamViewList = new ArrayList<>();
    final Calendar myCalendar = Calendar.getInstance();
    ListPopupWindow entrypopupwindow,selectproductpopupwindow;
    String entry[] ={"10","50","100","500","1000","5000","10000"};
    String team[] ={"All","Left Team","Right Team"};
    String strfromdate = " ",strtodate = " ",getUserid=" ",strPin=" ",getuserid=" ",strselectteam=" ";

    public ActiveTeamViewFragment() {
        // Required empty public constructor
    }

    public static ActiveTeamViewFragment newInstance() {
        ActiveTeamViewFragment fragment = new ActiveTeamViewFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_active_team_view, container, false);
        ButterKnife.bind(this, view);


        fragmentManager = getFragmentManager();
        entrypopupwindow = new ListPopupWindow(
                ActiveTeamViewFragment.this.getContext());
        selectproductpopupwindow = new ListPopupWindow(
                ActiveTeamViewFragment.this.getContext());

        recycler_active_view_team_view.setHasFixedSize(true);
        recycler_active_view_team_view.setLayoutManager(new LinearLayoutManager(ActiveTeamViewFragment.this.getContext(),LinearLayoutManager.VERTICAL,false));
        getselectedentry.setText(countselected);
        dropdoenentry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                entrypopupwindow.setAdapter(new ArrayAdapter(
                        ActiveTeamViewFragment.this.getContext(),
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
                activeteamViewList.clear();
                mStringUserId = "";
                getdata(mStringUserId);
                entrypopupwindow.dismiss();
            }
        });

        selectteam.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                selectproductpopupwindow.setAdapter(new ArrayAdapter(
                        ActiveTeamViewFragment.this.getContext(),
                        R.layout.check_list_item, team));
                selectproductpopupwindow.setAnchorView(selectteam);
                selectproductpopupwindow.setWidth(300);
                selectproductpopupwindow.setHeight(300);
                selectproductpopupwindow.setModal(true);
                selectproductpopupwindow.show();
            }
        });
        selectproductpopupwindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectteam.setText(team[i]);
                strselectteam = selectteam.getText().toString();
                if(strselectteam.equals("Left Team")){
                    strselectteam = "Left";
                }else if(strselectteam.equals("Right Team")){
                    strselectteam = "Right";
                }else {
                    strselectteam = "All";
                }
                //getUserid = String.valueOf(teamViewList.get(i).getId());
                selectproductpopupwindow.dismiss();
            }
        });
        fromdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(ActiveTeamViewFragment.this.getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        todate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(ActiveTeamViewFragment.this.getContext(), date1, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        searchbyid.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                TextView text = (TextView)getActivity().getCurrentFocus();

                if (text != null && text.length() > 0)
                {
                    View next = text.focusSearch(View.FOCUS_RIGHT); // or FOCUS_FORWARD
                    if (next != null)
                        next.requestFocus();
                    activeteamViewList.clear();
                    mStringUserId = searchbyid.getText().toString().trim();
                    getdata(mStringUserId);
                }
            }

            // afterTextChanged
            @Override
            public void afterTextChanged(Editable s) {}

            // beforeTextChanged
            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {}

        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activeteamViewList.clear();
                mStringUserId = edit_user_id.getText().toString().trim();
                getdata(mStringUserId);


            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectteam.setText("Select Team");
                todate.setText("");
                fromdate.setText("");
                edit_user_id.setText("");
                activeteamViewList.clear();
                mStringUserId = " ";
                getdata(mStringUserId);
            }
        });

        return view;
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
    DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabell();
        }

    };

    private void updateLabell() {
            String myFormat = "dd-MM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        todate.setText(sdf.format(myCalendar.getTime()));
        strtodate = fromdate.getText().toString();
    }

    private void updateLabel() {
        String myFormat = "dd-MM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        fromdate.setText(sdf.format(myCalendar.getTime()));
        strfromdate = fromdate.getText().toString();

    }
    @Override
    public void onResume() {
        super.onResume();
        if (Utils.checkInternetConnection(ActiveTeamViewFragment.this.getContext())) {

            recycler_active_view_team_view.setHasFixedSize(true);
            recycler_active_view_team_view.setLayoutManager(new LinearLayoutManager(ActiveTeamViewFragment.this.getContext(),LinearLayoutManager.VERTICAL,false));
            activeteamViewList.clear();
            mStringUserId="";
            getdata(mStringUserId);
        } else {
            Toast.makeText(ActiveTeamViewFragment.this.getContext(),
                    getString(R.string.no_internet_connection_message), Toast.LENGTH_SHORT).show();
        }
    }

    private void getdata(String mStringUserId) {


        gif.setVisibility(View.VISIBLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        Map<String, String> tvMap = new HashMap<>();

        tvMap.put("start", String.valueOf(0));
        tvMap.put("length", countselected);
        tvMap.put("frm_date",strfromdate);
        tvMap.put("position",strselectteam);
        tvMap.put("to_date",strtodate);
        tvMap.put("user_id",getUserid);
        tvMap.put("search[value]", mStringUserId);

        Emwi apiService = ApiHandler.getApiService();
        final Call<ActiveTeamViewResponseModel> loginCall = apiService.wsGetActiveTeamView(
                "Bearer " + SharedPreferenceUtils.getAccesstoken(ActiveTeamViewFragment.this.getContext()),tvMap);
        loginCall.enqueue(new Callback<ActiveTeamViewResponseModel>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<ActiveTeamViewResponseModel> call,
                                   Response<ActiveTeamViewResponseModel> response) {

                if (response.isSuccessful()) {

                    gif.setVisibility(View.GONE);
                    getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                    ActiveTeamViewResponseModel activeTeamViewResponseModel = response.body();
                    if (activeTeamViewResponseModel.getCode() == Constants.RESPONSE_CODE_OK &&
                            activeTeamViewResponseModel.getStatus().equals("OK")) {
                        activeteamViewList.addAll(activeTeamViewResponseModel.getData().getRecords());
                        if(activeteamViewList.size() > 0) {
                            activeTeamViewAdapter = new ActiveTeamViewAdapter(ActiveTeamViewFragment.this.getContext(), activeteamViewList);
                            recycler_active_view_team_view.setAdapter(activeTeamViewAdapter);
                        }else{
                            Toast.makeText(ActiveTeamViewFragment.this.getContext(), "No data available in table", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ActiveTeamViewFragment.this.getContext(), activeTeamViewResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ActiveTeamViewResponseModel> call,
                                  Throwable t) {
                gif.setVisibility(View.GONE);
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                Toast.makeText(ActiveTeamViewFragment.this.getContext(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }


}

