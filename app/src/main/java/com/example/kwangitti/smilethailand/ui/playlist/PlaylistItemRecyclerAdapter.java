package com.example.kwangitti.smilethailand.ui.playlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.kwangitti.smilethailand.R;

/**
 * Created by softbaked on 8/19/16 AD.
 */
public abstract class PlaylistItemRecyclerAdapter extends RecyclerView.Adapter<PlaylistItemRecyclerAdapter.ViewHolder> {
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.playlist_item_adapter, parent, false));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgPlay, imgPause;
        public TextView textDate;

        public ViewHolder(View v) {
            super(v);
            imgPlay = (ImageView) v.findViewById(R.id.img_play);
            imgPause = (ImageView) v.findViewById(R.id.img_pause);
            textDate = (TextView) v.findViewById(R.id.text_date);
        }
    }
}
