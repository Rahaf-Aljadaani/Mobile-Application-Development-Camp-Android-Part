package com.example.notificationsapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService

class MainActivity : AppCompatActivity() {

   // lateinit var notificationManager: NotificationManager
 lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
  //  private val notificationId = 1234
    private val channelId = "myapp.notifications"
    private val description = "Notification App Example"
    lateinit var text: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text = findViewById(R.id.etNot)

        }


    fun show(view: View) {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val intent = Intent(this, NotificationActivity::class.java)
        val pendingIntent =
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
            builder = Notification.Builder(this, channelId)
                .setSmallIcon(R.mipmap.s)
                .setContentIntent(pendingIntent)
                .setContentTitle("My Notification")
                .setContentText(text.text.toString())
        } else {
            builder = Notification.Builder(this)
                .setSmallIcon(R.mipmap.s)
                .setContentIntent(pendingIntent)
                .setContentTitle("My Notification")
                .setContentText(("Hello"))
        }
        notificationManager.notify(1234, builder.build())
    }

}

