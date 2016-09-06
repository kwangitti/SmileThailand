package com.example.kwangitti.smilethailand.background;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.kwangitti.smilethailand.utils.broadcast.AppWatcher;

import java.io.IOException;

/**
 * Created by kwangitti on 8/17/16 AD.
 */
public class PlaylistService extends Service {

    MediaPlayer mMediaPlayer;
    String url;

    public PlaylistService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                AppWatcher.getInstance().notify(new AppWatcher.AppWatcherMessage(AppWatcher.ON_MUSIC_STATE_CHANGE, null));
            }
        });

    }

    private static final String TAG = "PlaylistService";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (null != intent && intent.getExtras() != null) {
            this.url = intent.getExtras().getString("url");
            Log.i(TAG, "url is : " + url);
            try {
                mMediaPlayer.setDataSource(this, Uri.parse(url));
            } catch (IOException e) {
                e.printStackTrace();
            }
            mMediaPlayer.prepareAsync();
        }


        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        AppWatcher.getInstance().notify(new AppWatcher.AppWatcherMessage(AppWatcher.ON_MUSIC_STATE_CHANGE, null));
        mMediaPlayer.stop();
        mMediaPlayer.release();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
