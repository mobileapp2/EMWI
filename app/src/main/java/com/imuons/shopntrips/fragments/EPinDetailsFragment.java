package com.imuons.shopntrips.fragments;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import com.imuons.shopntrips.R;
import com.imuons.shopntrips.adapters.EPinDetailsAdapter;
import com.imuons.shopntrips.adapters.TeamViewAdapter;
import com.imuons.shopntrips.model.GetProductDatumModel;
import com.imuons.shopntrips.model.GetProductResponseModel;
import com.imuons.shopntrips.model.PinDataModel;
import com.imuons.shopntrips.model.PinRecordModel;
import com.imuons.shopntrips.model.PinResponseModel;
import com.imuons.shopntrips.model.TeamViewRecordModel;
import com.imuons.shopntrips.model.TeamViewResponseModel;
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


public class EPinDetailsFragment extends Fragment {
    @BindView(R.id.edit_from_date)
    EditText fromdate;
    @BindView(R.id.edit_to_date)
    EditText todate;
    @BindView(R.id.selectproduct)
    EditText selectproduct;
    @BindView(R.id.epin)
    EditText epin;
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

    @BindView(R.id.recycler_view_epin_report)
    RecyclerView recycler_view_epin_report;
    private EPinDetailsAdapter ePinDetailsAdapter;
    String countselected = "10";
    private FragmentManager fragmentManager;
    List<String> productnamelist = new ArrayList<>();
    private List<PinRecordModel> epinList = new ArrayList<>();
    final Calendar myCalendar = Calendar.getInstance();
    ListPopupWindow entrypopupwindow,selectproductpopupwindow;
    String entry[] ={"10","50","100","500","1000","5000","10000"};

    String strfromdate = " ",strtodate = " ",getUserid=" ",strPin=" ",getuserid=" ",strselectteam=" ",strproduct,productid="";
    ListPopupWindow productlistPopupWindow;
    List<String> listProductName = new ArrayList<>();
    private List<GetProductDatumModel> productlist = new ArrayList<>();
    public EPinDetailsFragment() {
        // Required empty public constructor
    }


    public static EPinDetailsFragment newInstance() {
        EPinDetailsFragment fragment = new EPinDetailsFragment();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_epin_details, container, false);
        ButterKnife.bind(this, view);


