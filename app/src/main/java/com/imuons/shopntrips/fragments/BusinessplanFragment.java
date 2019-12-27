package com.imuons.shopntrips.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewSwitcher;

import com.imuons.shopntrips.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class BusinessplanFragment extends Fragment {
@BindView(R.id.simpleImageSwitcher)
    ImageSwitcher sw;
@BindView(R.id.buttonNext)
Button buttonNext;

    int imageIds[] = {R.drawable.bpone, R.drawable.bptwo, R.drawable.bpthree};
    int count = imageIds.length;
    // to keep current Index of ImageID array
    int currentIndex = 0;

    public BusinessplanFragment() {
        // Required empty public constructor
    }
    public static BusinessplanFragment newInstance() {
        BusinessplanFragment fragment = new BusinessplanFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_businessplan, container, false);
        ButterKnife.bind(this, view);

        sw.setFactory(new ViewSwitcher.ViewFactory() {
            @Override

            public View makeView() {
                // TODO Auto-generated method stub

                // Create a new ImageView and set it's properties
                ImageView myView = new ImageView(BusinessplanFragment.this.getContext());
                // set Scale type of ImageView to Fit Center
                myView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                // set the Height And Width of ImageView To FIll PARENT
                myView.setLayoutParams(new ImageSwitcher.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));

                return myView;
            }
        });

        Animation in = AnimationUtils.loadAnimation(BusinessplanFragment.this.getContext(), android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(BusinessplanFragment.this.getContext(), android.R.anim.slide_out_right);

        // set the animation type to ImageSwitcher
        sw.setInAnimation(in);
        sw.setOutAnimation(out);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex++;
                //  Check If index reaches maximum then reset it
                if (currentIndex == count)
                    currentIndex = 0;
                sw.setImageResource(imageIds[currentIndex]); //
            }
        });
      sw.setImageResource(imageIds[0]);
        return view;
            }




}
