package com.imuons.shopntrips.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.imuons.shopntrips.R;


public class AwardReportFragment extends Fragment {

    public AwardReportFragment() {
        // Required empty public constructor
    }


    public static AwardReportFragment newInstance() {
        AwardReportFragment fragment = new AwardReportFragment();

        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_award_report, container, false);
        return v;
    }



}
