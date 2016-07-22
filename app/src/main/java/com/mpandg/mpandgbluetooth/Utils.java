package com.mpandg.mpandgbluetooth;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;

import com.mpandg.mpandgbluetooth.model.BodyType;
import com.mpandg.mpandgbluetooth.model.UserInfo;

public class Utils {

    /**
     * A general method to output debug messages in the log
     */
    public static void log(String message) {
        if (BuildConfig.DEBUG) {
            if (message != null) Log.i(Const.TERMINAL_TAG, message);
        }
    }


    /**
     * Convert hex to string
     */
    public static String printHex(String hex) {
        StringBuilder sb = new StringBuilder();
        int len = hex.length();
        try {
            for (int i = 0; i < len; i += 2) {
                sb.append("0x").append(hex.substring(i, i + 2)).append(" ");
            }
        } catch (NumberFormatException e) {
            log("printHex NumberFormatException: " + e.getMessage());

        } catch (StringIndexOutOfBoundsException e) {
            log("printHex StringIndexOutOfBoundsException: " + e.getMessage());
        }
        return sb.toString();
    }


    /**
     * Convert hex string to byte array.
     * @param hex - hex string
     * @return - byte array
     */
    public static byte[] toHex(String hex) {
        int len = hex.length();
        byte[] result = new byte[len];
        try {
            int index = 0;
            for (int i = 0; i < len; i += 2) {
                result[index] = (byte) Integer.parseInt(hex.substring(i, i + 2), 16);
                index++;
            }
        } catch (NumberFormatException e) {
            log("toHex NumberFormatException: " + e.getMessage());

        } catch (StringIndexOutOfBoundsException e) {
            log("toHex StringIndexOutOfBoundsException: " + e.getMessage());
        }
        return result;
    }


    /**
     * concatenate two byte arrays into one.
     */
    public static byte[] concat(byte[] A, byte[] B) {
        byte[] C = new byte[A.length + B.length];
        System.arraycopy(A, 0, C, 0, A.length);
        System.arraycopy(B, 0, C, A.length, B.length);
        return C;
    }


    /**
     * get preferences.
     */
//    public static String getPreference(Context context, String item) {
//        final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
//        return settings.getString(item, Const.TERMINAL_TAG);
//    }

    public static String getStringPref (Context context, String key) {

        SharedPreferences prefs = context.getSharedPreferences(context.getResources().getString(R.string.default_prefs), Context.MODE_PRIVATE);
        return prefs.getString(key, "");
    }

    public static int getIntegerPref (Context context, String key) {

        SharedPreferences prefs = context.getSharedPreferences(context.getResources().getString(R.string.default_prefs), Context.MODE_PRIVATE);
        return prefs.getInt(key, 0);
    }

    /**
     * getting a boolean flag from the settings.
     */
//    public static boolean getBooleanPreference(Context context, String key) {
//        final SharedPreferences settings = context.getSharedPreferences(context.getResources().getString(R.string.default_prefs), Context.MODE_PRIVATE);
//        //return settings.getBoolean(key, true);
//
//        return settings.getBoolean(key, true);
//    }

    public static boolean getBooleanPref (Context context, String key) {

        SharedPreferences prefs = context.getSharedPreferences(context.getResources().getString(R.string.default_prefs), Context.MODE_PRIVATE);
        return prefs.getBoolean(key, true);
    }

    /**
     *
     * put a boolean value to sharedPrefs.
     */
    @SuppressLint("CommitPrefEdits")
    public static void saveValue(Context context, String key, boolean value) {

        SharedPreferences sharedPref = context.getSharedPreferences(context.getResources().getString(R.string.default_prefs), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     *
     * put a string value to sharedPrefs.
     */
    @SuppressLint("CommitPrefEdits")
    public static void saveValue(Context context, String key, String value) {

        SharedPreferences sharedPref = context.getSharedPreferences(context.getResources().getString(R.string.default_prefs), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     *
     * put an integer value to sharedPrefs.
     */
    @SuppressLint("CommitPrefEdits")
    public static void saveValue(Context context, String key, int value) {

        SharedPreferences sharedPref = context.getSharedPreferences(context.getResources().getString(R.string.default_prefs), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static class InputFilterHex implements InputFilter {

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            for (int i = start; i < end; i++) {
                if (!Character.isDigit(source.charAt(i))
                        && source.charAt(i) != 'A' && source.charAt(i) != 'D'
                        && source.charAt(i) != 'B' && source.charAt(i) != 'E'
                        && source.charAt(i) != 'C' && source.charAt(i) != 'F'
                        ) {
                    return "";
                }
            }
            return null;
        }
    }

    public static boolean isFirstTimeUse(Context context) {

        return getBooleanPref(context, Const.FLAG_FIRST_TIME_USE);
    }

    public static void invalidateFirstTimeUse(Context context) {

        saveValue(context, Const.FLAG_FIRST_TIME_USE, false);
    }

    public static void saveUserInfo(Context context, String name, String sex, int weight, int height, int age) {

        // save user registration info in sharedPrefs.
        saveValue(context, Const.KEY_USER_NAME, name);
        saveValue(context, Const.KEY_USER_SEX, sex);
        saveValue(context, Const.KEY_USER_WEIGHT, weight);
        saveValue(context, Const.KEY_USER_HEIGHT, height);
        saveValue(context, Const.KEY_USER_AGE, age);
    }

    public static void saveUserType(Context context, String type){

        // save user type.
        saveValue(context, Const.KEY_USER_TYPE, type);
    }

    public static UserInfo getUserInfo(Context context) {

        UserInfo info = new UserInfo();
        info.setType(getStringPref(context, Const.KEY_USER_TYPE));
        info.setHours(getIntegerPref(context, Const.KEY_USER_HOUR));
        info.setHeight(getIntegerPref(context, Const.KEY_USER_HEIGHT));
        info.setWeight(getIntegerPref(context, Const.KEY_USER_WEIGHT));
        info.setSex(getStringPref(context, Const.KEY_USER_SEX));
        info.setUsername(getStringPref(context, Const.KEY_USER_NAME));
        info.setToken(getStringPref(context, Const.KEY_USER_TOKEN));

        return info;
    }

    public static double evaluateIbm(Context context) {

        int weight = getIntegerPref(context, Const.KEY_USER_WEIGHT);
        int height = getIntegerPref(context, Const.KEY_USER_HEIGHT);

        // x=weight/height^2.
        return weight / Math.pow(height, 2.0);
    }

}
