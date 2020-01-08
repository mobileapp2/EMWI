package com.imuons.shopntrips.retrofit;

import com.imuons.shopntrips.model.ActiveTeamViewResponseModel;
import com.imuons.shopntrips.model.AddToCartResponseModel;
import com.imuons.shopntrips.model.AllTicketsResponseModel;
import com.imuons.shopntrips.model.AwardReportGetResponse;
import com.imuons.shopntrips.model.AwardReportResponseModel;
import com.imuons.shopntrips.model.BonanzaResponseModel;
import com.imuons.shopntrips.model.ChatRecievedResponseModel;
import com.imuons.shopntrips.model.DirectNewJoinResponseModel;
import com.imuons.shopntrips.model.EPinProductDetailResponseModel;
import com.imuons.shopntrips.model.GetLevelResponseModel;
import com.imuons.shopntrips.model.GetProductFroPinTransferResponseModel;
import com.imuons.shopntrips.model.GetProductsPinRequestResponseModel;
import com.imuons.shopntrips.model.LevelViewResponseModel;
import com.imuons.shopntrips.model.MakeWthdrawReponseModel;
import com.imuons.shopntrips.model.PinRequestReportResponseModel;
import com.imuons.shopntrips.model.PinResponseModel;
import com.imuons.shopntrips.model.ProductResponseModel;
import com.imuons.shopntrips.model.ReceivedPinResponseModel;
import com.imuons.shopntrips.model.RemoveProductResponseModel;
import com.imuons.shopntrips.model.RoyalityIncomeReportResponseModel;
import com.imuons.shopntrips.model.DirectIncomeReportResponseModel;
import com.imuons.shopntrips.model.RepurchaseIncomeReportResponseModel;
import com.imuons.shopntrips.model.ChangePasswordResponseModel;
import com.imuons.shopntrips.model.CheckDownlineUserResponseModel;
import com.imuons.shopntrips.model.CheckUserExistResponseModel;
import com.imuons.shopntrips.model.DashboardResponseModel;
import com.imuons.shopntrips.model.DepartmentResponseModel;
import com.imuons.shopntrips.model.RoyalityQualifiedReportResponseModel;
import com.imuons.shopntrips.model.DirectUserListResponseModel;
import com.imuons.shopntrips.model.DownlineSummaryResponseModel;
import com.imuons.shopntrips.model.DownlineTopUpReportResponseModel;
import com.imuons.shopntrips.model.FundRequestReportResponseModel;
import com.imuons.shopntrips.model.FundRequestResponseModel;
import com.imuons.shopntrips.model.FundTransferReportResponseModel;
import com.imuons.shopntrips.model.FundTransferResponseModel;
import com.imuons.shopntrips.model.GetBalanceReponseModel;
import com.imuons.shopntrips.model.GetCityResponseModel;
import com.imuons.shopntrips.model.GetProductResponseModel;
import com.imuons.shopntrips.model.GetStateResponseModel;
import com.imuons.shopntrips.model.LoginResponseModel;
import com.imuons.shopntrips.model.OTPResponseModel;
import com.imuons.shopntrips.model.RegisterResponseModel;
import com.imuons.shopntrips.model.ResetPasswordResponseModel;
import com.imuons.shopntrips.model.BinaryIncomeReportResponseModel;
import com.imuons.shopntrips.model.SearchTreeResponse;
import com.imuons.shopntrips.model.SendChatResponseModel;
import com.imuons.shopntrips.model.SendMultiplePinResponseModel;
import com.imuons.shopntrips.model.SubmitTopUpReponseModel;
import com.imuons.shopntrips.model.TeamViewResponseModel;
import com.imuons.shopntrips.model.TicketResponseModel;
import com.imuons.shopntrips.model.TopUpReportResponseModel;
import com.imuons.shopntrips.model.TransferEpinReportResponseModel;
import com.imuons.shopntrips.model.TransferPinDetailResponseModel;
import com.imuons.shopntrips.model.TransferPinResponseModel;
import com.imuons.shopntrips.model.TreeViewResponseModel;
import com.imuons.shopntrips.model.UpdateProfileResponseModel;
import com.imuons.shopntrips.model.UsedEpinReportResponseModel;
import com.imuons.shopntrips.model.UserCartResponseModel;
import com.imuons.shopntrips.model.UserPhotosResponseModel;
import com.imuons.shopntrips.model.UserProfileResponseModel;
import com.imuons.shopntrips.model.UserTopUpResponse;
import com.imuons.shopntrips.model.VerifyOTPResponseModel;
import com.imuons.shopntrips.model.WithdrawHistoryReportResponseModel;
import com.imuons.shopntrips.model.WithdrawRequestReportResponseModel;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface Emwi {


    @FormUrlEncoded
    @POST("login")
    Call<LoginResponseModel> wsLogin(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("reset-password")
    Call<ResetPasswordResponseModel> wsResetPassword(@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("checkuserexist")
    Call<CheckUserExistResponseModel> wsCheckUser(@FieldMap Map<String, String> loginMap);

    @FormUrlEncoded
    @POST("register")
    Call<RegisterResponseModel> wsRegister(@FieldMap Map<String, String> map);

    @GET("userDashboard")
    Call<DashboardResponseModel> wsGetDashboardData(@Header("Authorization") String authHeader);


    @FormUrlEncoded
    @POST("getStateByCountry")
    Call<GetStateResponseModel> wsGetStateByCountry(@FieldMap Map<String, String> loginMap);

    @FormUrlEncoded
    @POST("getCityByState")
    Call<GetCityResponseModel> wsGetCityByState(@FieldMap Map<String, String> loginMap);

    @FormUrlEncoded
    @POST("withdraw-amount-report")
    Call<WithdrawRequestReportResponseModel> wsWithdrawalRequestReport(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);

    @FormUrlEncoded
    @POST("withdraw-report")
    Call<WithdrawHistoryReportResponseModel> wsWithdrawalHistoryReport(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);

    @FormUrlEncoded
    @POST("direct_income")
    Call<DirectIncomeReportResponseModel> wsDirectIncomeReport(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);

    @FormUrlEncoded
    @POST("PayoutNoWiseHistoryReport")
    Call<BinaryIncomeReportResponseModel> wsBinaryIncomeReport(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);

    @FormUrlEncoded
    @POST("RepurchaseIncomeReport")
    Call<RepurchaseIncomeReportResponseModel> wsRepurchaseIncomeReport(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);

    @FormUrlEncoded
    @POST("RoyalityQualifiedReport")
    Call<RoyalityQualifiedReportResponseModel> wsRoyalityQualifiedReport(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);


    @FormUrlEncoded
    @POST("RoyalityIncomeReport")
    Call<RoyalityIncomeReportResponseModel> wsRoyalityIncomeReport(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);

    @FormUrlEncoded
    @POST("withdraw-amount-report")
    Call<WithdrawRequestReportResponseModel> wsWithdrawRequest(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);

    @FormUrlEncoded
    @POST("withdraw-report")
    Call<WithdrawHistoryReportResponseModel> wsWithdrawHistory(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);

    @FormUrlEncoded
    @POST("repurchaseProductReport")
    Call<TopUpReportResponseModel> wsTopupReport(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);

    @FormUrlEncoded
    @POST("downlinerepurchaseProductReport")
    Call<DownlineTopUpReportResponseModel> wsDownlineTopupReport(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);

    @FormUrlEncoded
    @POST("fund-request-report")
    Call<FundRequestReportResponseModel> wsFundRequestReport(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);

    @FormUrlEncoded
    @POST("fund-transfer-report")
    Call<FundTransferReportResponseModel> wsFundTransferReport(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);

    @FormUrlEncoded
    @POST("fund-transfer")
    Call<FundTransferResponseModel> wsFundTransfer(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);

    @GET("get_departements")
    Call<DepartmentResponseModel> wsGetDepartments(@Header("Authorization") String authHeader);

    @Multipart
    @POST("insert_ticket")
    Call<TicketResponseModel> wsSendQuery(@Header("Authorization") String authHeader, @PartMap() Map<String, RequestBody> partMap,
                                          @Part MultipartBody.Part file);

    @GET("user-profile")
    Call<UserProfileResponseModel> wsUserProfileInfo(@Header("Authorization") String authHeader);

    @GET("user-photos")
    Call<UserPhotosResponseModel> wsUserPhotos(@Header("Authorization") String authHeader);

    @FormUrlEncoded
    @POST("get_transfer_pin_report")
    Call<TransferEpinReportResponseModel> wsTransferEpinReport(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);

    @FormUrlEncoded
    @POST("change_password")
    Call<ChangePasswordResponseModel> wsChangePassword(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);

    @FormUrlEncoded
    @POST("checkdownlineuser")
    Call<CheckDownlineUserResponseModel> wsCheckDownlineUser(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);

    @GET("getproducts")
    Call<GetProductResponseModel> wsGetProducts(@Header("Authorization") String authHeader);

    @GET("newDirectJoining")
    Call<DirectNewJoinResponseModel> wsNewJoin(@Header("Authorization") String authHeader);
    @FormUrlEncoded
    @POST("getTransferPinDetailReport")
    Call<TransferPinDetailResponseModel> wsGetTransferPins(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);

    @FormUrlEncoded
    @POST("getbalance")
    Call<GetBalanceReponseModel> wsTopUpBalance(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);

    @Multipart
    @POST("fund-request")
    Call<FundRequestResponseModel> wsMakeFundRequest(@Header("Authorization") String authHeader, @PartMap() Map<String, Integer> partMap,
                                                     @Part MultipartBody.Part file);

    @FormUrlEncoded
    @POST("repurchaseProduct")
    Call<SubmitTopUpReponseModel> wsTopup(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);


    @FormUrlEncoded
    @POST("make-withdraw")
    Call<MakeWthdrawReponseModel> wsMakeWithdraw(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);


    @FormUrlEncoded
    @POST("remove_items_from_cart")
    Call<RemoveProductResponseModel> wsRemoveProduct(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);


    @FormUrlEncoded
    @POST("add_to_cart")
    Call<AddToCartResponseModel> wsAddToCart(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);

    @GET("get_user_cart_items")
    Call<UserCartResponseModel> wsGetUserCart(@Header("Authorization") String authHeader);

    @FormUrlEncoded
    @POST("sendOtp-update-user-profile")
    Call<OTPResponseModel> wsSendOTP(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);

    @Multipart
    @POST("send_multiple_pin_request")
    Call<SendMultiplePinResponseModel> wsSendPinRequest(@Header("Authorization") String authHeader, @PartMap() Map<String, okhttp3.RequestBody> partMap,
                                                        @Part MultipartBody.Part file);

    @GET("get_products_for_pin_request")
    Call<GetProductsPinRequestResponseModel> wsGetProductsForPinRequest(@Header("Authorization") String authHeader);

    @FormUrlEncoded
    @POST("checkotp1")
    Call<VerifyOTPResponseModel> wsVeryfiedOTP(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);

    @FormUrlEncoded
    @POST("view-tickets-by-id")
    Call<ChatRecievedResponseModel> wsSupportChat(@Header("Authorization") String authHeader, @FieldMap Map<String, Integer> loginMap);


    @GET("get-user-all-tickets")
    Call<AllTicketsResponseModel> wsAllTickets(@Header("Authorization") String authHeader);

    @FormUrlEncoded
    @POST("update-user-profile")
    Call<UpdateProfileResponseModel> wsUpdateProfile(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);


    @GET("get-user-first-topup")
    Call<UserTopUpResponse> wsTopUP(@Header("Authorization") String authHeader);


    @FormUrlEncoded
    @POST("direct_list")
    Call<DirectUserListResponseModel> wsDirectUserList(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);

    @FormUrlEncoded
    @POST("inactive_epins")
    Call<UsedEpinReportResponseModel> wsUsedEpinReport(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);

    @GET("downline-summary")
    Call<DownlineSummaryResponseModel> wsDownlineSummary(@Header("Authorization") String authHeader);

    @FormUrlEncoded
    @POST("get-team-view")
    Call<TeamViewResponseModel> wsGetTeamView(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);

    @GET("get-level")
    Call<GetLevelResponseModel> wsGetLevel(@Header("Authorization") String authHeader);

    @GET("get_products_for_pin_transfer")
    Call<GetProductFroPinTransferResponseModel> wsPinTransfer(@Header("Authorization") String authHeader);

    @FormUrlEncoded
    @POST("active-team-view")
    Call<ActiveTeamViewResponseModel> wsGetActiveTeamView(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);


    @FormUrlEncoded
    @POST("getlevelviewtree/productbase")
    Call<TreeViewResponseModel> wsGetTreeView(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);

    @FormUrlEncoded
    @POST("get-award")
    Call<AwardReportGetResponse> wsGetAward(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);

    @FormUrlEncoded
    @POST("getlevelviewtree/productbase")
    Call<TreeViewResponseModel> wsGetTree(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);

    @FormUrlEncoded
    @POST("getlevelviewtree/productbase")
    Call<SearchTreeResponse> wsGetTree1(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);

    @FormUrlEncoded
    @POST("getlevelviewtree/crossleg")
    Call<SearchTreeResponse> wsGetTreeByWord(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);

    @FormUrlEncoded
    @POST("getproducts")
    Call<ProductResponseModel> wsGetProduct(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);

    @FormUrlEncoded
    @POST("get-user-order-items")
    Call<EPinProductDetailResponseModel> wsEPinProductDetail(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);

    @FormUrlEncoded
    @POST("user/report/awardwinners")
    Call<AwardReportResponseModel> wsBAwardReport(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);

    @FormUrlEncoded
    @POST("awardwinnerReport")
    Call<BonanzaResponseModel> wsGetBonanzaReport(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);

    @FormUrlEncoded
    @POST("level-view")
    Call<LevelViewResponseModel> wsLevelViewReport(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);


    @FormUrlEncoded
    @POST("transfer_pins")
    Call<TransferPinResponseModel> wsTransferPins(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);

    @FormUrlEncoded
    @POST("get_receive_pin_report")
    Call<ReceivedPinResponseModel> wsReceivePins(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);

    @FormUrlEncoded
    @POST("epin-details")
    Call<PinResponseModel> wsEPinReport(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);

    @FormUrlEncoded
    @POST("get_pin_requests")
    Call<PinRequestReportResponseModel> wsPinrequestReportReport(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);
    @Multipart
    @POST("submit-comment")
    Call<SendChatResponseModel> wsSupportSendMessage(@Header("Authorization") String authHeader, @PartMap() Map<String, okhttp3.RequestBody> partMap
            , @Part MultipartBody.Part file
    );
  /*






    @GET("country")
    Call<GetCountryResponseModel> wsGetCountry();
    @FormUrlEncoded

    @FormUrlEncoded
    @POST("checkPinValid")
    Call<CheckPinValidResponseModel> wsCheckPinValid(@FieldMap Map<String, String> loginMap);
    @FormUrlEncoded
    @POST("checkPanValid")
    Call<CheckPanValidResponseModel> wsCheckPanValid(@FieldMap Map<String, String> loginMap);



    @FormUrlEncoded
    @POST("user/change-password")
    Call<ChangePasswordResponseModel> wsChangePassword(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);

    @FormUrlEncoded
    @POST("user/get_transfer_pin_report")
    Call<TransferPinReportResponseModel> wsTransferEpinReport(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);

    @FormUrlEncoded
    @POST("user/getTransferPinDetailReport")
    Call<TransferPinDetailDataModel> wsGetTransferPins(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);

    @FormUrlEncoded
    @POST("user/get_received_pin_report")
    Call<RecivedPinHistoryResponseModel> wsGetReceivedEpin(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);

    @FormUrlEncoded
    @POST("user/userexist")
    Call<UserExistResponseModel> wsCheckUserexist(@FieldMap Map<String, String> loginMap);

    @FormUrlEncoded
    @POST("user/roi-income")
    Call<CashbackIncomeReportResponseModel> wsROIIncomeReport(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);



    @FormUrlEncoded
    @POST("user/all-epins")
    Call<PinReportResponseModel> wsPinReport(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);

    @FormUrlEncoded
    @POST("user/get_pin_requests")
    Call<PinRequestReportResponseModel> wsPinrequestReportReport(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);

    @FormUrlEncoded
    @POST("user/get_pin_requests_byid")
    Call<PinRequestAmountDepositeResponseModel> wsPinrequestAmtDepositeReport(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);

    @FormUrlEncoded
    @POST("user/get-user-order-items")
    Call<PinRequestDetailResponseModel> wsPinRequestDetail(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);

    @FormUrlEncoded
    @POST("user/send-registration-otp")
    Call<SendRegistrationOtpResponseModel> wsSendOTP(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);

    @GET("user/profile")
    Call<UserProfileResponseModel> wsUserProfileInfo(@Header("Authorization") String authHeader);

    @GET("user/get_products_for_pin_transfer")
    Call<GetProductsPinTransferResponseModel> wsGetProductsForPinTransfer(@Header("Authorization") String authHeader);

//


    @GET("user/get_products_for_pin_request")
    Call<ProductsForPinRequestResponseModel> wsGetProductForPinRequest(@Header("Authorization") String authHeader);

    @FormUrlEncoded
    @POST("user/add_to_cart")
    Call<AddToCartResponseModel> wsAddToCart(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);

    @GET("user/get_user_cart_items")
    Call<UserCartResponseModel> wsGetUserCart(@Header("Authorization") String authHeader);

    @GET("user/dashboard")
    Call<DashboardResponseModel> wsGetDashboardData(@Header("Authorization") String authHeader);

    @GET("user/checkRegistrationPackage")
    Call<CheckRegistrationResponseModel> wsGetregistrationData(@Header("Authorization") String authHeader);

    @FormUrlEncoded
    @POST("user/verify-registration-otp")
    Call<VerifyotpResponseModel> wsVerifyOTP(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);

    @FormUrlEncoded
    @POST("user/update-bank-detail")
    Call<UpdateUserProfileResponseModel> wsUpdateUserProfile(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);

    @FormUrlEncoded
    @POST("user/remove_items_from_cart")
    Call<RemoveProductResponseModel> wsRemoveProduct(@Header("Authorization") String authHeader, @FieldMap Map<String, String> loginMap);


    @GET("user/directOfDirectList")
    Call<DirectOfUserModel> wsDirectOfDirect(@Header("Authorization") String authHeader);

    @Multipart
    @POST("user/send_multiple_pin_request")
    Call<SendMultiplePinResponseModel> wsSendPinRequest(@Header("Authorization") String authHeader, @PartMap() Map<String, okhttp3.RequestBody> partMap,
                                                        @Part MultipartBody.Part file);
*/
}
