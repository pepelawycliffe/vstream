package com.webandcrafts.vstream.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import com.webandcrafts.vstream.R;

public class CommonBaseActivity extends AppCompatActivity {

    private AlertDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Internet connectivity change listener starting..........
        registerReceiver(
                mConnectivityChangeReceiver,
                new IntentFilter(
                        ConnectivityManager.CONNECTIVITY_ACTION));
        //End.....................................................
    }

    @Override
    protected void onDestroy() {

        if (mDialog != null) {
            if (mDialog.isShowing()) {
                mDialog.dismiss();
            }
            mDialog = null;
        }

        unregisterReceiver(mConnectivityChangeReceiver);
        super.onDestroy();
    }

    private final BroadcastReceiver mConnectivityChangeReceiver =
            new BroadcastReceiver() {

                @Override
                public void onReceive(Context context, Intent intent) {
                    debugIntent(intent, "Vstream");

                    if (!isNetworkAvailable()) {

                        Log.d("Vstream", "==no internet");

                        showNetworkCheckingDialog();
                    } else {
                        Log.d("Vstream", "==yes internet");

                        if (mDialog != null) {

                            Log.d("Vstream", "==1");
                            if (mDialog.isShowing()) {
                                Log.d("Vstream", "==2");
                                mDialog.dismiss();
                                recreate();
                            }
                            mDialog = null;
                        }
                    }
                }

                @SuppressWarnings("SameParameterValue")
                private void debugIntent(Intent intent, String tag) {
                    Log.v(tag, "action: " + intent.getAction());
                    Log.v(tag, "component: " + intent.getComponent());
                    Bundle extras = intent.getExtras();
                    if (extras != null) {
                        for (String key : extras.keySet()) {
                            Log.v(tag, "key [" + key + "]: " +
                                    extras.get(key));
                        }

                    } else {
                        Log.v(tag, "no extras");
                    }
                }
            };

    private void showNetworkCheckingDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(CommonBaseActivity.this, R.style.AlertDialogTheme);
        builder.setTitle("Lost Internet");
        builder.setMessage("You cannot proceed because you have lost internet connection. Please make sure that you have active WIFI or Data Connection  enabled.");
//        builder.setIcon(android.R.drawable.ic_dialog_alert);
//        builder.setIcon(R.mipmap.app_icon_blue_and_yellow);
        builder.setPositiveButton("Close",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        dialog.dismiss();
//                        System.exit(0);
                        finish();
                    }
                });
        builder.setCancelable(false);

        mDialog = builder.create();
        mDialog.show();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}