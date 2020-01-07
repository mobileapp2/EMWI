package com.imuons.shopntrips.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imuons.shopntrips.R;


public class EPinAmtDepositedFragment extends Fragment {


    public EPinAmtDepositedFragment() {
        // Required empty public constructor
    }

    public static EPinAmtDepositedFragment newInstance() {
        EPinAmtDepositedFragment fragment = new EPinAmtDepositedFragment();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_epin_amt_deposited, container, false);
    }


}