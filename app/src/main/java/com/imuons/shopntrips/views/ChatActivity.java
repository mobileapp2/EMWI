package com.imuons.shopntrips.views;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.imuons.shopntrips.R;
import com.imuons.shopntrips.adapters.MessageListAdapter;
import com.imuons.shopntrips.model.ChatMsg;
import com.imuons.shopntrips.model.ChatRecievedResponseModel;
import com.imuons.shopntrips.model.SendChatResponseModel;
import com.imuons.shopntrips.retrofit.ApiHandler;
import com.imuons.shopntrips.retrofit.Emwi;
import com.imuons.shopntrips.utils.SharedPreferenceUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.LENGTH_SHORT;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener, MessageListAdapter.RecyclerViewClickListener {

    String transitionId;
    String m_selectedPath, filenamestr;
    ImageView mUploadImage, btnAttachment, btnSend;
    EditText mEditTextMessage;
    MessageListAdapter messageListAdapter;
    RecyclerView recyclerView;
    private static final String TAG = "SelectImageActivity";
    private static final int SELECT_PICTURE = 100;
    static final Integer READ_EXST = 0x4;
    private List<ChatMsg> mHistoryList = new ArrayList<>();

    String mTextMessage, path, selectedcf;

    public static final String EXTRA_URL = "imageUrl";

    @BindView(R.id.txtpp)
    TextView txtpp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        recyclerView = findViewById(R.id.rvChat);
        btnAttachment = findViewById(R.id.image_attachment);
        btnSend = findViewById(R.id.btSend);
        mEditTextMessage = findViewById(R.id.etMessage);
        askForPermission(Manifest.permission.READ_EXTERNAL_STORAGE, READ_EXST);
        transitionId = getIntent().getStringExtra("btn_Chat");
        registerListener(transitionId);
        handlePermission();

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTextMessage = mEditTextMessage.getText().toString();
                if (!mTextMessage.equals("")) {
                    sendMessage(mTextMessage, transitionId);
                    mEditTextMessage.getText().clear();

                } else {
                    Toast.makeText(ChatActivity.this
                            , "Write a messaage !!", LENGTH_SHORT).show();
                }
                if (path != null && !mTextMessage.equals("")) {
                    registerListener(transitionId);
                    txtpp.setText("No File Choosen");
                }

            }
        });

        btnAttachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedcf = "photo";
                askForPermission(Manifest.permission.READ_EXTERNAL_STORAGE, READ_EXST);

                openImageChooser();
            }
        });
    }

    private void registerListener(String transitionId) {
        int at = Integer.parseInt(transitionId);
        Map<String, Integer> loginMap = new HashMap<>();


        loginMap.put("ticket_id", at);

        Emwi apiService = ApiHandler.getApiService();
        final Call<ChatRecievedResponseModel> loginCall = apiService.wsSupportChat(
                "Bearer " + SharedPreferenceUtils.getAccesstoken(ChatActivity.this.getApplication()),
                loginMap);
        loginCall.enqueue(new Callback<ChatRecievedResponseModel>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<ChatRecievedResponseModel> call,
                                   Response<ChatRecievedResponseModel> response) {
//                pd.hide();
//                gifImageView.setVisibility(View.GONE);
                //   getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                if (response.isSuccessful()) {
                    ChatRecievedResponseModel loginModel = response.body();
                    if (loginModel.getCode() == 200 &&
                            loginModel.getStatus().equals("OK")) {

                        mHistoryList.clear();
                        mHistoryList.addAll(loginModel.getData().getChatMsg());
                        messageListAdapter = new MessageListAdapter(ChatActivity.this, mHistoryList, ChatActivity.this);
                        recyclerView.setLayoutManager(new LinearLayoutManager(ChatActivity.this.getApplication(), LinearLayoutManager.VERTICAL, false));
                        recyclerView.setAdapter(messageListAdapter);
                        recyclerView.scrollToPosition(mHistoryList.size() - 1);

                    }
                }
            }

            @Override
            public void onFailure(Call<ChatRecievedResponseModel> call,
                                  Throwable t) {
//                pd.hide();
//                gifImageView.setVisibility(View.GONE);
                Toast.makeText(ChatActivity.this.getApplication(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void sendMessage(String mTextMessage, String transitionId) {
        int at = Integer.parseInt(transitionId);
        okhttp3.RequestBody transaction_id = okhttp3.RequestBody.create(MediaType.parse("text/plain"), transitionId);

        okhttp3.RequestBody msg = okhttp3.RequestBody.create(MediaType.parse("text/plain"), mTextMessage);
        okhttp3.MultipartBody.Part body = null;
        try {
            File file = new File(m_selectedPath);
            okhttp3.RequestBody requestFile = okhttp3.RequestBody.create(MediaType.parse("multipart/form-data"), file);
            body = okhttp3.MultipartBody.Part.createFormData("file", file.getName().replace(" ", "_"), requestFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        HashMap<String, RequestBody> map = new HashMap<>();
        map.put("ticketno", transaction_id);
        map.put("message", msg);


        Emwi apiService = ApiHandler.getApiService();
        Call<SendChatResponseModel> call = apiService.wsSupportSendMessage("Bearer " + SharedPreferenceUtils.getLoginObject(
                ChatActivity.this.getApplication()).getData().getAccess_token(), map, body); //, body
        call.enqueue(new Callback<SendChatResponseModel>() {
            @Override
            public void onResponse(Call<SendChatResponseModel> call, Response<SendChatResponseModel> response) {

                if (response.isSuccessful()) {
                    // tasks available
                    SendChatResponseModel responseState = response.body();
                    if (responseState.getCode() == 200) {
                        Toast.makeText(ChatActivity.this.getApplication(),
                                responseState.getMessage(), Toast.LENGTH_LONG).show();
                        Toast.makeText(ChatActivity.this, "Message Send", LENGTH_SHORT).show();

                        //   registerListener(transitionId);
                        Intent intent = new Intent(ChatActivity.this, DashboardActivity.class);
                        startActivity(intent);

                    } else {

                        Log.v("Error code 400", response.errorBody().toString());
                        Toast.makeText(ChatActivity.this.getApplication(),
                                responseState.getMessage(), LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ChatActivity.this.getApplication(),
                            "not uploading", LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SendChatResponseModel> call, Throwable t) {
                // something went completely south (like no internet connection)
                Toast.makeText(ChatActivity.this.getApplication(),
                        t.toString(), LENGTH_SHORT).show();

            }
        });
    }

    private void askForPermission(String permission, Integer requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getApplication().checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{permission}, requestCode);
            } else {
                //  Toast.makeText(MakeFundRequestFragment.this.getApplication(), "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void handlePermission() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return;
        }

        if (ContextCompat.checkSelfPermission(Objects.requireNonNull(this), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //ask for permission
            ActivityCompat.requestPermissions(Objects.requireNonNull(this),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    SELECT_PICTURE);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_PICTURE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            // Get the url from data
            Uri selectedImageUri = data.getData();
            if (null != selectedImageUri) {
                // Get the path from the Uri
                path = getPathFromUri(selectedImageUri);
                m_selectedPath = path;
                if (path != null) {
                    filenamestr = path.substring(path.lastIndexOf("/") + 1);

                    if (selectedcf.equals("photo")) {
                        txtpp.setText(filenamestr);
                    }

                } else {
                    Toast.makeText(ChatActivity.this.getApplication(), "Select Image", LENGTH_SHORT).show();
                }


            }
        } else {
            Toast.makeText(ChatActivity.this.getApplication(), "Select Payment Slip",
                    LENGTH_SHORT).show();
        }
    }

    private void openImageChooser() {
        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, SELECT_PICTURE);
    }


    public String getPathFromUri(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getApplication().getApplicationContext().getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.image_attachment:
                selectedcf = "photo";
                askForPermission(Manifest.permission.READ_EXTERNAL_STORAGE, READ_EXST);

                openImageChooser();
                break;

            case R.id.btSend:

                mTextMessage = mEditTextMessage.getText().toString();
                if (!mTextMessage.equals("")) {
                    sendMessage(mTextMessage, transitionId);
                    mEditTextMessage.getText().clear();

                } else {
                    Toast.makeText(this.getApplicationContext(), "Write a messaage !!", LENGTH_SHORT).show();
                }
                if (path != null && !mTextMessage.equals("")) {
                    registerListener(transitionId);
                    txtpp.setText("No File Choosen");
                }
                break;
        }
    }

    @Override
    public void onClickCallback(View view, int position, String url) {
     /*   Intent intent = new Intent(this.getApplicationContext(), ImageViewActivity.class);
        intent.putExtra("uploadImage", url);
        startActivity(intent);*/
    }


}
