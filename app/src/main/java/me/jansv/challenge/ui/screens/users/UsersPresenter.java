package me.jansv.challenge.ui.screens.users;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.jansv.challenge.api.GithubService;
import me.jansv.challenge.model.User;

public class UsersPresenter implements UsersContract.Presenter {

    private UsersContract.View mView;

    private GithubService api;

    public UsersPresenter(GithubService api) {
        this.api = api;
    }

    @Override
    public void bind(UsersContract.View view) {
        mView = view;
    }

    @Override
    public void unbind() {
        mView = null;
    }

    @Override
    public void fetchUserList() {
        final String filter = "language:java location:lagos";
        api.getUserList(filter).flatMap(userList -> Observable.fromArray(userList.getItems()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<List<User>>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            //action if you suscribe data
                        }

                        @Override
                        public void onNext(List<User> users) {
                            mView.showUserList(users);
                        }
                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            mView.showNetworkErrorMessage();
                        }
                        @Override
                        public void onComplete() {
                            //action when all of this finish
                        }
                    });
    }
}