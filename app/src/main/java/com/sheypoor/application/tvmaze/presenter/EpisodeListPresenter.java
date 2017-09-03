package com.sheypoor.application.tvmaze.presenter;

import com.sheypoor.application.tvmaze.view.EpisodeListView;

/**
 * Created by Banafshe.Zarefar on 03/09/2017.
 */

public interface EpisodeListPresenter {
    void episodeListService();

    void attachEpisodeListView(EpisodeListView episodeListView);
}