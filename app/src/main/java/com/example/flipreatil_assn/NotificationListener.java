package com.example.flipreatil_assn;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.PowerManager;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.Locale;

public class NotificationListener extends NotificationListenerService {

    TextToSpeech textToSpeech;
    MediaPlayer mp;
    private PowerManager.WakeLock wakeLock;

//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
//        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
//                "SoundService::WakeLock");
//        wakeLock.acquire();
//
//
//
//
//        startForeground(1, new Notification());
//
//        return START_STICKY;
//    }

    @Override
    public void onListenerConnected() {
        super.onListenerConnected();
        Log.d("MANAGER","CONNECTED TO NOTIFICATION MANAGER");
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);
        if(sbn.getPackageName().equalsIgnoreCase("com.whatsapp") || sbn.getPackageName().equalsIgnoreCase("com.instagram")){
            playSound();
            speakNotification(sbn.getNotification().toString());
        }
    }


    private void playSound(){

        try{
            mp = MediaPlayer.create(this,R.raw.sound1);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mp.release();
                }
            });
        } catch (Exception e){
            e.printStackTrace();
        }

    }



    private void speakNotification(String notificationContent){


            textToSpeech=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int i) {
                    if(i==TextToSpeech.SUCCESS){
                        Log.d("LUVLUV", String.valueOf(i));
                        textToSpeech.setLanguage(Locale.getDefault());
                        textToSpeech.setSpeechRate(1.0f);
                        textToSpeech.setPitch(1.0f);

                        Log.d("CONTENT",notificationContent);

                        textToSpeech.speak(notificationContent,TextToSpeech.QUEUE_FLUSH,null,null);


                    }
                }
            });

//            Log.d("CONTENT",textToSpeech.toString());
//
//        if(textToSpeech != null && textToSpeech.isLanguageAvailable(Locale.getDefault())==TextToSpeech.LANG_AVAILABLE){
//            textToSpeech.setSpeechRate(1.0f);
//            textToSpeech.setPitch(1.0f);
//
//            Log.d("CONTENT",notificationContent);
//
//            textToSpeech.speak(notificationContent,TextToSpeech.QUEUE_FLUSH,null,null);
//        }
//        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                @Override
//                public void onCompletion(MediaPlayer mediaPlayer) {
//                    mp.release();
//                }
//            });

    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        mp.stop();
//        mp.release();
//
//        if (wakeLock != null && wakeLock.isHeld()) {
//            wakeLock.release();
//            wakeLock = null;
//        }


}
