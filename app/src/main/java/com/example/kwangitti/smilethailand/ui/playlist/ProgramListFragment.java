package com.example.kwangitti.smilethailand.ui.playlist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.kwangitti.smilethailand.R;
import com.example.kwangitti.smilethailand.api.RetrofitManager;
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
        public void onBindViewHolder(ProgramItemRecyclerAdapter.ViewHolder holder, int position) {
            if (null == mData || mData.getDatas() == null) {
                return;
            }
            ProgramGson.Data data = mData.getDatas().get(position);
            Glide.with(holder.imgProgram.getContext()).load(data.getThumbnailPhoto()).fallback(R.drawable.facebookbutton).into(holder.imgProgram);
            holder.tvChannelName.setText(data.getProgramName());
        }

        @Override
        public int getItemCount() {
            if (null == mData || mData.getDatas() == null) {
                return 0;
            }
            return mData.getDatas().size();
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.playlist_fragment, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview_playlist);
        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swiperefreshlayout_playlist);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestServerTime(mSwipeRefreshLayout.getContext());
            }
        });


        Toast.makeText(getContext(), "Start Request", Toast.LENGTH_LONG).show();

        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 2));
        recyclerView.setAdapter(mAdapter);
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
                    requestTalkProgram(contextForToast, ApiHeaderUtils.parseToMd5(response.body()));
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
                mSwipeRefreshLayout.setRefreshing(false);
                if (response.isSuccessful()) {
                    mData = response.body();
                    mAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(contextForToast, "onResponse is not successful", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ProgramGson> call, Throwable t) {
                mSwipeRefreshLayout.setRefreshing(false);
                mData = ProgramGson.getDummyData();
                mAdapter.notifyDataSetChanged();
                Toast.makeText(contextForToast, "onFailure : " + t.toString(), Toast.LENGTH_LONG).show();
            }
        });


    }


}
