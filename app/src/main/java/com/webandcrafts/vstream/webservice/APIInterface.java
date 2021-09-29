package com.webandcrafts.vstream.webservice;


import com.webandcrafts.vstream.AppController;
import com.webandcrafts.vstream.model.Channel;
import com.webandcrafts.vstream.model.ScheduleListBaseModel;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Bino on 4/20/2017.
 */
public interface APIInterface {
    //AUTHENTICATION


    @GET(AppController.GET_SCHEDULE_SERVICE_ENDPOINT)
    Call<ScheduleListBaseModel> getScheduleList();

    @GET(AppController.GET_VIDEO_URl_ENDPOINT)
    Call<Channel> channelUrl();

}
