package com.example.formi.mediasoftnetworking.presentation.searchResultActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.formi.mediasoftnetworking.R;
import com.example.formi.mediasoftnetworking.domain.model.User;
import com.example.formi.mediasoftnetworking.presentation.idSearchActivity.IDSearchActivity;
import com.squareup.picasso.Picasso;

public class SearchResultActivity extends AppCompatActivity {
    private ImageView imgPhoto;
    private TextView txtFirstName, txtLastName;

    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        user = (User) getIntent().getSerializableExtra(IDSearchActivity.EXTRA_USER);

        imgPhoto = findViewById(R.id.imgPhoto);
        txtFirstName = findViewById(R.id.txtFirstName);
        txtLastName = findViewById(R.id.txtLastName);

        getResult(user);

        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchResultActivity.this, IDSearchActivity.class));
            }
        });

    }

    private void getResult(User user){
        Picasso.with(SearchResultActivity.this).load(user.getUser().get(0).getImgURL()).placeholder(R.drawable.place_holder).error(R.drawable.error).into(imgPhoto);
        txtFirstName.setText(user.getUser().get(0).getFirstName());
        txtLastName.setText(user.getUser().get(0).getLastName());
    }
}
