package com.example.kwangitti.smilethailand;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.app.AppCompatDialogFragment;

import java.util.Observer;

/**
 * Created by kwangitti on 8/19/16 AD.
 */
public abstract class BaseFragment extends AppCompatDialogFragment implements Observer {


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AppCompatDialog dialog = new AppCompatDialog(getContext(), R.style.AppTheme_FullScreen);
        return dialog;
    }
}
