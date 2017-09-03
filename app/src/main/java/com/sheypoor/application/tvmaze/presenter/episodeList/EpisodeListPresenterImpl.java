package com.sheypoor.application.tvmaze.presenter.episodeList;

import com.sheypoor.application.tvmaze.dto.response.episodeList.Episode;
import com.sheypoor.application.tvmaze.event.Events;
import com.sheypoor.application.tvmaze.service.episodeList.ServiceEpisodeList;
import com.sheypoor.application.tvmaze.service.factory.ServiceFactory;
import com.sheypoor.application.tvmaze.util.ConstantServices;
import com.sheypoor.application.tvmaze.util.RxBus;
import com.sheypoor.application.tvmaze.view.EpisodeListView;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Banafshe.Zarefar on 03/09/2017.
 */

public class EpisodeListPresenterImpl implements EpisodeListPresenter {
    EpisodeListView view;
    String request = "1";

    @Override
    public void episodeListService() {
        ServiceEpisodeList serviceEpisodeList = ServiceFactory.createRetrofitService(ServiceEpisodeList.class, ConstantServices.END_POINT, ConstantServices.SER_NAME_EDPISODE_LIST, view.getContext());
        RxBus.getInstance().send(new Events.EventLoadingView(true));
        serviceEpisodeList.resp(request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResponseEpisodeListSubscriber());
    }

    @Override
    public void attachEpisodeListView(EpisodeListView episodeListView) {
        view = episodeListView;

    }

    private class ResponseEpisodeListSubscriber extends Subscriber<List<Episode>> {
        @Override
        public void onCompleted() {
            // do nothing here
        }

        @Override
        public void onError(Throwable e) {
            RxBus.getInstance().send(new Events.EventLoadingView(false));
        }


        @Override
        public void onNext(List<Episode> response) {
            view.updateList(response);
            RxBus.getInstance().send(new Events.EventLoadingView(false));
        }
    }

}
