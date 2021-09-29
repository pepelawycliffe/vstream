
package com.webandcrafts.vstream.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScheduleListScheduleModel {

    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("events")
    @Expose
    private List<ScheduleListEventModel> events = null;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public List<ScheduleListEventModel> getEvents() {
        return events;
    }

    public void setEvents(List<ScheduleListEventModel> events) {
        this.events = events;
    }

}
