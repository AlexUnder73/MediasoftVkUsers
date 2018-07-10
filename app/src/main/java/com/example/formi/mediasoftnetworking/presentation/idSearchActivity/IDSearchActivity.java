package com.example.formi.mediasoftnetworking.presentation.idSearchActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.formi.mediasoftnetworking.R;
import com.example.formi.mediasoftnetworking.data.db.DbHelper;
import com.example.formi.mediasoftnetworking.data.net.Controller;
import com.example.formi.mediasoftnetworking.domain.model.User;
import com.example.formi.mediasoftnetworking.other.Constants;
import com.example.formi.mediasoftnetworking.presentation.mainActivity.MainActivity;
import com.example.formi.mediasoftnetworking.presentation.searchResultActivity.SearchResultActivity;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IDSearchActivity extends AppCompatActivity {
    public static final String EXTRA_USER = "extra_user";

    private EditText editUserId;
    private Button btnSearch;
    private ProgressBar progBar;
    private LinearLayout linearLayout;

    //private User user = null;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id_search);

        dbHelper = new DbHelper(this);

        linearLayout = findViewById(R.id.search_id_container);
        progBar = findViewById(R.id.progBar);
        editUserId = findViewById(R.id.edtUserId);
        btnSearch = findViewById(R.id.btnSearchId);
        btnSearch.setOnClickListener(onSearchClickListener);
    }

    View.OnClickListener onSearchClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switchLoader(true);
            String id = editUserId.getText().toString().trim();

            if(id.isEmpty()){
                Toast.makeText(IDSearchActivity.this, "id не может быть пустым", Toast.LENGTH_SHORT).show();
                switchLoader(false);
                return;
            }

            new ApiThread(id, new ApiThread.SendUserCallback() {
                @Override
                public void sendUser(User user) {
                    if (user != null) {
                        if(!user.getUser().isEmpty()){
                            Intent intent = new Intent(IDSearchActivity.this, SearchResultActivity.class);
                            intent.putExtra(EXTRA_USER, user);
                            dbHelper.addToDataBase(user);
                            dbHelper.showDataToLogs();
                            startActivity(intent);
                        }else{
                            IDSearchActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(IDSearchActivity.this, "Пользователь с данным id не найден", Toast.LENGTH_SHORT).show();
                                    switchLoader(false);
                                }
                            });
                            editUserId.setText("");
                        }
                    }
                }

                @Override
                public void failMessage() {
                    AlertDialog.Builder adBuilder = new AlertDialog.Builder(IDSearchActivity.this)
                            .setTitle("Ошибка")
                            .setMessage("Что-то пошло не так. Возможная проблема - отсутствие интернет-соединения.")
                            .setPositiveButton("ОК", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alertDialog = adBuilder.create();
                    alertDialog.show();
                }
            }).start();
                    /*Controller.getApi()
                    .getUser(id, Constants.VkApiConstants.VERSION, Constants.VkApiConstants.SERVER_ACCESS_TOKEN, Constants.VkApiConstants.FIELDS)
                    .enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            if(response.isSuccessful()){
                                user = response.body();
                                if (user != null) {
                                    if(!user.getUser().isEmpty()){
                                        Intent intent = new Intent(IDSearchActivity.this, SearchResultActivity.class);
                                        intent.putExtra(EXTRA_USER, user);
                                        dbHelper.addToDataBase(user);

                                        dbHelper.showDataToLogs();
                                        startActivity(intent);
                                    }else{
                                        Toast.makeText(IDSearchActivity.this, "Пользователь с данным id не найден", Toast.LENGTH_SHORT).show();
                                        editUserId.setText("");
                                    }
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            AlertDialog.Builder adBuilder = new AlertDialog.Builder(IDSearchActivity.this)
                                    .setTitle("Ошибка")
                                    .setMessage("Что-то пошло не так. Возможная проблема - отсутствие интернет-соединения.")
                                    .setPositiveButton("ОК", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    });
                            AlertDialog alertDialog = adBuilder.create();
                            alertDialog.show();
                        }
                    });*/
        }
    };

    private void switchLoader(boolean flag){
        if(flag){
            progBar.setVisibility(View.VISIBLE);
            linearLayout.setVisibility(View.INVISIBLE);
        }else{
            progBar.setVisibility(View.INVISIBLE);
            linearLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        switchLoader(false);
    }
}
