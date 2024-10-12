package com.app.secureglobal.network
import com.app.secureglobal.model.saveresidenceverification.SaveVerificationDataDetail
import com.app.secureglobal.model.changepassword.GetChangePasswordResponse
import com.app.secureglobal.model.dashboard.getDashboardApiResponse.GetDashboardApiResponse
import com.app.secureglobal.model.finalSubmission.GetFinalSubmissionApiResponse
import com.app.secureglobal.model.finalSubmission.SaveFinalSubmissionData
import com.app.secureglobal.model.getAcceptRejectResponse.GetAcceptRejectResponse
import com.app.secureglobal.model.getDocketForScan.GetDocketForScanResponse
import com.app.secureglobal.model.getDocketforPickupResponse.GetDocketForPickupResponse
import com.app.secureglobal.model.getFiResidencePicture.GetFiResidencePictureResponse
import com.app.secureglobal.model.getMenuListResponse.GetMenuListResponse
import com.app.secureglobal.model.getMenuWebUrlResponse.GetMenuURLResponse
import com.app.secureglobal.model.getPreNeighbourData.GetPreNeighbourResponse
import com.app.secureglobal.model.getSavePickupDataResponse.GetSavePickupResponse
import com.app.secureglobal.model.getSavePickupDataResponse.SaveDocketPickupData
import com.app.secureglobal.model.getSaveResidenceVerificationResponse.GetSaveResidenceVerificationResponse
import com.app.secureglobal.model.getSaveScanResponse.GetSaveScanData
import com.app.secureglobal.model.getSaveScanResponse.GetSaveScanResponse
import com.app.secureglobal.model.getSaveScanResponse.SaveScanData
import com.app.secureglobal.model.getverificationDetailResponse.GetVerificationDetailResponse
import com.app.secureglobal.model.getUserProfileData.GetUserProfileResponse
import com.app.secureglobal.model.getmasterData.GetMasterApiResponse
import com.app.secureglobal.model.login.GetLoginResponseModel
import com.app.secureglobal.model.pendingRequest.GetPendingRequestResponse
import com.app.secureglobal.model.registerDevice.GetDeviceRegistrationResponse
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query


interface ApiInterface {

    @Headers("secret-key: AiPC9BjkCyDFQXbSkoZcgqH3hpacA76123J8322EpesabBDjs23RTdsq8L123278956565450")
    @POST("api/login/mobileuserlogin")
    fun login(@Body requestBody: RequestBody): Observable<GetLoginResponseModel>

    @Headers("secret-key: MiPC9BjkCyGDFQXbSkoZcgqH3hpacKA76123J8322EpesabBDjsF23RTdsq8L123278956565452")
    @POST("api/MobileRegistrationRequest/Save")
    fun registerUser(@Body requestBody: RequestBody): Observable<GetDeviceRegistrationResponse>

    @GET("api/FIRequestVerificationView/GetPendingVerification")
    fun getPendingRequest(@Query("rcutype") Rcutype: String): Observable<GetPendingRequestResponse>

    @GET("api/MasterData/GetAllMaster")
    fun getMasterApiData(): Observable<GetMasterApiResponse>

    @GET("api/FiRequest/GetVerificationRecord")
    fun getVerificationRequestDetail(@Query("FIRequestId") requestId: String): Observable<GetVerificationDetailResponse>


    @POST("api/FiRequest/FIAcceptReject")
    fun getAcceptRejectResponse(@Body requestBody: RequestBody): Observable<GetAcceptRejectResponse>

    @POST("api/FiRequest/SavePreNeighbourDetail")
    fun getSavePreNeighbourData(@Body requestBody: RequestBody): Observable<GetPreNeighbourResponse>

    @POST("api/FiRequest/SavePostNeighbourDetail")
    fun getSavePostNeighbourData(@Body requestBody: RequestBody): Observable<GetPreNeighbourResponse>


    @GET("api/user/GetProfileRecord")
    fun getUserProfileData(): Observable<GetUserProfileResponse>


    @GET("api/MobileAppMenu/GetRecords")
    fun getMenuListResponse(): Observable<GetMenuListResponse>

    @POST("api/user/changePassword")
    fun getChangePasswordApiResponse(@Body requestBody: RequestBody): Observable<GetChangePasswordResponse>

    @GET("api/MobileAppMenu/GetWebViewUrl")
    fun getMenuURLResponse(@Query("menuId") menuId: String,@Query("Latitude") latitude: String,@Query("Longitude") longitude: String): Observable<GetMenuURLResponse>

    @POST("api/FiRequest/SaveVerification")
    fun getSaveFiResidenceResponse(@Body requestBody: SaveVerificationDataDetail): Observable<GetSaveResidenceVerificationResponse>


    @POST("api/FiRequest/SaveFirequestFinalSubmission")
    fun getSaveFinalSubmissionResponse(@Body requestBody: SaveFinalSubmissionData): Observable<GetFinalSubmissionApiResponse>


    @POST("api/FiRequest/SaveMobileAppFIDocument")
    fun saveSurveyPictureBase(@Body body: RequestBody): Observable<GetFinalSubmissionApiResponse?>?


    @POST("api/FiRequest/RemoveMobileAppFIDocument")
    fun deleteFiRequestPicture(@Body body: RequestBody): Observable<GetFinalSubmissionApiResponse?>?


    @GET("api/FiRequest/GetFIDocuments")
    fun getFiRequestPicture(@Query("FIRequestId") fiRequestId: String): Observable<GetFiResidencePictureResponse>


    @GET("api/MobileAppMenu/GetWebViewUrl")
    fun getRcuVerificationWebpage(@Query("MenuId") menuId: String,@Query("Latitude") latitude: String,@Query("Longitude") longitude: String,@Query("FIRequestId") fIRequestId: String): Observable<GetMenuURLResponse>

    @GET("api/MobileAppMenu/GetDashboard")
    fun getDashboardMenuResponse(): Observable<GetDashboardApiResponse>

    @GET("api/Docket/GetDocketForScan")
    fun getDocketForScanResponse(@Query("DocketNo") docketNo: String): Observable<GetDocketForScanResponse>

    @GET("api/Docket/GetDocketForPickup")
    fun getDocketForPickupResponse(@Query("DocketNo") docketNo: String): Observable<GetDocketForPickupResponse>

    @POST("api/docket/SavePickup")
    fun getSavePickupResponse(@Body requestBody: SaveDocketPickupData): Observable<GetSavePickupResponse>


    @POST("api/Docket/SaveInScan")
    fun getSaveInScanResponse(@Body requestBody: SaveScanData): Observable<GetSavePickupResponse>
}