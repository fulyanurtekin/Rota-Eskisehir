package com.rotaeskisehir.Login


import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import com.rotaeskisehir.Home.MainActivity
import com.rotaeskisehir.Home
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_register.*
import org.greenrobot.eventbus.EventBus


//Kullanıcı kayıt sayfasıdır.
btn_Kayit.setOnClickListener
    {
    if(et_Mail.text.isNotEmpty() && et_Sifre.text.isNotEmpty() && et_Sifre_Tekrar.text.isNotEmpty()){
        if (et_Sifre.text.toString().equals(et_Sifre_Tekrar.text.toString())){
            yeniUyeKayit(et_Mail.text.toString(), et_Sifre.text.toString())
        }else{
            Toast.makeText(this,"Şifreler aynı değil", Toast.LENGTH_SHORT).show()
        }
    }else{
        Toast.makeText(this,"Boş alanları doldurunuz", Toast.LENGTH_SHORT).show()
    }
}

//Kayıt ekranında yerleştirdiğimiz kayıt butonunun içerisine eğer tanımlanan şifreler aynı değilse ekranda bir bildirim gösteriyoruz ve
// alanlar boş bırakıldıysa yine aynı şekilde ekranda bildirim gösteriyoruz.
private fun yeniUyeKayit(mail: String, sifre: String) {
    progressBarGoster()
    FirebaseAuth.getInstance().createUserWithEmailAndPassword(mail,userid,sifre)
            .addOnCompleteListener(object:OnCompleteListener<AuthResult>{
                override fun onComplete(p0: Task<AuthResult>) {
                    if(p0.isSuccessful){
                        Toast.makeText(this@RegisterActivity,"Başarıyla kaydoldunuz:"+FirebaseAuth.getInstance().currentUser?.email, Toast.LENGTH_SHORT).show()
                        mailGonder()
                        FirebaseAuth.getInstance().signOut()
                    }else{
                        Toast.makeText(this@RegisterActivity,"Kaydınız oluşturulurken sorun oluştu:"+p0.exception?.message, Toast.LENGTH_SHORT).show()
                    }
                }
            })
    progressBarGizle()
}

// Yeni bir kullanıcının kayıt yapabilmesi için kontrolünü sağlayabiliriz. Ancak bu modülü null özelliği ile pasif hale getirdik. Otomatik giriş yapıyorlar.
private fun mailGonder(){
    var kullanici=FirebaseAuth.getInstance().currentUser
    if (kullanici != null){
        kullanici.sendEmailVerification()
                .addOnCompleteListener(object : OnCompleteListener<Void>{
                    override fun onComplete(p0: Task<Void>) {
                        if(p0.isSuccessful){
                            Toast.makeText(this@RegisterActivity,"Mailinizi kontrol edin, mailinizi onaylayın", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(this@RegisterActivity,"Mail gönderilirken sorun oluştu "+p0.exception?.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                })
    }
}

