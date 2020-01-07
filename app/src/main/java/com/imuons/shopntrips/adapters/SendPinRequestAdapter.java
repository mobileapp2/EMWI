package com.imuons.shopntrips.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;


import com.imuons.shopntrips.R;
import com.imuons.shopntrips.fragments.AddCartFragment;
import com.imuons.shopntrips.fragments.SendEpinRequestFragment;
import com.imuons.shopntrips.model.AddToCartResponseModel;
import com.imuons.shopntrips.model.GetProductsPinRequestDataModel;
import com.imuons.shopntrips.retrofit.ApiHandler;
import com.imuons.shopntrips.retrofit.Emwi;
import com.imuons.shopntrips.utils.Constants;
import com.imuons.shopntrips.utils.SharedPreferenceUtils;
import com.imuons.shopntrips.views.NumberPicker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendPinRequestAdapter extends RecyclerView.Adapter<SendPinRequestAdapter.SendPinRequestAdapterHolder> {
    SendEpinRequestFragment sendEpinRequestFragment;
   private List<GetProductsPinRequestDataModel> spList = new ArrayList<>();
   private Context context;
    String datefromurl;
    private static final int SELECT_PICTURE = 100;
    private FragmentManager fragmentManager;

    private static int currentPosition = 0;
    public SendPinRequestAdapter(SendEpinRequestFragment sendEpinRequestFragment, List<GetProductsPinRequestDataModel> spList) {
        this.spList = spList;
        this.sendEpinRequestFragment = sendEpinRequestFragment;
        Log.e("list: ", spList.size() + "");
    }

    @Override
    public SendPinRequestAdapter.SendPinRequestAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(sendEpinRequestFragment.getContext()).inflate(R.layout.adapter_send_pin_request, parent, false);
        return new SendPinRequestAdapter.SendPinRequestAdapterHolder(v);
    }

    @Override
    public void onBindViewHolder(final SendPinRequestAdapter.SendPinRequestAdapterHolder holder, final int position) {

        final GetProductsPinRequestDataModel getProductsPinRequestDataModel = spList.get(position);

        holder.hiddenlayout.setVisibility(View.GONE);

        if (position % 2 == 0) {
            holder.llmain.setBackgroundColor(Color.parseColor("#f5f4f4"));
        } else {
            holder.llmain.setBackgroundColor(Color.parseColor("#c9caca"));

        }

        holder.srno.setText(String.valueOf(position + 1));


        holder.price.setText(getProductsPinRequestDataModel.getCost());
      //  int noofcount = getProductsPinRequestDataModel.getCount();
       // holder.count.setText(String.valueOf(noofcount));
        holder.productname.setText(getProductsPinRequestDataModel.getName());



        //if the position is equals to the item position which is to be expanded
        if (currentPosition == position) {
          //  holder.eye.setImageResource(R.drawable.oprneye);
            //creating an animation
            Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down);

            //toggling visibility
            holder.hiddenlayout.setVisibility(View.VISIBLE);

            //adding sliding effect
            holder.hiddenlayout.startAnimation(slideDown);
        } else {
          //  holder.eye.setImageResource(R.drawable.closeeye);
        }

        holder.llmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //getting the position of the item to expand it
                currentPosition = position;
                //reloding the list
                notifyDataSetChanged();
            }
        });
        holder.cart.increaseBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPickerButtonOperation(true, holder);


            }
        });
        holder.cart.decreaseBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPickerButtonOperation(false, holder);

            }
        });

        holder.addtocarttxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager = ((AppCompatActivity)view.getContext()).getSupportFragmentManager();
                String getid = String.valueOf(getProductsPinRequestDataModel.getId());
                String getNo = String.valueOf(holder.cart.getNumber());
                getData(getid,getNo);

//                SharedPreferenceUtils.storeProductId(context, getid);
//                SharedPreferenceUtils.storeCartnumber(context, getNo);
//                fragmentManager.beginTransaction().replace(R.id.content_frame, AddCartFragment.newInstance()).commit();
//                ((AppCompatActivity)view.getContext()).getSupportActionBar().setTitle("Send E-Pin Request");
            }
        });

    }

    private void getData(String getid, String getNo) {
        Map<String, String> edMap = new HashMap<>();

        edMap.put("id",getid);
        edMap.put("quantity", getNo);


        Emwi apiService = ApiHandler.getApiService();
        final Call<AddToCartResponseModel> loginCall = apiService.wsAddToCart(
                "Bearer " + SharedPreferenceUtils.getAccesstoken(context),edMap);
        loginCall.enqueue(new Callback<AddToCartResponseModel>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<AddToCartResponseModel> call,
                                   Response<AddToCartResponseModel> response) {

                if (response.isSuccessful()) {
                    AddToCartResponseModel addToCartResponseModel = response.body();
                    if (addToCartResponseModel.getCode() == Constants.RESPONSE_CODE_OK &&
                            addToCartResponseModel.getStatus().equals("OK")) {
                        fragmentManager.beginTransaction().replace(R.id.content_frame, AddCartFragment.newInstance()).commit();
               ((AppCompatActivity)context).getSupportActionBar().setTitle("Send E-Pin Request");
 //                       getusercartitem();
//                         acList.add(addToCartResponseModel.getData());
//                        if(acList.size() > 0) {
//                            addToCartAdapter = new AddToCartAdapter(AddCartFragment.this, acList);
//                            recycler_view_addtocart.setAdapter(addToCartAdapter);
//                        }else{
//                            Toast.makeText(AddCartFragment.this.getContext(), "No data available in table", Toast.LENGTH_SHORT).show();
//                        }
                    } else {
                        fragmentManager.beginTransaction().replace(R.id.content_frame, SendEpinRequestFragment.newInstance()).commit();
                        ((AppCompatActivity)context).getSupportActionBar().setTitle("Add Product To Cart");
                        Toast.makeText(context, addToCartResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<AddToCartResponseModel> call,
                                  Throwable t) {
                Toast.makeText(context,"Something went Wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void numberPickerButtonOperation(boolean isIncrease, SendPinRequestAdapterHolder holder) {
        if (isIncrease) {
            int number = holder.cart.getNumber();
            holder.cart.setNumber(number + 1);
        } else {
            int number = holder.cart.getNumber();
            holder.cart.setNumber(number - 1);
        }
    }
    @Override
    public int getItemCount() {
        return spList.size();
    }

    public class SendPinRequestAdapterHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.srno)
        TextView srno;
        @BindView(R.id.productname)
        TextView productname;
        @BindView(R.id.price)
        TextView price;


        @BindView(R.id.cart)
        NumberPicker cart;
        @BindView(R.id.addtocarttxt)
        TextView addtocarttxt;

        @BindView(R.id.eye)
        ImageView eye;


        @BindView(R.id.llmain)
        LinearLayout llmain;
        @BindView(R.id.hiddenlayout)
        LinearLayout hiddenlayout;
        View container;


        public SendPinRequestAdapterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            container = itemView;
        }
    }
}
