package com.example.formi.mediasoftnetworking.presentation.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.formi.mediasoftnetworking.R;
import com.example.formi.mediasoftnetworking.presentation.id_search.IDSearchActivity;
import com.example.formi.mediasoftnetworking.presentation.name_search.NameSearchActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnIdSearch, btnNameSearch;
    private TextView txtAllReqs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIdSearch = findViewById(R.id.btnIdSearch);
        btnNameSearch = findViewById(R.id.btnNameSearch);
        txtAllReqs = findViewById(R.id.txtAllRequests);

        btnIdSearch.setOnClickListener(onIdSearchClickListener);
        btnNameSearch.setOnClickListener(onNameSearchClickListener);
    }

    View.OnClickListener onIdSearchClickListener = v -> startActivity(new Intent(MainActivity.this, IDSearchActivity.class));

    View.OnClickListener onNameSearchClickListener = v -> startActivity(new Intent(MainActivity.this, NameSearchActivity.class));
}
