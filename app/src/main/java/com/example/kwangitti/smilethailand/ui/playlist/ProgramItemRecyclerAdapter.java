package com.example.kwangitti.smilethailand.ui.playlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kwangitti.smilethailand.R;

/**
 * Created by softbaked on 8/19/16 AD.
 */
public abstract class ProgramItemRecyclerAdapter extends RecyclerView.Adapter<ProgramItemRecyclerAdapter.ViewHolder> {
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_program, parent, false));
    }

//    @Override
//    public void onBindViewHolder(final ViewHolder holder, int position) {
//
//
//        holder.imgPlay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                v.getContext().startService(new Intent(v.getContext(), PlaylistService.class));
//                holder.imgPlay.setVisibility(View.GONE);
//                holder.imgPause.setVisibility(View.VISIBLE);
//            }
//        });
//
//        holder.imgPause.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                v.getContext().stopService(new Intent(v.getContext(), PlaylistService.class));
//                holder.imgPlay.setVisibility(View.VISIBLE);
//                holder.imgPause.setVisibility(View.GONE);
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgProgram;
        public TextView tvChannelName;

        public ViewHolder(View v) {
            super(v);
            imgProgram = (ImageView) v.findViewById(R.id.imageview_item_program_cover_photo);
            tvChannelName = (TextView) v.findViewById(R.id.textview_item_program_channel_name);
        }
    }
}
