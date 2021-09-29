package com.webandcrafts.vstream.interfaces;

import com.webandcrafts.vstream.model.ScheduleListScheduleModel;

import java.util.ArrayList;

/**
 * Created by Bino on 12/4/2017.
 */

public interface AllDaysScheduleCallBack {

    public void passApiResponseToSchedulePage(ArrayList<ScheduleListScheduleModel> schedule, String name);

}
