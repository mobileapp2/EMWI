package com.imuons.shopntrips.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imuons.shopntrips.R;



public class EPinRequestReportFragment extends Fragment {


    public EPinRequestReportFragment() {
        // Required empty public constructor
    }


    public static EPinRequestReportFragment newInstance() {
        EPinRequestReportFragment fragment = new EPinRequestReportFragment();

        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_epin_request_report, container, false);
        return view;
    }


}
