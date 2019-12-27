package com.imuons.shopntrips.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.imuons.shopntrips.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowPassword extends AppCompatActivity {
    @BindView(R.id.login)
    TextView login;
    @BindView(R.id.userid)
    TextView userid;
    @BindView(R.id.password)
    TextView password;
    String useridstr,passwordstr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_password);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        useridstr = intent.getStringExtra("userid");
        passwordstr = intent.getStringExtra("password");
        userid.setText(useridstr);
        password.setText(passwordstr);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login  =  new Intent(ShowPassword.this,LoginActivity.class);
                startActivity(login);

            }
        });

    }
    @Override
    public void onBackPressed() {

    }

}
