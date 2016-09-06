package com.example.kwangitti.smilethailand.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by softbaked on 8/18/16 AD.
 */
public class ServerTimeGson {

    @SerializedName("servertime")//must be same key as json api from server
    private String serverTime;

    public ServerTimeGson() {
    }

    public String getServerTime() {
        return serverTime;
    }
}
