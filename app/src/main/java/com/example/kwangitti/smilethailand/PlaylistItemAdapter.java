package com.example.kwangitti.smilethailand;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by kwangitti on 8/16/16 AD.
 */
public class PlaylistItemAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    private Context mContext;

    public PlaylistItemAdapter(Context context) {

        mContext = context;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder{
        ImageView imgPlay, imgPause;
        SeekBar seekBarPlaylist;
        TextView textDate;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final Holder holder = new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.playlist_item_adapter, null);
        holder.imgPlay = (ImageView) rowView.findViewById(R.id.btn_play);
        holder.imgPause = (ImageView) rowView.findViewById(R.id.btn_pause);
        holder.seekBarPlaylist = (SeekBar) rowView.findViewById(R.id.seekbar_player);
        holder.textDate = (TextView) rowView.findViewById(R.id.text_date_playlist);

        holder.imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startService(new Intent(mContext, PlaylistService.class));
                holder.imgPlay.setVisibility(View.GONE);
                holder.imgPause.setVisibility(View.VISIBLE);
            }
        });

        holder.imgPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.stopService(new Intent(mContext, PlaylistService.class));
                holder.imgPlay.setVisibility(View.VISIBLE);
                holder.imgPause.setVisibility(View.GONE);
            }
        });

        return rowView;
    }
}
