package com.sheypoor.application.tvmaze.fragment;


import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sheypoor.application.tvmaze.R;
import com.sheypoor.application.tvmaze.adapter.AdapterEpisodeList;
import com.sheypoor.application.tvmaze.core.FragmentBase;
import com.sheypoor.application.tvmaze.dto.response.episodeList.Episode;
import com.sheypoor.application.tvmaze.presenter.episodeList.EpisodeListPresenterImpl;
import com.sheypoor.application.tvmaze.view.EpisodeListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Banafshe.Zarefar on 18/08/2017.
 */
public class FragmentEpisodeList extends FragmentBase implements EpisodeListView {
    @BindView(R.id.rv_episodes)
    RecyclerView rvEpisodes;
    List<Episode> episodeList = new ArrayList<>();
    private EpisodeListPresenterImpl episodeListPresenter;
    private View view;
    private AdapterEpisodeList adapterEpisodeList;

    public FragmentEpisodeList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        episodeListPresenter = new EpisodeListPresenterImpl();
        episodeListPresenter.attachEpisodeListView(this);
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_episode_list, container, false);
        ButterKnife.bind(this, view);

        int numberOfColumns = 2;
        final GridLayoutManager gridLayoutManager = (new GridLayoutManager(getActivity(), numberOfColumns));
        rvEpisodes.setLayoutManager(gridLayoutManager);
        rvEpisodes.setItemAnimator(new DefaultItemAnimator());
        rvEpisodes.setHasFixedSize(true);
        adapterEpisodeList = new AdapterEpisodeList(episodeList, getContext());
        rvEpisodes.setAdapter(adapterEpisodeList);
        rvEpisodes.setNestedScrollingEnabled(false);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        episodeListPresenter.episodeListService();
    }

    @Override
    protected void handlerBus(Object o) {
        super.handlerBus(o);
    }

    @Override
    public void updateList(List<Episode> responseList) {
        if (!responseList.isEmpty()) {
            episodeList.clear();
            episodeList.addAll(responseList);
            adapterEpisodeList.notifyDataSetChanged();
        }
    }
}
