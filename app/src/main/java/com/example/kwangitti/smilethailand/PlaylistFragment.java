package com.example.kwangitti.smilethailand;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kwangitti.smilethailand.api.RetrofitManager;
import com.example.kwangitti.smilethailand.api.model.ServerTimeGson;
import com.example.kwangitti.smilethailand.api.model.TimeService;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kwangitti on 8/15/16 AD.
 */
public class PlaylistFragment extends Fragment {

    public PlaylistFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.playlist_fragment, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.listview_playlist);

        requestServerTime(rootView.getContext());
        Toast.makeText(getContext(), "Start Request", Toast.LENGTH_LONG).show();

        PlaylistItemAdapter adapter = new PlaylistItemAdapter(getActivity());
        listView.setAdapter(adapter);

        return rootView;
    }

    private void requestServerTime(final Context contextForToast) {
        final TimeService service = RetrofitManager.getRetrofit().create(TimeService.class);
        Call<ServerTimeGson> call = service.getServerTime();
        call.enqueue(new Callback<ServerTimeGson>() {
            @Override
            public void onResponse(Call<ServerTimeGson> call, Response<ServerTimeGson> response) {
                if (response.isSuccessful()) {
                    try {
                        setTimeData(response.body());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(contextForToast, "onResponse is not successful", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ServerTimeGson> call, Throwable t) {
                Toast.makeText(contextForToast, "onFailure : " + t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public String setTimeData(ServerTimeGson data) throws ParseException {
        String serverTime = data.getServerTime();
        return md5Hashing(serverTime);
    }

    private String md5Hashing(String serverTime){

        String test = "elims" + serverTime;

        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");

        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();
        }
        m.update(test.getBytes(),0,test.length());

        return new BigInteger(1, m.digest()).toString(16);
    }
}
