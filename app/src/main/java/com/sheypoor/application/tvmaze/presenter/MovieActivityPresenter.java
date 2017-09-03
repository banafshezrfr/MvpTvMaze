package com.sheypoor.application.tvmaze.presenter;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.sheypoor.application.tvmaze.fragment.FragmentEpisodeDetail;
import com.sheypoor.application.tvmaze.fragment.FragmentEpisodeList;
import com.sheypoor.application.tvmaze.view.MovieActivityView;

/**
 * Created by Banafshe.Zarefar on 03/09/2017.
 */

public interface MovieActivityPresenter {
    void openBrowser(String url);

    void showListFragment(AppBarLayout appBarLayout, FloatingActionButton fab, FragmentEpisodeList fragmentEpisodeList, FragmentManager fm, FragmentTransaction ft);

    void showDetailFragment(Long number, Long season, AppBarLayout appBarLayout, FloatingActionButton fab, FragmentEpisodeDetail fragmentEpisodeList, FragmentManager fm, FragmentTransaction ft);

    void attachMovieActivityView(MovieActivityView movieActivityView);
}

