package com.rotaeskisehir.Profil

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sifre_degistir.*

//Şifre değişikliği için eski şifre istemi yapılır, doğruysa yeni şifre istenerek değişiklik kaydedilir.
class SifreDegistir : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sifre_degistir)

        imgKapat.setOnClickListener {
            onBackPressed()
        }

        imgSifreDegistir.setOnClickListener {

            var mevcutSifre=etMevcutSifre!!.text!!.toString()
            var yeniSifre=etYeniSifre!!.text!!.toString()
            var yeniSifreTekrar=eetYeniSifreTekrar!!.text!!.toString()

            if(!mevcutSifre.isNullOrEmpty() && mevcutSifre.length>=6){

                var myUser= FirebaseAuth.getInstance().currentUser
                if(myUser != null){
                    var credential= EmailAuthProvider.getCredential(myUser!!.email.toString(),mevcutSifre)
                    myUser.reauthenticate(credential).addOnCompleteListener(object : OnCompleteListener<Void> {
                        override fun onComplete(p0: Task<Void>) {
                            if(p0!!.isSuccessful){

                                if(yeniSifre.equals(yeniSifreTekrar)){

                                    if(!yeniSifre.isNullOrEmpty() && yeniSifre.length>=6){

                                        var myUser=FirebaseAuth.getInstance().currentUser
                                        myUser!!.updatePassword(yeniSifre).addOnCompleteListener(object : OnCompleteListener<Void>{
                                            override fun onComplete(p0: Task<Void>) {
                                                if(p0!!.isSuccessful){
                                                    Toast.makeText(this@SifreDegistir,"Şifreniz güncellendi.",
                                                        Toast.LENGTH_SHORT).show()
                                                }else {
                                                    Toast.makeText(this@SifreDegistir,"Şifre güncellenemedi.",
                                                        Toast.LENGTH_SHORT).show()
                                                }
                                            }

                                        })


                                    }else {
                                        Toast.makeText(this@SifreDegistir,"Yeni şifre en az 4 karakter olmalıdır.",
                                            Toast.LENGTH_SHORT).show()
                                    }

                                }else {
                                    Toast.makeText(this@SifreDegistir,"Şifreniz eşleşmiyor.", Toast.LENGTH_SHORT).show()
                                }



                            }else {
                                Toast.makeText(this@SifreDegistir,"Mevcut şifreniz yanlış.", Toast.LENGTH_SHORT).show()
                            }
                        }

                    })
                }


            }else {
                Toast.makeText(this,"Mevcut şifre en az 4 karakter olmalıdır.", Toast.LENGTH_SHORT).show()
            }


        }

    }
}
