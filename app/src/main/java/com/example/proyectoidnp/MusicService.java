package com.example.proyectoidnp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.app.PendingIntent;


import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class MusicService extends Service {
    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.carnabalarequipa); // Asegúrate de tener el archivo en res/raw
        mediaPlayer.setLooping(false); // No repetir
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent.getAction();
        if ("PLAY".equals(action)) {
            playMusic();
        } else if ("PAUSE".equals(action)) {
            pauseMusic();
        }
        return START_STICKY;
    }

    private void playMusic() {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            showNotification("Reproduciendo música", true);
        }
    }

    private void pauseMusic() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            showNotification("Música pausada", false);
        }
    }

    private void showNotification(String text, boolean isPlaying) {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "music_channel",
                    "Música",
                    NotificationManager.IMPORTANCE_LOW
            );
            manager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "music_channel")
                .setContentTitle("Carnaval Arequipa")
                .setContentText(text)
                .setSmallIcon(R.drawable.ic_music_note) // Asegúrate de tener este ícono en res/drawable
                .setPriority(NotificationCompat.PRIORITY_LOW);

        if (isPlaying) {
            builder.addAction(R.drawable.ic_pause, "Pausar", getActionIntent("PAUSE"));
        } else {
            builder.addAction(R.drawable.ic_play_arrow, "Reproducir", getActionIntent("PLAY"));
        }

        startForeground(1, builder.build());
    }

    private PendingIntent getActionIntent(String action) {
        Intent intent = new Intent(this, MusicService.class);
        intent.setAction(action);
        return PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
