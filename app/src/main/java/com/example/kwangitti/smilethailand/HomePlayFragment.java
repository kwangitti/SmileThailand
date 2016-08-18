package com.example.kwangitti.smilethailand;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by kwangitti on 8/15/16 AD.
 */
public class HomePlayFragment extends Fragment{

    private ImageView btnPlay, btnPause;
    private boolean isPlaying;
    private static boolean stillplaying;

    public HomePlayFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.play_streaming_fragment, container, false);

        btnPlay = (ImageView) rootView.findViewById(R.id.btn_play);
        btnPause = (ImageView) rootView.findViewById(R.id.btn_pause);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isStillPlaying(true);
                btnPause.setVisibility(View.VISIBLE);
                btnPlay.setVisibility(View.INVISIBLE);
                playStreaming();
            }
        });

//        "http://210.1.60.208:1935/live/105.sdp/playlist.m3u8"

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isStillPlaying(false);
                btnPause.setVisibility(View.INVISIBLE);
                btnPlay.setVisibility(View.VISIBLE);
                pauseStreaming();
            }
        });

        return rootView;
    }

    public void playStreaming(){
        getActivity().startService(new Intent(getActivity(), BackgroundSoundService.class));
    }

    public void pauseStreaming(){
        getActivity().stopService(new Intent(getActivity(), BackgroundSoundService.class));
    }

    public boolean isStillPlaying(boolean isPlaying){
        return isPlaying;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        pauseStreaming();
    }
}
