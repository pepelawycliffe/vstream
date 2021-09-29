package com.webandcrafts.vstream.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.Map;

public class PreferencesHandler {

    private static final String SHARED_PREFERENCES_KEY = "com.webandcrafts.itspremium";
    private Context mContext;
    static PreferencesHandler mThis;

    private PreferencesHandler(Context c) {

        mContext = c.getApplicationContext();
    }

    public static PreferencesHandler getInstance(Context c) {

        if (mThis == null) {
            mThis = new PreferencesHandler(c);
        }
        return mThis;
    }

    /**
     * @return {@link SharedPreferences} for the application that has been saved
     * with the key DwConstants.SHARED_PREFERENCES_KEY and in
     * Context.MODE_PRIVATE mode
     */
    private SharedPreferences getAppSharedPreferencesObject() {

        return mContext.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE);
    }

    /**
     * Returns the value from the app's {@link SharedPreferences} in private
     * mode with the <b>key</b> provided
     * <p/>
     *
     * @param key : Key used to save the value in the
     *            {@linkplain SharedPreferences}<br>
     * @return {@link Object} the value that has been saved with the respective
     * <b>key</b>. Returns <b>null</b> if no value is found
     * <p/>
     * <i><b>Note:</b> The {@link Object} can be {@link String},
     * {@link Float}, {@link Long}, {@link Boolean} or
     * {@link Integer} </i>
     */
    public Object getValue(String key) {

        if (key == null) {
            return null;
        }
        Map<String, ?> allPreferences;
        SharedPreferences preferences = getAppSharedPreferencesObject();
        allPreferences = preferences.getAll();
        for (Map.Entry<String, ?> entry : allPreferences.entrySet()) {
            if (entry.getKey().equals(key))
                return entry.getValue();
        }
        return null;
    }

    /**
     * Saves the value to the app's {@link SharedPreferences} in private mode
     * with the <b>key</b> provided
     * <p/>
     *
     * @param key    : Key used to save the value in the
     *               {@linkplain SharedPreferences}<br>
     * @param object : This object will be saved to the {@link SharedPreferences}
     *               <b> with key</b><br>
     * @return {@link Boolean}; <b>true</b>, if the value is saved successfully,
     * else returns <b>false </b>
     */
    public boolean saveSharedPreferences(String key, boolean object) {

        if (key == null) {
            return false;
        }
        SharedPreferences preferences = getAppSharedPreferencesObject();
        Editor sharedPreferencesEditor = preferences.edit();
        sharedPreferencesEditor.putBoolean(key, object);
        return sharedPreferencesEditor.commit();


    }

    /**
     * Saves the value to the app's {@link SharedPreferences} in private mode
     * with the <b>key</b> provided
     * <p/>
     *
     * @param key    : Key used to save the value in the
     *               {@linkplain SharedPreferences}<br>
     * @param object : This object will be saved to the {@link SharedPreferences}
     *               <b> with key</b><br>
     * @return {@link Boolean}; <b>true</b>, if the value is saved successfully,
     * else returns <b>false </b>
     */
    public boolean saveSharedPreferences(String key, float object) {

        if (key == null) {
            return false;
        }
        SharedPreferences preferences = getAppSharedPreferencesObject();
        Editor sharedPreferencesEditor = preferences.edit();
        sharedPreferencesEditor.putFloat(key, object);
        return sharedPreferencesEditor.commit();

    }

    /**
     * Saves the value to the app's {@link SharedPreferences} in private mode
     * with the <b>key</b> provided
     * <p/>
     *
     * @param key    : Key used to save the value in the
     *               {@linkplain SharedPreferences}<br>
     * @param object : This object will be saved to the {@link SharedPreferences}
     *               <b> with key</b><br>
     * @return {@link Boolean}; <b>true</b>, if the value is saved successfully,
     * else returns <b>false </b>
     */
    public boolean saveSharedPreferences(String key, int object) {

        if (key == null) {
            return false;
        }
        SharedPreferences preferences = getAppSharedPreferencesObject();
        Editor sharedPreferencesEditor = preferences.edit();
        sharedPreferencesEditor.putInt(key, object);
        return sharedPreferencesEditor.commit();

    }

    /**
     * Saves the value to the app's {@link SharedPreferences} in private mode
     * with the <b>key</b> provided
     * <p/>
     *
     * @param key    : Key used to save the value in the
     *               {@linkplain SharedPreferences}<br>
     * @param object : This object will be saved to the {@link SharedPreferences}
     *               <b> with key</b><br>
     * @return {@link Boolean}; <b>true</b>, if the value is saved successfully,
     * else returns <b>false </b>
     */
    public boolean saveSharedPreferences(String key, long object) {

        if (key == null) {
            return false;
        }
        SharedPreferences preferences = getAppSharedPreferencesObject();
        Editor sharedPreferencesEditor = preferences.edit();
        sharedPreferencesEditor.putLong(key, object);
        return sharedPreferencesEditor.commit();

    }

    /**
     * Saves the value to the app's {@link SharedPreferences} in private mode
     * with the <b>key</b> provided
     * <p/>
     *
     * @param key    : Key used to save the value in the
     *               {@linkplain SharedPreferences}<br>
     * @param object : This object will be saved to the {@link SharedPreferences}
     *               <b> with key</b><br>
     * @return {@link Boolean}; <b>true</b>, if the value is saved successfully,
     * else returns <b>false </b>
     */
    public boolean saveSharedPreferences(String key, String object) {

        if ((key == null) || (object == null)) {
            return false;
        }
        SharedPreferences preferences = getAppSharedPreferencesObject();
        Editor sharedPreferencesEditor = preferences.edit();
        sharedPreferencesEditor.putString(key, object);
        return sharedPreferencesEditor.commit();
    }


    public void setNavigation(String from) {

        saveSharedPreferences("from", from);
    }

    public String getNavigation() {

        Object prefObj = getValue("from");
        if (prefObj != null) {
            return (String) prefObj;
        }
        return (String) prefObj;

    }


}
