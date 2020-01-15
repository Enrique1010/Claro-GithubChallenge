package me.jansv.challenge.ui.screens.repos;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.jansv.challenge.R;
import me.jansv.challenge.model.Repos;

import java.util.List;


public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.RepostHolder> {

    private List<Repos> reposList;

    public ReposAdapter(List<Repos> reposList){
        this.reposList = reposList;
    }

    @NonNull
    @Override
    public RepostHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new RepostHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_repositories_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RepostHolder repostHolder, int i) {
        repostHolder.bind(reposList.get(i));
    }

    @Override
    public int getItemCount() {
        return reposList.size();
    }

    public class RepostHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_username)
        TextView userName;

        @BindView(R.id.tv_user_repositorie)
        TextView userRepositories;

        public RepostHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bind(Repos repos) {
            userName.setText(repos.getName());
            userRepositories.setText(repos.getHtmlUrl());
        }
    }
}
