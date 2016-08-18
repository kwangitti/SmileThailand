package com.example.kwangitti.smilethailand.api.model;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by softbaked on 8/18/16 AD.
 */
public interface TimeService {

    @GET("backoffice/api/getservertime")
    Call<ServerTimeGson> getServerTime();

    @GET("backoffice/api/talkprogram")
    Call<ServerPlaylistGson> getThumnailPhoto();
}
