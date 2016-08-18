package com.example.kwangitti.smilethailand.api;

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
    private static final String BASE_URL = "http://105smilethailand.com/";



    private RetrofitManager() {
    }


    public static Retrofit getRetrofit(String baseUrl) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl);
        builder.client(getOkHttpClientWithHeader());
        builder.addConverterFactory(GsonConverterFactory.create());// for support gson as default

        return builder.build();
    }

    public static Retrofit getRetrofit() {
        return getRetrofit(BASE_URL);
    }


    public static OkHttpClient getOkHttpClientWithHeader() {
        return new OkHttpClient.Builder()
//                .connectTimeout(5, TimeUnit.SECONDS) customize as you want
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException { // customize header
                        Request original = chain.request();
                        Request.Builder builder = original.newBuilder()
                                .header("X-API-KEY", "")
                                .method(original.method(), original.body());
                        Response response = chain.proceed(builder.build());

                        return response;
                    }
                })
                .build();
    }

}
