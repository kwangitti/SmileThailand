package com.example.kwangitti.smilethailand.api.model;

import com.bumptech.glide.load.model.StringLoader;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by softbaked on 8/19/16 AD.
 */
public class ProgramGson {

    /**
     * "status": 200,
     * "description_en": "Success.",
     * "description_th": "สำเร็จ",
     * "data": [
     * {
     * "id": "7",
     * "program_name": "test 7",
     * "host1": "host1",
     * "host2": "host2",
     * "host3": "host3",
     * "timeperiod_start": "17:00",
     * "timeperiod_end": "18:00",
     * "date": "Sat,Sun",
     * "thumbnail_photo": null
     * }
     */


    @SerializedName("status")
    private int status;
    @SerializedName("description_en")
    private String descriptionEn;
    @SerializedName("description_th")
    private String descriptionTh;
    @SerializedName("data")
    private List<Data> datas = new ArrayList<>();

    public ProgramGson() {
    }


    public static ProgramGson getDummyData() {
        String jsonStringFromServer = "{\"status\":200,\"description_en\":\"Success.\",\"description_th\":\"\\u0e2a\\u0e33\\u0e40\\u0e23\\u0e47\\u0e08\",\"data\":[{\"id\":\"7\",\"program_name\":\"test 7\",\"host1\":\"host1\",\"host2\":\"host2\",\"host3\":\"host3\",\"timeperiod_start\":\"17:00\",\"timeperiod_end\":\"18:00\",\"date\":\"Sat,Sun\",\"thumbnail_photo\":null},{\"id\":\"5\",\"program_name\":\"test4\",\"host1\":\"test\",\"host2\":\"\",\"host3\":\"\",\"timeperiod_start\":\"00:00\",\"timeperiod_end\":\"00:00\",\"date\":\"All\",\"thumbnail_photo\":\"assets\\/uploads\\/thumb\\/d11042c17c2dfecb49b65de41e5bf241.png\"},{\"id\":\"6\",\"program_name\":\"test5\",\"host1\":\"host1\",\"host2\":\"host2\",\"host3\":\"host3\",\"timeperiod_start\":\"14:30\",\"timeperiod_end\":\"15:30\",\"date\":\"Fri,Sat\",\"thumbnail_photo\":null}]}";
        Gson gson = new Gson();

        return gson.fromJson(jsonStringFromServer, ProgramGson.class);
    }

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

        @SerializedName("program_name")
        private String programName;
        @SerializedName("host1")
        private String host1;
        @SerializedName("host2")
        private String host2;
        @SerializedName("host3")
        private String host3;
        @SerializedName("timeperiod_start")
        private String timePeriodStart;
        @SerializedName("timeperiod_end")
        private String timePeriodEnd;
        @SerializedName("date")
        private String date;
        @SerializedName("thumbnail_photo")
        private String thumbnailPhoto;


        public Data() {
        }


        public String getId() {
            return id;
        }

        public String getProgramName() {
            return programName;
        }

        public String getHost1() {
            return host1;
        }

        public String getHost2() {
            return host2;
        }

        public String getHost3() {
            return host3;
        }

        public String getTimePeriodStart() {
            return timePeriodStart;
        }

        public String getTimePeriodEnd() {
            return timePeriodEnd;
        }

        public String getDate() {
            return date;
        }

        public String getThumbnailPhoto() {
            if (thumbnailPhoto == null){
                return null;
            } else {
                return "http://105smilethailand.com/backoffice/" + thumbnailPhoto;
            }
        }
    }
}
