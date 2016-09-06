package com.example.kwangitti.smilethailand.ui.playlist;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kwangitti.smilethailand.BaseFragment;
import com.example.kwangitti.smilethailand.background.PlayMp3ServiceManager;
import com.example.kwangitti.smilethailand.R;
import com.example.kwangitti.smilethailand.api.RetrofitManager;
import com.example.kwangitti.smilethailand.api.model.PlaylistLastestGson;
import com.example.kwangitti.smilethailand.api.model.TimeService;

import java.util.Observable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kwangitti on 8/15/16 AD.
 */
public class PlayListLastestFragment extends BaseFragment {


    private String mProgramId;
    private PlaylistLastestGson mData;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private String md5;

    private static final String KEY_PROGRAM_ID = "PROGRAM_ID";
    private static final String KEY_MD5 = "MD5";

    public PlayListLastestFragment() {
        super();
    }


    //อ่าวแล้ว programId md5 จะไปไว้ไหน เออ อ้อ
    public static PlayListLastestFragment newInstance(String programId, String md5) {
        Bundle args = new Bundle();
        PlayListLastestFragment fragment = new PlayListLastestFragment();
        args.putString(KEY_PROGRAM_ID, programId);
        args.putString(KEY_MD5, md5);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mProgramId = getArguments().getString(KEY_PROGRAM_ID, "");
            md5 = getArguments().getString(KEY_MD5, "");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.playlist_lastest_fragment, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview_playlist_lastest);
        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swiperefreshlayout_playlist);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestPlaylistLastest(mSwipeRefreshLayout.getContext(), mProgramId, md5);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setSaveEnabled(true);
        recyclerView.setAdapter(mAdapter);

        return rootView;
    }

    private PlaylistItemRecyclerAdapter mAdapter = new PlaylistItemRecyclerAdapter() {
        @Override
        public void onBindViewHolder(final PlaylistItemRecyclerAdapter.ViewHolder holder, final int position) {
            if (null == mData || mData.getDatas() == null) {
                return;
            }

            PlaylistLastestGson.Data data = mData.getDatas().get(position);
            holder.textDate.setText(data.getDate());
            holder.imgPlay.setImageResource(R.drawable.ic_play_playlist);
            holder.imgPause.setImageResource(R.drawable.ic_pause_playlist);

            PlayMp3ServiceManager.MusicViewModel playingData = PlayMp3ServiceManager.getInstance().getPlayingData();
            if (playingData != null) {

                String idInList = data.getId();
                String playingId = PlayMp3ServiceManager.getInstance().getPlayingData().getId();
                boolean thisItemIsPlaying = TextUtils.equals(idInList, playingId);
                if (thisItemIsPlaying) {
                    //TODO implement if view is playing here
                    holder.imgPause.setVisibility(View.VISIBLE);
                    holder.imgPlay.setVisibility(View.GONE);
                } else {
                    holder.imgPause.setVisibility(View.GONE);
                    holder.imgPlay.setVisibility(View.VISIBLE);
                }
            } else {
                holder.imgPause.setVisibility(View.GONE);
                holder.imgPlay.setVisibility(View.VISIBLE);
            }

            holder.imgPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PlaylistLastestGson.Data data = mData.getDatas().get(holder.getAdapterPosition());
                    PlayMp3ServiceManager.getInstance().playSound(v.getContext(), new PlayMp3ServiceManager.MusicViewModel<>(data, data.getId(), data.getAudio()));
                    notifyItemRangeChanged(0, getItemCount());
                }
            });

            holder.imgPause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.imgPause.setVisibility(View.GONE);
                    holder.imgPlay.setVisibility(View.VISIBLE);
                    PlayMp3ServiceManager.getInstance().stopSound(v.getContext());
                    notifyItemRangeChanged(0, getItemCount());
                }
            });
        }

        @Override
        public int getItemCount() {
            if (null == mData || mData.getDatas() == null) {
                return 0;
            }
            return mData.getDatas().size();
        }
    };

//    private void savePreferences(){
//        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putBoolean("state", isPlaying);
//        editor.apply();   // I missed to save the data to preference here,.
//    }

//    private void loadPreferences(){
//        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
//        isPlaying = sharedPreferences.getBoolean("state", isPlaying);
//    }

    @Override
    public void onDestroyView() {
//        savePreferences();
        super.onDestroyView();
    }

    @Override
    public void onStart() {
        super.onStart();
//        loadPreferences();
        requestPlaylistLastest(getContext(), mProgramId, md5);
    }

    private void requestPlaylistLastest(final Context contextForToast, String programId, String md5) {
        mSwipeRefreshLayout.setRefreshing(true);
        final TimeService service = RetrofitManager.getRetrofit(RetrofitManager.BASE_URL, md5).create(TimeService.class);
        Call<PlaylistLastestGson> call = service.getPlaylistLastest(programId);
        call.enqueue(new Callback<PlaylistLastestGson>() {
            @Override
            public void onResponse(Call<PlaylistLastestGson> call, Response<PlaylistLastestGson> response) {
                mSwipeRefreshLayout.setRefreshing(false);
                if (response.isSuccessful()) {
                    mData = response.body();
                    mAdapter.notifyDataSetChanged();
                    Toast.makeText(contextForToast, "onResponse is successful", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(contextForToast, "onResponse is not successful", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<PlaylistLastestGson> call, Throwable t) {
                mSwipeRefreshLayout.setRefreshing(false);
                mAdapter.notifyDataSetChanged();
                Toast.makeText(contextForToast, "onFailure : " + t.toString(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void update(Observable observable, Object data) {

    }
}
