package com.hiddenfounders.hiddenfounderscodingchallenge.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hiddenfounders.hiddenfounderscodingchallenge.R;
import com.hiddenfounders.hiddenfounderscodingchallenge.adapters.RepositoryAdapter;
import com.hiddenfounders.hiddenfounderscodingchallenge.listeners.InfiniteScrollerListener;
import com.hiddenfounders.hiddenfounderscodingchallenge.models.GithubResponse;
import com.hiddenfounders.hiddenfounderscodingchallenge.models.Repository;
import com.hiddenfounders.hiddenfounderscodingchallenge.services.GithubApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    //Declaring some variables
    ListView starredReposListView;
    RepositoryAdapter adapter;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.top_starred_repo));
        }
        Toast.makeText(this, R.string.scroll_for_more, Toast.LENGTH_LONG).show();
        //I always add these two methods to keep onCreate clean
        initView();
        initListeners();

        //getting the data from github api
        getNextReposPage(1);
    }

    private void initView() {
        //Initialize listview and set adapter
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        starredReposListView = (ListView) findViewById(R.id.starred_repos_list_view);
    }

    private void initListeners() {
        //starredReposListView won't be null since I call initView before initListeners
        starredReposListView.setOnScrollListener(new InfiniteScrollerListener() {
            @Override
            public boolean onItemsLoaded(int page, int totalItems) {
                getNextReposPage(page);
                return true;
            }

            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }
        });
        starredReposListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Repository repository = (Repository) adapterView.getItemAtPosition(position);
                takeMeToRepo(repository);
            }
        });
    }


    //services
    private void getNextReposPage(int page) {
        progressBar.setVisibility(View.VISIBLE);
        //Retrofit asynchronous call, it is excecuted in another thread, I know you know :D just sayin
        GithubApiClient.GetService(this).getStarredRepos(page).enqueue(new Callback<GithubResponse>() {
            @Override
            public void onResponse(Call<GithubResponse> call, Response<GithubResponse> response) {
                if (response.body() != null) {
                    if (adapter == null && response.body().getItems() != null) {
                        //this is when getNextReposPage is called for the first time, we instantiate
                        //the adapter with the first result
                        adapter = new RepositoryAdapter(MainActivity.this,
                                R.layout.repository_list_item, response.body().getItems());
                        starredReposListView.setAdapter(adapter);
                    } else if (adapter != null && response.body().getItems() !=null){
                        //this is when getNextReposPage is called for the n > 1 time, adapter is
                        //instantiated we just need to change the dataSet. Since the minsdk is 15
                        //it won't have a problem with addAll Adapter method
                        adapter.addAll(response.body().getItems());
                        adapter.notifyDataSetChanged();
                    } else {
                        //notifying the user that we can't fetch data
                        Toast.makeText(MainActivity.this, R.string.business_layer_error, Toast.LENGTH_LONG).show();
                    }
                    progressBar.setVisibility(View.GONE);
                }
            }


            @Override
            public void onFailure(Call<GithubResponse> call, Throwable t) {
                //server error or timeoutexception due to no mobile data
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, R.string.server_error, Toast.LENGTH_LONG).show();
                //we should call FirebaseCrash.report(t); in production
            }
        });

    }

    //utilities
    private void takeMeToRepo(Repository repository) {
        if (repository == null)
            return;
        if (repository.getHtml_url().startsWith("http")) {
            Intent openUrlIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(repository.getHtml_url()));
            startActivity(openUrlIntent);
        }
        else {
            Toast.makeText(this, R.string.cannot_open_link, Toast.LENGTH_LONG).show();
        }
    }
}
