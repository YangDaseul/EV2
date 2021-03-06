package com.genesis.apps.comm.util;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.genesis.apps.BuildConfig;

public class DeviceUtil {
    /*
     * Application Version name
     * */
    public static String getAppVersionName() {
        return BuildConfig.VERSION_NAME;
    }

    /*
     * Application Version Code
     * */
    public static int getAppVersionCode() {
        return BuildConfig.VERSION_CODE;
    }

    /*
     *
     * Application package name and id both are same
     * */
    public static String getApplicationId() {
        return BuildConfig.APPLICATION_ID;
    }


    /*
     *
     * Application package name
     * */
    public static String getApplicationPackageName() {
        return getApplicationId();
    }

    /*
     *
     * Application build type
     * */
    public static String getBuildType() {
        return BuildConfig.BUILD_TYPE;
    }

    /*
     *
     * Application build type
     * */
    public static boolean isDebug() {
        return BuildConfig.DEBUG;
    }

    /**
     * @param context
     * @return
     * @brief get android id
     */
    public static String getDeviceId(Application context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID).toUpperCase();
    }

    /*
     * device model no
     * */
    public static String getModel() {
        return android.os.Build.MODEL;
    }

    /**
     * Android 버전 정보
     *
     * @return
     */
    public static String getAndroidOS() {
        return android.os.Build.VERSION.RELEASE;
    }


    public static String getPhoneNumber(Application context) {
        if ((context.checkCallingOrSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED
                && context.checkCallingOrSelfPermission(Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED)
                || (Build.VERSION_CODES.O <= Build.VERSION.SDK_INT && context.checkCallingOrSelfPermission(Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED)) {
            throw new RuntimeException("");
        }
        String parserd = "";
        String phoneNumber = "";
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        if (tm != null) {
            phoneNumber = tm.getLine1Number();
        }
//        String phoneNumber = getPhoneNumber(context);
        if (TextUtils.isEmpty(phoneNumber)) {
            return null;
        }else{
            phoneNumber = StringUtil.isValidString(phoneNumber);
        }
        if (phoneNumber.startsWith("+82")) {
            // is korea location number..
            parserd = phoneNumber.replace("+82", "0");
        } else {
            // Others...
            parserd = phoneNumber;
        }

        return parserd.replace("-", "");
    }

    //    /*
//     * device device id
//     * */
//    public static String getDeviceId() {
//        return Build.ID;
//    }
//    /*
//     * device manufacturer
//     * */
//    public static String getManufacturer() {
//        return Build.MANUFACTURER;
//    }
//    /*
//     * device manufacturer
//     * */
//    public static String getBrand() {
//        return Build.BRAND;
//    }
//    /*
//     * device type
//     * */
//    public static String getType() {
//        return Build.TYPE;
//    }
//    /*
//     * device User
//     * */
//    public static String getUser() {
//        return Build.USER;
//    }
//    /*
//     * device Version code base
//     * */
//    public static int getBaseVersionCode() {
//        return Build.VERSION_CODES.BASE;
//    }
    /*
     * device version SDK code
     * */
    public static int getSDKVersion() {
        return Build.VERSION.SDK_INT;
    }



    /**
     * 단말기 높이 넓이<br/>
     * pixel 단위<br/>
     *
     * @param context
     * @return
     */
    public static int getDeviceWidth(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay()
                .getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    /**
     * DP 단위를 Pixel 단위로 변환
     *
     * @param context
     * @param dpi
     * @return
     */
    public static float dip2Pixel(Context context, float dpi) {
        float density = context.getResources().getDisplayMetrics().densityDpi;
        return dpi * (density / (float) DisplayMetrics.DENSITY_DEFAULT);
    }

    /**
     * Pixel 단위를 DP 단위로 변환
     *
     * @param context
     * @param pixel
     * @return
     */
    public static float pixel2Dip(Context context, float pixel) {
        float density = context.getResources().getDisplayMetrics().densityDpi;
        return pixel / (density / (float) DisplayMetrics.DENSITY_DEFAULT);
    }

    public static Spanned fromHtml(String text) {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            return Html.fromHtml(text);
        }

        return Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY);
    }


    public static boolean checkNfcEnabled(Context context) throws Exception {
        if (context == null) return false;

        NfcManager manager = (NfcManager) context.getSystemService(Context.NFC_SERVICE);
        if (manager == null) {
            // NFC 미지원 단말
            throw new IllegalAccessException("this device is has not nfc feature");
        }
        NfcAdapter adapter = manager.getDefaultAdapter();
        if (adapter == null) {
            // NFC 미지원 단말
            throw new IllegalAccessException("this device is has not nfc feature");
        }

        if (adapter.isEnabled()) {
            // 활성화 됨.
            return true;
        } else {
            // 비 활성화 또는 카드모드
            return false;
        }
    }
}
