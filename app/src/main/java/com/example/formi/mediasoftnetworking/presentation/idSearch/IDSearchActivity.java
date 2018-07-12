package com.example.formi.mediasoftnetworking.presentation.idSearch;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.formi.mediasoftnetworking.R;
import com.example.formi.mediasoftnetworking.data.db.DbHelper;
import com.example.formi.mediasoftnetworking.data.net.Controller;
import com.example.formi.mediasoftnetworking.domain.model.id.User;
import com.example.formi.mediasoftnetworking.domain.model.name.SearchResult;
import com.example.formi.mediasoftnetworking.other.Constants;
import com.example.formi.mediasoftnetworking.presentation.idSearch.requests.RequestsActivity;
import com.example.formi.mediasoftnetworking.presentation.idSearch.searchByIdResult.SearchResultActivity;

import java.util.Observable;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class IDSearchActivity extends AppCompatActivity {
    public static final String EXTRA_USER = "extra_user";

    private EditText editUserId;
    private Button btnSearch;
    private ProgressBar progBar;
    private LinearLayout linearLayout;

    private TextView txtAllReqs;

    //for enqueue-method
    //private User user = null;

    private DbHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id_search);

        dbHelper = new DbHelper(this);

        linearLayout = findViewById(R.id.search_id_container);
        progBar = findViewById(R.id.idProgBar);
        editUserId = findViewById(R.id.edtUserId);
        btnSearch = findViewById(R.id.btnSearchId);
        btnSearch.setOnClickListener(onSearchClickListener);

        txtAllReqs = findViewById(R.id.txtAllRequests);

        String htmlText = "<u>ПОСМОТРЕТЬ ВСЕ ЗАПРОСЫ</u>";
        Spanned spannedText = Html.fromHtml(htmlText);
        txtAllReqs.setText(spannedText);
        txtAllReqs.setOnClickListener(onAllRequestsClickListener);
    }

    View.OnClickListener onAllRequestsClickListener = v -> startActivity(new Intent(IDSearchActivity.this, RequestsActivity.class));

    View.OnClickListener onSearchClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switchLoader(true);
            String id = editUserId.getText().toString().trim();

            if(id.isEmpty()){
                Snackbar.make(btnSearch, "id не может быть пустым", Snackbar.LENGTH_INDEFINITE).setActionTextColor(ContextCompat.getColor(IDSearchActivity.this, R.color.colorPrimary)).setAction("OK", v1 -> {}).show();
                switchLoader(false);
                return;
            }

            Controller.getVkApi().getUserById(id, Constants.VkApiConstants.VERSION, Constants.VkApiConstants.SERVER_ACCESS_TOKEN, Constants.VkApiConstants.FIELDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<User>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onSuccess(User user) {
                            if (user != null) {
                                if(user.getResponse() != null){
                                    Intent intent = new Intent(IDSearchActivity.this, SearchResultActivity.class);
                                    intent.putExtra(EXTRA_USER, user);
                                    dbHelper.addToDataBase(user);
                                    dbHelper.showDataToLogs();
                                    startActivity(intent);
                                }else{
                                    Snackbar.make(btnSearch, "Пользователь с данным id не найден", Snackbar.LENGTH_INDEFINITE).setActionTextColor(ContextCompat.getColor(IDSearchActivity.this, R.color.colorPrimary)).setAction("OK", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            }}).show();
                                    switchLoader(false);
                                    editUserId.setText("");
                                }
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            AlertDialog.Builder adBuilder = new AlertDialog.Builder(IDSearchActivity.this)
                                    .setTitle("Ошибка")
                                    .setMessage("Что-то пошло не так. Возможная проблема - отсутствие интернет-соединения.")
                                    .setCancelable(false)
                                    .setPositiveButton("ОК", (dialog, which) -> {
                                        dialog.cancel();
                                        switchLoader(false);
                                    });
                            AlertDialog alertDialog = adBuilder.create();
                            alertDialog.show();
                        }
                    });

            // execute-method
            /*new ApiThread(id, new ApiThread.SendUserCallback() {
                @Override
                public void sendUser(User user) {
                    if (user != null) {
                        if(!user.getResponse().isEmpty()){
                            Intent intent = new Intent(IDSearchActivity.this, SearchResultActivity.class);
                            intent.putExtra(EXTRA_USER, user);
                            dbHelper.addToDataBase(user);
                            dbHelper.showDataToLogs();
                            startActivity(intent);
                        }else{
                            IDSearchActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Snackbar.make(btnSearch, "Пользователь с данным id не найден", Snackbar.LENGTH_INDEFINITE).setActionTextColor(ContextCompat.getColor(IDSearchActivity.this, R.color.colorPrimary)).setAction("OK", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                        }
                                    }).show();
                                    switchLoader(false);
                                }
                            });
                            editUserId.setText("");
                        }
                    }
                }

                @Override
                public void failMessage() {
                    IDSearchActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            AlertDialog.Builder adBuilder = new AlertDialog.Builder(IDSearchActivity.this)
                                    .setTitle("Ошибка")
                                    .setMessage("Что-то пошло не так. Возможная проблема - отсутствие интернет-соединения.")
                                    .setPositiveButton("ОК", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                            switchLoader(false);
                                        }
                                    });
                            AlertDialog alertDialog = adBuilder.create();
                            alertDialog.show();
                        }
                    });
                }
            }).start();*/

            // .enqueue-method
            /*Controller.getVkApi()
                   .getVkApi(id, Constants.VkApiConstants.VERSION, Constants.VkApiConstants.SERVER_ACCESS_TOKEN, Constants.VkApiConstants.FIELDS)
                   .enqueue(new Callback<User>() {
                       @Override
                       public void onResponse(Call<User> call, Item<User> response) {
                           if(response.isSuccessful()){
                               user = response.body();
                               if (user != null) {
                                   if(!user.getVkApi().isEmpty()){
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
            linearLayout.setVisibility(View.GONE);
            txtAllReqs.setVisibility(View.GONE);
        }else{
            progBar.setVisibility(View.GONE);
            linearLayout.setVisibility(View.VISIBLE);
            txtAllReqs.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        switchLoader(false);
    }
}
