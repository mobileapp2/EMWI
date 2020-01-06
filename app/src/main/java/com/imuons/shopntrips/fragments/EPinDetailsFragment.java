package com.imuons.shopntrips.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imuons.shopntrips.R;


public class EPinDetailsFragment extends Fragment {


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
        return inflater.inflate(R.layout.fragment_epin_details, container, false);
    }


}
