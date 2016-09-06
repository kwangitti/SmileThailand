package com.example.kwangitti.smilethailand.utils.broadcast;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Observable;

/**
 * Created by softbaked on 8/21/16 AD.
 */
public class AppWatcher extends Observable {
    private static AppWatcher INSTANCE = new AppWatcher();


    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ON_MUSIC_STATE_CHANGE})
    public @interface Code {
    }

    public static final int ON_MUSIC_STATE_CHANGE = 1;

    public static AppWatcher getInstance() {
        return INSTANCE;
    }

    private AppWatcher() {
    }

    public void notify(AppWatcherMessage message) {
        setChanged();
        notifyObservers(message);
    }


    public static class AppWatcherMessage {
        private
        @Code
        int code;
        private Object object;

        public AppWatcherMessage(@Code int key, Object object) {
            this.code = key;
            this.object = object;
        }

        public
        @Code
        int getCode() {
            return code;
        }

        public void setCode(@Code int code) {
            this.code = code;
        }

        public Object getObject() {
            return object;
        }

        public void setObject(Object object) {
            this.object = object;
        }
    }

}
