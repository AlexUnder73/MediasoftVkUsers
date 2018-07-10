package com.example.formi.mediasoftnetworking.presentation.requestsActivity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.formi.mediasoftnetworking.R;
import com.example.formi.mediasoftnetworking.data.db.DbHelper;
import com.example.formi.mediasoftnetworking.domain.model.User;
import com.example.formi.mediasoftnetworking.other.Constants;
import com.example.formi.mediasoftnetworking.presentation.requestsActivity.adapter.RequestsAdapter;

import java.util.ArrayList;
import java.util.List;

public class RequestsActivity extends AppCompatActivity implements RequestsAdapter.OnDeleteClickListener{

    private RecyclerView recView;
    private RequestsAdapter requestsAdapter;

    private DbHelper dbHelper;
    private List<User> userList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);

        dbHelper = new DbHelper(this);

        userList = dbHelper.getUserList();
        requestsAdapter = new RequestsAdapter(this, userList);
        requestsAdapter.setOnDeleteClickListenerInstance(this);

        recView  = findViewById(R.id.recView);
        recView.setLayoutManager(new LinearLayoutManager(this));
        recView.setAdapter(requestsAdapter);
    }

    @Override
    public void deleteFromDb(int pos) {
        User user = userList.get(pos);
        userList.remove(pos);
        requestsAdapter.notifyItemRemoved(pos);
        dbHelper.deleteFromDataBase(user.getUser().get(0).getId());
        Log.i(Constants.LOG_TAG, "Deleted user - " + user.getUser().get(0).getFirstName() + " " + user.getUser().get(0).getLastName());
    }
}
