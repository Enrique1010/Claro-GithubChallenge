package me.jansv.challenge.ui.screens.repos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import me.jansv.challenge.R;
import me.jansv.challenge.api.GithubService;
import me.jansv.challenge.model.Repos;

import static me.jansv.challenge.util.Constants.SP_KEY;

public class ReposActivity extends AppCompatActivity implements ReposContract.View {
    // injected components
    @Inject
    GithubService api;

    @BindView(R.id.repos_Main)
    View contentView;

    @BindView(R.id.rv_user_repositorie)
    RecyclerView reposRecycler;

    //private components
    private ReposContract.Presenter mPresenter;

    private boolean mIsActive;

    private String repoUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos);

        ButterKnife.bind(this);
        AndroidInjection.inject(this);

        //get url of repositories de from users (clicked user in recycler view)
        repoUser = getIntent().getStringExtra(SP_KEY);
        System.out.println(repoUser);

        mPresenter = new ReposPresenter(api, repoUser);

        setUpReposView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mIsActive = true;
        mPresenter.bind(this);

        mPresenter.fechtReposList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mIsActive = false;
        mPresenter.unbind();
    }

    public void setUpReposView(){
        reposRecycler.setHasFixedSize(true);
        reposRecycler.setLayoutManager(new LinearLayoutManager(ReposActivity.this));
    }

    @Override
    public void showNetworkMessage() {
        Snackbar.make(contentView, R.string.network_error, Snackbar.LENGTH_LONG)
                .setAction(R.string.retry, v ->
                        mPresenter.fechtReposList()
                )
                .show();
    }

    @Override
    public void showErrorNetworkMessage() {
        Snackbar.make(contentView, R.string.no_network, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showReposList(List<Repos> reposList) {
        reposRecycler.setAdapter(new ReposAdapter(reposList));
    }

    @Override
    public boolean isActive() {
        return mIsActive;
    }
}
