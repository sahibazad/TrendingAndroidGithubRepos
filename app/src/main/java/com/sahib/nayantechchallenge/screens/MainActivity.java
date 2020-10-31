package com.sahib.nayantechchallenge.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.sahib.nayantechchallenge.R;
import com.sahib.nayantechchallenge.adapters.RepoAdapter;
import com.sahib.nayantechchallenge.constants.Constants;
import com.sahib.nayantechchallenge.network.ApiClient;
import com.sahib.nayantechchallenge.network.CheckConnection;
import com.sahib.nayantechchallenge.network.models.RepositoryModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeContainer;
    private RecyclerView recyclerView;
    private List<RepositoryModel.Repo> list = new ArrayList<>();
    private RepoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerView);
        swipeContainer = findViewById(R.id.swipeRefreshLayout);

        initRecyclerView();
        initSwipeListener();
        swipeContainer.setRefreshing(true);
        getRepos();
    }

    private void initRecyclerView() {
        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new RepoAdapter(this, new RepoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RepositoryModel.Repo item) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra(Constants.KEY_INTENT_REPO, item);
                startActivity(intent);
            }
        }, list);

        recyclerView.setAdapter(adapter);
    }

    private void initSwipeListener() {
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getRepos();
            }
        });
    }

    private void getRepos() {
        if (!CheckConnection.isConnected(this)) {
            Toast.makeText(MainActivity.this, "Internet connection not available, please try again later.", Toast.LENGTH_SHORT).show();
        }

        if (ApiClient.getBaseInstance(this) != null) {
            HashMap<String, String> map = new HashMap();
            map.put("q", "Android+language:java");
            map.put("sort", "stars");
            map.put("order", "desc");

            swipeContainer.setRefreshing(true);
            ApiClient.getBaseInstance(this).listRepos(map).enqueue(new Callback<RepositoryModel>() {
                @Override
                public void onResponse(Call<RepositoryModel> call, Response<RepositoryModel> response) {
                    swipeContainer.setRefreshing(false);
                    if (response.errorBody() == null) {
                        handleResponse(response.body().getItems());
                    } else {
                        Toast.makeText(MainActivity.this, "Error fetching repos!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<RepositoryModel> call, Throwable t) {
                    swipeContainer.setRefreshing(false);
                    Toast.makeText(MainActivity.this, "Error fetching repos!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void handleResponse(List<RepositoryModel.Repo> response) {
        list.clear();
        if (response != null && response.size() > 0) {
            list.addAll(response);
        } else {
            findViewById(R.id.no_history).setVisibility(View.VISIBLE);
        }
        adapter.notifyDataSetChanged();
    }
}