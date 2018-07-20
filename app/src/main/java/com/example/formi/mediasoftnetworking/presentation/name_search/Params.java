package com.example.formi.mediasoftnetworking.presentation.name_search;

public class Params {

    private String userName;
    private String userCountryIndex;
    private String userCity;
    private String userSexIndex;
    private String userAgeFrom;
    private String userAgeTo;
    private String userCount;
    private String userSortIndex;

    public Params(String userName, String userCountryIndex, String userCity, String userSexIndex, String userAgeFrom, String userAgeTo, String userCount, String userSortIndex) {
        this.userName = userName;
        this.userCountryIndex = userCountryIndex;
        this.userCity = userCity;
        this.userSexIndex = userSexIndex;
        this.userAgeFrom = userAgeFrom;
        this.userAgeTo = userAgeTo;
        this.userCount = userCount;
        this.userSortIndex = userSortIndex;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCountryIndex() {
        return userCountryIndex;
    }

    public void setUserCountryIndex(String userCountryIndex) {
        this.userCountryIndex = userCountryIndex;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public String getUserSexIndex() {
        return userSexIndex;
    }

    public void setUserSexIndex(String userSexIndex) {
        this.userSexIndex = userSexIndex;
    }

    public String getUserAgeFrom() {
        return userAgeFrom;
    }

    public void setUserAgeFrom(String userAgeFrom) {
        this.userAgeFrom = userAgeFrom;
    }

    public String getUserAgeTo() {
        return userAgeTo;
    }

    public void setUserAgeTo(String userAgeTo) {
        this.userAgeTo = userAgeTo;
    }

    public String getUserCount() {
        return userCount;
    }

    public void setUserCount(String userCount) {
        this.userCount = userCount;
    }

    public String getUserSortIndex() {
        return userSortIndex;
    }

    public void setUserSortIndex(String userSortIndex) {
        this.userSortIndex = userSortIndex;
    }
}
