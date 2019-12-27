package com.imuons.shopntrips.views;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.imuons.shopntrips.R;
import com.imuons.shopntrips.adapters.CustomExpandableListAdapter;
import com.imuons.shopntrips.fragments.ActiveTeamViewFragment;
import com.imuons.shopntrips.fragments.AwardIncomeReportFragment;
import com.imuons.shopntrips.fragments.BinaryIncomeReportFragment;
import com.imuons.shopntrips.fragments.BinaryRoiReportFragment;
import com.imuons.shopntrips.fragments.BusinessplanFragment;
import com.imuons.shopntrips.fragments.DashboardFragment;
import com.imuons.shopntrips.fragments.DirectRoiIncomeReportFragment;
import com.imuons.shopntrips.fragments.DirectUserListFragment;
import com.imuons.shopntrips.fragments.DownlineSummaryFragment;
import com.imuons.shopntrips.fragments.DownloadPDFFragment;
import com.imuons.shopntrips.fragments.DownlineTopupReportFragment;
import com.imuons.shopntrips.fragments.FundRequestFragment;
import com.imuons.shopntrips.fragments.FundRequestReportFragment;
import com.imuons.shopntrips.fragments.FundTransferFragment;
import com.imuons.shopntrips.fragments.FundTransferReportFragment;
import com.imuons.shopntrips.fragments.InvoiceFragment;
import com.imuons.shopntrips.fragments.ProfileFragment;
import com.imuons.shopntrips.fragments.ROIIncomeFragment;
import com.imuons.shopntrips.fragments.TeamViewFragment;
import com.imuons.shopntrips.fragments.TopupReportFragment;
import com.imuons.shopntrips.fragments.TopupFragment;
import com.imuons.shopntrips.fragments.TreeViewFragment;
import com.imuons.shopntrips.fragments.WithdrawRequestReportFragment;
import com.imuons.shopntrips.fragments.WithdrawHistoryReportFragment;
import com.imuons.shopntrips.model.ExpandableListModel;
import com.imuons.shopntrips.utils.SharedPreferenceUtils;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.ButterKnife;

