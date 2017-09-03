package com.sheypoor.application.tvmaze.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sheypoor.application.tvmaze.R;
import com.sheypoor.application.tvmaze.core.FragmentBase;
import com.sheypoor.application.tvmaze.dto.response.episodeList.Episode;
import com.sheypoor.application.tvmaze.presenter.episodeDetail.EpisodeDetaiPresenterImpl;
import com.sheypoor.application.tvmaze.util.ConstantServices;
import com.sheypoor.application.tvmaze.util.UtilString;
import com.sheypoor.application.tvmaze.view.EpisodeDetailView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Banafshe.Zarefar on 18/08/2017.
 */
public class FragmentEpisodeDetail extends FragmentBase implements EpisodeDetailView {
    EpisodeDetaiPresenterImpl presenter;
    View view;
    @BindView(R.id.tv_summary)
    TextView tvSummary;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_time)
    TextView tvTime;
    private Long number;
    private Long season;

    public FragmentEpisodeDetail() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public FragmentEpisodeDetail(Long number, Long season) {
        this.number = number;
        this.season = season;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        presenter = new EpisodeDetaiPresenterImpl();
        presenter.attachEpisodeDetailView(this);
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_episode_detail, container, false);
        ButterKnife.bind(this, view);
        tvSummary.setMovementMethod(new ScrollingMovementMethod());
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.episodeDetailService(number, season);
    }


    @Override
    protected void handlerBus(Object o) {
        super.handlerBus(o);
    }

    @Override
    public void updateView(Episode response) {
        if (null != response) {
            tvSummary.setText(UtilString.stripHtml(response.getSummary()));
            tvTitle.setText(response.getName());
            tvTime.setText(response.getAirdate() + ConstantServices.SPACE + response.getRuntime() + ConstantServices.MIN);
        }
    }
}
