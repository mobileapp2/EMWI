package com.imuons.shopntrips.fragments;


import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.imuons.shopntrips.R;
import com.imuons.shopntrips.model.DepartmentDataModel;
import com.imuons.shopntrips.model.DepartmentResponseModel;
import com.imuons.shopntrips.model.TicketResponseModel;
import com.imuons.shopntrips.retrofit.ApiHandler;
import com.imuons.shopntrips.retrofit.Emwi;
import com.imuons.shopntrips.utils.Constants;
import com.imuons.shopntrips.utils.SharedPreferenceUtils;
import com.imuons.shopntrips.utils.Utils;
import com.imuons.shopntrips.utils.ViewUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static android.widget.Toast.LENGTH_SHORT;

/**
 * A simple {@link Fragment} subclass.
 */
public class GenerateTicketFragment extends Fragment {

    @BindView(R.id.edit_ticketNo)
    EditText mEditTicketNo;
    @BindView(R.id.edit_title)
    EditText mEditTitle;
    @BindView(R.id.edit_message)
    EditText mEditMessage;

    @BindView(R.id.spinner_department)
    Spinner mSpinnerDepartment;

    @BindView(R.id.text_image_name)
    TextView mTextImageName;

    @BindView(R.id.button_select_image)
    Button mButtonSelectImage;

    @BindView(R.id.button_submit)
    Button mButtonSubmit;


    private String mStringDeptId = "";
    private Uri m_selectedImageUri;
    private String m_selectedPath;

    public static final int MY_PERMISSIONS_REQUEST_ACCESS_STORAGE = 123;
    private static final int SELECT_PICTURE = 100;
    private Uri selectedFileUri;


    private List<DepartmentDataModel> mListCountry = null;


    public GenerateTicketFragment() {
        // Required empty public constructor
    }

