package com.imuons.shopntrips.fragments;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.imuons.shopntrips.R;
import com.imuons.shopntrips.model.SearchTreeResponse;
import com.imuons.shopntrips.model.TreeViewDataModel;
import com.imuons.shopntrips.model.TreeViewResponseModel;
import com.imuons.shopntrips.retrofit.ApiHandler;
import com.imuons.shopntrips.retrofit.Emwi;
import com.imuons.shopntrips.utils.Constants;
import com.imuons.shopntrips.utils.SharedPreferenceUtils;
import com.imuons.shopntrips.utils.Utils;
import com.imuons.shopntrips.utils.ViewUtils;
import com.imuons.shopntrips.views.NotPresentActivity;

import java.util.HashMap;
import java.util.Map;

import de.blox.treeview.BaseTreeAdapter;
import de.blox.treeview.PositionDataModel;
import de.blox.treeview.TreeNode;
import de.blox.treeview.TreeView;
import pl.droidsonroids.gif.GifImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TreeViewFragment extends Fragment {

    private TreeView mTreeView;
    private BaseTreeAdapter adapter;
    private EditText mEditUserId;
    private ImageView mImageBack, mImageSearch;
    GifImageView gifImageView;
    String strId = "";
    String mStringUserId="";
    TreeViewResponseModel treeViewResponseModel;

    public TreeViewFragment() {
        // Required empty public constructor
    }

    public static TreeViewFragment newInstance() {
        TreeViewFragment fragment = new TreeViewFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tree_view, container, false);

        initializeViews(view);
        registerListeners();
        if (Utils.checkInternetConnection(TreeViewFragment.this.getContext())) {
            getTreeView("");
            // treeSearch(SharedPreferenceUtils.getUserName(TreeViewFragment.this.getContext()));
        } else {
            Toast.makeText(TreeViewFragment.this.getContext(),
                    getString(R.string.no_internet_connection_message), Toast.LENGTH_SHORT).show();
        }

        adapter = new BaseTreeAdapter<ViewHolder>(TreeViewFragment.this.getContext()
                , R.layout.layout_tree_node) {
            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(View view) {
                return new ViewHolder(view);
            }

            @Override
            public void onBindViewHolder(ViewHolder viewHolder, PositionDataModel data, int position) {
                viewHolder.mTextView.setText(data.getUser_id());
                if (data.getUser_id().equals("Not Available")) {
                    viewHolder.mImageStatus.setImageResource(R.drawable.ic_absent);
                }
                try {

                    if (data.getImage().contains("absent")) {
                        viewHolder.mImageStatus.setImageResource(R.drawable.ic_absent);
                    } else if (data.getImage().contains("block")) {
                        viewHolder.mImageStatus.setImageResource(R.drawable.ic_block);
                    } else if (data.getImage().contains("no_topup")) {
                        viewHolder.mImageStatus.setImageResource(R.drawable.ic_not_paid);
                    } else if (data.getImage().contains("present")) {
                        viewHolder.mImageStatus.setImageResource(R.drawable.ic_active);
                    } else if (data.getUser_id().contains("Not Available")) {
                        viewHolder.mImageStatus.setImageResource(R.drawable.ic_absent);
                    }

                    if (data.getImage().equalsIgnoreCase("active")) {
                        viewHolder.mImageStatus.setImageResource(R.drawable.ic_active);
                    } else if (data.getImage().equalsIgnoreCase("inactive")) {
                        viewHolder.mImageStatus.setImageResource(R.drawable.ic_absent);
                    } else if (data.getImage().equalsIgnoreCase("blocked")) {
                        viewHolder.mImageStatus.setImageResource(R.drawable.ic_block);
                    }
                } catch (Exception e) {
                    viewHolder.mImageStatus.setImageResource(R.drawable.ic_not_paid);
                }
            }
        };
        mTreeView.setAdapter(adapter);

        return view;
    }

    private void registerListeners() {
        mImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.checkInternetConnection(TreeViewFragment.this.getContext())) {
                    getTreeView("");
                    //treeSearch(SharedPreferenceUtils.getUserName(TreeViewFragment.this.getContext()));
                } else {
                    Toast.makeText(TreeViewFragment.this.getContext(),
                            getString(R.string.no_internet_connection_message), Toast.LENGTH_SHORT).show();
                }
            }
        });

        mImageSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.checkInternetConnection(TreeViewFragment.this.getContext())) {
                    if (validateUserId()) {
                        mStringUserId  = mEditUserId.getText().toString().trim();
                       getTreeView(mStringUserId);
                      //  findTree(id);

                    }
                } else {
                    Toast.makeText(TreeViewFragment.this.getContext(),
                            getString(R.string.no_internet_connection_message), Toast.LENGTH_SHORT).show();
                }
            }
        });


    /*  mEditUserId.addTextChangedListener(new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

          }

          @Override
          public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
              if (mEditUserId.getText().toString().length()>1){
                  searchByWord();
              }

          }

          @Override
          public void afterTextChanged(Editable editable) {

          }
      });
*/

        mTreeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                PositionDataModel model = adapter.getNode(position).getData();
                if (model.getUser_id().contains("Not Available") || model.getImage().contains("absent")) {
                    Intent intent = new Intent(TreeViewFragment.this.getActivity(), NotPresentActivity.class);
                    startActivity(intent);
                } else if (model.getImage().contains("block")) {
                    showDialog(model);
                } else if (model.getImage().contains("no_topup")) {
                    showDialog(model);
                } else if (model.getImage().contains("active")) {
                    showDialog(model);
                } else if (model.getImage().contains("inactive")) {
                    showDialog(model);
                } else if (model.getImage().contains("blocked")) {
                    showDialog(model);
                } else if (model.getImage().contains("present")) {
                    showDialog(model);
                }

            }
        });

    }

    private void searchByWord() {

        String s = mEditUserId.getText().toString().trim();
        Map<String, String> roiMap = new HashMap<>();
        roiMap.put("id", s);
        roiMap.put("Content-Length:", "23");
        Emwi apiService = ApiHandler.getApiService();

        final Call<SearchTreeResponse> loginCall = apiService.wsGetTreeByWord("Bearer "
                + SharedPreferenceUtils.getLoginObject(
                TreeViewFragment.this.getContext()).getData().getAccess_token(), roiMap);

        loginCall.enqueue(new Callback<SearchTreeResponse>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<SearchTreeResponse> call,
                                   Response<SearchTreeResponse> response) {

                if (response.isSuccessful()) {
                    SearchTreeResponse treeViewResponseModel = response.body();
                    if (treeViewResponseModel.getCode() == 404) {
                        Toast.makeText(TreeViewFragment.this.getContext(), "User not available", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(TreeViewFragment.this.getContext(), "User available", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<SearchTreeResponse> call,
                                  Throwable t) {

                //     Toast.makeText(TreeViewFragment.this.getContext(), treeViewResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(TreeViewFragment.this.getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void getTreeView(String mStringUserId) {
        final ProgressDialog pd = ViewUtils.getProgressBar(TreeViewFragment.this.getContext(), "Loading...", "Please wait..!");

        Map<String, String> roiMap = new HashMap<>();
        roiMap.put("id", mStringUserId);
        roiMap.put("Content-Length:", "17");
        Emwi apiService = ApiHandler.getApiService();

        final Call<TreeViewResponseModel> loginCall = apiService.wsGetTree("Bearer "
                + SharedPreferenceUtils.getLoginObject(
                TreeViewFragment.this.getContext()).getData().getAccess_token(), roiMap);

        loginCall.enqueue(new Callback<TreeViewResponseModel>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<TreeViewResponseModel> call,
                                   Response<TreeViewResponseModel> response) {
                pd.hide();
                if (response.isSuccessful()) {
                    TreeViewResponseModel treeViewResponseModel = response.body();
                    if (treeViewResponseModel.getCode() == Constants.RESPONSE_CODE_OK &&
                            treeViewResponseModel.getStatus().equals("OK")) {
                        displayTreeView(treeViewResponseModel.getData());
                    } else {
                        Toast.makeText(TreeViewFragment.this.getContext(), treeViewResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<TreeViewResponseModel> call,
                                  Throwable t) {
                pd.hide();
           //     Toast.makeText(TreeViewFragment.this.getContext(), treeViewResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(TreeViewFragment.this.getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showDialog(PositionDataModel details) {
        // custom dialog
        final Dialog dialog = new Dialog(TreeViewFragment.this.getContext());
        dialog.setContentView(R.layout.layout_custom_alert_treeview);
        TextView userId = (TextView) dialog.findViewById(R.id.text_user_id);
        TextView name = (TextView) dialog.findViewById(R.id.text_name);
        TextView sopnsorId = (TextView) dialog.findViewById(R.id.text_sponsor_id);
        TextView DOJ = (TextView) dialog.findViewById(R.id.text_date_of_joining);
        TextView leftId = (TextView) dialog.findViewById(R.id.text_left_id);
        TextView rightId = (TextView) dialog.findViewById(R.id.text_right_id);
        TextView leftBV = (TextView) dialog.findViewById(R.id.text_left_bv);
        TextView rightBV = (TextView) dialog.findViewById(R.id.text_right_bv);
        TextView repurchaseLeftBV = (TextView) dialog.findViewById(R.id.text_repurchase_left_bv);
        TextView repurchaseRightBV = (TextView) dialog.findViewById(R.id.text_repurchase_right_bv);
        TextView productCost = (TextView) dialog.findViewById(R.id.text_product_cost);
        ImageView imageClose = dialog.findViewById(R.id.image_close);

        try {
            userId.setText(details.getUser_id());
            name.setText(details.getFullname());
            sopnsorId.setText(details.getSponser_id());
            DOJ.setText(details.getDateOfjoining());
            leftId.setText(details.getLeft_id());
            rightId.setText(details.getRight_id());
            leftBV.setText(details.getLeft_bv());
            rightBV.setText(details.getRight_bv());
            repurchaseLeftBV.setText(details.getL_bv_rep());
            repurchaseRightBV.setText(details.getR_bv_rep());
            productCost.setText(details.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }
        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }


    private boolean validateUserId() {
        String userId = mEditUserId.getText().toString().trim();
        if (userId.isEmpty()) {
            Toast.makeText(TreeViewFragment.this.getContext(), getString(R.string.empty_user_id), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void initializeViews(View view) {
        mTreeView = view.findViewById(R.id.treeview);
        mImageBack = view.findViewById(R.id.image_back);
        mImageSearch = view.findViewById(R.id.image_search);
        mEditUserId = view.findViewById(R.id.edit_user_id);
        gifImageView = view.findViewById(R.id.gif);
    }



    private void displayTreeView(TreeViewDataModel data) {
        PositionDataModel model = new PositionDataModel();
        model.setUser_id(data.getUser().getUserId());
        model.setImage(data.getUser().getImage());
        model.setSponser_id(data.getUser().getSponsorId());
        model.setFullname(data.getUser().getFullname());
//        model.setStatus(data.getUser().getCost());
        try {
            model.setDateOfjoining(data.getUser().getEntryTime().split(" ")[0]);
        } catch (Exception e) {
            model.setDateOfjoining("");
        }
        model.setLeft_id(String.valueOf(data.getUser().getLCCount().toString()));
        model.setRight_id(String.valueOf(data.getUser().getRCCount().toString()));
        model.setLeft_bv(String.valueOf(data.getUser().getLBv().toString()));
        model.setRight_bv(String.valueOf(data.getUser().getRBv().toString()));
        model.setL_bv_rep(String.valueOf(data.getUser().getLeftBvRep().toString()));
        model.setR_bv_rep(String.valueOf(data.getUser().getRightBvRep().toString()));
        TreeNode rootNode = new TreeNode(model);

        model = new PositionDataModel();
        model.setUser_id(data.getTreeData().get(0).getLevel().get(0).getUserId());
        model.setImage(data.getTreeData().get(0).getLevel().get(0).getImage());
        model.setSponser_id(data.getTreeData().get(0).getLevel().get(0).getSponsorId());
//        model.setFullname(data.getTreeData().get(0).getLevel().get(0).getSponsorId());
        model.setFullname(data.getUser().getFullname());
//        model.setStatus(data.getUser().getCost());
        try {
            model.setDateOfjoining(data.getTreeData().get(0).getLevel().get(0).getEntryTime().split(" ")[0]);
        } catch (Exception e) {
            model.setDateOfjoining("");
        }
        model.setLeft_id(String.valueOf(data.getTreeData().get(0).getLevel().get(0).getLCCount().toString()));
        model.setRight_id(String.valueOf(data.getTreeData().get(0).getLevel().get(0).getRCCount().toString()));
        model.setLeft_bv(String.valueOf(data.getTreeData().get(0).getLevel().get(0).getLBv().toString()));
        model.setRight_bv(String.valueOf(data.getTreeData().get(0).getLevel().get(0).getRBv().toString()));
        model.setL_bv_rep(String.valueOf(data.getTreeData().get(0).getLevel().get(0).getLeftBvRep().toString()));
        model.setR_bv_rep(String.valueOf(data.getTreeData().get(0).getLevel().get(0).getRightBvRep().toString()));
        TreeNode nodeLevelOneLeft = new TreeNode(model);

        model = new PositionDataModel();
        model.setUser_id(data.getTreeData().get(0).getLevel().get(1).getUserId());
        model.setImage(data.getTreeData().get(0).getLevel().get(1).getImage());
        model.setSponser_id(data.getTreeData().get(0).getLevel().get(1).getSponsorId());
        model.setFullname(data.getUser().getFullname());
//        model.setStatus(data.getUser().getCost());
        try {
            model.setDateOfjoining(data.getTreeData().get(0).getLevel().get(1).getEntryTime().split(" ")[0]);
        } catch (Exception e) {
            model.setDateOfjoining("");
        }
        model.setLeft_id(String.valueOf(data.getTreeData().get(0).getLevel().get(1).getLCCount().toString()));
        model.setRight_id(String.valueOf(data.getTreeData().get(0).getLevel().get(1).getRCCount().toString()));
        model.setLeft_bv(String.valueOf(data.getTreeData().get(0).getLevel().get(1).getLBv().toString()));
        model.setRight_bv(String.valueOf(data.getTreeData().get(0).getLevel().get(1).getRBv().toString()));
        model.setL_bv_rep(String.valueOf(data.getTreeData().get(0).getLevel().get(1).getLeftBvRep().toString()));
        model.setR_bv_rep(String.valueOf(data.getTreeData().get(0).getLevel().get(1).getRightBvRep().toString()));
        TreeNode nodeLevelOneRight = new TreeNode(model);

        rootNode.addChild(nodeLevelOneLeft);
        rootNode.addChild(nodeLevelOneRight);

        model = new PositionDataModel();
        model.setUser_id(data.getTreeData().get(1).getLevel().get(0).getUserId());
        model.setImage(data.getTreeData().get(1).getLevel().get(0).getImage());
        model.setSponser_id(data.getTreeData().get(1).getLevel().get(0).getSponsorId());
        model.setFullname(data.getUser().getFullname());
//        model.setStatus(data.getUser().getCost());
        try {
            model.setDateOfjoining(data.getTreeData().get(1).getLevel().get(0).getEntryTime().split(" ")[0]);
        } catch (Exception e) {
            model.setDateOfjoining("");
        }
        model.setLeft_id(String.valueOf(data.getTreeData().get(1).getLevel().get(0).getLCCount().toString()));
        model.setRight_id(String.valueOf(data.getTreeData().get(1).getLevel().get(0).getRCCount().toString()));
        model.setLeft_bv(String.valueOf(data.getTreeData().get(1).getLevel().get(0).getLBv().toString()));
        model.setRight_bv(String.valueOf(data.getTreeData().get(1).getLevel().get(0).getRBv().toString()));
        model.setL_bv_rep(String.valueOf(data.getTreeData().get(1).getLevel().get(0).getLeftBvRep().toString()));
        model.setR_bv_rep(String.valueOf(data.getTreeData().get(1).getLevel().get(0).getRightBvRep().toString()));
        TreeNode nodeLevelTwoLeftChildOne = new TreeNode(model);

        model = new PositionDataModel();
        model.setUser_id(data.getTreeData().get(1).getLevel().get(1).getUserId());
        model.setImage(data.getTreeData().get(1).getLevel().get(1).getImage());
        model.setSponser_id(data.getTreeData().get(1).getLevel().get(1).getSponsorId());
        model.setFullname(data.getUser().getFullname());
//        model.setStatus(data.getUser().getCost());
        try {
            model.setDateOfjoining(data.getTreeData().get(1).getLevel().get(1).getEntryTime().split(" ")[0]);
        } catch (Exception e) {
            model.setDateOfjoining("");
        }
        model.setLeft_id(String.valueOf(data.getTreeData().get(1).getLevel().get(1).getLCCount().toString()));
        model.setRight_id(String.valueOf(data.getTreeData().get(1).getLevel().get(1).getRCCount().toString()));
        model.setLeft_bv(String.valueOf(data.getTreeData().get(1).getLevel().get(1).getLBv().toString()));
        model.setRight_bv(String.valueOf(data.getTreeData().get(1).getLevel().get(1).getRBv().toString()));
        model.setL_bv_rep(String.valueOf(data.getTreeData().get(1).getLevel().get(1).getLeftBvRep().toString()));
        model.setR_bv_rep(String.valueOf(data.getTreeData().get(1).getLevel().get(1).getRightBvRep().toString()));
        TreeNode nodeLevelOneLeftChildTwo = new TreeNode(model);

        nodeLevelOneLeft.addChild(nodeLevelTwoLeftChildOne);
        nodeLevelOneLeft.addChild(nodeLevelOneLeftChildTwo);

        model = new PositionDataModel();
        model.setUser_id(data.getTreeData().get(1).getLevel().get(2).getUserId());
        model.setImage(data.getTreeData().get(1).getLevel().get(2).getImage());
        model.setSponser_id(data.getTreeData().get(1).getLevel().get(2).getSponsorId());
        model.setFullname(data.getUser().getFullname());
//        model.setStatus(data.getUser().getCost());
        try {
            model.setDateOfjoining(data.getTreeData().get(1).getLevel().get(2).getEntryTime().split(" ")[0]);
        } catch (Exception e) {
            model.setDateOfjoining("");
        }
        model.setLeft_id(String.valueOf(data.getTreeData().get(1).getLevel().get(2).getLCCount().toString()));
        model.setRight_id(String.valueOf(data.getTreeData().get(1).getLevel().get(2).getRCCount().toString()));
        model.setLeft_bv(String.valueOf(data.getTreeData().get(1).getLevel().get(2).getLBv().toString()));
        model.setRight_bv(String.valueOf(data.getTreeData().get(1).getLevel().get(2).getRBv().toString()));
        model.setL_bv_rep(String.valueOf(data.getTreeData().get(1).getLevel().get(2).getLeftBvRep().toString()));
        model.setR_bv_rep(String.valueOf(data.getTreeData().get(1).getLevel().get(2).getRightBvRep().toString()));
        TreeNode nodeLevelTwoRightChildOne = new TreeNode(model);

        model = new PositionDataModel();
        model.setUser_id(data.getTreeData().get(1).getLevel().get(3).getUserId());
        model.setImage(data.getTreeData().get(1).getLevel().get(3).getImage());
        model.setSponser_id(data.getTreeData().get(1).getLevel().get(3).getSponsorId());
        model.setFullname(data.getUser().getFullname());
//        model.setStatus(data.getUser().getCost());
        try {
            model.setDateOfjoining(data.getTreeData().get(1).getLevel().get(3).getEntryTime().split(" ")[0]);
        } catch (Exception e) {
            model.setDateOfjoining("");
        }
        model.setLeft_id(String.valueOf(data.getTreeData().get(1).getLevel().get(3).getLCCount().toString()));
        model.setRight_id(String.valueOf(data.getTreeData().get(1).getLevel().get(3).getRCCount().toString()));
        model.setLeft_bv(String.valueOf(data.getTreeData().get(1).getLevel().get(3).getLBv().toString()));
        model.setRight_bv(String.valueOf(data.getTreeData().get(1).getLevel().get(3).getRBv().toString()));
        model.setL_bv_rep(String.valueOf(data.getTreeData().get(1).getLevel().get(3).getLeftBvRep().toString()));
        model.setR_bv_rep(String.valueOf(data.getTreeData().get(1).getLevel().get(3).getRightBvRep().toString()));
        TreeNode nodeLevelOneRightChildTwo = new TreeNode(model);

        nodeLevelOneRight.addChild(nodeLevelTwoRightChildOne);
        nodeLevelOneRight.addChild(nodeLevelOneRightChildTwo);

        adapter.setRootNode(rootNode);
    }

    private class ViewHolder {
        TextView mTextView;
        ImageView mImageStatus;

        ViewHolder(View view) {
            mTextView = view.findViewById(R.id.textView);
            mImageStatus = view.findViewById(R.id.image_status);
        }
    }

}
