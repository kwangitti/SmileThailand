package com.example.kwangitti.smilethailand;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.kwangitti.smilethailand.background.PlayMp3ServiceManager;
import com.example.kwangitti.smilethailand.utils.broadcast.AppWatcher;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by kwangitti on 8/15/16 AD.
 */
public class HomePlayFragment extends Fragment implements Observer {

    private ImageView btnPlay, btnPause;
    private boolean isPlaying;
    private static boolean stillplaying;
    private static final String HARDCODED_PLAY_URL = "http://210.1.60.208:1935/live/105.sdp/playlist.m3u8";

    public HomePlayFragment() {

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

                btnPause.setVisibility(View.VISIBLE);
                btnPlay.setVisibility(View.INVISIBLE);
                PlayMp3ServiceManager.MusicViewModel<Void> data = new PlayMp3ServiceManager.MusicViewModel<Void>(null, HARDCODED_PLAY_URL, HARDCODED_PLAY_URL);
                PlayMp3ServiceManager.getInstance().playSound(v.getContext(), data);
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPause.setVisibility(View.INVISIBLE);
                btnPlay.setVisibility(View.VISIBLE);
                PlayMp3ServiceManager.getInstance().stopSound(v.getContext());
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        AppWatcher.getInstance().addObserver(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AppWatcher.getInstance().deleteObserver(this);
    }

    private void refreshButton() {
        if (PlayMp3ServiceManager.getInstance().isPlaying() && null != PlayMp3ServiceManager.getInstance().getPlayingData() && TextUtils.equals(PlayMp3ServiceManager.getInstance().getPlayingData().getUrl(), HARDCODED_PLAY_URL)) {
            btnPause.setVisibility(View.VISIBLE);
            btnPlay.setVisibility(View.INVISIBLE);
        } else {
            btnPlay.setVisibility(View.VISIBLE);
            btnPause.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        if (o instanceof AppWatcher.AppWatcherMessage) {
            final AppWatcher.AppWatcherMessage message = (AppWatcher.AppWatcherMessage) o;
            @AppWatcher.Code int code = message.getCode();
            if (code == AppWatcher.ON_MUSIC_STATE_CHANGE) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        refreshButton();
                    }
                });

            }
        }
    }

    private void runOnUiThread(Runnable runnable) {
        if (null != getActivity() && !getActivity().isFinishing()) {
            getActivity().runOnUiThread(runnable);
        }
    }
}
