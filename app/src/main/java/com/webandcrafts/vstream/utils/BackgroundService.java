package com.webandcrafts.vstream.utils;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;

import com.webandcrafts.vstream.interfaces.UpdatePlayListListener;

import java.util.Timer;

/**
 * Created by wac46 on 2/19/2018.
 */

public class BackgroundService extends Service {

    //    public static final int notify = 300000;  //interval between two services(Here Service run every 5 Minute)
    public static final int notify = 20000;  //interval between two services(Here Service run every 5 Minute)
    private Handler mHandler = new Handler();   //run on another Thread to avoid crash
    private Timer mTimer = null;    //timer handling
    private UpdatePlayListListener mUpdatePlayListListener;
    private final IBinder iBinder = new LocalBinder();
    private CallService callService;
    private int i = 0;


   /* @Override
    public void onCreate() {
        if (mTimer != null) // Cancel if already existed
            mTimer.cancel();
        else
            mTimer = new Timer();   //recreate new
        mTimer.scheduleAtFixedRate(new TimeDisplay(), notify, notify);   //Schedule task
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mTimer.cancel();    //For Cancel Timer
        Toast.makeText(this, "Service is Destroyed", Toast.LENGTH_SHORT).show();
    }*/

    //class TimeDisplay for handling task
   /* class TimeDisplay extends TimerTask {
        @Override
        public void run() {
            // run on another thread
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    // display toast
                    Toast.makeText(BackgroundService.this, "Service is running", Toast.LENGTH_SHORT).show();


                }
            });
        }
    }*/

    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    public class LocalBinder extends Binder {
        public BackgroundService getService() {
            return BackgroundService.this;
        }
    }

    public void cancelCallService() {
        if (callService != null && callService.getStatus() != AsyncTask.Status.FINISHED)
            callService.cancel(true);
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        callService = new CallService();
        callService.execute();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private class CallService extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            while (i < 101) {
                if (i < 100) {
                    i += 1;
                } else {
                    i = 0;
                }
                try {
                    Thread.sleep(5 * 60 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mHandler.post(new Runnable() {
                    public void run() {
                        if (mUpdatePlayListListener != null) {
                            mUpdatePlayListListener.updatePlayList();
//                            Toast.makeText(BackgroundService.this, "Service is running", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                if (isCancelled()) {
                    break; // REMOVE IF NOT USED IN A FOR LOOP
                }
            }
            return null;
        }
    }

    public void setUpdatePlayListListener(UpdatePlayListListener updatePlayListListener) {
        mUpdatePlayListListener = updatePlayListListener;
    }
}