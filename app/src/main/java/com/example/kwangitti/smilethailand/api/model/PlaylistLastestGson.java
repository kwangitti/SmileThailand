package com.example.kwangitti.smilethailand.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kwangitti on 8/19/16 AD.
 */
public class PlaylistLastestGson {

    /**
     {
     "status": 200,
     "description_en": "Success.",
     "description_th": "สำเร็จ",
     "data": [
     {
     "id": "11",
     "program_id": "5",
     "date": "2016-06-02 00:00:00",
     "episode_name": "ep 7",
     "guest_name1": "",
     "guest_name2": null,
     "guest_name3": null,
     "audio": "assets/uploads/playlists/9dfaa445585830bfeae284cd39a55288.mp3"
     },
     **/

    @SerializedName("status")
    private int status;
    @SerializedName("description_en")
    private String descriptionEn;
    @SerializedName("description_th")
    private String descriptionTh;
    @SerializedName("data")
    private List<Data> datas = new ArrayList<>();

    public int getStatus() {
        return status;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public String getDescriptionTh() {
        return descriptionTh;
    }

    public List<Data> getDatas() {
        return datas;
    }

    public class Data {

        @SerializedName("id")
        private String id;
        @SerializedName("program_id")
        private String programId;
        @SerializedName("date")
        private String date;
        @SerializedName("episode_name")
        private String episode_name;
        @SerializedName("guest_name1")
        private String guest_name1;
        @SerializedName("guest_name2")
        private String guest_name2;
        @SerializedName("guest_name3")
        private String guest_name3;
        @SerializedName("audio")
        private String audio;


        public Data() {
        }


        public String getId() {
            return id;
        }

        public String getProgramId() {
            return programId;
        }

        public String getDate() {
            return date;
        }

        public String getEpisode_name() {
            return episode_name;
        }

        public String getGuest_name1() {
            return guest_name1;
        }

        public String getGuest_name2() {
            return guest_name2;
        }

        public String getGuest_name3() {
            return guest_name3;
        }

        public String getAudio() {

            if (audio == null){
                return null;
            } else {
                return "http://105smilethailand.com/backoffice/" + audio;
            }
        }
    }

}
