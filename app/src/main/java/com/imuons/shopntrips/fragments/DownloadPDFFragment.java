package com.imuons.shopntrips.fragments;


import android.Manifest;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.imuons.shopntrips.R;
import com.joanzapata.pdfview.PDFView;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.DOWNLOAD_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class DownloadPDFFragment extends Fragment {
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
    private final int REQUEST_PERMISSIONS = 10001;
@BindView(R.id.downloadpdf)
    Button downloadpdf;
String urltosend;
    DownloadManager dm;
    public DownloadPDFFragment() {
        // Required empty public constructor
    }
    public static DownloadPDFFragment newInstance() {
        DownloadPDFFragment fragment = new DownloadPDFFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_download_pdf, container, false);
        ButterKnife.bind(this, view);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkReadStoragePermission();
        }

        requestThePermissions();

        downloadpdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                urltosend = "https://www.shopntripsindia.com/replica/shopntripsindia/public/PDF-SentimentUpdated.pdf";

                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(urltosend));

// in order for this if to run, you must use the android 3.2 to compile your app
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    request.allowScanningByMediaScanner();
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                }
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "test");

// get download service and enqueue file
                DownloadManager manager = (DownloadManager) getContext().getSystemService(Context.DOWNLOAD_SERVICE);
                manager.enqueue(request);
                Toast.makeText(DownloadPDFFragment.this.getContext(), "Downloading", Toast.LENGTH_SHORT).show();
            }
        });




//        File downloadDir = new File(Environment.getExternalStoragePublicDirectory(
//                Environment.DIRECTORY_DOWNLOADS).getAbsolutePath());
//        String path = downloadDir.getPath();

//        File yourFile = new File(downloadDir, urltosend);


        return view;
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkReadStoragePermission() {
        int hasWriteContactsPermission = ContextCompat.checkSelfPermission(DownloadPDFFragment.this.getContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE_ASK_PERMISSIONS);
        } else {

        }
    }




    private void requestThePermissions() {
        int locationPermission = ActivityCompat
                .checkSelfPermission(DownloadPDFFragment.this.getContext(),
                        android.Manifest.permission.ACCESS_FINE_LOCATION);

        if (locationPermission == PackageManager.PERMISSION_GRANTED) {
//            Toast.makeText(this, "No need of permissions", Toast.LENGTH_SHORT).show();
            //setLocation();
        } else {
            ActivityCompat.requestPermissions(DownloadPDFFragment.this.getActivity(),
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_PERMISSIONS);
        }
    }






}




