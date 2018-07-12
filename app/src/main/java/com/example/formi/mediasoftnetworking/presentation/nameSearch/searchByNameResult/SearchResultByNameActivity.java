package com.example.formi.mediasoftnetworking.presentation.nameSearch.searchByNameResult;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.formi.mediasoftnetworking.R;
import com.example.formi.mediasoftnetworking.domain.model.name.SearchResult;
import com.example.formi.mediasoftnetworking.presentation.nameSearch.adapter.NameAdapter;
import com.example.formi.mediasoftnetworking.presentation.nameSearch.NameSearchActivity;

public class SearchResultByNameActivity extends AppCompatActivity {

    private RecyclerView recView;
    private NameAdapter adapter;

    private SearchResult searchResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result_by_name);

        searchResult = (SearchResult) getIntent().getSerializableExtra(NameSearchActivity.EXTRA_SEARCH_RESULT);

        recView = findViewById(R.id.nameRecView);
        recView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NameAdapter(this, searchResult.getResponse().getItems());
        recView.setAdapter(adapter);
    }
}
