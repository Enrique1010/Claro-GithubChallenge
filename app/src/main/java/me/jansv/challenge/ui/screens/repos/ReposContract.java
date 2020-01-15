package me.jansv.challenge.ui.screens.repos;

import java.util.List;

import me.jansv.challenge.model.Repos;
import me.jansv.challenge.ui.screens.BasePresenter;
import me.jansv.challenge.ui.screens.BaseView;

public interface ReposContract {
    interface View extends BaseView{
        void showNetworkMessage();

        void showErrorNetworkMessage();

        void showReposList(List<Repos> reposList);
    }

    interface Presenter extends BasePresenter<View>{
        void fechtReposList();
    }
}
