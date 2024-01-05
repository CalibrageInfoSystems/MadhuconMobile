package com.trst01.locationtracker.services.api;

import com.google.gson.JsonElement;
import com.trst01.locationtracker.database.entity.AddGeoBoundariesTrackingTable;
import com.trst01.locationtracker.models.LoginDTO;
import com.trst01.locationtracker.models.LoginResponseDTO;
import com.trst01.locationtracker.models.MastersResponseDTO;
import com.trst01.locationtracker.models.TransactionSyncResponseDTO;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AppAPI {

    @POST("Master/MasterSync")
    Call<MastersResponseDTO> getMasterSyncDetailsFromServer();

//    @POST("Transaction/TransactionSync/{userId}")
    @POST("Transaction/TransactionSync")
    Call<TransactionSyncResponseDTO> getTransactionDetails(@Query("UserId") String userId,@Query("SeasonCode") String SeasonCode);

//    @POST("Farmer")
//    Call<ResponseBody> addFarmerDetails(@Body String userId);
    @POST("UserTracking")
    Call<String> addTrackingDetails(@Body AddGeoBoundariesTrackingTable addGeoBoundariesTrackingTable);
//    Call<ResponseBody> getTransactionDetails(@Path("userId") String userId, @Header("Authorization") String authHeader);
//

//    // TODO:Get farmer details from server
//    @GET("User/GetUserDetailsByIMEINo/{userId}")
//    Call<LoginResponseDTO> logInService(@Path("userId") String userId);

    @GET("ValidateUserByIMEINo/{userId}")
    Call<LoginResponseDTO> getlogInService(@Path("userId") String userId);

    @GET("ValidateUserByIMEINo/{UserName}/{Password}/{IMEINo}")
    Call<LoginResponseDTO> getlogInService(@Path("UserName") String userId,@Path("Password") String Password,@Path("IMEINo") String IMEINo);

//    @POST("Sync/AddTransactionDetails")
    @POST("TransactionSync")
    Call<String> syncFarmerDetailsDataToServer(@Body TransactionSyncResponseDTO syncPersonalLandAllDetailsRequestDTO);
    @POST("TransactionSync")
    Call<String> syncUserTrackingdata(@Body TransactionSyncResponseDTO syncPersonalLandAllDetailsRequestDTO);



    @Multipart
    @POST("api/TabDatabase/UploadDatabase")
    Call<ResponseBody> uploadDatabasefileDataToServer(@Part("UserId") RequestBody api_key, @Part MultipartBody.Part file);

    @GET("Users/Validate")
    Call<List<LoginDTO>> getLoginCredentials(@Query("UserName") String userId, @Query("Password") String SeasonCode);

    @Multipart
    @POST("Image/UploadImages")
    Call<ResponseBody> uploadFileDataToServer(@Part("UserId") RequestBody api_key, @Part MultipartBody.Part file);

//    Call<ResponseBody> syncFarmerDetailsDataToServer(@Body TransactionSyncResponseDTO syncPersonalLandAllDetailsRequestDTO);
//    Call<ResponseBody> syncFarmerDetailsDataToServer(@Body TransactionSyncResponseDTO syncPersonalLandAllDetailsRequestDTO);
//    Call<ResponseBody> syncFarmerDetailsDataToServer(@Body SyncPersonalLandAllDetailsRequestDTO syncPersonalLandAllDetailsRequestDTO, @Header("Authorization") String authHeader);

////    https://dev.trst01.in/attalurufarmsapi/Sync/GetMasterDetails
////    https://dev.trst01.in/attalurufarmsapi/GetUserDetailsByIMEINo/Id
////    https://dev.trst01.in/attalurufarmsapi/Sync/GetTransactionDetailsByIMEINO/
////    https://dev.trst01.in/attalurufarmsapi/Sync/AddMaster
////    https://dev.trst01.in/attalurufarmsapi/User/ValidateUser
//    // TODO: send Farmer details data to server
////    @POST("Sync/AddMaster")
//    @POST("Sync/AddTransactionDetails")
//    Call<ResponseBody> syncFarmerDetailsDataToServer(@Body SyncPersonalLandAllDetailsRequestDTO syncPersonalLandAllDetailsRequestDTO, @Header("Authorization") String authHeader);
//
//    // TODO:Get pincode  details from server
//    @GET("Village/GetDetailsByPincode/{userId}")
//    Call<List<PinCodeDetailsDataTable>> getPinCodeDetails(@Path("userId") String userId, @Header("Authorization") String authHeader);
//
//    @GET("Village/GetDetailsByPincode/{userId}")
//    Call<List<PinCodeDetailsResponseDTO>> getPinCodeDeatilsFromServer(@Path("userId") String userId, @Header("Authorization") String authHeader);
//
//    @GET("State/GetStateDetails")
//    Call<List<StateListResponseDTO>> getStateDeatilsFromServer(@Header("Authorization") String authHeader);
//
//    @GET("District/GetDistrictDetailsByStateId/{userId}")
//    Call<List<DistricDetailsResponseDTO>> getDistricDeatilsBystateIdFromServer(@Path("userId") String userId, @Header("Authorization") String authHeader);
//
//    @GET("Mandal/GetMandalDetailsByDistrictId/{userId}")
//    Call<List<MandalDetailsResponseDTO>> getMandalDeatilsByDistricIdFromServer(@Path("userId") String userId, @Header("Authorization") String authHeader);
//
//    @GET("Village/GetVillageDetailsByMandalId/{userId}")
//    Call<List<VillageByMandalIdDetailsResponseDTO>> getVillageDeatilsByMandalIdFromServer(@Path("userId") String userId, @Header("Authorization") String authHeader);
//
//    @GET("ClusterHDR/GetClusterNameByVillageId/{userId}")
//    Call<List<ClusterDetailsResponseDTO>> getClusertResponseFromServer(@Path("userId") String userId, @Header("Authorization") String authHeader);
//
//    // TODO:Get pincode  details from server
//    @GET("Village/GetVillageDetailsById/{userId}")
//    Call<List<PinCodeDetailsDataTable>> getMandalDetailsByvillageId(@Path("userId") String userId, @Header("Authorization") String authHeader);
//
//    @GET("Crop/GetCropDetails")
//    Call<List<CropDetailsResponseFromServerDTO>> getCropDetailsResponseFromServer(@Header("Authorization") String authHeader);
//
//    @GET("Village/GetVillageDetailsById/{userId}")
//    Call<List<VillageDetailsResponseDTO>> getVillageDetailsResponseFromServer(@Path("userId") String userId, @Header("Authorization") String authHeader);
//
//    // TODO: 1/21/2022 Callin Get APIS for taking details from server to save in local db
//    @GET("State/GetStateDetails")
//    Call<List<StatesTable>> getStateListFromServerTosaveIntoLocalDB(@Header("Authorization") String authHeader);
//
//    @GET("District/GetDistrictDetails")
//    Call<List<DistrictTable>> getDistricListFromServerTosaveIntoLocalDB(@Header("Authorization") String authHeader);
//
//    @GET("Mandal/GetMandalDetails")
//    Call<List<MandalTable>> getMandalListFromServerTosaveIntoLocalDB(@Header("Authorization") String authHeader);
//
//    @GET("Village/GetVillageDetails")
//    Call<List<VillageTable>> getVillageListFromServerTosaveIntoLocalDB(@Header("Authorization") String authHeader);
//
//    @GET("ClusterHDR/GetClusterHDRDetails")
//    Call<List<ClusterHDRTable>> getClusterHDRListFromServerTosaveIntoLocalDB(@Header("Authorization") String authHeader);
//
//    @GET("ClusterDTL/GetClusterDTLDetails")
//    Call<List<ClusterDTLTable>> getClusterDTLListFromServerTosaveIntoLocalDB(@Header("Authorization") String authHeader);
//
//    @GET("Pincode/GetPincodeDetails")
//    Call<List<PinCodeDetailsListTable>> getPincodeListFromServerTosaveIntoLocalDB(@Header("Authorization") String authHeader);
//
//    @GET("Crop/GetCropDetails")
//    Call<List<CropListTable>> getCropListTableFromServerTosaveIntoLocalDB(@Header("Authorization") String authHeader);
//
//    @GET("Sync/GetMasterDetails")
//    Call<ResponseBody> getListFromServerTosaveIntoLocalDB(@Header("Authorization") String authHeader);
//
//    @GET("Sync/GetMasterDetails")
//    Call<JsonElement> getMasterSyncDetailsFromServer(@Header("Authorization") String authHeader);
//
//    @GET("Sync/GetTransactionDetailsByIMEINO/{userId}")
//    Call<JsonElement> getFarmerAllSyncDataDetailsFromServer(@Path("userId") String userId,@Header("Authorization") String authHeader);
//
////    @POST("TabDatabase/UploadTabDatabase")
////    Call<JsonElement> uploadDatabasefileDataToServer(@Body SyncPersonalLandAllDetailsRequestDTO syncPersonalLandAllDetailsRequestDTO, @Header("Authorization") String authHeader);
////    @Multipart
////    @POST("TabDatabase/UploadTabDatabaseFile")
//    @Multipart
//    @POST("TabDatabase/UploadTabDatabaseFile")
//    Call<ResponseBody> uploadDatabasefileDataToServer(@Part("UserId") RequestBody api_key, @Part MultipartBody.Part file);
//
////    @FormUrlEncoded
////    @POST("TabDatabase/UploadTabDatabaseFile")
////    Call<JsonElement> uploadDatabasefileDataToServer(@Field("UserId") String userID, @Field("DBFile") String DBFile, @Header("Authorization") String authHeader);

}