package com.imuons.shopntrips.views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.imuons.shopntrips.R;
import com.imuons.shopntrips.adapters.AllTicketsAdapter;
import com.imuons.shopntrips.adapters.OpenTicketsAdapter;
import com.imuons.shopntrips.model.AllTicketsDataModel;
import com.imuons.shopntrips.model.AllTicketsResponseModel;
import com.imuons.shopntrips.retrofit.ApiHandler;
import com.imuons.shopntrips.retrofit.Emwi;
import com.imuons.shopntrips.utils.Constants;
import com.imuons.shopntrips.utils.SharedPreferenceUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuportActivity extends AppCompatActivity implements AllTicketsAdapter.AllTicketsRecyclerViewClickListener, OpenTicketsAdapter.OpenTicketsRecyclerViewClickListener {


    @BindView(R.id.recycler_allTicket)
    RecyclerView recycler_allTickets;
    @BindView(R.id.gif)
    GifImageView gif;
    private List<AllTicketsDataModel> teList = new ArrayList<>();
    AllTicketsAdapter allTicketsAdapter;
    OpenTicketsAdapter openTicketsAdapter;

    private List<AllTicketsDataModel> teList2 = new ArrayList<>();
    @BindView(R.id.recycler_openTicket)
    RecyclerView recycler_OpenTickets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suport);
        ButterKnife.bind(this);
        recycler_allTickets.setHasFixedSize(true);
        recycler_allTickets.setLayoutManager(new LinearLayoutManager(SuportActivity.this, LinearLayoutManager.VERTICAL, false));
        getData();
    }

    private void getData() {
        //final ProgressDialog pd = ViewUtils.getProgressBar(UserProfileFragment.this.getContext(), "Loading...", "Please wait..!");
        gif.setVisibility(View.VISIBLE);
        Emwi apiService = ApiHandler.getApiService();
        final Call<AllTicketsResponseModel> loginCall = apiService.wsAllTickets(
                "Bearer " + SharedPreferenceUtils.getAccesstoken(SuportActivity.this));
        loginCall.enqueue(new Callback<AllTicketsResponseModel>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<AllTicketsResponseModel> call,
                                   Response<AllTicketsResponseModel> response) {
//                pd.hide();
                gif.setVisibility(View.GONE);

                if (response.isSuccessful()) {
                    AllTicketsResponseModel transferEpinReportResponseModel = response.body();
                    if (transferEpinReportResponseModel.getCode() == Constants.RESPONSE_CODE_OK &&
                            transferEpinReportResponseModel.getStatus().equals("OK")) {
                        teList.addAll(transferEpinReportResponseModel.getData());
                        teList2.addAll(transferEpinReportResponseModel.getData());
                        if (teList.size() > 0) {
                            allTicketsAdapter = new AllTicketsAdapter(SuportActivity.this, teList, SuportActivity.this);
                            recycler_allTickets.setAdapter(allTicketsAdapter);

                        } else {
                            Toast.makeText(SuportActivity.this, "No data available in table", Toast.LENGTH_SHORT).show();
                        }
                        if (teList2.size() > 0) {
                            openTicketsAdapter = new OpenTicketsAdapter(SuportActivity.this, teList2, SuportActivity.this);
                            recycler_OpenTickets.setAdapter(openTicketsAdapter);

                        } else {
                            Toast.makeText(SuportActivity.this, "No data available in table", Toast.LENGTH_SHORT).show();
                        }


                    } else {
                        Toast.makeText(SuportActivity.this, transferEpinReportResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<AllTicketsResponseModel> call,
                                  Throwable t) {
//                pd.hide();
                gif.setVisibility(View.GONE);

                Toast.makeText(SuportActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClickCallbacks(View view, int position, String transactionId) {
        Intent intent = new Intent(SuportActivity.this, ChatActivity.class);

        intent.putExtra("btn_Chat", transactionId);
        startActivity(intent);

    }
}
