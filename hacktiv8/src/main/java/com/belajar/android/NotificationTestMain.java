package com.belajar.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NotificationTestMain extends AppCompatActivity implements View.OnClickListener {

    private static final String CHANNEL_ID = "Important_mail_channel";
    NotificationManagerCompat sNotificationManagerCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_test_main);

        Button simpleNotif = findViewById(R.id.SIMPLE_NOTIF);
        Button bigTextNotif = findViewById(R.id.BIGTEXTSTYLE_NOTIF);
        Button bigPictNotif = findViewById(R.id.BIGPICTURESTYLE_NOTIF);
        Button inboxNotif = findViewById(R.id.INBOX_NOTIF);
        Button actionNotif = findViewById(R.id.ACTION_NOTIF);

        simpleNotif.setOnClickListener(this);
        bigTextNotif.setOnClickListener(this);
        bigPictNotif.setOnClickListener(this);
        inboxNotif.setOnClickListener(this);
        actionNotif.setOnClickListener(this);

        createNotifChannel();
        sNotificationManagerCompat = NotificationManagerCompat.from(NotificationTestMain.this);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.SIMPLE_NOTIF:
                createSimpleNotif("Simple Notifikasi","Deskripsi Simple Notifikasi",1);
                break;
            case R.id.BIGTEXTSTYLE_NOTIF:
                createBigTextNotify("BigText Notifikasi","Deskripsi Simple Notifikasi,Deskripsi Simple Notifikasi,Deskripsi Simple Notifikasi,Deskripsi Simple Notifikasi,Deskripsi Simple Notifikasi",2);
                break;
            case R.id.BIGPICTURESTYLE_NOTIF:
                createBigPictureNotif("Big Picture Style","Big Picture Style Deskripsi",3);
                break;
            case R.id.INBOX_NOTIF:
                createInboxStyleNotif("Inbox Style","Inbox Style Deskripsi",4);
                break;
            case R.id.ACTION_NOTIF:
                createActionStyleNotif("Action Style","Action Style Deskripsi",5);
                break;
        }
    }

    private void createSimpleNotif(String title, String text, int notificationID){
        sNotificationManagerCompat.cancelAll();

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://hacktiv8.com"));
        @SuppressLint("UnspecifiedImmutableFlag") PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,intent,0);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.icon_notif)
                .setContentTitle(title)
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();

        sNotificationManagerCompat.notify(notificationID, notification);
    }

    private void createBigTextNotify(String title, String text, int notificationID){
        sNotificationManagerCompat.cancelAll();

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_notif);

        NotificationCompat.BigTextStyle style = new NotificationCompat.BigTextStyle().bigText(text)
                .setBigContentTitle(null)
                .setSummaryText("BigtextStyle");

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.icon_notif)
                .setContentTitle(title)
                .setContentText(text)
                .setStyle(style)
                .setLargeIcon(bitmap)
                .build();

        sNotificationManagerCompat.notify(notificationID, notification);
    }

    private void createInboxStyleNotif(String title, String text, int notificationId) {
        sNotificationManagerCompat.cancelAll();

        String line1 = "Hallo Apakabar?";
        String line2 = "Lagi di mana?";
        String line3 = "Kapan Pulang?";
        String line4 = "Sedang Apa?";
        String line5 = "Lagi di mana?";

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://hacktiv8.com"));
        @SuppressLint("UnspecifiedImmutableFlag") PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);

        NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle()
                .addLine(line1)
                .addLine(line2)
                .addLine(line3)
                .addLine(line4)
                .addLine(line5)
                .setSummaryText("InboxStyle")
                .setBigContentTitle(null);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.icon_notif)
                .setContentTitle(title)
                .setContentText(text)
                .setStyle(style)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();

        sNotificationManagerCompat.notify(notificationId, notification);
    }

    private void createActionStyleNotif(String title, String text, int notificationId) {
        sNotificationManagerCompat.cancelAll();

        Intent intent = new Intent(getApplicationContext(), NotificationTestReceiver.class);
        intent.setAction("ACTION_CANCEL");
        intent.putExtra("id", notificationId);

        @SuppressLint("UnspecifiedImmutableFlag") PendingIntent pendingIntent =
                PendingIntent.getBroadcast(getApplicationContext(),
                        0,intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Action actionDismiss =
                new NotificationCompat.Action.Builder(0, "Dismiss", pendingIntent).build();


        NotificationCompat.Action actionDelete=
                new NotificationCompat.Action.Builder(0, "Delete", pendingIntent).build();

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.icon_notif)
                .setContentTitle(title)
                .setContentText(text)
                .setStyle(new NotificationCompat.BigTextStyle().bigText("This is example BigText Style notif with action"))
                .addAction(actionDismiss)
                .addAction(actionDelete)
                .build();

        sNotificationManagerCompat.notify(notificationId, notification);
    }


    private void createBigPictureNotif(String title, String text, int notificationId) {
        sNotificationManagerCompat.cancelAll();

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://hacktiv8.com"));
        @SuppressLint("UnspecifiedImmutableFlag") PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_notif);

        NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle()
                .bigPicture(bitmap)
                .setSummaryText("Big Picture Style is used to show large image")
                .setBigContentTitle(null)
                .bigLargeIcon(null);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.icon_notif)
                .setContentTitle(title)
                .setContentText(text)
                .setStyle(style)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();

        sNotificationManagerCompat.notify(notificationId, notification);
    }


    private void createNotifChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "Important_null_channel";
            String description = "This Channel will show notify only to important people";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}