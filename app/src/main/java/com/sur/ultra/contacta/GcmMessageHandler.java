package com.sur.ultra.contacta;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

/**
 * Created by alexis on 5/9/16.
 */
public class GcmMessageHandler extends GcmListenerService {
    public static final int MESSAGE_NOTIFICATION_ID = 435345;
    public static final String TAG = "GcmMessageHandler";

    @Override
    public void onMessageReceived(String from, Bundle data) {
        String message = data.getString("message");

        Log.d(TAG, "From: " + from);
        Log.d(TAG, "Message: " + message);

        if (from.startsWith("/topics/")) {
            // message received from some topic.
        } else {
            // normal downstream message.
        }

        /**
         * Production applications would usually process the message here.
         * Eg: - Syncing with server.
         *     - Store message in local database.
         *     - Update UI.
         */

        /**
         * In some cases it may be useful to show a notification indicating to the user
         * that a message was received.
         */

        sendNotification(message);
    }

//    // Creates notification based on title and body received
//    private void createNotification(String title, String body) {
//        Context context = getBaseContext();
//        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle("Contacta")
//                .setContentText(body);
//        NotificationManager mNotificationManager = (NotificationManager) context
//                .getSystemService(Context.NOTIFICATION_SERVICE);
//        mNotificationManager.notify(MESSAGE_NOTIFICATION_ID, mBuilder.build());
//    }

    /*Comienzo copia GCM*/

    private void sendNotification(String message) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("GCM Message")
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

    /*Fin copia GCM*/

}
