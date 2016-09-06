package com.example.kwangitti.smilethailand.api.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by softbaked on 8/18/16 AD.
 */
public interface TimeService {

    @GET("backoffice/api/getservertime")
    Call<ServerTimeGson> getServerTime();

    @POST("backoffice/api/talkprogram")
//    @Headers("Content-Type: application/json") this is no need it's default by retrofit
    Call<ProgramGson> getTalkProgram();

    @POST("backoffice/api/playlistlastest")
//    @Headers("Content-Type: application/json") this is no need it's default by retrofit
    @FormUrlEncoded
    Call<PlaylistLastestGson> getPlaylistLastest(@Field("program_id") String programId);

}
