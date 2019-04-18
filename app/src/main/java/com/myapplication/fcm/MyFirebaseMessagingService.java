package com.myapplication.fcm;

import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.myapplication.utils.NotificationHelper;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    NotificationHelper notificationHelper;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        notificationHelper = new NotificationHelper(getApplicationContext());

        //if the message contains data payload
        //It is a map of custom keyvalues
        //we can read it easily
        if (remoteMessage.getData().size() > 0) {
            //handle the data message here
        }


        Log.d("Kill mode", remoteMessage.getData().toString());

        //getting the title and the body
        String title = remoteMessage.getNotification().getTitle();
        String body = remoteMessage.getNotification().getBody();

        String data_key1 = remoteMessage.getData().get("key_1");
        String data_key2 = remoteMessage.getData().get("key_2");


        notificationHelper.createNotification(title, body,data_key1,data_key2);


        //then here we can use the title and body to build a notification

    }
}
