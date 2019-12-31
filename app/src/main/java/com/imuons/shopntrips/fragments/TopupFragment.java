package com.imuons.shopntrips.fragments;


import android.annotation.SuppressLint;
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
import com.imuons.shopntrips.model.CheckDownlineUserResponseModel;
import com.imuons.shopntrips.model.GetBalanceReponseModel;
import com.imuons.shopntrips.model.GetProductDatumModel;
import com.imuons.shopntrips.model.GetProductResponseModel;
import com.imuons.shopntrips.model.ProductDatumModel;
import com.imuons.shopntrips.model.ProductResponseModel;
import com.imuons.shopntrips.model.SubmitTopUpReponseModel;
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
public class TopupFragment extends Fragment {
@BindView(R.id.topupbal)
    EditText topupbal;
@BindView(R.id.userid)
EditText userid;
@BindView(R.id.fullname)
EditText fullname;
@BindView(R.id.selectproduct)
EditText selectproduct;
@BindView(R.id.submit)
    Button submit;
    private FragmentManager fragmentManager;
    @BindView(R.id.gif)
    GifImageView gif;
    ListPopupWindow productlistPopupWindow;
    List<String> listProductName = new ArrayList<>();
    String strproduct,productid,fullName,strselectproduct,struserid,strbal;
    boolean mSponsorAvailable;
    private List<ProductDatumModel> productlist = new ArrayList<>();
    public TopupFragment() {
        // Required empty public constructor
    }

