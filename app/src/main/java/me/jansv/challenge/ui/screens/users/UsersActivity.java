package me.jansv.challenge.ui.screens.users;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import io.reactivex.disposables.CompositeDisposable;
import me.jansv.challenge.R;
import me.jansv.challenge.api.GithubService;
import me.jansv.challenge.model.User;

public class UsersActivity extends AppCompatActivity implements UsersContract.View{

    // injected components
    @Inject
    GithubService api;

    // views binding
    @BindView(R.id.content_main)
    View contentView;

    @BindView(R.id.user_list)
    RecyclerView userList;

    // private components
    private CompositeDisposable subscriptions = new CompositeDisposable();

    private UsersContract.Presenter mPresenter;

    private boolean mIsActive;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        ButterKnife.bind(this);
        AndroidInjection.inject(this);

        mPresenter = new UsersPresenter(api);

        setupViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mIsActive = true;
        mPresenter.bind(this);

        mPresenter.fetchUserList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mIsActive = false;
        mPresenter.unbind();
        subscriptions.clear();
    }

    private void setupViews() {
        userList.setHasFixedSize(true);
        userList.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
    }

    @Override
    public void showNoNetworkMessage() {
        Snackbar.make(contentView, R.string.network_error, Snackbar.LENGTH_LONG)
                .setAction(R.string.retry, v ->
                    mPresenter.fetchUserList()
                )
                .show();
    }

    @Override
    public void showNetworkErrorMessage() {
        Snackbar.make(contentView, R.string.no_network, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showUserList(List<User> users) {
        userList.setAdapter(new UsersAdapter(users));
    }

    @Override
    public boolean isActive() {
        return mIsActive;
    }

    /**/
}
