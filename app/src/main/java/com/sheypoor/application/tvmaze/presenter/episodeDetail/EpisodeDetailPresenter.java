package com.sheypoor.application.tvmaze.presenter.episodeDetail;

import com.sheypoor.application.tvmaze.view.EpisodeDetailView;

/**
 * Created by Banafshe.Zarefar on 03/09/2017.
 */

public interface EpisodeDetailPresenter {
    void episodeDetailService(Long number, Long season);

    void attachEpisodeDetailView(EpisodeDetailView episodeDetailView);
}