package com.example.formi.mediasoftnetworking.presentation.idSearchActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Looper;
import android.util.Log;

import com.example.formi.mediasoftnetworking.data.net.Controller;
import com.example.formi.mediasoftnetworking.domain.model.User;
import com.example.formi.mediasoftnetworking.other.Constants;

import java.io.IOException;
import java.util.List;

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
            Response<User> response = Controller.getApi()
                    .getUser(id, Constants.VkApiConstants.VERSION, Constants.VkApiConstants.SERVER_ACCESS_TOKEN, Constants.VkApiConstants.FIELDS)
                    .execute();
            if(response.isSuccessful()) {
                User user = response.body();
                sendUserCallback.sendUser(user);
            }
        } catch (IOException e) {
            sendUserCallback.failMessage();
        }
    }
}
