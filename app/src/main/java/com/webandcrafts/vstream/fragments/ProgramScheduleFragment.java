package com.webandcrafts.vstream.fragments;


import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.webandcrafts.vstream.R;
import com.webandcrafts.vstream.adapter.ProgramListAdapter;
import com.webandcrafts.vstream.adapter.ProgramScheduleDayAdapter;
import com.webandcrafts.vstream.customviews.SKtextViewRobotoRegular;
import com.webandcrafts.vstream.interfaces.AllDaysScheduleCallBack;
import com.webandcrafts.vstream.interfaces.DaySelectionCallBack;
import com.webandcrafts.vstream.model.ProgramListModel;
import com.webandcrafts.vstream.model.ScheduleListEventModel;
import com.webandcrafts.vstream.model.ScheduleListScheduleModel;
import com.webandcrafts.vstream.utils.Utils;

import java.util.ArrayList;
import java.util.List;


public class ProgramScheduleFragment extends Fragment implements DaySelectionCallBack {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private View mRootView;
    private RecyclerView mProgramListView;
    private ProgramListAdapter mProgramListAdapter;
    private ProgramScheduleDayAdapter mDayAdapter;
    private ArrayList<ProgramListModel> mProgramList = new ArrayList<>();
    private ArrayList<String> mDaysList = new ArrayList<>();
    private List<ScheduleListEventModel> mScheduleListEventModel = new ArrayList<>();
    private static ArrayList<ScheduleListScheduleModel> mScheduleList = new ArrayList<>();
    private String currentDayAdapter;
    private RecyclerView daySelectionList;
    private SKtextViewRobotoRegular mNoSchedule;

    public ProgramScheduleFragment() {
        // Required empty public constructor
    }


    public static ProgramScheduleFragment newInstance(String param1, String param2) {
        ProgramScheduleFragment fragment = new ProgramScheduleFragment();
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
            String mParam1 = getArguments().getString(ARG_PARAM1);
            String mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_program_schedule, container, false);

        initUi();
        Log.e("called", "program schedule");
        return mRootView;
    }

    private void initUi() {

        mNoSchedule = mRootView.findViewById(R.id.mNoSchedule);
        mProgramListView = mRootView.findViewById(R.id.recyclerViewProgramList);
//        if (mScheduleListEventModel != null && mScheduleListEventModel.size() > 0) {
//            mNoSchedule.setVisibility(View.GONE);
//        } else {
//            mNoSchedule.setVisibility(View.VISIBLE);
//        }
        currentDayAdapter = Utils.currentDay();
        setDaySelection(currentDayAdapter);


        Utils.passApiResponseToSchedulePage(new AllDaysScheduleCallBack() {


            @Override
            public void passApiResponseToSchedulePage(ArrayList<ScheduleListScheduleModel> schedule, String name) {

                mScheduleList = schedule;
                String currentDay = Utils.currentDay();
                if (mScheduleList.size() > 0) {
                    setDaySelection(currentDayAdapter);
                    setValuesToAdapter(currentDay);

                }

            }


        });
    }

    private void setDaySelection(String day) {

        int position = 0;
        mDaysList.clear();
        if (mScheduleList != null && mScheduleList.size() > 0) {
            for (int i = 0; i < mScheduleList.size(); i++) {
                mDaysList.add(mScheduleList.get(i).getDay());
            }
        }


        mDayAdapter = new ProgramScheduleDayAdapter(day, getActivity(), mDaysList, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        daySelectionList = mRootView.findViewById(R.id.recyclerViewDayMenu);
        daySelectionList.setLayoutManager(layoutManager);
        daySelectionList.setItemAnimator(new DefaultItemAnimator());
        daySelectionList.setHasFixedSize(true);
        daySelectionList.setAdapter(mDayAdapter);


        switch (day) {
            case "sunday":
                position = 0;

                break;

            case "monday":
                position = 1;

                break;

            case "tuesday":
                position = 2;

                break;

            case "wednesday":
                position = 3;

                break;

            case "thursday":
                position = 4;

                break;

            case "friday":
                position = 5;

                break;

            case "saturday":
                position = 6;

                break;
        }
        daySelectionList.getLayoutManager().scrollToPosition(position - 1);

    }

    private void setValuesToAdapter(String currentDay) {


        //  mScheduleListEventModel.clear();

        switch (currentDay) {
            case "sunday":
                if (mScheduleList.size() > 0) {
                    if (mScheduleList.get(0).getEvents() != null && mScheduleList.get(0).getEvents().size() > 0)
                        mScheduleListEventModel = mScheduleList.get(0).getEvents();
                }
                break;

            case "monday":
                if (mScheduleList.size() > 1) {
                    if (mScheduleList.get(1).getEvents() != null && mScheduleList.get(1).getEvents().size() > 0)
                        mScheduleListEventModel = mScheduleList.get(1).getEvents();
                }
                break;

            case "tuesday":
                if (mScheduleList.size() > 2) {
                    if (mScheduleList.get(2).getEvents() != null && mScheduleList.get(2).getEvents().size() > 0)
                        mScheduleListEventModel = mScheduleList.get(2).getEvents();
                }
                break;

            case "wednesday":
                if (mScheduleList.size() > 3) {
                    if (mScheduleList.get(3).getEvents() != null && mScheduleList.get(3).getEvents().size() > 0)
                        mScheduleListEventModel = mScheduleList.get(3).getEvents();
                }
                break;

            case "thursday":
                if (mScheduleList.size() > 4) {
                    if (mScheduleList.get(4).getEvents() != null && mScheduleList.get(4).getEvents().size() > 0)
                        mScheduleListEventModel = mScheduleList.get(4).getEvents();
                }
                break;

            case "friday":
                if (mScheduleList.size() > 5) {
                    if (mScheduleList.get(5).getEvents() != null && mScheduleList.get(5).getEvents().size() > 0)
                        mScheduleListEventModel = mScheduleList.get(5).getEvents();
                }

                break;

            case "saturday":

                if (mScheduleList.size() > 6) {
                    if (mScheduleList.get(6).getEvents() != null && mScheduleList.get(6).getEvents().size() > 0)
                        mScheduleListEventModel = mScheduleList.get(6).getEvents();
                }

                break;
        }
        if (mScheduleListEventModel != null && mScheduleListEventModel.size() > 0) {
            mNoSchedule.setVisibility(View.GONE);
            mProgramListAdapter = new ProgramListAdapter(getActivity(), mScheduleListEventModel);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            mProgramListView.setLayoutManager(mLayoutManager);
            mProgramListView.setItemAnimator(new DefaultItemAnimator());
            mProgramListView.setHasFixedSize(true);
            mProgramListView.setAdapter(mProgramListAdapter);
        } else {
            mNoSchedule.setVisibility(View.VISIBLE);
            mNoSchedule.setText("No schedule for today");
        }


    }


    @Override
    public void daySelected(String name, int position) {
        if (mScheduleList.size() > 0) {
            setValuesToAdapter(name);
            currentDayAdapter = name;
            setDaySelection(name);
            daySelectionList.getLayoutManager().scrollToPosition(position - 1);
        }

        // mDayAdapter.notifyDataSetChanged();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Activity a = getActivity();
            if (a != null) a.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }
}