    public static TopupFragment newInstance() {
        TopupFragment fragment = new TopupFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_topup, container, false);
        ButterKnife.bind(this, view);
        fragmentManager = getFragmentManager();
        productlistPopupWindow = new ListPopupWindow(TopupFragment.this.getContext());

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
                        TopupFragment.this.getContext(),
                        R.layout.check_list_item, listProductName));
                productlistPopupWindow.setAnchorView(selectproduct);
                productlistPopupWindow.setWidth(800);
                productlistPopupWindow.setHeight(800);
                productlistPopupWindow.setModal(true);
                productlistPopupWindow.show();
            }
        });
        userid.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TextView text = (TextView) TopupFragment.this.getActivity().getCurrentFocus();

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
                if (validateFullname() && validateProduct() && validateSponserId()  && validateBal()) {


                    callsubmit();
                }
            }
        });
        return view;
    }

    private boolean validateBal() {
        strbal = topupbal.getText().toString().trim();
        if (strbal.isEmpty() ) {
            Toast.makeText(TopupFragment.this.getContext(),
                    "Balance is empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean validateProduct() {
        strselectproduct = selectproduct.getText().toString().trim();
        if (strselectproduct.isEmpty() || strselectproduct.equals("Select Product")) {
            Toast.makeText(TopupFragment.this.getContext(),
                    "Enter City", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private boolean validateSponserId() {
        struserid= userid.getText().toString().trim();
        if (struserid.isEmpty()) {
            Toast.makeText(TopupFragment.this.getContext(), "Enter valid User Id", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private boolean validateFullname() {
        fullName =  fullname.getText().toString().trim();
        if (fullName.isEmpty()) {
            Toast.makeText(TopupFragment.this.getContext(),
                    getString(R.string.invalid_full_name_message), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void callsubmit() {
        gif.setVisibility(View.VISIBLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        Map<String, String> submitmap = new HashMap<>();


        submitmap.put("product_id",productid);
        submitmap.put("user_id",struserid);
        submitmap.put("type","balance");
        Emwi apiService = ApiHandler.getApiService();
        final Call<SubmitTopUpReponseModel> loginCall = apiService.wsTopup(
                "Bearer " + SharedPreferenceUtils.getAccesstoken(TopupFragment.this.getContext()),submitmap);
        loginCall.enqueue(new Callback<SubmitTopUpReponseModel>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<SubmitTopUpReponseModel> call,
                                   Response<SubmitTopUpReponseModel> response) {
//                pd.hide();
                gif.setVisibility(View.GONE);
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                if (response.isSuccessful()) {
                    SubmitTopUpReponseModel advanceWalletTopupResponseModel = response.body();
                    if (advanceWalletTopupResponseModel.getCode() == Constants.RESPONSE_CODE_OK &&
                            advanceWalletTopupResponseModel.getStatus().equals("OK")) {
                        //call top_up Report
                        fragmentManager.beginTransaction().replace(R.id.content_frame, DownlineTopupReportFragment.newInstance()).commit();
                        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Download Topup Report");

                        Toast.makeText(TopupFragment.this.getContext(), advanceWalletTopupResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(TopupFragment.this.getContext(), advanceWalletTopupResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<SubmitTopUpReponseModel> call,
                                  Throwable t) {
//                pd.hide();
                gif.setVisibility(View.GONE);
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                Toast.makeText(TopupFragment.this.getContext(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void checkSponsorAvailability() {



        final String user;

        user = userid.getText().toString().trim();

        Map<String, String> submitmap = new HashMap<>();


        submitmap.put("user_id",user);

        Emwi apiService = ApiHandler.getApiService();
        final Call<CheckDownlineUserResponseModel> loginCall = apiService.wsCheckDownlineUser(
                "Bearer " + SharedPreferenceUtils.getAccesstoken(TopupFragment.this.getContext()),submitmap);
        loginCall.enqueue(new Callback<CheckDownlineUserResponseModel>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<CheckDownlineUserResponseModel> call,
                                   Response<CheckDownlineUserResponseModel> response) {

                if (response.isSuccessful()) {
                    CheckDownlineUserResponseModel loginModel = response.body();
                 //   sponsorname = loginModel.getData().getFullname();
                    fullname.setText(loginModel.getData().getFullname());
                    if (loginModel.getCode() == 404) {
                        mSponsorAvailable = true;

                        Toast.makeText(TopupFragment.this.getContext(), loginModel.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        mSponsorAvailable = false;
                        // Toast.makeText(Registration.this, loginModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<CheckDownlineUserResponseModel> call,
                                  Throwable t) {

                Toast.makeText(TopupFragment.this.getContext(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Utils.checkInternetConnection(TopupFragment.this.getContext())) {


            getTopUpBal();
getProducts();
        } else {
            Toast.makeText(TopupFragment.this.getContext(),
                    getString(R.string.no_internet_connection_message), Toast.LENGTH_SHORT).show();
        }
    }

    private void getTopUpBal() {
        gif.setVisibility(View.VISIBLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        Map<String, String> topmap = new HashMap<>();

        topmap.put("wallet", "topup");
        Emwi apiService = ApiHandler.getApiService();
        final Call<GetBalanceReponseModel> loginCall = apiService.wsTopUpBalance(
                "Bearer " + SharedPreferenceUtils.getAccesstoken(TopupFragment.this.getContext()),topmap);
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
                        Toast.makeText(TopupFragment.this.getContext(), getBalanceReponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetBalanceReponseModel> call,
                                  Throwable t) {
//                pd.hide();
                gif.setVisibility(View.GONE);
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                Toast.makeText(TopupFragment.this.getContext(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getProducts() {
        //final ProgressDialog pd = ViewUtils.getProgressBar(UserProfileFragment.this.getContext(), "Loading...", "Please wait..!");
        gif.setVisibility(View.VISIBLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        Map<String, String> loginmap = new HashMap<>();

        loginmap.put("product_type", "Repurchase");
        Emwi apiService = ApiHandler.getApiService();
        final Call<ProductResponseModel> loginCall = apiService.wsGetProduct(
                "Bearer " + SharedPreferenceUtils.getAccesstoken(TopupFragment.this.getContext()),loginmap);
        loginCall.enqueue(new Callback<ProductResponseModel>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<ProductResponseModel> call,
                                   Response<ProductResponseModel> response) {
                //                pd.hide();
                gif.setVisibility(View.GONE);
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                if (response.isSuccessful()) {
                    ProductResponseModel  getProductsResponseModel = response.body();
                    if (getProductsResponseModel.getCode() == Constants.RESPONSE_CODE_OK &&
                            getProductsResponseModel.getStatus().equals("OK")) {
                        productlist.addAll(getProductsResponseModel.getData());
                        listProductName.clear();
                        getproductbyname(getProductsResponseModel.getData());


                    } else {
                        Toast.makeText(TopupFragment.this.getContext(), getProductsResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductResponseModel> call,
                                  Throwable t) {
//                pd.hide();
                gif.setVisibility(View.GONE);
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                Toast.makeText(TopupFragment.this.getContext(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getproductbyname(List<ProductDatumModel> data) {
        for (int i = 0; i < data.size(); i++) {
            double bp = data.get(i).getBinaryPer();
            String strbp = String.valueOf(bp);
            listProductName.add(data.get(i).getName()+"("+data.get(i).getCost()+")"+"("+strbp+"%"+")");
        }
    }
}
