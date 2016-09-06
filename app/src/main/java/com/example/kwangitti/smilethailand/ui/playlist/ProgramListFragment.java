package com.example.kwangitti.smilethailand.ui.playlist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.kwangitti.smilethailand.R;
import com.example.kwangitti.smilethailand.api.RetrofitManager;
import com.example.kwangitti.smilethailand.api.model.PlaylistLastestGson;
import com.example.kwangitti.smilethailand.api.model.ProgramGson;
import com.example.kwangitti.smilethailand.api.model.ServerTimeGson;
import com.example.kwangitti.smilethailand.api.model.TimeService;
import com.example.kwangitti.smilethailand.utils.ApiHeaderUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kwangitti on 8/15/16 AD.
 */
public class ProgramListFragment extends Fragment {


    private ProgramGson mData = null;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private String textMd5;
    private RecyclerView recyclerViewProgram;

    public ProgramListFragment() {
        super();
    }

    public static ProgramListFragment newInstance() {
        Bundle args = new Bundle();
        ProgramListFragment fragment = new ProgramListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private ProgramItemRecyclerAdapter mAdapter = new ProgramItemRecyclerAdapter() {
        @Override
        public void onBindViewHolder(final ProgramItemRecyclerAdapter.ViewHolder holder, final int position) {
            if (null == mData || mData.getDatas() == null) {
                return;
            }
            ProgramGson.Data data = mData.getDatas().get(position);
            Glide.with(holder.imgProgram.getContext()).load(data.getThumbnailPhoto()).fallback(R.drawable.ic_headphone).into(holder.imgProgram);
            holder.tvChannelName.setText(data.getProgramName());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ProgramGson.Data data = mData.getDatas().get(holder.getAdapterPosition());
                    PlayListLastestFragment.newInstance(data.getId(), textMd5).show(getFragmentManager(),"");
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.playlist_fragment, container, false);
        recyclerViewProgram = (RecyclerView) rootView.findViewById(R.id.recyclerview_playlist);
        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swiperefreshlayout_playlist);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestServerTime(mSwipeRefreshLayout.getContext());
            }
        });

        recyclerViewProgram.setLayoutManager(new GridLayoutManager(recyclerViewProgram.getContext(), 2));
        recyclerViewProgram.setAdapter(mAdapter);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        requestServerTime(getContext());
    }

    private void requestServerTime(final Context contextForToast) {
        mSwipeRefreshLayout.setRefreshing(true);
        final TimeService service = RetrofitManager.getRetrofit().create(TimeService.class);
        Call<ServerTimeGson> call = service.getServerTime();
        call.enqueue(new Callback<ServerTimeGson>() {
            @Override
            public void onResponse(Call<ServerTimeGson> call, Response<ServerTimeGson> response) {
                mSwipeRefreshLayout.setRefreshing(false);
                if (response.isSuccessful()) {
                    textMd5 = ApiHeaderUtils.parseToMd5(response.body());
                    requestTalkProgram(contextForToast, textMd5);
                } else {
                    Toast.makeText(contextForToast, "onResponse is not successful", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ServerTimeGson> call, Throwable t) {
                mSwipeRefreshLayout.setRefreshing(false);
                Toast.makeText(contextForToast, "onFailure : " + t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void requestTalkProgram(final Context contextForToast, String md5) {
        mSwipeRefreshLayout.setRefreshing(true);
        final TimeService service = RetrofitManager.getRetrofit(RetrofitManager.BASE_URL, md5).create(TimeService.class);
        Call<ProgramGson> call = service.getTalkProgram();
        call.enqueue(new Callback<ProgramGson>() {
            @Override
            public void onResponse(Call<ProgramGson> call, Response<ProgramGson> response) {
                Toast.makeText(contextForToast,response + "", Toast.LENGTH_LONG).show();
                mSwipeRefreshLayout.setRefreshing(false);
                if (response.isSuccessful()) {
                    mData = response.body(); // data from service
                    mAdapter.notifyDataSetChanged();
                    Toast.makeText(contextForToast, "onResponse is successful", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(contextForToast, "onResponse is not successful" + "\n" + response, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ProgramGson> call, Throwable t) {
                mSwipeRefreshLayout.setRefreshing(false);
                mAdapter.notifyDataSetChanged();
                Toast.makeText(contextForToast, "onFailure : " + t.toString(), Toast.LENGTH_LONG).show();
            }
        });

    }

}
