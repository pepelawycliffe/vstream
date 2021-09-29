package com.webandcrafts.vstream.model;

        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

/**
 * Created by wac46 on 2/23/2018.
 */

public class Channel {
    @SerializedName("channel_url")
    @Expose
    private String channelUrl;

    public String getChannelUrl() {
        return channelUrl;
    }

    public void setChannelUrl(String channelUrl) {
        this.channelUrl = channelUrl;
    }
}
