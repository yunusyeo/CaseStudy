package com.example.casestudy.activity.mainActivity;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.casestudy.constants.Constants;
import com.example.casestudy.R;
import com.example.casestudy.main.BaseActivity;
import com.example.casestudy.model.RepoDetail;
import com.example.casestudy.model.response.RepoResponse;
import com.example.casestudy.activity.repoActivity.RepoDetailActivity;

import java.util.List;

public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.RepoListViewHolder> {
    private Activity activity;
    public List<RepoResponse> repoResponses;

    public RepoListAdapter(Activity activity, List<RepoResponse> repoResponses) {
        this.activity = activity;
        this.repoResponses = repoResponses;
    }

    @NonNull
    @Override
    public RepoListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_repo, parent, false);
        return new RepoListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoListViewHolder repoListViewHolder, int position) {
        repoListViewHolder.setIsRecyclable(false);
        final RepoResponse repoResponse = repoResponses.get(position);
        if (repoResponse.getName() != null) {
            repoListViewHolder.repoName.setText(repoResponse.getName());
        }
        //Interface kullanılarakta yapılabilirdi.
        repoListViewHolder.repoListParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = RepoDetailActivity.newIntent(activity, new RepoDetail(
                        repoResponse.getId(),
                        repoResponse.getName(),
                        repoResponse.getOwner().getLogin(),
                        repoResponse.getOwner().getAvatarUrl(),
                        repoResponse.getStargazersCount(),
                        repoResponse.getOpenIssuesCount()));
                activity.startActivity(intent);
            }
        });

        BaseActivity baseActivity = (BaseActivity) activity;
        if (baseActivity.retrieveFavoriteListItem(String.valueOf(repoResponse.getId())) != Constants.ZERO) {
            repoListViewHolder.favoriteImage.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return repoResponses != null ? repoResponses.size() : Constants.ZERO;
    }

    public class RepoListViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout repoListParent;
        private TextView repoName;
        private ImageView favoriteImage;

        public RepoListViewHolder(@NonNull View itemView) {
            super(itemView);
            repoListParent = (LinearLayout) itemView.findViewById(R.id.repo_list_parent);
            repoName = (TextView) itemView.findViewById(R.id.repo_name_text);
            favoriteImage = (ImageView) itemView.findViewById(R.id.favorite_image);
        }
    }
}
