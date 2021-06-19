package com.rotaeskisehir.Offer

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import com.rotaeskisehir.Home.MainActivity

//Bu alanda günlük gezilecek mekan önerisi yapılmaktadır. Alanlar otomatik olarak çekim yapacaktır.

class MyReceiver : BroadcastReceiver() {
    private val channelId = "com.rotaeskisehir"

    override fun onReceive(context: Context, intent: Intent) {
        Log.i("TITLE","/////////////////// BAŞLIK ALANIDIR //////////////////////")
        val builder = NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setLargeIcon(BitmapFactory.decodeResource(context.resources,R.mipmap.ic_launcher_round))
                .setContentTitle("Mekan Başlığı")
                .setContentText("Mekan Açıklama Alanı")
                .setStyle(
                        NotificationCompat.BigTextStyle()
                                .bigText("Detaylar bu alanda görünecektir."))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        with(NotificationManagerCompat.from(context)) {
        Toast.makeText(context,"", Toast.LENGTH_LONG).show()
    }

        // Bu alanda veri çekimi yapıldığında yerleştirilecek alanlar belirlenmiştir.
        @Serializable
        data class HttpCatModel(
        @SerialName(value: "title")
        val title: String = "",
        @SerialName(value: "imageUrl")
        val image: String = "",
        @SerialName(value: "description")
        val description: String = "",
        )
}