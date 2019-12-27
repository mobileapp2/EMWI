package com.imuons.shopntrips.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.imuons.shopntrips.R;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class RoiIncomeReportFragment extends Fragment {


    public RoiIncomeReportFragment() {
        // Required empty public constructor
    }

    public static RoiIncomeReportFragment newInstance() {
        RoiIncomeReportFragment fragment = new RoiIncomeReportFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_roi_income_report, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

}
