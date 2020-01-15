package me.jansv.challenge.api;

import android.support.annotation.Nullable;

import java.util.List;

import io.reactivex.Observable;

import me.jansv.challenge.model.Repos;
import me.jansv.challenge.model.UserList;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface GithubService {
    @GET("/search/users")
    Observable<UserList> getUserList(@Query("q") @Nullable String filter);

    @GET
    Observable<List<Repos>> getReposList(@Url String url);

}
