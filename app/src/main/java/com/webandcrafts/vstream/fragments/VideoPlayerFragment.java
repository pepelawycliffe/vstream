package com.webandcrafts.vstream.fragments;


import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.webandcrafts.vstream.AppController;
import com.webandcrafts.vstream.R;
import com.webandcrafts.vstream.adapter.CurrentDayprogramScheduleAdapter;
import com.webandcrafts.vstream.bettervideoplayer.BetterVideoCallback;
import com.webandcrafts.vstream.bettervideoplayer.BetterVideoPlayer;
import com.webandcrafts.vstream.customviews.SKtextViewRobotoRegular;
import com.webandcrafts.vstream.interfaces.ItemSelectionListner;
import com.webandcrafts.vstream.interfaces.ScheduleApiCallBack;
import com.webandcrafts.vstream.interfaces.UpdatePlayListListener;
import com.webandcrafts.vstream.model.ProgramListModel;
import com.webandcrafts.vstream.model.ScheduleListEventModel;
import com.webandcrafts.vstream.model.ScheduleListScheduleModel;
import com.webandcrafts.vstream.utils.BackgroundService;
import com.webandcrafts.vstream.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/*
   For details about video player
   https://github.com/halilozercan/BetterVideoPlayer
 */

public class VideoPlayerFragment extends Fragment implements BetterVideoCallback, ItemSelectionListner, UpdatePlayListListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ArrayList<ProgramListModel> mProgramListCurrentDay = new ArrayList<>();
    private CurrentDayprogramScheduleAdapter mCurrentDayprogramScheduleAdapter;
    private RecyclerView mCurrentDayProgramList;
    static int position = -1;
    public static List<ScheduleListEventModel> mScheduleListEventModel = new ArrayList<>();


    private String mParam1;
    private String mParam2;
    private View mRootView;

    private Handler resizeHandler;
    private Runnable resizeRunnble;


    private Button pauseButton;
    private ArrayList<ScheduleListScheduleModel> mScheduleList;
    private ImageView mImageViewFullScreen, imageViewResize;
    private RelativeLayout mProgramListContainer;
    private RelativeLayout mFrameLayOutContainer;
    private BetterVideoPlayer bvp;
    private android.widget.LinearLayout.LayoutParams paramsFrameLayout;
    private android.widget.LinearLayout.LayoutParams paramsProgramContainer;
    private android.widget.LinearLayout.LayoutParams paramsProgramsEqual;

    private boolean backgroundServiceBound = false;
    private BackgroundService updateProgramNamesService;
    int HANDLER_TIME = 1000;
    Async async = new Async();
    public SKtextViewRobotoRegular mNoSchedule;
    private SKtextViewRobotoRegular mNoStreamUrl;
    private RelativeLayout mResizeLayout;


    public VideoPlayerFragment() {
        // Required empty public constructor
    }


    public static VideoPlayerFragment newInstance(String param1, String param2) {
        VideoPlayerFragment fragment = new VideoPlayerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


        if (!backgroundServiceBound) {
            Intent playerIntent = new Intent(getActivity(), BackgroundService.class);
            getActivity().startService(playerIntent);
            getActivity().bindService(playerIntent, backgroundServiceConnection,
                    Context.BIND_AUTO_CREATE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.fragment_video_player, container, false);
        Log.e("called", "videoplayer");

        initUi();
        return mRootView;
    }

    private void initUi() {


        //  pauseButton=(Button)mRootView.findViewById(R.id.btnPlayPause);
        paramsFrameLayout = new android.widget.LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 2f);
        paramsProgramContainer = new android.widget.LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0f);
        paramsProgramsEqual = new android.widget.LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);


        mNoSchedule = mRootView.findViewById(R.id.mNoSchedule);
        mNoStreamUrl = mRootView.findViewById(R.id.mNoStreamUrl);
        if (AppController.WEB_SERVICE_URL.equals("") || AppController.GET_SCHEDULE_SERVICE_ENDPOINT.equals("")) {
            mNoSchedule.setVisibility(View.VISIBLE);
        } else {
            mNoSchedule.setVisibility(View.GONE);
        }
        imageViewResize = mRootView.findViewById(R.id.imageViewResize);
        mImageViewFullScreen = mRootView.findViewById(R.id.fullScreenView);
        mProgramListContainer = mRootView.findViewById(R.id.programListCOntainer);
        mFrameLayOutContainer = mRootView.findViewById(R.id.frameLayOutContainer);
        mResizeLayout = mRootView.findViewById(R.id.lyt_resize);
        imageViewResize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageViewResize.setVisibility(View.GONE);
                if (getActivity() != null)
                    //TODO : Setting orientation Portrait
                    getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

                //TODO : Orientation to sensor
                changeOrientaionToSenser();
            }
        });
        mImageViewFullScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TODO : Setting orientation Landscape
                imageViewResize.setVisibility(View.VISIBLE);
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(0, 0, 40, 40);
                imageViewResize.setLayoutParams(params);
                if (getActivity() != null)
                    getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

                //TODO : Orientation to sensor
                changeOrientaionToSenser();

            }
        });


        try {
            setVideoplayer();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Utils.passApiResponse(new ScheduleApiCallBack() {


            @Override
            public void passScheduleApiResponse(ArrayList<ScheduleListScheduleModel> schedule, String name) {

                if (mScheduleList != null && mScheduleList.size() > 0) {
                    mScheduleList.clear();
                }
                //   Toast.makeText(getActivity(), "toast length", Toast.LENGTH_LONG).show();
                mScheduleList = schedule;
                setValuesToAdapter();

            }
        });


        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //Do some stuff
            ladscapeMode();
        }

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            //  portriateMode();
            portriateMode();
        }


    }

    private void changeOrientaionToSenser() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (getActivity() != null)
                    getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
            }
        }, HANDLER_TIME);


    }

    private void setVideoplayer() {

        bvp = mRootView.findViewById(R.id.bvp);
        bvp.setCallback(this);
        if (!AppController.VIDEO_STREAMING_URL.equals("")) {
            System.out.println(">>>>>>>>" + AppController.VIDEO_STREAMING_URL);
            bvp.setSource(Uri.parse(AppController.VIDEO_STREAMING_URL));
            mNoStreamUrl.setVisibility(View.GONE);
        } else {
//            Toast.makeText(getActivity(), "Streaming url not specified", Toast.LENGTH_SHORT).show();
            mNoStreamUrl.setVisibility(View.VISIBLE);
        }

    }

    private void setValuesToAdapter() {


        String currentDay = Utils.currentDay();
        mScheduleListEventModel.clear();

        switch (currentDay) {
            case "sunday":

//
//                int eventSize = mScheduleList.get(0).getEvents().size();
//                addEventToLit(eventSize);
                if (mScheduleList.size() > 0) {
                    if (mScheduleList.get(0).getDay().equalsIgnoreCase("sunday")) {
                        if (mScheduleList.get(0).getEvents() != null && mScheduleList.get(0).getEvents().size() > 0)
                            mScheduleListEventModel = mScheduleList.get(0).getEvents();
                    }
                }
                break;

            case "monday":
                if (mScheduleList.size() > 1) {
                    if (mScheduleList.get(1).getDay().equalsIgnoreCase("monday")) {
                        if (mScheduleList.get(1).getEvents() != null && mScheduleList.get(1).getEvents().size() > 0)
                            mScheduleListEventModel = mScheduleList.get(1).getEvents();
                    }
                }
                break;

            case "tuesday":
                if (mScheduleList.size() > 2) {
                    if (mScheduleList.get(2).getDay().equalsIgnoreCase("tuesday")) {
                        if (mScheduleList.get(2).getEvents() != null && mScheduleList.get(2).getEvents().size() > 0)
                            mScheduleListEventModel = mScheduleList.get(2).getEvents();
                    }
                }
                break;

            case "wednesday":
                if (mScheduleList.size() > 3) {
                    if (mScheduleList.get(3).getDay().equalsIgnoreCase("wednesday")) {
                        if (mScheduleList.get(3).getEvents() != null && mScheduleList.get(3).getEvents().size() > 0)
                            mScheduleListEventModel = mScheduleList.get(3).getEvents();
                    }
                }
                break;

            case "thursday":
                if (mScheduleList.size() > 4) {
                    if (mScheduleList.get(4).getDay().equalsIgnoreCase("thursday")) {
                        if (mScheduleList.get(4).getEvents() != null && mScheduleList.get(4).getEvents().size() > 0)
                            mScheduleListEventModel = mScheduleList.get(4).getEvents();
                    }
                }
                break;

            case "friday":
                if (mScheduleList.size() > 5) {
                    if (mScheduleList.get(5).getDay().equalsIgnoreCase("friday")) {
                        if (mScheduleList.get(5).getEvents() != null && mScheduleList.get(5).getEvents().size() > 0)
                            mScheduleListEventModel = mScheduleList.get(5).getEvents();
                    }
                }
                break;

            case "saturday":
                if (mScheduleList.size() > 6) {
                    if (mScheduleList.get(6).getDay().equalsIgnoreCase("saturday")) {

                        if (mScheduleList.get(6).getEvents() != null && mScheduleList.get(6).getEvents().size() > 0) {
                            mScheduleListEventModel = mScheduleList.get(6).getEvents();
                        }
                    }
                }
                break;


        }

        mCurrentDayProgramList = mRootView.findViewById(R.id.recyclerViewCurrentProgramList);
        if (mScheduleListEventModel != null && mScheduleListEventModel.size() > 0) {
            for (int it = 0; it < mScheduleListEventModel.size(); it++) {
                Calendar calendar1 = Calendar.getInstance();
                @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter1 = new SimpleDateFormat("hh:mm aa");
                String timeOfDay = formatter1.format(calendar1.getTime());
                String startTime = mScheduleListEventModel.get(it).getShowTimeStart();
                String endTime = mScheduleListEventModel.get(it).getShowTimeEnd();

                @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa");

                try {
                    Date startTimeFormat = sdf.parse(startTime);
                    Date endTimeFormat = sdf.parse(endTime);
                    Date timeOfDayFormat = sdf.parse(timeOfDay);

                    long startTimeDifference = timeOfDayFormat.getTime() - startTimeFormat.getTime();
                    long endTimeDifference = endTimeFormat.getTime() - timeOfDayFormat.getTime();

                    if ((startTimeDifference >= 0) && (endTimeDifference >= 0)) {
                        mScheduleListEventModel.get(it).setSelected(true);
                        position = it;
                    } else {
                        mScheduleListEventModel.get(it).setSelected(false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        } else {
            mNoSchedule.setVisibility(View.VISIBLE);
            mNoSchedule.setText("No schedule for today");
        }

        async.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }


    @Override
    public void onStarted(BetterVideoPlayer player) {

    }

    @Override
    public void onPaused(BetterVideoPlayer player) {

    }

    @Override
    public void onPreparing(BetterVideoPlayer player) {

    }

    @Override
    public void onPrepared(BetterVideoPlayer player) {
    }

    @Override
    public void onBuffering(int percent) {

    }

    @Override
    public void onError(BetterVideoPlayer player, Exception e) {
        e.printStackTrace();
        if (player != null) {
            player.reset();
            player.release();
            Toast.makeText(getActivity(), "Url not valid", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCompletion(BetterVideoPlayer player) {
        if (Utils.isNetworkAvailable(getActivity())) {
            if (player != null && bvp != null) {
                player.mBtnPlayPause.setClickable(false);
                player.mBtnPlayPause.setEnabled(false);
                bvp.reset();
                bvp.setCallback(this);
                if (!AppController.VIDEO_STREAMING_URL.equals("")) {
                    bvp.setSource(Uri.parse(AppController.VIDEO_STREAMING_URL));
                }
            }
        }

    }

    @Override
    public void onToggleControls(BetterVideoPlayer player, boolean isShowing) {
        if (isShowing) {
            if (resizeHandler == null) {
                resizeHandler = new Handler();
            } else if (resizeRunnble != null) {
                resizeHandler.removeCallbacks(resizeRunnble);
            }
            mResizeLayout.setVisibility(View.VISIBLE);
            resizeRunnble = new Runnable() {
                @Override
                public void run() {
                    mResizeLayout.setVisibility(View.GONE);
                }
            };
            resizeHandler.postDelayed(resizeRunnble, 3000);
        }
    }


    @Override
    public void onPause() {
        super.onPause();

        if (bvp != null) {
            if (bvp.isPlaying()) {
                bvp.pause();
            }

        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);
        int currentOrientation = getResources().getConfiguration().orientation;


        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {

            ladscapeMode();

        } else {

            portriateMode();
        }
    }

    private void portriateMode() {

        mCurrentDayProgramList = mRootView.findViewById(R.id.recyclerViewCurrentProgramList);
        if (mImageViewFullScreen != null & mCurrentDayProgramList != null && mProgramListContainer != null && mFrameLayOutContainer != null && mProgramListContainer != null) {
            mImageViewFullScreen.setVisibility(View.VISIBLE);
            mCurrentDayProgramList.setVisibility(View.VISIBLE);
            mProgramListContainer.setVisibility(View.VISIBLE);
            mFrameLayOutContainer.setLayoutParams(paramsProgramsEqual);
            mProgramListContainer.setLayoutParams(paramsProgramsEqual);

        }
        imageViewResize.setVisibility(View.GONE);
    }

    private void ladscapeMode() {

        mCurrentDayProgramList = mRootView.findViewById(R.id.recyclerViewCurrentProgramList);
        if (mImageViewFullScreen != null & mCurrentDayProgramList != null && mProgramListContainer != null && mFrameLayOutContainer != null && mProgramListContainer != null) {
            mImageViewFullScreen.setVisibility(View.GONE);
            mCurrentDayProgramList.setVisibility(View.GONE);
            mProgramListContainer.setVisibility(View.GONE);
            mFrameLayOutContainer.setLayoutParams(paramsFrameLayout);
            mProgramListContainer.setLayoutParams(paramsProgramContainer);
        }
        imageViewResize.setVisibility(View.VISIBLE);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 0, 40, 40);
        imageViewResize.setLayoutParams(params);
    }

    @Override
    public void itemSelection(int position) {
        mCurrentDayProgramList.scrollToPosition(position);
    }

    @Override
    public void updatePlayList() {
        if (async != null && async.getStatus() != AsyncTask.Status.
                FINISHED) {
            async.cancel(true);

        }
        if (mScheduleListEventModel != null && mScheduleListEventModel.size() > 0) {
            mCurrentDayProgramList.setVisibility(View.VISIBLE);
            async = new Async();
            async.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class Async extends AsyncTask<Void, Integer, String> {

        protected void onPreExecute() {
            super.onPreExecute();
            mCurrentDayprogramScheduleAdapter = new CurrentDayprogramScheduleAdapter(getActivity(), mScheduleListEventModel);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            mCurrentDayProgramList.setLayoutManager(mLayoutManager);
            mCurrentDayProgramList.setItemAnimator(new DefaultItemAnimator());
            mCurrentDayProgramList.setHasFixedSize(true);
            mCurrentDayProgramList.setAdapter(mCurrentDayprogramScheduleAdapter);
        }

        protected String doInBackground(Void... arg0) {
            return "You are at PostExecute";
        }

        protected void onProgressUpdate(Integer... a) {
            super.onProgressUpdate(a);
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            mCurrentDayProgramList.scrollToPosition(position);
        }
    }

    /**
     * Called when the fragment is no longer in use.  This is called
     * after {@link #onStop()} and before {@link #onDetach()}.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (resizeHandler != null && resizeRunnble != null) {
            resizeHandler.removeCallbacks(resizeRunnble);
        }
        resizeHandler = null;
        resizeRunnble = null;
        if (backgroundServiceBound) {
            if (updateProgramNamesService != null) {
                updateProgramNamesService.cancelCallService();
                updateProgramNamesService.stopSelf();
                updateProgramNamesService.setUpdatePlayListListener(null);
            }

            requireActivity().unbindService(backgroundServiceConnection);
            //Service is active
            Intent UpdateProgramNamesServiceIntent = new Intent(requireActivity(), BackgroundService.class);
            requireActivity().stopService(UpdateProgramNamesServiceIntent);
        }
    }

    private final ServiceConnection backgroundServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            BackgroundService.LocalBinder binder = (BackgroundService.LocalBinder)
                    service;
            updateProgramNamesService = binder.getService();
            backgroundServiceBound = true;
            updateProgramNamesService.setUpdatePlayListListener(VideoPlayerFragment.this);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            backgroundServiceBound = false;
        }
    };
}
