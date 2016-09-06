package com.example.kwangitti.smilethailand.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by softbaked on 8/20/16 AD.
 */
public class ProgramIdPostGson {

    @SerializedName("program_id")
    private String programId;

    public ProgramIdPostGson(String programId) {
        this.programId = programId;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }
}
