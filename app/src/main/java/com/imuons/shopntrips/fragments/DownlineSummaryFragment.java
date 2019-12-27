package com.imuons.shopntrips.fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.imuons.shopntrips.R;
import com.imuons.shopntrips.model.DownlineSummaryDataModel;
import com.imuons.shopntrips.model.DownlineSummaryResponseModel;
import com.imuons.shopntrips.retrofit.ApiHandler;
import com.imuons.shopntrips.retrofit.ShopNTrips;
import com.imuons.shopntrips.utils.Constants;
import com.imuons.shopntrips.utils.SharedPreferenceUtils;
import com.imuons.shopntrips.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DownlineSummaryFragment extends Fragment {
@BindView(R.id.userid)
    EditText userid;
    @BindView(R.id.fullname)
    EditText fullname;
    @BindView(R.id.leftuserid)
    EditText leftuserid;
    @BindView(R.id.rightuserid)
    EditText rightuserid;
    @BindView(R.id.leftbs)
    EditText leftbs;
    @BindView(R.id.rightbs)
    EditText rightbs;
    @BindView(R.id.carryleftbs)
    EditText carryleftbs;
    @BindView(R.id.carryrightbs)
    EditText carryrightbs;

    @BindView(R.id.gif)
    GifImageView gif;
    DownlineSummaryResponseModel downlineSummaryResponseModel;
    public DownlineSummaryFragment() {
        // Required empty public constructor
    }

    public static DownlineSummaryFragment newInstance() {
        DownlineSummaryFragment fragment = new DownlineSummaryFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_downline_summary, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        if (Utils.checkInternetConnection(DownlineSummaryFragment.this.getContext())) {

            getDownline();
        } else {
            Toast.makeText(DownlineSummaryFragment.this.getContext(),
                    getString(R.string.no_internet_connection_message), Toast.LENGTH_SHORT).show();
        }
    }

    private void getDownline() {
        //final ProgressDialog pd = ViewUtils.getProgressBar(UserProfileFragment.this.getContext(), "Loading...", "Please wait..!");
        gif.setVisibility(View.VISIBLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        ShopNTrips apiService = ApiHandler.getApiService();
        final Call<DownlineSummaryResponseModel> loginCall = apiService.wsDownlineSummary(
                "Bearer " + SharedPreferenceUtils.getAccesstoken(DownlineSummaryFragment.this.getContext()));
        loginCall.enqueue(new Callback<DownlineSummaryResponseModel>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<DownlineSummaryResponseModel> call,
                                   Response<DownlineSummaryResponseModel> response) {
                //                    pd.dismiss();
                gif.setVisibility(View.GONE);
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                if (response.isSuccessful()) {
                    downlineSummaryResponseModel = response.body();
                    if (downlineSummaryResponseModel.getCode() == Constants.RESPONSE_CODE_OK &&
                            downlineSummaryResponseModel.getStatus().equals("OK")) {
                        setUserData(downlineSummaryResponseModel.getData());
                    } else {
                        Toast.makeText(DownlineSummaryFragment.this.getContext(), downlineSummaryResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<DownlineSummaryResponseModel> call,
                                  Throwable t) {
                //                    pd.dismiss();
                gif.setVisibility(View.GONE);
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                Toast.makeText(DownlineSummaryFragment.this.getContext(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUserData(DownlineSummaryDataModel data) {
        userid.setText(data.getUserId());
        fullname.setText(data.getFullname());
        int intleftuserid =  data.getLCCount();
        int intrightuserid = data.getRCCount();
        int intleftbs = data.getLBv();
        int intrightbs = data.getRBv();
        int cleft = data.getCarryLeftBv();
        int cright = data.getCarryRightBv();


        leftuserid.setText(String.valueOf(intleftuserid));
        rightuserid.setText(String.valueOf(intrightuserid));

        leftbs.setText(String.valueOf(intleftbs));
        rightbs.setText(String.valueOf(intrightbs));

        carryleftbs.setText(String.valueOf(cleft));
        carryrightbs.setText(String.valueOf(cright));
    }


}