public class DashboardActivity extends AppCompatActivity {
    private ExpandableListAdapter mExpandableListAdapter;
    private ArrayList<ExpandableListModel> mExpandableListTitle;
    private HashMap<String, ArrayList<String>> mExpandableListData;
    ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private ExpandableListView mExpandableListView;
    private FragmentManager fragmentManager;
    private long lastPressedTime;
    private static final int PERIOD = 2000;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.title_activity_dashboard);
        }
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mExpandableListView = findViewById(R.id.navList);
        mExpandableListView.setIndicatorBounds(mExpandableListView.getRight() + 120, mExpandableListView.getWidth());
        setupToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        prepareListData();





        fragmentManager = getSupportFragmentManager();
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        fragmentManager.beginTransaction().replace(R.id.content_frame, DashboardFragment.newInstance()).commit();
        setupDrawerToggle();
        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                int index = parent.getFlatListPosition(ExpandableListView.getPackedPositionForChild(groupPosition, childPosition));
                parent.setItemChecked(index, true);


                return true;
            }
        });

        mExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {


                switch (groupPosition) {
                    case 0:
                        fragmentManager.beginTransaction().replace(R.id.content_frame, DashboardFragment.newInstance()).commit();
                        getSupportActionBar().setTitle("Dashboard");
                        mDrawerLayout.closeDrawers();

                        break;
                    case 1:
                        fragmentManager.beginTransaction().replace(R.id.content_frame, ProfileFragment.newInstance()).commit();
                        getSupportActionBar().setTitle("Profile");
                        mDrawerLayout.closeDrawers();
                        break;
//                    case 2:
//                        fragmentManager.beginTransaction().replace(R.id.content_frame, InvoiceFragment.newInstance()).commit();
//                        getSupportActionBar().setTitle("Invoice");
//                        mDrawerLayout.closeDrawers();
//                        break;


                    case 7:
                        fragmentManager.beginTransaction().replace(R.id.content_frame, BusinessplanFragment.newInstance()).commit();
                        getSupportActionBar().setTitle("Business Plan");
                        mDrawerLayout.closeDrawers();
                        break;

                    case 8:
                        fragmentManager.beginTransaction().replace(R.id.content_frame, DownloadPDFFragment.newInstance()).commit();
                        getSupportActionBar().setTitle(" Download PDF");
                        mDrawerLayout.closeDrawers();
                        break;

                    case 9:
                        showAlertDialog();
                        break;
                }
                return false;
            }
        });

        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                switch (groupPosition) {

                    case 2:
                        switch (childPosition) {

                            case 0:
                                fragmentManager.beginTransaction().replace(R.id.content_frame, TreeViewFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("Tree View");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;
                            case 1:
                                fragmentManager.beginTransaction().replace(R.id.content_frame, TeamViewFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("Team View");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;
                            case 2:
                                fragmentManager.beginTransaction().replace(R.id.content_frame, ActiveTeamViewFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("Activite Team View");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;
                            case 3:
                                fragmentManager.beginTransaction().replace(R.id.content_frame, DirectUserListFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("Direct User List");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;
                            case 4:
                                fragmentManager.beginTransaction().replace(R.id.content_frame, DownlineSummaryFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("Downline Summary");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;

                        }
                        break;

                    case 3:
                        switch (childPosition) {
                            case 0:
                                fragmentManager.beginTransaction().replace(R.id.content_frame, BinaryIncomeReportFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("Binary Income Report");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;

                            case 1:
                                fragmentManager.beginTransaction().replace(R.id.content_frame, ROIIncomeFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("ROI Income");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;
                            case 2:
                                fragmentManager.beginTransaction().replace(R.id.content_frame, BinaryRoiReportFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("Binary ROI Report");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;
                            case 3:
                                fragmentManager.beginTransaction().replace(R.id.content_frame, DirectRoiIncomeReportFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("Direct ROI Income Report");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;
                            case 4:
                                fragmentManager.beginTransaction().replace(R.id.content_frame, AwardIncomeReportFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("Award Income Report");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;


                        }
                        break;

                    case 4:
                        switch (childPosition) {
                            case 0:
                                fragmentManager.beginTransaction().replace(R.id.content_frame, FundRequestFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("Fund Request");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;

                            case 1:
                                fragmentManager.beginTransaction().replace(R.id.content_frame, FundRequestReportFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("Fund Request Report");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;
                            case 2:
                                fragmentManager.beginTransaction().replace(R.id.content_frame, FundTransferFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("Fund Transfer");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;
                            case 3:
                                fragmentManager.beginTransaction().replace(R.id.content_frame, FundTransferReportFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("Fund Transfer Report");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;

                        }
                        break;

                    case 5:
                        switch (childPosition) {
                            case 0:
                                fragmentManager.beginTransaction().replace(R.id.content_frame, TopupFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("Topup");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;

                            case 1:
                                fragmentManager.beginTransaction().replace(R.id.content_frame, TopupReportFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("Topup Report");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;
                            case 2:
                                fragmentManager.beginTransaction().replace(R.id.content_frame, DownlineTopupReportFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("Download Topup Report");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;

                        }
                        break;

                    case 6:
                        switch (childPosition) {
                            case 0:
                                fragmentManager.beginTransaction().replace(R.id.content_frame, WithdrawRequestReportFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("Withdraw Request");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;

                            case 1:
                                fragmentManager.beginTransaction().replace(R.id.content_frame, WithdrawHistoryReportFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("Withdraw History");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;


                        }
                        break;
                }
                mDrawerLayout.closeDrawers();
                return false;

            }
        });

        mExpandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                for (int i = 0; i < mExpandableListView.getCount(); i++) {
                    if (i != groupPosition) {
                        mExpandableListView.collapseGroup(i);
                    }
                }
            }
        });
    }


    private void showAlertDialog() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(DashboardActivity.this, AlertDialog.THEME_HOLO_LIGHT);
        builder1.setTitle("Exit");
        builder1.setMessage("Are you sure you want to Logout ?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        DashboardActivity.this.finish();
                        SharedPreferenceUtils.clearPreferences(DashboardActivity.this);
                        SharedPreferenceUtils.clearID(DashboardActivity.this);
                        SharedPreferenceUtils.clearAccess_Token(DashboardActivity.this);

                        startActivity(new Intent(DashboardActivity.this, LoginActivity.class));
                        finish();
                        Toast.makeText(DashboardActivity.this, "Logout Successfully", Toast.LENGTH_SHORT).show();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    void setupDrawerToggle() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggle.syncState();
    }

    private void prepareListData() {
        mExpandableListTitle = new ArrayList<ExpandableListModel>();
        mExpandableListData = new HashMap<String, ArrayList<String>>();


        mExpandableListTitle.add(new ExpandableListModel(R.drawable.ic_drawer_home, "Home"));
        mExpandableListTitle.add(new ExpandableListModel(R.drawable.ic_drawer_profile, "Profile"));
//        mExpandableListTitle.add(new ExpandableListModel(R.drawable.ic_drawer_epin, "Invoice"));
        mExpandableListTitle.add(new ExpandableListModel(R.drawable.ic_drawer_network, "Network"));
        mExpandableListTitle.add(new ExpandableListModel(R.drawable.ic_drawer_income_report, "Income Report"));
        mExpandableListTitle.add(new ExpandableListModel(R.drawable.ic_drawer_repurchase, "Manage Fund"));
        mExpandableListTitle.add(new ExpandableListModel(R.drawable.ic_drawer_income_report, "Top Up"));
        mExpandableListTitle.add(new ExpandableListModel(R.drawable.ic_drawer_withdraw, "Withdraw"));
        mExpandableListTitle.add(new ExpandableListModel(R.drawable.ic_drawer_business_plan, "Business Plan"));
        mExpandableListTitle.add(new ExpandableListModel(R.drawable.ic_drawer_income_report, "Download PDF"));
        mExpandableListTitle.add(new ExpandableListModel(R.drawable.ic_drawer_logout, "Logout"));


        ArrayList<String> incomereport = new ArrayList<String>();
        incomereport.add("Binary Income Report");
        incomereport.add("ROI Income");
        incomereport.add("Binary ROI Report");
        incomereport.add("Direct ROI Income Report");
        incomereport.add("Award Income Report");

        ArrayList<String> network = new ArrayList<String>();
        network.add("Tree View");
        network.add("Team View");
        network.add("Active Team Member");
        network.add("Direct User List");
        network.add("Downline Summary");

        ArrayList<String> Managefund = new ArrayList<String>();
        Managefund.add("Fund Request");
        Managefund.add("Fund Request Report");
        Managefund.add("Fund Transfer");
        Managefund.add("Fund Transfer Report");

        ArrayList<String> Topup = new ArrayList<String>();
        Topup.add("Topup");
        Topup.add("Topup Report");
        Topup.add("Download Topup Report");

        ArrayList<String> withdraw = new ArrayList<String>();
        withdraw.add("Withdraw Request");
        withdraw.add("Withdraw History");

        ArrayList<String> allTransactions = new ArrayList<String>();
        mExpandableListData.put(mExpandableListTitle.get(0).title, allTransactions);
        mExpandableListData.put(mExpandableListTitle.get(1).title, allTransactions);
        mExpandableListData.put(mExpandableListTitle.get(2).title, network);
        mExpandableListData.put(mExpandableListTitle.get(3).title, incomereport);
        mExpandableListData.put(mExpandableListTitle.get(4).title, Managefund);
        mExpandableListData.put(mExpandableListTitle.get(5).title, Topup);
        mExpandableListData.put(mExpandableListTitle.get(6).title, withdraw);
        mExpandableListData.put(mExpandableListTitle.get(7).title, allTransactions);
        mExpandableListData.put(mExpandableListTitle.get(8).title, allTransactions );
        mExpandableListData.put(mExpandableListTitle.get(9).title, allTransactions);
//        mExpandableListData.put(mExpandableListTitle.get(10).title, );


        mExpandableListAdapter = new CustomExpandableListAdapter(DashboardActivity.this, mExpandableListTitle, mExpandableListData);
        mExpandableListView.setAdapter(mExpandableListAdapter);


    }

    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            switch (event.getAction()) {
                case KeyEvent.ACTION_DOWN:
                    if (event.getDownTime() - lastPressedTime < PERIOD) {
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Press again to exit.",
                                Toast.LENGTH_SHORT).show();
                        lastPressedTime = event.getEventTime();
                    }
                    return true;
            }
        }
        return false;
    }
}
