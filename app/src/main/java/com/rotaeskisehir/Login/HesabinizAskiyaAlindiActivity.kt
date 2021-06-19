package com.rotaeskisehir.Login


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_hesabiniz_askiya_alindi.*


//Bir kullanıcının hesabı askıya alınırsa eğer hata sayfasını görür.
class HesabinizAskiyaAlindiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_askiya_alindi)

        tvAski.text = "Hesabınız askıya alınmıştır.\n" +
                "Yanlışlıkla askıya alındığını düşünüyorsanız, rotaeskisehir26@gmail mail adresi ile iletişime geçebilirsiniz."


        CikisYap.setOnClickListener{
            FirebaseAuth.getInstance().signOut()

//Çıkış yapıldığı için giriş sayfasına yönlenecek olup, loginactivity'e ya da registeractivity'e geçiş yaparak kayıt veya giriş yapacaktır.
            AuthUI.getInstance().signOut(this)
                    .addOnCompleteListener {
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    }
        }

    }
}
