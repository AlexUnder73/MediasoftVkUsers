/*package com.example.formi.mediasoftnetworking.presentation.namesearch;

import com.example.formi.mediasoftnetworking.data.net.Controller;
import com.example.formi.mediasoftnetworking.domain.model.name.SearchResult;
import com.example.formi.mediasoftnetworking.other.Constants;
import com.example.formi.mediasoftnetworking.presentation.namesearch.Params;

import java.io.IOException;

import retrofit2.Response;

public class ApiThread extends Thread {

    public interface SendSearchResultCallback{
        void sendUser(SearchResult searchResult);
        void failMessage();
    }

    private SendSearchResultCallback sendSearchResultCallback;

    private String userName;
    private String userCountryIndex;
    private String userCity;
    private String userSexIndex;
    private String userAgeFrom;
    private String userAgeTo;
    private String userCount;
    private String userSortIndex;

    public ApiThread(Params params, SendSearchResultCallback sendSearchResultCallback) {
        this.userName = params.getUserName();
        this.userCountryIndex = params.getUserCountryIndex();
        this.userCity = params.getUserCity();
        this.userSexIndex = params.getUserSexIndex();
        this.userAgeFrom = params.getUserAgeFrom();
        this.userAgeTo = params.getUserAgeTo();
        this.userCount = params.getUserCount();
        this.userSortIndex = params.getUserSortIndex();

        this.sendSearchResultCallback = sendSearchResultCallback;
    }

    @Override
    public void run() {
        Response<SearchResult> response = null;
        try {
            response = Controller.getVkApi()
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
                    .execute();
            if(response.isSuccessful()){
                SearchResult searchResult = response.body();
                sendSearchResultCallback.sendUser(searchResult);
            }
        } catch (IOException e) {
            sendSearchResultCallback.failMessage();
        }
    }
}*/
