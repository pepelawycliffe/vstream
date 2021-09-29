package com.webandcrafts.vstream.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.webandcrafts.vstream.R;
import com.webandcrafts.vstream.utils.BackgroundService;
import com.webandcrafts.vstream.utils.Utils;
import com.webandcrafts.vstream.adapter.FragmentAdapter;
import com.webandcrafts.vstream.customviews.SKtextViewAladin;
import com.webandcrafts.vstream.customviews.SKviewPager;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private SKviewPager mViewPager;
    private FragmentAdapter mFragmentAdapter;

    private RelativeLayout mActionBar;
    private LinearLayout mStreamLayout;
    private LinearLayout mProgramLayout;
    private LinearLayout mAboutLayout;

    private SKtextViewAladin mStreamTextView;
    private SKtextViewAladin mprogramsTextView;
    private SKtextViewAladin mAboutTextView;
    private boolean doubleBackToExitPressedOnce = false;
    private LinearLayout mBottomNavigation;
    private ImageView imgAbout;
    private ImageView imgPrograms;
    private ImageView imgStream;
    private int selectedColor, unSeleclectedcolor;
    private AlertDialog mDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
        //Internet connectivity change listener starting..........
        registerReceiver(
                mConnectivityChangeReceiver,
                new IntentFilter(
                        ConnectivityManager.CONNECTIVITY_ACTION));
        //End.....................................................
    }


    private void initUi() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mViewPager = findViewById(R.id.fragmentContainer);
        mActionBar = findViewById(R.id.actionBar);
        mViewPager.setPagingEnabled(false);
        mViewPager.setOffscreenPageLimit(3);
        setupViewPager(mViewPager);

        imgAbout = findViewById(R.id.imgAbout);
        imgPrograms = findViewById(R.id.imgPrograms);
        imgStream = findViewById(R.id.imgStream);

        mStreamLayout = findViewById(R.id.streamLayout);
        mProgramLayout = findViewById(R.id.prgramsLayout);
        mAboutLayout = findViewById(R.id.aboutLayout);
        mBottomNavigation = findViewById(R.id.navigation);

        mStreamTextView = findViewById(R.id.streamTextView);
        mprogramsTextView = findViewById(R.id.programsTextView);
        mAboutTextView = findViewById(R.id.aboutTextView);


//        mStreamTextView.setTextColor(getResources().getColor(R.color.streamSelectedTextColor));
        selectedColor = Color.parseColor("#fce76c"); //The color u want
        unSeleclectedcolor = Color.parseColor("#FFFFFF"); //The color u want
        imgStream.setColorFilter(selectedColor);
        imgPrograms.setColorFilter(unSeleclectedcolor);
        imgAbout.setColorFilter(unSeleclectedcolor);
        mStreamTextView.setTextColor(getResources().getColor(R.color.selected_color));
        mprogramsTextView.setTextColor(getResources().getColor(R.color.white));
        mAboutTextView.setTextColor(getResources().getColor(R.color.white));

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                // navigation.getMenu().getItem(position).setChecked(true);
                switch (position) {
                    case 0:
                        mActionBar.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        mActionBar.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        mActionBar.setVisibility(View.VISIBLE);
                        break;

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        fetchSchedule();


        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.streamLayout:


                        mViewPager.setCurrentItem(0);
//                        mStreamTextView.setTextColor(getResources().getColor(R.color.streamSelectedTextColor));
                        mStreamTextView.setTextColor(getResources().getColor(R.color.selected_color));
                        mprogramsTextView.setTextColor(getResources().getColor(R.color.white));
                        mAboutTextView.setTextColor(getResources().getColor(R.color.white));
                        imgStream.setColorFilter(selectedColor);
                        imgPrograms.setColorFilter(unSeleclectedcolor);
                        imgAbout.setColorFilter(unSeleclectedcolor);
                        break;

                    case R.id.prgramsLayout:

                        mViewPager.setCurrentItem(1);
                        mStreamTextView.setTextColor(getResources().getColor(R.color.white));
//                        mprogramsTextView.setTextColor(getResources().getColor(R.color.programsSelectedTextColor));
                        mprogramsTextView.setTextColor(getResources().getColor(R.color.selected_color));
                        mAboutTextView.setTextColor(getResources().getColor(R.color.white));
                        imgStream.setColorFilter(unSeleclectedcolor);
                        imgPrograms.setColorFilter(selectedColor);
                        imgAbout.setColorFilter(unSeleclectedcolor);
                        break;

                    case R.id.aboutLayout:


                        mViewPager.setCurrentItem(2, false);
                        mStreamTextView.setTextColor(getResources().getColor(R.color.white));
                        mprogramsTextView.setTextColor(getResources().getColor(R.color.white));
//                        mAboutTextView.setTextColor(getResources().getColor(R.color.aboutSelectedTextColor));
                        mAboutTextView.setTextColor(getResources().getColor(R.color.selected_color));
                        imgStream.setColorFilter(unSeleclectedcolor);
                        imgPrograms.setColorFilter(unSeleclectedcolor);
                        imgAbout.setColorFilter(selectedColor);
                        break;


                }
            }
        };
        mStreamLayout.setOnClickListener(listener);
        mProgramLayout.setOnClickListener(listener);
        mAboutLayout.setOnClickListener(listener);


        int currentOrientation = getResources().getConfiguration().orientation;


        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //Do some stuff

            mActionBar.setVisibility(View.GONE);
            mBottomNavigation.setVisibility(View.GONE);
        }

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            mActionBar.setVisibility(View.VISIBLE);
            mBottomNavigation.setVisibility(View.VISIBLE);
        }
    }

    private void fetchSchedule() {


        if (Utils.isNetworkAvailable(MainActivity.this)) {
            Utils.callSchedule(MainActivity.this);
        }
    }


    private void setupViewPager(ViewPager viewPager) {
        mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mFragmentAdapter);


    }


    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Checks the orientation of the screen for landscape and portrait and set portrait mode always
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {


            mActionBar.setVisibility(View.GONE);
            mBottomNavigation.setVisibility(View.GONE);


        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

            mActionBar.setVisibility(View.VISIBLE);
            mBottomNavigation.setVisibility(View.VISIBLE);
        }
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
        Intent svc = new Intent(MainActivity.this, BackgroundService.class);
        stopService(svc);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.AlertDialogTheme);
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
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
