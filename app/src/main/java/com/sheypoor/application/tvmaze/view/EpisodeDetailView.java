package com.sheypoor.application.tvmaze.view;

import android.content.Context;

import com.sheypoor.application.tvmaze.dto.response.episodeList.Episode;

/**
 * Created by Banafshe.Zarefar on 03/09/2017.
 */

public interface EpisodeDetailView {
    Context getContext();

    void updateView(Episode episode);
}
