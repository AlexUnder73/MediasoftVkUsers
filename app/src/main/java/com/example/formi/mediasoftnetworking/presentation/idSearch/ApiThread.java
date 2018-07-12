/*package com.example.formi.mediasoftnetworking.presentation.idSearch;

import com.example.formi.mediasoftnetworking.data.net.Controller;
import com.example.formi.mediasoftnetworking.domain.model.id.User;
import com.example.formi.mediasoftnetworking.other.Constants;

import java.io.IOException;

import retrofit2.Response;

public class ApiThread extends Thread {

    public interface SendUserCallback{
        void sendUser(User user);
        void failMessage();
    }

    private String id;
    private SendUserCallback sendUserCallback;

    public ApiThread(String id, SendUserCallback sendUserCallback){
        this.id = id;
        this.sendUserCallback = sendUserCallback;
    }

    @Override
    public void run() {
        try {
            Response<User> response = Controller.getVkApi()
                    .getUserById(id, Constants.VkApiConstants.VERSION, Constants.VkApiConstants.SERVER_ACCESS_TOKEN, Constants.VkApiConstants.FIELDS)
                    .execute();
            if(response.isSuccessful()) {
                User user = response.body();
                sendUserCallback.sendUser(user);
            }
        } catch (IOException e) {
            sendUserCallback.failMessage();
        }
    }
}*/
