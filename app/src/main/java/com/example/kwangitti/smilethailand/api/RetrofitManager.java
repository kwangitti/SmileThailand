package com.example.kwangitti.smilethailand.api;

import android.text.TextUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kwangitti on 8/18/16 AD.
 */

public class RetrofitManager {
    public static final String BASE_URL = "http://105smilethailand.com/";


    private RetrofitManager() {
    }


    public static Retrofit getRetrofit(String baseUrl) {
        return getRetrofit(baseUrl, null);
    }

    public static Retrofit getRetrofit(String baseUrl, String md5) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl);
        builder.client(getOkHttpClientWithHeader(md5));
        builder.addConverterFactory(GsonConverterFactory.create());// for support gson as default

        return builder.build();
    }

    public static Retrofit getRetrofit() {
        return getRetrofit(BASE_URL);
    }

    public static OkHttpClient getOkHttpClientWithHeader(final String md5) {
        return new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException { // customize header
                        Request original = chain.request();
                        Request.Builder builder = original.newBuilder();
                        if (!TextUtils.isEmpty(md5)) {
                            builder.header("X-API-KEY", md5);
                        }
                        builder.method(original.method(), original.body());
                        Response response = chain.proceed(builder.build());

                        return response;
                    }
                })
                .build();
    }

}
