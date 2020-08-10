package com.example.lab8;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseInstanceIDService extends FirebaseMessagingService {
    String TAG = "MyFirebaseInstanceIDService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.e(TAG, refreshedToken);


        Log.e("message", remoteMessage.getFrom() + " : " +
                remoteMessage.getData().toString());

        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            Log.d(TAG, remoteMessage.getNotification().getBody() + remoteMessage.getNotification().getTitle());
        }
    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.d(TAG, "onNewToken: " + s);
    }
}
