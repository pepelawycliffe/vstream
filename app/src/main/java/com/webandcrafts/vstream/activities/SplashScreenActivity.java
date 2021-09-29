package com.webandcrafts.vstream.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import androidx.appcompat.app.AlertDialog;

import com.webandcrafts.vstream.AppController;
import com.webandcrafts.vstream.R;
import com.webandcrafts.vstream.model.Channel;
import com.webandcrafts.vstream.utils.PreferencesHandler;
import com.webandcrafts.vstream.utils.Utils;
import com.webandcrafts.vstream.webservice.APIClient;
import com.webandcrafts.vstream.webservice.APIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenActivity extends CommonBaseActivity {
    public static final long SPLASH_WAIT_TIME = 2500;
    private PreferencesHandler mPreferencesHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        initUi();
    }


    private void initUi() {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mPreferencesHandler = PreferencesHandler.getInstance(this);
        mPreferencesHandler.setNavigation("splash");

        if (Utils.isNetworkAvailable(this)) {
//            checkLatestVersion();
            if (!AppController.VIDEO_STREAMING_URL.equals("")&&!AppController.VIDEO_STREAMING_URL.equalsIgnoreCase("<...your Video Streaming URL ..>")) {
                startMainActivity();
            } else {
                webServiceChannelUrl();
            }
        }
    }


    private void webServiceChannelUrl() {
        if (!AppController.WEB_SERVICE_URL.equals("") && !AppController.GET_VIDEO_URl_ENDPOINT.equals("")&& !AppController.WEB_SERVICE_URL.equalsIgnoreCase("<...your Base URL...>")) {
            Call<Channel> call;
            APIInterface apiInterface = APIClient.getList().create(APIInterface.class);

            call = apiInterface.channelUrl();

            call.enqueue(new Callback<Channel>() {

                @Override
                public void onResponse(Call<Channel> call, Response<Channel> response) {
                    if (response.code() == 200) {
                        final Channel channel = response.body();

                        if (channel != null) {
                            if (channel.getChannelUrl() != null && !channel.getChannelUrl().equals("")) {
                                AppController.VIDEO_STREAMING_URL = channel.getChannelUrl();
                                startMainActivity();
                            } else {
                                startMainActivity();
                            }
                        } else {
                            startMainActivity();
                        }
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    Log.e("", "Got error : " + t.getLocalizedMessage());
                }
            });
        } else {
            startMainActivity();
        }

    }

    private void noNetworkConnection() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogTheme);
        builder.setTitle("No active internet connection");
        builder.setCancelable(false);


        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Cancel button action

                finish();
            }
        });

        builder.setCancelable(false);
        builder.show();
    }

    private void startMainActivity() {
        Thread waitThread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(SPLASH_WAIT_TIME);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        waitThread.start();
    }

}