    public static GenerateTicketFragment newInstance() {
        GenerateTicketFragment fragment = new GenerateTicketFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_generate_ticket, container, false);
        ButterKnife.bind(this, view);
        registerListener();
        checkPermission();
        getDepartments();
        setRandomNo();
        return view;
    }

    private void getDepartments() {
        final ProgressDialog pd = ViewUtils.getProgressBar(GenerateTicketFragment.this.getContext(), "Loading...", "Please wait..!");

        Emwi apiService = ApiHandler.getApiService();

        final Call<DepartmentResponseModel> loginCall = apiService.wsGetDepartments("Bearer "
                + SharedPreferenceUtils.getLoginObject(
                GenerateTicketFragment.this.getContext()).getData().getAccess_token());

        loginCall.enqueue(new Callback<DepartmentResponseModel>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<DepartmentResponseModel> call,
                                   Response<DepartmentResponseModel> response) {
                pd.hide();
                if (response.isSuccessful()) {
                    DepartmentResponseModel DepartmentResponseModel = response.body();
                    if (DepartmentResponseModel.getCode() == Constants.RESPONSE_CODE_OK &&
                            DepartmentResponseModel.getStatus().equals("OK")) {
                        setSpinnerAdapter(DepartmentResponseModel.getData());
                    } else {
                        Toast.makeText(GenerateTicketFragment.this.getContext(), DepartmentResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<DepartmentResponseModel> call,
                                  Throwable t) {
                pd.hide();
                Toast.makeText(GenerateTicketFragment.this.getContext(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setSpinnerAdapter(List<DepartmentDataModel> data) {
        List<String> listPackageName = new ArrayList<>();
        mListCountry = new ArrayList<DepartmentDataModel>();
        listPackageName.add("Select Department");
        for (int i = 0; i < data.size(); i++) {
            listPackageName.add(data.get(i).getDeptName());
        }
        mListCountry.addAll(data);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(GenerateTicketFragment.this.getContext(),
                R.layout.layout_spinner_item, listPackageName);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerDepartment.setAdapter(spinnerArrayAdapter);
    }

    private void setRandomNo() {
        Random rnd = new Random();
        int randomNo = 100000000 + rnd.nextInt(900000000);
        mEditTicketNo.setText(Integer.toString(randomNo));
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Utils.checkInternetConnection(GenerateTicketFragment.this.getContext())) {
            // getDepartments();
        } else {
            Toast.makeText(GenerateTicketFragment.this.getContext(),
                    getString(R.string.no_internet_connection_message), Toast.LENGTH_SHORT).show();
        }
    }
    private void registerListener() {
        mButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateTitle() && validateMessage() && validateDepartment()) {
                    sendQuery();
                }
            }
        });
        mButtonSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImageChooser();
            }
        });

        mSpinnerDepartment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    mStringDeptId = String.valueOf(mListCountry.get(position - 1).getDeptId());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void sendQuery() {
        final ProgressDialog pd = new ProgressDialog(GenerateTicketFragment.this.getContext());
        pd.setTitle("Sending Query..");
        pd.setMessage("Please wait...!");
        pd.setCancelable(false);
        pd.show();

        RequestBody ticketNo = RequestBody.create(MediaType.parse("text/plain"), mEditTicketNo.getText().toString().trim());
        RequestBody title = RequestBody.create(MediaType.parse("text/plain"), mEditTitle.getText().toString().trim());
        RequestBody message = RequestBody.create(MediaType.parse("text/plain"), mEditMessage.getText().toString().trim());
        RequestBody department = RequestBody.create(MediaType.parse("text/plain"), mStringDeptId);

        MultipartBody.Part body = null;
        try {
            File file = new File(m_selectedPath);
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            body = MultipartBody.Part.createFormData("file", file.getName().replace(" ", "_"), requestFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        HashMap<String, RequestBody> map = new HashMap<>();
        map.put("ticketno", ticketNo);
        map.put("title", title);
        map.put("department", department);
        map.put("address", message);

        Emwi apiService = ApiHandler.getApiService();
        Call<TicketResponseModel> call = apiService.wsSendQuery("Bearer " + SharedPreferenceUtils.getLoginObject(
                GenerateTicketFragment.this.getContext()).getData().getAccess_token(), map, body);
        call.enqueue(new Callback<TicketResponseModel>() {
            @Override
            public void onResponse(Call<TicketResponseModel> call, Response<TicketResponseModel> response) {
                pd.dismiss();
                if (response.isSuccessful()) {
                    // tasks available
                    TicketResponseModel responseState = response.body();
                    if (responseState.getCode() == Constants.RESPONSE_CODE_OK) {
                        Toast.makeText(GenerateTicketFragment.this.getContext(),
                                responseState.getMessage(), LENGTH_SHORT).show();
                        setRandomNo();
                        mEditMessage.setText("");
                        mEditTitle.setText("");
                        mSpinnerDepartment.setSelection(0);
                        mTextImageName.setText("");
                        if (m_selectedPath != null && !m_selectedPath.isEmpty()) {
                            m_selectedPath = null;
                        }
                    } else {
                        Toast.makeText(GenerateTicketFragment.this.getContext(),
                                responseState.getMessage(), LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<TicketResponseModel> call, Throwable t) {
                // something went completely south (like no internet connection)
                Toast.makeText(GenerateTicketFragment.this.getContext(),
                        t.toString(), LENGTH_SHORT).show();
                pd.dismiss();
            }
        });

    }
    void openImageChooser() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_PICTURE);
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public boolean checkPermission() {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(GenerateTicketFragment.this.getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) GenerateTicketFragment.this.getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(GenerateTicketFragment.this.getContext());
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("Permission necessary");
                    alertBuilder.setMessage("Access Location permission is necessary to add image!!!");
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity) GenerateTicketFragment.this.getContext(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_ACCESS_STORAGE);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();
                } else {
                    ActivityCompat.requestPermissions((Activity) GenerateTicketFragment.this.getContext(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_ACCESS_STORAGE);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCESS_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    //code for deny
                    Toast.makeText(GenerateTicketFragment.this.getContext(), "Please allow application to access storage.", LENGTH_SHORT).show();
                }
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {


                try {
                    Log.d("imageCount", "imageCount :" + data.getClipData().getItemCount());
                    if (data.getClipData().getItemCount() <= 10) {
                        for (int i = 0; i < data.getClipData().getItemCount(); i++) {
                            selectedFileUri = data.getClipData().getItemAt(i).getUri();
                            ContentResolver cr = GenerateTicketFragment.this.getContext().getContentResolver();
                            String mime = cr.getType(data.getClipData().getItemAt(i).getUri());
                            String type = null;
                            if (mime == null) {
                                String extension = MimeTypeMap.getFileExtensionFromUrl(data.getClipData().getItemAt(i).getUri().toString());
                                if (extension != null) {
                                    MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
                                    type = mimeTypeMap.getMimeTypeFromExtension(extension);
                                }
                            }
                            String s_path = null;

                            if (mime != null || type != null) {

                                if (getRealPathFromURI(GenerateTicketFragment.this.getContext(), data.getClipData().getItemAt(i).getUri()) == null) {
                                    if (Build.VERSION.SDK_INT < 19)
                                        s_path = getRealPathFromURI_API11to18(GenerateTicketFragment.this.getContext(), data.getClipData().getItemAt(i).getUri());

                                        // SDK > 19 (Android 4.4)
                                    else
                                        s_path = getRealPathFromURI_API19(GenerateTicketFragment.this.getContext(), data.getClipData().getItemAt(i).getUri());
                                } else {
                                    s_path = getRealPathFromURI(GenerateTicketFragment.this.getContext(), data.getClipData().getItemAt(i).getUri());
                                }

                                m_selectedPath = s_path;
                            } else {
                                Toast.makeText(GenerateTicketFragment.this.getContext(), getString(R.string.something_went_wrong),
                                        Toast.LENGTH_SHORT).show();
                            }

                        }
                    } else {
                        Toast.makeText(GenerateTicketFragment.this.getContext(), "You can send maximum 10 images",
                                Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    selectedFileUri = data.getData();
                    ContentResolver cr = GenerateTicketFragment.this.getContext().getContentResolver();
                    String mime = null;
                    try {
                        mime = cr.getType(data.getData());
                    } catch (Exception ec) {
                        ec.printStackTrace();
                    }
                    String type = null;
                    if (mime == null) {
                        String extension = MimeTypeMap.getFileExtensionFromUrl(String.valueOf(data.getData()));
                        if (extension != null) {
                            MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
                            type = mimeTypeMap.getMimeTypeFromExtension(extension);
                        }

                    }
                    String s_path = null;


                    if (mime != null || type != null) {

                        try {
                            if (getRealPathFromURI(GenerateTicketFragment.this.getContext(), data.getData()) == null) {
                                if (Build.VERSION.SDK_INT < 19) {
                                    s_path = getRealPathFromURI_API11to18(GenerateTicketFragment.this.getContext(), data.getData());
                                } else {
                                    // SDK > 19 (Android 4.4)
                                    s_path = getRealPathFromURI_API19(GenerateTicketFragment.this.getContext(), data.getData());
                                }
                            } else {
                                s_path = getRealPathFromURI(GenerateTicketFragment.this.getContext(), data.getData());
                            }
                            m_selectedPath = s_path;

                        } catch (Exception ec) {
                            Toast.makeText(GenerateTicketFragment.this.getContext(), "Unsupported file format",
                                    Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(GenerateTicketFragment.this.getContext(), "Unsupported file format",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                mTextImageName.setText(m_selectedPath);
            }
        }
    }


    @SuppressLint("NewApi")
    public String getRealPathFromURI_API19(Context context, Uri uri) {
        String filePath = "";

        try {
            String wholeID = DocumentsContract.getDocumentId(uri);

            // Split at colon, use second item in the array
            String id = wholeID.split(":")[1];
            String[] column = {MediaStore.Images.Media.DATA};

            // where id is equal to
            String sel = MediaStore.Images.Media._ID + "=?";

            Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    column, sel, new String[]{id}, null);

            int columnIndex = cursor.getColumnIndex(column[0]);

            if (cursor.moveToFirst()) {
                filePath = cursor.getString(columnIndex);
            }
            cursor.close();
        } catch (Exception e) {
            try {
                filePath = getRealPathFromURI(GenerateTicketFragment.this.getContext(), uri);
            } catch (Exception ec) {
                File myFile = new File(uri.getPath());
                filePath = myFile.getAbsolutePath();
            }
        }
        return filePath;
    }


    @SuppressLint("NewApi")
    public String getRealPathFromURI_API11to18(Context context, Uri contentUri) {
        String result = null;


        String[] proj = {MediaStore.Images.Media.DATA};

        try {
            CursorLoader cursorLoader = new CursorLoader(
                    context,
                    contentUri, proj, null, null, null);
            Cursor cursor = cursorLoader.loadInBackground();

            if (cursor != null) {
                int column_index =
                        cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                result = cursor.getString(column_index);
            }
        } catch (Exception e) {
            result = getRealPathFromURI(GenerateTicketFragment.this.getContext(), contentUri);
        }
        return result;
    }

    public String getPathFromURI(Uri contentUri) {
        String[] projection = {MediaStore.MediaColumns.DATA};
        CursorLoader cursorLoader = new CursorLoader(GenerateTicketFragment.this.getContext(), contentUri, projection, null, null, null);
        Cursor cursor = cursorLoader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }


    @SuppressLint("NewApi")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public static String getRealPathFromURI(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        String filePath = "";

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                } else {

                    if (Build.VERSION.SDK_INT > 20) {
                        //getExternalMediaDirs() added in API 21
                        File extenal[] = context.getExternalMediaDirs();
                        if (extenal.length > 1) {
                            filePath = extenal[1].getAbsolutePath();
                            filePath = filePath.substring(0, filePath.indexOf("Android")) + split[1];
                        }
                    } else {
                        filePath = "/storage/" + type + "/" + split[1];
                    }
                    return filePath;
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);

                if (id.startsWith("raw:")) {
                    return id.replaceFirst("raw:", "");
                }
                Uri contentUri = null;
                try {
                    contentUri = ContentUris.withAppendedId(
                            Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return uri.getLastPathSegment();
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    /**
     * .
     *
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }
    
    
    
    
    private boolean validateTitle() {
            String title = mEditTitle.getText().toString().trim();
            if (title.isEmpty()) {
                Toast.makeText(GenerateTicketFragment.this.getContext(), "Empty or Invalid Title", Toast.LENGTH_SHORT).show();
                return false;
            }
            return true;
        }
    private boolean validateMessage() {
        String title = mEditMessage.getText().toString().trim();
        if (title.isEmpty()) {
            Toast.makeText(GenerateTicketFragment.this.getContext(), "Empty or Invalid Message", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean validateDepartment() {
        if (mStringDeptId.isEmpty()) {
            Toast.makeText(GenerateTicketFragment.this.getContext(), "Please select department", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
