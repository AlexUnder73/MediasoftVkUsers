package com.example.formi.mediasoftnetworking.presentation.mainActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.formi.mediasoftnetworking.R;
import com.example.formi.mediasoftnetworking.presentation.idSearchActivity.IDSearchActivity;
import com.example.formi.mediasoftnetworking.presentation.nameSearchActivity.NameSearchActivity;
import com.example.formi.mediasoftnetworking.presentation.requestsActivity.RequestsActivity;

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

        String htmlText = "<u>ПОСМОТРЕТЬ ВСЕ ЗАПРОСЫ</u>";
        Spanned spannedText = Html.fromHtml(htmlText);
        txtAllReqs.setText(spannedText);
        txtAllReqs.setOnClickListener(onAllRequestsClickListener);

        btnIdSearch.setOnClickListener(onIdSearchClickListener);
        btnNameSearch.setOnClickListener(onNameSearchClickListener);
    }

    View.OnClickListener onIdSearchClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(MainActivity.this, IDSearchActivity.class));
        }
    };

    View.OnClickListener onNameSearchClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            startActivity(new Intent(MainActivity.this, NameSearchActivity.class));
        }
    };

    View.OnClickListener onAllRequestsClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(MainActivity.this, RequestsActivity.class));
        }
    };
}
