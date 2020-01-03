package com.imuons.shopntrips.fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.imuons.shopntrips.R;
import com.imuons.shopntrips.model.DashboardDataModel;
import com.imuons.shopntrips.model.DashboardResponseModel;
import com.imuons.shopntrips.retrofit.ApiHandler;
import com.imuons.shopntrips.retrofit.Emwi;
import com.imuons.shopntrips.utils.Constants;
import com.imuons.shopntrips.utils.SharedPreferenceUtils;
import com.imuons.shopntrips.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DashboardFragment extends Fragment implements View.OnClickListener {


    @BindView(R.id.leftbv)
    TextView leftbv;
    @BindView(R.id.rightbv)
    TextView rightbv;
    @BindView(R.id.directincome)
    TextView directincome;
    @BindView(R.id.txt_TotalWithdrawal)
    TextView txt_TotalWithdrawal;
    @BindView(R.id.binaryincome)
    TextView binaryincome;
    @BindView(R.id.txt_RipBinaryIncome)
    TextView txt_RipBinaryIncome;
    @BindView(R.id.royalityincome)
    TextView royalityincome;
    @BindView(R.id.leftbvrep)
    TextView leftbvrep;
    @BindView(R.id.rightbvrep)
    TextView rightbvrep;
    @BindView(R.id.txt_TotalIncome)
    TextView txt_TotalIncome;
    @BindView(R.id.btn_DirectNewJoining)
    ImageView btn_DirectNewJoining;
    @BindView(R.id.btn_GenerateTicket)
    ImageView btn_GenerateTicket;
    @BindView(R.id.loader_view)
    View loaderView;
    private FragmentManager fragmentManager;

    public DashboardFragment() {
        // Required empty public constructor
    }

    public static DashboardFragment newInstance() {
        DashboardFragment fragment = new DashboardFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        ButterKnife.bind(this, view);

        btn_DirectNewJoining.setOnClickListener(this);
        btn_GenerateTicket.setOnClickListener(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Utils.checkInternetConnection(DashboardFragment.this.getContext())) {
            getDashBoardData();
        } else {
            Toast.makeText(DashboardFragment.this.getContext(),
                    getString(R.string.no_internet_connection_message), Toast.LENGTH_SHORT).show();
        }
    }


    private void getDashBoardData() {
        loaderView.setVisibility(View.VISIBLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        Emwi apiService = ApiHandler.getApiService();
        final Call<DashboardResponseModel> loginCall = apiService.wsGetDashboardData(
                "Bearer " + SharedPreferenceUtils.getAccesstoken(DashboardFragment.this.getContext()));
        loginCall.enqueue(new Callback<DashboardResponseModel>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<DashboardResponseModel> call,
                                   Response<DashboardResponseModel> response) {

                loaderView.setVisibility(View.GONE);
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                if (response.isSuccessful()) {
                    DashboardResponseModel dashboardResponseModel = response.body();
                    if (dashboardResponseModel.getCode() == Constants.RESPONSE_CODE_OK &&
                            dashboardResponseModel.getStatus().equals("OK")) {

                        setUserData(dashboardResponseModel.getData());

                    } else {
                        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        Toast.makeText(DashboardFragment.this.getContext(), dashboardResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<DashboardResponseModel> call,
                                  Throwable t) {
//        pd.hide();
                loaderView.setVisibility(View.GONE);
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                Toast.makeText(DashboardFragment.this.getContext(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUserData(DashboardDataModel data) {
        leftbv.setText(String.valueOf(data.getLeftBv()));
        rightbv.setText(String.valueOf(data.getRightBv()));
        directincome.setText(String.valueOf(data.getDirectIncome()));
        binaryincome.setText(String.valueOf(data.getBinaryIncome()));
        royalityincome.setText(String.valueOf(data.getRoyalityIncome()));
        txt_RipBinaryIncome.setText(String.valueOf(data.getRepurchaseIncome()));
        leftbvrep.setText(String.valueOf(data.getLeftBvRep()));
        rightbvrep.setText(String.valueOf(data.getRightBvRep()));
        txt_TotalWithdrawal.setText(String.valueOf(data.getTotalWithdraw()));

        txt_TotalIncome.setText(String.valueOf(data.getTotalIncome()));
    }

    @Override
    public void onClick(View view) {
        Fragment fragment = null;

        switch (view.getId()) {
            case R.id.btn_DirectNewJoining:
                fragment = new DirectNewJoiningFragment();
                replaceFragment(fragment);
                break;

            case R.id.btn_GenerateTicket:
                fragment = new GenerateTicketFragment();
                replaceFragment(fragment);
                break;
        }
    }

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