        fragmentManager = getFragmentManager();
        entrypopupwindow = new ListPopupWindow(
                EPinDetailsFragment.this.getContext());
        selectproductpopupwindow = new ListPopupWindow(
                EPinDetailsFragment.this.getContext());
        productlistPopupWindow = new ListPopupWindow(EPinDetailsFragment.this.getContext());
        recycler_view_epin_report.setHasFixedSize(true);
        recycler_view_epin_report.setLayoutManager(new LinearLayoutManager(EPinDetailsFragment.this.getContext(),LinearLayoutManager.VERTICAL,false));
        getselectedentry.setText(countselected);
        dropdoenentry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                entrypopupwindow.setAdapter(new ArrayAdapter(
                        EPinDetailsFragment.this.getContext(),
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
                epinList.clear();
                mStringUserId = "";
                getdata(mStringUserId);
                entrypopupwindow.dismiss();
            }
        });

        productlistPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectproduct.setText(productlist.get(i).getName());
                strproduct = selectproduct.getText().toString();
                productid = String.valueOf(productlist.get(i).getId());
                productlistPopupWindow.dismiss();
            }
        });
        selectproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productlistPopupWindow.setAdapter(new ArrayAdapter(
                        EPinDetailsFragment.this.getContext(),
                        R.layout.check_list_item, listProductName));
                productlistPopupWindow.setAnchorView(selectproduct);
                productlistPopupWindow.setWidth(800);
                productlistPopupWindow.setHeight(800);
                productlistPopupWindow.setModal(true);
                productlistPopupWindow.show();
            }
        });
        fromdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(EPinDetailsFragment.this.getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        todate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(EPinDetailsFragment.this.getContext(), date1, myCalendar
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
                    epinList.clear();
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
                epinList.clear();

                mStringUserId = searchbyid.getText().toString().trim();
                getdata(mStringUserId);


            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectproduct.setText("Select Team");
                todate.setText("");
                fromdate.setText("");
                epin.setText("");
                epinList.clear();
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
        if (Utils.checkInternetConnection(EPinDetailsFragment.this.getContext())) {

            recycler_view_epin_report.setHasFixedSize(true);
            recycler_view_epin_report.setLayoutManager(new LinearLayoutManager(EPinDetailsFragment.this.getContext(),LinearLayoutManager.VERTICAL,false));
            epinList.clear();
            mStringUserId="";
            getProducts();
            getdata(mStringUserId);
        } else {
            Toast.makeText(EPinDetailsFragment.this.getContext(),
                    getString(R.string.no_internet_connection_message), Toast.LENGTH_SHORT).show();
        }
    }

    private void getProducts() {
        //final ProgressDialog pd = ViewUtils.getProgressBar(UserProfileFragment.this.getContext(), "Loading...", "Please wait..!");
        gif.setVisibility(View.VISIBLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        Emwi apiService = ApiHandler.getApiService();
        final Call<GetProductResponseModel> loginCall = apiService.wsGetProducts(
                "Bearer " + SharedPreferenceUtils.getAccesstoken(EPinDetailsFragment.this.getContext()));
        loginCall.enqueue(new Callback<GetProductResponseModel>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<GetProductResponseModel> call,
                                   Response<GetProductResponseModel> response) {
                //                pd.hide();
                gif.setVisibility(View.GONE);
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                if (response.isSuccessful()) {
                    GetProductResponseModel  getProductsResponseModel = response.body();
                    if (getProductsResponseModel.getCode() == Constants.RESPONSE_CODE_OK &&
                            getProductsResponseModel.getStatus().equals("OK")) {
                        productlist.addAll(getProductsResponseModel.getData());
                        listProductName.clear();
                        getproductbyname(getProductsResponseModel.getData());


                    } else {
                        Toast.makeText(EPinDetailsFragment.this.getContext(), getProductsResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetProductResponseModel> call,
                                  Throwable t) {
//                pd.hide();
                gif.setVisibility(View.GONE);
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                Toast.makeText(EPinDetailsFragment.this.getContext(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getproductbyname(List<GetProductDatumModel> data) {
        for (int i = 0; i < data.size(); i++) {
            double bp = data.get(i).getBinaryPer();
            String strbp = String.valueOf(bp);
            listProductName.add(data.get(i).getName()+"("+data.get(i).getCost()+")"+"("+strbp+"%"+")");
        }
    }

    private void getdata(String mStringUserId) {

        strPin  = epin.getText().toString().trim();
        gif.setVisibility(View.VISIBLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        Map<String, String> tvMap = new HashMap<>();

        tvMap.put("start", String.valueOf(0));
        tvMap.put("length", countselected);
        tvMap.put("frm_date",strfromdate);
        tvMap.put("product_id",productid);
        tvMap.put("to_date",strtodate);
        tvMap.put("user_id",getUserid);
        tvMap.put("search[value]", mStringUserId);
         tvMap.put("pin",strPin);

        Emwi apiService = ApiHandler.getApiService();
        final Call<PinResponseModel> loginCall = apiService.wsEPinReport(
                "Bearer " + SharedPreferenceUtils.getAccesstoken(EPinDetailsFragment.this.getContext()),tvMap);
        loginCall.enqueue(new Callback<PinResponseModel>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<PinResponseModel> call,
                                   Response<PinResponseModel> response) {

                if (response.isSuccessful()) {

                    gif.setVisibility(View.GONE);
                    getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                    PinResponseModel pinResponseModel = response.body();
                    if (pinResponseModel.getCode() == Constants.RESPONSE_CODE_OK &&
                            pinResponseModel.getStatus().equals("OK")) {
                        epinList.addAll(pinResponseModel.getData().getRecords());
                        if(epinList.size() > 0) {
                            ePinDetailsAdapter = new EPinDetailsAdapter(EPinDetailsFragment.this.getContext(), epinList);
                            recycler_view_epin_report.setAdapter(ePinDetailsAdapter);
                        }else{
                            Toast.makeText(EPinDetailsFragment.this.getContext(), "No data available in table", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(EPinDetailsFragment.this.getContext(), pinResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<PinResponseModel> call,
                                  Throwable t) {
                gif.setVisibility(View.GONE);
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                Toast.makeText(EPinDetailsFragment.this.getContext(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
