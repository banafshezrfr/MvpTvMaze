package com.sheypoor.application.tvmaze.view;

import android.content.Context;

import com.sheypoor.application.tvmaze.dto.response.episodeList.Episode;

import java.util.List;

/**
 * Created by Banafshe.Zarefar on 03/09/2017.
 */

public interface EpisodeListView {
    Context getContext();

    void updateList(List<Episode> responseList);
}
