package com.sheypoor.application.tvmaze.presenter.episodeDetailPresenter;

import com.sheypoor.application.tvmaze.dto.response.episodeList.Episode;
import com.sheypoor.application.tvmaze.event.Events;
import com.sheypoor.application.tvmaze.service.episodeDetail.ServiceEpisodeDetail;
import com.sheypoor.application.tvmaze.service.factory.ServiceFactory;
import com.sheypoor.application.tvmaze.util.ConstantServices;
import com.sheypoor.application.tvmaze.util.RxBus;
import com.sheypoor.application.tvmaze.view.EpisodeDetailView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Banafshe.Zarefar on 03/09/2017.
 */

public class EpisodeDetaiPresenterImpl implements EpisodeDetailPresenter {
    EpisodeDetailView view;

    @Override
    public void episodeDetailService(Long number, Long season) {
        ServiceEpisodeDetail serviceEpisodeDetail = ServiceFactory.createRetrofitService(ServiceEpisodeDetail.class, ConstantServices.END_POINT, ConstantServices.SER_NAME_EDPISODE_DETAIL, view.getContext());
        RxBus.getInstance().send(new Events.EventLoadingView(true));
        serviceEpisodeDetail.resp(season, number)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResponseEpisodeDetailSubscriber());
    }

    @Override
    public void attachEpisodeDetailView(EpisodeDetailView episodeDetailView) {
        view = episodeDetailView;
    }


    private class ResponseEpisodeDetailSubscriber extends Subscriber<Episode> {
        @Override
        public void onCompleted() {
            // do nothing here
        }

        @Override
        public void onError(Throwable e) {
            RxBus.getInstance().send(new Events.EventLoadingView(false));
        }

        @Override
        public void onNext(Episode response) {
            view.updateView(response);
            RxBus.getInstance().send(new Events.EventLoadingView(false));
        }
    }

}
