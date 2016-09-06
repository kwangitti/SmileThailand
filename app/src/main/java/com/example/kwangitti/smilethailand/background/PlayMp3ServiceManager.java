package com.example.kwangitti.smilethailand.background;

import android.content.Context;
import android.content.Intent;

import com.example.kwangitti.smilethailand.utils.broadcast.AppWatcher;

/**
 * Created by softbaked on 8/21/16 AD.
 */
public class PlayMp3ServiceManager {
    private static PlayMp3ServiceManager INSTANCE = new PlayMp3ServiceManager();
    private boolean isPlaying = false;
    private MusicViewModel playingData = null;


    public static PlayMp3ServiceManager getInstance() {
        return INSTANCE;
    }

    private PlayMp3ServiceManager() {
    }

    public void playSound(final Context context, final MusicViewModel data) {
        stopPlaySoundIfItIsPlaying(context);
        setPlaying(true);
        playingData = data;
        Intent intentPlay = new Intent(context, PlaylistService.class);
        intentPlay.putExtra("url", data.getUrl());
        context.startService(intentPlay);

    }

    public void stopSound(final Context context) {
        setPlaying(false);
        playingData = null;
        context.stopService(new Intent(context, PlaylistService.class));
    }

    private void stopPlaySoundIfItIsPlaying(Context context) {
        if (isPlaying) {
            stopSound(context);
        }
    }

    //this is setter
    private void setPlaying(boolean isPlaying) {
        this.isPlaying = isPlaying;
    }

    //this is getter
    public boolean isPlaying() {
        return isPlaying;
    }

    public MusicViewModel getPlayingData() {
        return playingData;
    }


    public static class MusicViewModel<T> {
        private T data;
        private String id;
        private String url;

        public MusicViewModel(T data, String id, String url) {
            this.data = data;
            this.id = id;
            this.url = url;
        }

        public T getData() {
            return data;
        }

        public String getId() {
            return id;
        }

        public String getUrl() {
            return url;
        }
    }
}
