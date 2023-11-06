 package com.trst01.locationtracker.constant;

import android.Manifest;

import java.io.File;

 public class  AppConstant {

     public static String APP_ENVIRONMENT = "DEV";

     //     TODO: DEV
//           http://120.138.8.8:9040/api/Master/MasterSync
//     public static String BASE_AUTH_URL = "http://120.138.8.8:9040/api/";
   //  public static String BASE_AUTH_URL = "http://144.48.227.107:3333/api/"; //live


 public static String BASE_AUTH_URL = "http://182.18.157.215/Madhucon/api/";  // CIS Local

 //    // TODO: 2/22/2022 for Live URl
 //    public static String BASE_AUTH_URL = "https://corecorban.trst01.in/api/";

   //  public static String BASE_AUTH_URL = "http://144.48.227.107:4444/Madhucon_AMC/api/";  // CIS AMC
     // TODO: 2/22/2022 For Dev URL
     public static String LOGIN_URL = BASE_AUTH_URL;
     public static String LOGOUT_URL = BASE_AUTH_URL;
     public static String RAW_DATA_URL = BASE_AUTH_URL; // TODO: RAW DATA URL
     public static final String DATE_FORMAT_DDMMYYYYHHMM = "dd-MM-yyyy HH:mm";
     public static final String UPLOAD_DB_LINK = "TabDatabase/UploadTabDatabaseFile";

     // TODO: PROJECT NAME
     public static final String PROJECT_NAME_Verification = "AttaluruFarms";
     public static final String DeviceUserID = "DEVICEUSER ID";
     public static final String SeasonCode = "SeasonCode";
     public static final String IsFirstTime = "IsFirstTime";
     public static final String TestLoc = "TestLoc";
     public static final String DeviceUserName = "DEVICEUSER NAME";
     public static final String DeviceUserPwd = "DEVICEUSER PWD";
     public static final String LogBookNumber = "LOGBOOK NUM";
     public static final String DeviceLoginFirst = "DEVICEUSER ID";
     public static String IsFirstTimeLogin = "first";
     public static final String accessToken = "accessToken";
     public static final String FarmerCode = "FARMER CODE";
//     public static String FARMER_CODE = "CODE VALUE";
     public static String LANDCODE = "LANDCODE";
     public static String DB_NAME = "Location.db";
     //public static final int DB_VERSION = 8-9(Released apk db );
     //public static final int DB_VERSION = 10 sathish device(Released apk db );
     public static final int DB_VERSION = 7;  //1_2
     public static String APP_FOLDER = "Location";
     public static String APK_FOLDER = "APK";
     public static String DB_FOLDER = "Location_DB";
     public static final String DB_SUB_FOLDER = APP_ENVIRONMENT;
     public static final int MY_PERMISSIONS_REQUEST_LOCATION = 1001;
     public static String APPLICATION_TYPE = "Mobile";
     public static String PASS_KEY = "key";
     public static String FROM_HOME = "home";
     public static boolean Farmer_Reg;
     public static String FarmerId = "formerID";
     public static String isTouched = "isTouched";

     // TODO: NETWORK STATUS
     public static final int MOBILE_CONNECTION = 1;
     public static final int WIFI_CONNECTION = 2;
     public static final int NO_INTERNET_CONNECTION = 0;
     public static String APK_EXTENSION = ".apk";
     public static String PARAM_MOBILE_NUMBER = "MOBILE NUMBER";
     public static final String PARAM_USER_NAME = "USER NAME";
     public static final String PARAM_USER_ID = "USER ID";
     public static String MESSAGE_NO_INTERNET_CONNECTION = "Please Check Internet Connection";
     public static String MESSAGE_ENTER_USER_ID = "Enter User ID";
     public static String MESSAGE_ENTER_PASSWORD = "Enter Password";
     public static String SYNC_ALL = "SyncAll";
     public static String SINGLE_SYNC = "SingleSync";
     public static final int IMAGE_CAPTURE_REQUEST = 12;
     public static String ERROR_MESSAGE_ENABLE_GPS = "Please turn ON Device Location";
     public static final int REQUEST_CODE_LOCATION = 111;


     public static final String[] permissions = {Manifest.permission.CAMERA,
             Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE,
             Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE,
             Manifest.permission.ACCESS_COARSE_LOCATION};


     public static String SUCCESS_RESPONSE_MESSAGE = "Data saved Successfully";
     public static String FAILURE_RESPONSE_MESSAGE = "failure";


     // TODO: Error messages
     public static String ERROR_MESSAGE_UNABLE_TO_GET_TOKEN = "Unable to get token";
     public static String ERROR_MESSAGE_LOGOUT_FAILED = "Logout Failed";
     public static String ERROR_MESSAGE_UNABLE_TO_CHANGE_PASSWORD = "Unable to change password";
     public static String ERROR_MESSAGE_PASSWORD_CHANGED_SUCCESSFULLY = "Password changed successfully";
     public static String ERROR_MESSAGE_PASSWORD_RESET_SUCCESSFULLY = "Password has been reset successfully";
     public static String ERROR_MESSAGE_UNABLE_TO_RESET_PASSWORD = "Unable to reset password";


     // TODO: Error messages for Add former
     public static String ERROR_MESSAGE_ENTER_FIRSTNAME = "Please enter farmer First Name";
     public static String ERROR_MESSAGE_ENTER_LASTNAME = "Please enter farmer Last Name";
     public static String ERROR_MESSAGE_ENTER_FATHERNAME = "Please enter farmer Father Name";
     public static String ERROR_MESSAGE_ENTER_ADHAR_NUMBER = "Please enter valid Adhar Number";
     public static String ERROR_MESSAGE_ENTER_MOBILE_NUM = "Please enter valid Mobile Number";
     public static String ERROR_MESSAGE_ENTER_ADDRESS = "Please enter farmer Address ";
     public static String ERROR_MESSAGE_ENTER_PLOTDETAILS = "Please enter farmer Plot Details";
     public static String ERROR_MESSAGE_ENTER_VILLAGE_NAME = "Please enter farmer Village Name";
     public static String ERROR_MESSAGE_ENTER_STATE_NAME = "Please enter farmer State Name";
     public static String ERROR_MESSAGE_ENTER_PINCODE = "Please enter farmer Pincode ";

     public File getDbFileToUpload() {
         try {
 //            File dir = Environment.getExternalStorageDirectory();
             File dbFileToUpload = new File("/sdcard/CoreCarbon/CoreCarbon_DB/DEV/CoreCarbon.db");
             return dbFileToUpload;
         } catch (Exception e) {
             android.util.Log.w("Settings Backup", e);
         }
         return null;
     }

     // TODO: Error messages for Plot Details
     public static String ERROR_MESSAGE_ENTER_PLOT_NUM = "PLEASE ENTER PLOT NUMBER";
     public static String ERROR_MESSAGE_ENTER_PLOT_SIZE = "PLEASE ENTER FARMER PLOT SIZE";
     public static String ERROR_MESSAGE_ENTER_PLOT_ADDRESS = "PLEASE ENTER FARMER PLOT ADDRESS";
     public static String ERROR_MESSAGE_ENTER_PLOT_CROP_NAME = "PLEASE ENTER FARMER CROP NAME";

     public static String ERROR_MESSAGE_ENTER_PLOT_DISTRIC_NAME = "PLEASE ENTER FARMER DISTRIC NAME";

     public static String ERROR_MESSAGE_ENTER_PLOT_PINCODE = "PLEASE ENTER PLOT PIN CODE";
     public static String ERROR_MESSAGE_ENTER_PLOT_MANDAL_NAME = "PLEASE ENTER FARMER MANDAL NAME";
     public static String ERROR_MESSAGE_ENTER_PLOT_VILLAGE_NAME = "PLEASE ENTER FARMER VILLAGE NAME";

     public static String ERROR_MESSAGE_ENTER_PLOT_SURVEY_NUMBER = "PLEASE ENTER PLOT SURVEY NUMBER";
     public static String ERROR_MESSAGE_ENTER_PLOT_PATTADAR_BOOK_NUMBER = "PLEASE ENTER PLOT PATTADAR  BOOK NUMBER";

     // TODO: Error messages for Add Lot Details
     public static String ERROR_MESSAGE_ENTER_TAG_NUM = "PLEASE ENTER TAG NUMBER";
     public static String ERROR_MESSAGE_ENTER_SEED_SOURCE_SEQUENCE_NUM = "PLEASE ENTER SEED SOURCE SEQUENCE NUMBER";
     public static String ERROR_MESSAGE_ENTER_LOT_NUM = "PLEASE ENTER LOT NUMBER";
     public static String ERROR_MESSAGE_ENTER_PARENTAL_TYPE = "PLEASE ENTER LOT PARENTAL TYPE";

     public static String MESSAGE_NO_DATA_FOUND = "No Data Found";
     public static String ERROR_MESSAGE_DATA_SAVED_SUCCESSFULLY = "DATA SAVED SUCCESSFULLY";

     public static String ERROR_MESSAGE_DATA_UPDATED_SUCCESSFULLY = "DATA UPDATED SUCCESSFULLY";

     public static String ERROR_MESSAGE_DATA_SEND_TO_SERVER_SUCCESSFULLY = "DATA SYNC TO SERVER SUCCESSFULLY";

     // TODO: FORGOT PASSWORD SCREEN
     public static String CHOOSE_YOUR_MOBILE_NUMBER = "Choose Your Mobile Number";

     // TODO: OTP SMS FORMAT
     public static final String OTP_SMS_FORMAT = "Please provide OTP XXXXXX to confirm your phone number";
     // TODO: OTP SMS FORMAT
     //  public static final String OTP_SMS_FORMAT="As per your request we are processing loan application. Enter OTP XXXXXX as consent on Loan application, Credit Bureau Check & Submission of scanned original docs";


     public static final String DATE_FORMAT_DD_MM_YYYY = "dd/MM/yyyy";
     public static final String DATE_FORMAT_DD_MM_YYYY_DISPLAY = "dd-MM-yyyy";
     public static final String DATE_FORMAT_MM_DD_YYYY = "MM-dd-yyyy";
     public static final String DATE_FORMAT_DDMMYYYYHHMM1 = "dd-MM-yyyy HH:mm a";
     public static final String DATE_FORMAT_DD_MM_YYYY2 = "dd-MM-yyyy";
     public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
     public static final String DATE_FORMAT_YYYY_MM_DD_T_Z_HH_MM_SS_SSS = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
     public static final String DATE_FORMAT_YYYY_MM_DD_T_Z_HH_MM_SS_XXX = "yyyy-MM-dd'T'HH:mm:ssXXX";
     public static final String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
     public static final String DATE_FORMAT_MMM_DD_YYYY = "MMM dd, yyyy";
     public static final String DATE_FORMAT_MMM_YYYY = "MMM yyyy";
     public static final String DATE_FORMAT_DD_MMM_YYYY = "dd MMM yyyy";
     public static final String DATE_FORMAT_DD_MMM_YY = "dd MMM yy";
     public static final String DATE_FORMAT_DD_MMM_YYYY2 = "dd-MMM-yyyy";
     public static final String DATE_FORMAT_MMM_DD_YYYY_HH_MM_SS = "MMM d d, yyyy HH:mm:ss";
     public static final String DATE_FORMAT_MMM_DD_YYYY_HH_MM_SS_SSS = "MMM dd, yyyy HH:mm:ss.SSS";
     public static final String DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS = "yyyy-gccccccccccccccccccffffdabMM-dd'T'HH:mm:ss";
     public static final String DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_ZERO = "yyyy-MM-dd'T'00:00:00";
     public static final String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_SSS = "yyyy/MM/dd HH:mm:ss.SSS";
 //    String dayOfTheWeek = (String) DateFormat.format("EEEE", date); // Thursday
 //    String day          = (String) DateFormat.format("dd",   date); // 20
 //    String monthString  = (String) DateFormat.format("MMM",  date); // Jun
 //    String monthNumber  = (String) DateFormat.format("MM",   date); // 06
 //    String year         = (String) DateFormat.format("yyyy", date); // 2013
     public static final String DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_SSS = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
     public static final String DATE_FORMAT_DDMMYYYY = "ddMMyyyyhhmmss";
     public static final String DATE_FORMAT_DDMMYYYYSMS = "ddMMyyyyhhmmssSSS";
     public static final String DATE_FORMAT_DD_MM_YYYY_1 = "dd/MM/yyyy";
     public static final String TIME_FORMAT_HH_MM_SS = "HH:mm:ss";
     public static final String TIME_FORMAT_HH = "HH";
     public final static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
     public static final String DATE_FORMAT_DDMMMYYYY = "ddMMMyyyy";
     public static final String DATE_FORMAT_DD_MM_YYYY_HH_MM_SS = "MM/dd/yyyy HH:mm:ss a";
     public static final String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_SSS2 = "yyyy-MM-dd HH:mm:ss:SSS";

     public static final String PERMISSION_DENIED_EXPLANATION = "Permission was denied, but is needed for core functionality";
     public static final String GO_TO_SETTINGS_MESSAGE = "You have denied some permissions, Allow all permissions at " +
             "[Setting] > [Permissions]";

     public static final String YES_GRANT_PERMISSIONS = "Yes , Grant permissions";
     public static final String NO_EXIT_APP = "No , Exit App";
     public static final String GO_TO_SETTINGS = "Go to Settings";

     public static final String REGEX_PATTERN_PASSWORD = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";
     public static final String REGEX_MOBILE = "^[0-9]{10}$";
     //public static String APP_ENVIRONMENT="PRD";

 }
