package com.example.formi.mediasoftnetworking.presentation.name_search;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.formi.mediasoftnetworking.R;
import com.example.formi.mediasoftnetworking.data.net.Controller;
import com.example.formi.mediasoftnetworking.domain.model.name.SearchResult;
import com.example.formi.mediasoftnetworking.other.Constants;
import com.example.formi.mediasoftnetworking.presentation.name_search.adapter.CustomSpinnerAdapter;
import com.example.formi.mediasoftnetworking.presentation.name_search.search_by_name_result.SearchResultByNameActivity;
//import com.example.formi.mediasoftnetworking.presentation.namesearch.ApiThread;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NameSearchActivity extends AppCompatActivity {
    public static final String EXTRA_SEARCH_RESULT = "extra_search_result";

    private Spinner spinnerCountries, spinnerSex, spinnerCount, spinnerSort;
    private CustomSpinnerAdapter adapterCountries, adapterSex, adapterCount, adapterSort;

    private EditText edtCity, edtAgeFrom, edtAgeTo, edtName;
    private Button btnSearch;

    private ProgressBar progBar;
    private RelativeLayout relativeLayout;

    private String userCountryIndex, userCity, userSexIndex, userAgeFrom, userAgeTo, userCount, userSortIndex, userName;

    // for enqueue-method
    // private SearchResult searchResult = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_search);

        progBar = findViewById(R.id.nameProgBar);
        relativeLayout = findViewById(R.id.relLayout);

        edtCity = findViewById(R.id.edtCity);
        edtAgeFrom = findViewById(R.id.edtAgeFrom);
        edtAgeTo = findViewById(R.id.edtAgeTo);
        edtName = findViewById(R.id.edtName);

        btnSearch = findViewById(R.id.btnSearchName);
        btnSearch.setOnClickListener(onSearchClickListener);

        initSpinners();
    }

    View.OnClickListener onSearchClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switchLoader(true);

            userCity = edtCity.getText().toString().trim();
            userAgeFrom = edtAgeFrom.getText().toString().trim();
            userAgeTo = edtAgeTo.getText().toString().trim();
            userName = edtName.getText().toString().trim();

            // RxJava-method
            Controller.getVkApi().getUsersByName(userName,
                    userCountryIndex,
                    userCity,
                    userSexIndex,
                    userAgeFrom,
                    userAgeTo,
                    userCount,
                    userSortIndex,
                    Constants.VkApiConstants.VERSION,
                    Constants.VkApiConstants.USER_ACCESS_TOKEN,
                    Constants.VkApiConstants.FIELDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<SearchResult>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onSuccess(SearchResult searchResult) {
                            if(searchResult != null){
                                if(!searchResult.getResponse().getItems().isEmpty()){
                                    Intent intent = new Intent(NameSearchActivity.this, SearchResultByNameActivity.class);
                                    intent.putExtra(EXTRA_SEARCH_RESULT, searchResult);
                                    startActivity(intent);
                                }else{
                                    Snackbar.make(btnSearch, "Не найдено ни одного пользователя", Snackbar.LENGTH_INDEFINITE).setActionTextColor(ContextCompat.getColor(NameSearchActivity.this, R.color.colorPrimary)).setAction("OK", v -> {}).show();
                                    switchLoader(false);
                                }
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            AlertDialog.Builder adBuilder = new AlertDialog.Builder(NameSearchActivity.this)
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
            /*Params params = new Params(userName,
                    userCountryIndex,
                    userCity,
                    userSexIndex,
                    userAgeFrom,
                    userAgeTo,
                    userCount,
                    userSortIndex);
            new ApiThread(params,
                    new com.example.formi.mediasoftnetworking.presentation.namesearch.ApiThread.SendSearchResultCallback() {
                        @Override
                        public void sendUser(SearchResult searchResult) {
                            if(searchResult != null){
                                if(!searchResult.getResponse().getItems().isEmpty()){
                                    Intent intent = new Intent(NameSearchActivity.this, SearchResultByNameActivity.class);
                                    intent.putExtra(EXTRA_SEARCH_RESULT, searchResult);
                                    startActivity(intent);
                                }else{
                                    NameSearchActivity.this.runOnUiThread(() -> {
                                        Snackbar.make(btnSearch, "Не найдено ни одного пользователя", Snackbar.LENGTH_INDEFINITE).setActionTextColor(ContextCompat.getColor(NameSearchActivity.this, R.color.colorPrimary)).setAction("OK", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v1) {

                                            }
                                        }).show();
                                        switchLoader(false);
                                    });
                                }
                            }
                        }

                        @Override
                        public void failMessage() {
                            AlertDialog.Builder adBuilder = new AlertDialog.Builder(NameSearchActivity.this)
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
                    })
                    .start();*/


            // enqueue-method
            /*Controller.getVkApi()
                    .getUsersByName(userName,
                            userCountryIndex,
                            userCity,
                            userSexIndex,
                            userAgeFrom,
                            userAgeTo,
                            userCount,
                            userSortIndex,
                            Constants.VkApiConstants.VERSION,
                            Constants.VkApiConstants.USER_ACCESS_TOKEN,
                            Constants.VkApiConstants.FIELDS)
                    .enqueue(new Callback<SearchResult>() {
                        @Override
                        public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                            if(response.isSuccessful()){
                                searchResult = response.body();
                                if(searchResult != null){
                                    if(!searchResult.getResponse().getItems().isEmpty()){
                                        Intent intent = new Intent(NameSearchActivity.this, SearchResultByNameActivity.class);
                                        intent.putExtra(EXTRA_SEARCH_RESULT, searchResult);
                                        startActivity(intent);
                                    }else{
                                        Snackbar.make(btnSearch, "Не найдено ни одного пользователя", Snackbar.LENGTH_INDEFINITE).setActionTextColor(ContextCompat.getColor(NameSearchActivity.this, R.color.colorPrimary)).setAction("OK", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                            }
                                        }).show();
                                        switchLoader(false);
                                    }
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<SearchResult> call, Throwable t) {
                            AlertDialog.Builder adBuilder = new AlertDialog.Builder(NameSearchActivity.this)
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
                    });*/

        }
    };

    private void switchLoader(boolean flag){
        if(flag){
            progBar.setVisibility(View.VISIBLE);
            relativeLayout.setVisibility(View.GONE);
        }else{
            progBar.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        switchLoader(false);
    }

    private void initSpinners(){
        spinnerCountries = findViewById(R.id.spnCountries);
        adapterCountries = new CustomSpinnerAdapter(this, getResources().getStringArray(R.array.countries));
        spinnerCountries.setAdapter(adapterCountries);
        spinnerCountries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userCountryIndex = String.valueOf(position+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerSex = findViewById(R.id.spnSex);
        adapterSex = new CustomSpinnerAdapter(this, getResources().getStringArray(R.array.sex));
        spinnerSex.setAdapter(adapterSex);
        spinnerSex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userSexIndex = String.valueOf(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerCount = findViewById(R.id.spnCount);
        adapterCount = new CustomSpinnerAdapter(this, getResources().getStringArray(R.array.users_count));
        spinnerCount.setAdapter(adapterCount);
        spinnerCount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userCount = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerSort = findViewById(R.id.spnSort);
        adapterSort = new CustomSpinnerAdapter(this, getResources().getStringArray(R.array.sort_result));
        spinnerSort.setAdapter(adapterSort);
        spinnerSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userSortIndex = String.valueOf(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


}
