package com.imuons.shopntrips.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.imuons.shopntrips.R;


public class NumberPicker extends LinearLayout {

    public final int MAX = 99, MIN = 0;
    public Button increaseBTN, decreaseBTN;
    private TextView numberTV;

    public NumberPicker(Context context) {
        super(context);
        init(context);
    }

    public NumberPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NumberPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    //
    //      API Level 21
    //
//    public NumberPicker(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }

    public void init(Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.layout_number_picker, this);

        increaseBTN = (Button) findViewById(R.id.increase_btn);
        decreaseBTN = (Button) findViewById(R.id.decrease_btn);
        numberTV = (TextView) findViewById(R.id.number_tv);

        setNumber(MIN);
    }

    public int getNumber() {
        String numberSTR = numberTV.getText().toString();
        return Integer.parseInt(numberSTR);
    }

    public void setNumber(int number) {
        if (number > MAX)
            numberTV.setText(String.valueOf(MAX));
        else if (number < MIN)
            numberTV.setText(String.valueOf(MIN));
        else
            numberTV.setText(String.valueOf(number));
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        increaseBTN.setEnabled(enabled);
        decreaseBTN.setEnabled(enabled);
    }
}

