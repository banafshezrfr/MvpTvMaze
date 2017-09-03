package com.sheypoor.application.tvmaze.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sheypoor.application.tvmaze.R;
import com.sheypoor.application.tvmaze.core.ActivityBase;
import com.sheypoor.application.tvmaze.event.Events;
import com.sheypoor.application.tvmaze.fragment.FragmentEpisodeDetail;
import com.sheypoor.application.tvmaze.fragment.FragmentEpisodeList;
import com.sheypoor.application.tvmaze.presenter.movieAct.MovieActivityPresenterImpl;
import com.sheypoor.application.tvmaze.util.ConstantEvent;
import com.sheypoor.application.tvmaze.view.MovieActivityView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Banafshe.Zarefar on 18/08/2017.
 */
public class ActivityMovie extends ActivityBase implements MovieActivityView {
    MovieActivityPresenterImpl presenter;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.im_episode_pic_det)
    ImageView imFirstPic;
    @BindView(R.id.loading_view_background)
    LinearLayout linProgress;
    FragmentTransaction ft;
    FragmentManager fm;
    FragmentEpisodeList fragmentEpisodeList;
    FragmentEpisodeDetail fragmentEpisodeDetail;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new MovieActivityPresenterImpl();
        presenter.attachMovieActivityView(this);

        setContentView(R.layout.activity_movie);
        ButterKnife.bind(this);
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        presenter.showListFragment(appBarLayout, fab, fragmentEpisodeList, fm, ft);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.openBrowser(url);
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        appBarLayout.setExpanded(false);
        appBarLayout.setEnabled(false);
        fab.setVisibility(View.GONE);
    }


    @Override
    protected void handlerBus(Object object) {
        super.handlerBus(object);
        if (object instanceof Events.EventEpisodeDetail && ((Events.EventEpisodeDetail) object).getMessage().equalsIgnoreCase(ConstantEvent.EVENT_MESSAGE_EPISODE_DETAIL)) {
            fm = getSupportFragmentManager();
            ft = fm.beginTransaction();
            presenter.showDetailFragment(((Events.EventEpisodeDetail) object).getNumber(), ((Events.EventEpisodeDetail) object).getSeason(), appBarLayout, fab, fragmentEpisodeDetail, fm, ft);
            Glide.with(ActivityMovie.this)
                    .load(((Events.EventEpisodeDetail) object).getImgUrl())
                    .placeholder(R.mipmap.ic_cloud_download_grey600)
                    .error(R.mipmap.ic_error_red)
                    .animate(android.R.anim.fade_in)
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(imFirstPic);
            url = ((Events.EventEpisodeDetail) object).getUrl();
        }
        if (object instanceof Events.EventLoadingView && ((Events.EventLoadingView) object).isShow()) {
            linProgress.setVisibility(View.VISIBLE);
        } else {
            linProgress.setVisibility(View.GONE);
        }
    }

    @Override
    public Context getContext() {
        return ActivityMovie.this;
    }

    @Override
    public void updateMovieActivityView() {

    }
}
