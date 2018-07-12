package com.example.formi.mediasoftnetworking.data.net.callback;

import com.example.formi.mediasoftnetworking.domain.model.name.SearchResult;
import com.example.formi.mediasoftnetworking.domain.model.id.User;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VkSearchApi {
    @GET("/method/users.get")
    Single<User> getUserById(@Query("user_ids") String usrId,
                             @Query("v") String version,
                             @Query("access_token") String access_token,
                             @Query("fields") String fields);

    @GET("/method/users.search")
    Single<SearchResult> getUsersByName(@Query("q") String name,
                                      @Query("country") String countryIndex,
                                      @Query("hometown") String city,
                                      @Query("sex") String sexIndex,
                                      @Query("age_from") String ageFrom,
                                      @Query("age_to") String ageTo,
                                      @Query("count") String usersCount,
                                      @Query("sort") String sort,
                                      @Query("v") String version,
                                      @Query("access_token") String access_token,
                                      @Query("fields") String fields);
}
