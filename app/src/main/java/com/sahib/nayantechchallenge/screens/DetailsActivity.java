package com.sahib.nayantechchallenge.screens;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.nostra13.universalimageloader.core.ImageLoader;
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

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private RepositoryModel.Repo item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        initView();
    }

    private void initView() {
        item = (RepositoryModel.Repo) getIntent().getSerializableExtra(Constants.KEY_INTENT_REPO);

        if (item != null) {

            ((TextView) findViewById(R.id.textRepoName)).setText(item.getName());
            ((TextView) findViewById(R.id.textDescription)).setText(item.getDescription());
            ((TextView) findViewById(R.id.textOwnerName)).setText(item.getOwner().getLogin());
            ImageLoader.getInstance().displayImage(item.getOwner().getAvatar_url(), ((ImageView) findViewById(R.id.imageOwner)), Constants.IMG_OPTIONS);
            ((TextView) findViewById(R.id.textLanguage)).setText(item.getLanguage());
            ((TextView) findViewById(R.id.textWatchersCount)).setText("" + item.getWatchers_count());
            ((TextView) findViewById(R.id.textHtmlUrl)).setText(Html.fromHtml("<u>" + item.getHtml_url() + "</u>"));

            findViewById(R.id.textHtmlUrl).setOnClickListener(this);
        }

        handleToolbar();
    }

    private void handleToolbar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            if (item != null) {
                setTitle(item.getName());
            }
        }
    }

    private void openURL(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.textHtmlUrl:
                if (item != null && item.getHtml_url() != null) {
                    openURL(item.getHtml_url());
                }
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}