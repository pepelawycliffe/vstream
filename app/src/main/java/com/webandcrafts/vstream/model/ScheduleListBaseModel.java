
package com.webandcrafts.vstream.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ScheduleListBaseModel {

    @SerializedName("schedule")
    @Expose
    private List<ScheduleListScheduleModel> schedule = null;

    public List<ScheduleListScheduleModel> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<ScheduleListScheduleModel> schedule) {
        this.schedule = schedule;
    }

}
