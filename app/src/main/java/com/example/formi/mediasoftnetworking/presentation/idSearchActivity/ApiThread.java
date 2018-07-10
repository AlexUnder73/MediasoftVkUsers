package com.example.formi.mediasoftnetworking.presentation.idSearchActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Looper;

import com.example.formi.mediasoftnetworking.data.net.Controller;
import com.example.formi.mediasoftnetworking.domain.model.User;
import com.example.formi.mediasoftnetworking.other.Constants;

import java.io.IOException;

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
            if(Controller.getApi()
                    .getUser(id, Constants.VkApiConstants.VERSION, Constants.VkApiConstants.SERVER_ACCESS_TOKEN, Constants.VkApiConstants.FIELDS)
                    .execute().isSuccessful()) {
                User user = Controller.getApi()
                        .getUser(id, Constants.VkApiConstants.VERSION, Constants.VkApiConstants.SERVER_ACCESS_TOKEN, Constants.VkApiConstants.FIELDS)
                        .execute().body();
                sendUserCallback.sendUser(user);
            }else{
                sendUserCallback.failMessage();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
