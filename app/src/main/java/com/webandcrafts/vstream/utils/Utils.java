package com.webandcrafts.vstream.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.webandcrafts.vstream.AppController;
import com.webandcrafts.vstream.interfaces.AllDaysScheduleCallBack;
import com.webandcrafts.vstream.interfaces.ScheduleApiCallBack;
import com.webandcrafts.vstream.model.ScheduleListBaseModel;
import com.webandcrafts.vstream.model.ScheduleListScheduleModel;
import com.webandcrafts.vstream.webservice.APIClient;
import com.webandcrafts.vstream.webservice.APIInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created Bino on 12/1/2017.
 */

public class Utils {


    private static APIInterface mApiInterface;
    private static ScheduleListBaseModel mScheduleListBaseModel;
    public static ArrayList<ScheduleListScheduleModel> schedule = new ArrayList<>();
    public static ScheduleApiCallBack mScheduleApiCallBack;

    public static AllDaysScheduleCallBack mAllDaysScheduleApiCallBack;

    public static boolean isNetworkAvailable(Context context) {

        boolean status = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = null;
            if (cm != null) {
                netInfo = cm.getNetworkInfo(0);
            }
            if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED) {
                status = true;
            } else {
                netInfo = cm.getNetworkInfo(1);
                if (netInfo != null
                        && netInfo.getState() == NetworkInfo.State.CONNECTED)
                    status = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return status;
    }


    public static void callSchedule(Context context) {
        if (!AppController.WEB_SERVICE_URL.equals("") && !AppController.GET_SCHEDULE_SERVICE_ENDPOINT.equals("")&& !AppController.WEB_SERVICE_URL.equalsIgnoreCase("<...your Base URL...>")) {

            mApiInterface = APIClient.getList().create(APIInterface.class);
            Call call = mApiInterface.getScheduleList();

            call.enqueue(new Callback() {

                @Override
                public void onResponse(Call call, Response response) {


                    if (response.body() != null) {
                        mScheduleListBaseModel = (ScheduleListBaseModel) response.body();
                        if (mScheduleListBaseModel != null) {

                            if (mScheduleListBaseModel.getSchedule() != null) {

                                if (mScheduleListBaseModel.getSchedule().size() > 0) {
                                    schedule.clear();
                                    int size = mScheduleListBaseModel.getSchedule().size();

                                    for (int i = 0; i < size; i++) {
                                        schedule.add(mScheduleListBaseModel.getSchedule().get(i));
                                    }

                                    mScheduleApiCallBack.passScheduleApiResponse(schedule, "HomeFragment");
                                    mAllDaysScheduleApiCallBack.passApiResponseToSchedulePage(schedule, "HomeFragment");

                                }
                            }

                        } else {
                            // Toast.makeText(getActivity(), "some error occur", Toast.LENGTH_LONG).show();
                            // showDialogConnectToUber();
                        }

                    }
                    if (response.errorBody() != null) {
                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            String msg = jObjError.getString("msg");


                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {

                    call.cancel();
                }
            });
        }

    }


    public static String currentDay() {


        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        String mDay = "";

        switch (day) {
            case Calendar.SUNDAY:

                mDay = "sunday";
                break;

            case Calendar.MONDAY:

                mDay = "monday";
                break;

            case Calendar.TUESDAY:
                mDay = "tuesday";
                break;

            case Calendar.WEDNESDAY:

                mDay = "wednesday";
                break;

            case Calendar.THURSDAY:
                mDay = "thursday";
                break;

            case Calendar.FRIDAY:

                mDay = "friday";
                break;

            case Calendar.SATURDAY:

                mDay = "saturday";
                break;
        }
        return mDay;
    }


    public static void passApiResponse(ScheduleApiCallBack scheduleApiCallBack) {
        mScheduleApiCallBack = scheduleApiCallBack;

    }


    public static void passApiResponseToSchedulePage(AllDaysScheduleCallBack alldaysScheduleApiCallBack) {
        mAllDaysScheduleApiCallBack = alldaysScheduleApiCallBack;

    }
}
