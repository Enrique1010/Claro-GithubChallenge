package me.jansv.challenge.ui.screens.repos;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.jansv.challenge.api.GithubService;
import me.jansv.challenge.model.Repos;

public class ReposPresenter implements ReposContract.Presenter {

    private ReposContract.View mView;

    private GithubService api;

    private String queryString;

    public ReposPresenter(GithubService api, String queryString) {
        this.api = api;
        this.queryString = queryString;
    }

    @Override
    public void bind(ReposContract.View view) {
        mView = view;
    }

    @Override
    public void unbind() {
        mView = null;
    }

    @Override
    public void fechtReposList() {

        api.getReposList(queryString).flatMap(reposList -> Observable.fromArray(reposList))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Repos>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //any action if you siscribe data
                    }

                    @Override
                    public void onNext(List<Repos> reposList) {
                        mView.showReposList(reposList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.showNetworkMessage();
                    }

                    @Override
                    public void onComplete() {
                        //any action when all of this is completed..
                    }
                });
    }
}
