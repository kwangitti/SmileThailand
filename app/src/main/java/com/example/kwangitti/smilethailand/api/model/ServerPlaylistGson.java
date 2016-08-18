package com.example.kwangitti.smilethailand.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kwangitti on 8/18/16 AD.
 */
public class ServerPlaylistGson {

    @SerializedName("thumbnail_photo")//must be same key as json api from server
    private String thumnailPhoto;

    @SerializedName("id")
    private String id;

    @SerializedName("program_name")
    private String programName;

    @SerializedName("date")
    private String date;

    public String getThumnailPhoto() {
        return thumnailPhoto;
    }

    public void setThumnailPhoto(String thumnailPhoto) {
        this.thumnailPhoto = thumnailPhoto;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }
}
