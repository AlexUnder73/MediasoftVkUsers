package com.example.formi.mediasoftnetworking.presentation.nameSearch;

import com.example.formi.mediasoftnetworking.data.net.Controller;
import com.example.formi.mediasoftnetworking.domain.model.name.SearchResult;
import com.example.formi.mediasoftnetworking.other.Constants;

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

    public ApiThread(String userName, String userCountryIndex, String userCity, String userSexIndex, String userAgeFrom, String userAgeTo, String userCount, String userSortIndex, SendSearchResultCallback sendSearchResultCallback) {
        this.userName = userName;
        this.userCountryIndex = userCountryIndex;
        this.userCity = userCity;
        this.userSexIndex = userSexIndex;
        this.userAgeFrom = userAgeFrom;
        this.userAgeTo = userAgeTo;
        this.userCount = userCount;
        this.userSortIndex = userSortIndex;

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
}
