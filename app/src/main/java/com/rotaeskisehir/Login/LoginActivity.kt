package com.rotaeskisehir.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.rotaeskisehir.Home.MainActivity
import com.rotaeskisehir.Home
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_login.*



//Kayıt olan bir kullanıcının giriş yapacağı fonksiyon kodları bulunmaktadır.
btn_Giris.setOnClickListener {

    if (et_Mail.text.isNotEmpty() && et_Sifre.text.isNotEmpty()){
        progressBarGoster()

        // Firebase kütüphanesinden e-mail ve password parametrelerine tanımladığımız edit textleri Stringe çeviriyoruz.
        FirebaseAuth.getInstance().signInWithEmailAndPassword(et_Mail.text.toString(),et_Sifre.text.toString())
                .addOnCompleteListener(object:OnCompleteListener<AuthResult>
                {
                    //Kullanıcı başarılı giriş yaptığında bildirim oluşturuyoruz.
                    override fun onComplete(p0: Task<AuthResult>) {
                        if(p0.isSuccessful){
                            progressBarGizle()
                            //  Toast.makeText(this@LoginActivity,"Başarılı Giriş: "+FirebaseAuth.getInstance().currentUser?.email, Toast.LENGTH_SHORT).show()
                            FirebaseAuth.getInstance().signOut()
                        }
                        else{
                            // Kullanıcı hatalı giriş yaptığında bildirim oluşturuyoruz.
                            progressBarGizle()
                            Toast.makeText(this@LoginActivity,"Hatalı Giriş: "+p0.exception?.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                })
    }
    else{
        // Kullanıcı alanı boş bıraktığında bildirim oluşturuyoruz.
        Toast.makeText(this@LoginActivity,"Boş alanları doldurunuz", Toast.LENGTH_SHORT).show()
    }
}

// Eklediğimiz btngiris fonksiyonunun kontrolünü sağlıyoruz.
private fun initAuthStateListener(){
    mAuthStateListener=object : FirebaseAuth.AuthStateListener{
        override fun onAuthStateChanged(p0: FirebaseAuth) {
            var kullanici=p0.currentUser
            if (kullanici != null){
                if(kullanici.isEmailVerified){
                    Toast.makeText(this@LoginActivity,"Onaylandı giriş yapabilirsiniz", Toast.LENGTH_SHORT).show()
                    var intent=Intent(this@LoginActivity,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this@LoginActivity,"E-mail adresinizi onaylamadan giriş yapamazsınız", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

    //E-mail ile giriş kontrolünün yapılması.
    fun oturumAc(okunanKullanici:String,sifre:String){
        var girisYapcakEmail = ""
            girisYapcakEmail = okunanKullanici
    }
}
