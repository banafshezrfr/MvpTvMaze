package com.sheypoor.application.tvmaze.presenter.movieAct;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.sheypoor.application.tvmaze.R;
import com.sheypoor.application.tvmaze.fragment.FragmentEpisodeDetail;
import com.sheypoor.application.tvmaze.fragment.FragmentEpisodeList;
import com.sheypoor.application.tvmaze.view.MovieActivityView;

/**
 * Created by Banafshe.Zarefar on 03/09/2017.
 */

public class MovieActivityPresenterImpl implements MovieActivityPresenter {
    MovieActivityView view;

    @Override
    public void openBrowser(String url) {
        final Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url));
        view.getContext().startActivity(intent);
    }

    @Override
    public void showListFragment(AppBarLayout appBarLayout, FloatingActionButton fab, FragmentEpisodeList fragmentEpisodeList, FragmentManager fm, FragmentTransaction ft) {
        appBarLayout.setExpanded(false);
        appBarLayout.setEnabled(false);
        fab.setVisibility(View.GONE);
        fragmentEpisodeList = new FragmentEpisodeList();
        ft.replace(R.id.frame_episodes, fragmentEpisodeList);
        ft.commit();
    }

    @Override
    public void showDetailFragment(Long number, Long season, AppBarLayout appBarLayout, FloatingActionButton fab, FragmentEpisodeDetail fragmentEpisodeDetail, FragmentManager fm, FragmentTransaction ft) {
        appBarLayout.setExpanded(true);
        appBarLayout.setEnabled(true);
        fab.setVisibility(View.VISIBLE);
        fragmentEpisodeDetail = new FragmentEpisodeDetail(number, season);
        ft.replace(R.id.frame_episodes, fragmentEpisodeDetail);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void attachMovieActivityView(MovieActivityView movieActivityView) {
        view = movieActivityView;
    }
}
