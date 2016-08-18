package com.example.kwangitti.smilethailand;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by kwangitti on 8/15/16 AD.
 */
public class AboutUsFragment extends Fragment {

    public AboutUsFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.about_us_tab, container, false);
        LinearLayout linOpenFb = (LinearLayout) rootView.findViewById(R.id.btn_fb);
        linOpenFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openFb = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/105smilethailand/"));
                startActivity(openFb);
            }
        });

        return rootView;
    }
}
