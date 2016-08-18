package com.example.kwangitti.smilethailand;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;

/**
 * Created by kwangitti on 8/15/16 AD.
 */
public class NewsFragment extends Fragment {

    public static String[] dateNews = {"date","date","date","date","date"};
    public static String[] titleNews = {"title","title","title","title","title"};
    public static String[] desNews = {"descriptiondescriptiondescription","descriptiondescription","descriptiondescriptiondescription",
            "descriptiondescriptiondescription","descriptiondescription"};
    public static int[] imgNews={R.drawable.img_test_news,R.drawable.img_test_news,R.drawable.img_test_news,
            R.drawable.img_test_news,R.drawable.img_test_news};

    public NewsFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.news_fragment, container, false);

        GridView gridViewNews = (GridView) rootView.findViewById(R.id.grid_news);
        NewsItemAdapter adapter = new NewsItemAdapter(getActivity(), dateNews, titleNews, desNews, imgNews);
        gridViewNews.setAdapter(adapter);

        return rootView;
    }
}
