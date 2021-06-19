package com.rotaeskisehir.Login

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_kayit.*
import kotlinx.android.synthetic.main.fragment_kayit.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe




//E-mail ile giriş yapılacaktır. Firebase yetkilendirmesi yapılmıştır.

class KayitFragment : Fragment() {

    var gelenEmail = ""
    var gelenSifre = ""

    lateinit var mAuth: FirebaseAuth
    lateinit var mRef: DatabaseReference
    var isEdu = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ):
            View? {

        var view = inflater.inflate(R.layout.fragment_kayit, container, false)

        mAuth = FirebaseAuth.getInstance()
        mRef = FirebaseDatabase.getInstance().reference


        //Veri girişide kısıtlamalar ve istenilen formatta veriler için watcher kullanıldı.
        view.etAdSoyad.addTextChangedListener(watcher)
        view.etKullaniciAdi.addTextChangedListener(watcher)

        view.btnGiris.setOnClickListener {

            //Girilen bilgilerin uygun karakter ve kullanım durumu kontrol edilmiştir.
            mRef.child("users").orderByChild("username").equalTo(etKullaniciAdi.text.toString())
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                        println(p0.message)
                    }
                 //Sistemde kayıtlı E-mail adresinin kontrolü gerçekleştiriliyor. Kullanımda ise hata verir.
                    override fun onDataChange(p0: DataSnapshot) {
                        if (p0.exists()){
                            Toast.makeText(activity!!,"Kullanıcı adı kullanımdadır. Lütfen başka bir kullanıcı adı kullanınız.",Toast.LENGTH_SHORT).show()
                        }else{
                            var username = view.etKullaniciAdi.text.toString()
                            var adSoyad = view.etAdSoyad.text.toString()

                            mAuth.createUserWithEmailAndPassword(gelenEmail,gelenSifre)
                                .addOnCompleteListener { p0 ->
                                    if (p0.isSuccessful)
                                    {
                                        //Tüm alanlar doğru eklendiğinde kullanıcı kaydı gerçekleştiriliyor.
                                        var userID = mAuth.currentUser!!.uid
                                        val kaydedilecekKullanıcıBilgisi = Users(gelenEmail,username,adSoyad,"","",userID,"","","",0,1)

                                        mRef.child("users").child(userID).setValue(kaydedilecekKullanıcıBilgisi)
                                            .addOnCompleteListener { p0 ->
                                                if (p0.isSuccessful){
                                                    println("Başarılı")
                                                    Toast.makeText(activity!!,"Kullanıcı kaydınız yapıldı. Yönlendiriliyorsunuz...",Toast.LENGTH_SHORT).show()
                                                }else{
                                                    mAuth.currentUser!!.delete()
                                                        .addOnCompleteListener { p0 ->
                                                            if (p0.isSuccessful){
                                                                Toast.makeText(activity!!,"Kullanıcı bilgileriniz kaydedilemedi, lütfen tekrar deneyiniz.",Toast.LENGTH_SHORT).show()
                                                            }
                                                        }
                                                }
                                            }
                                        Toast.makeText(activity!!,"Oturum Açıldı!",Toast.LENGTH_SHORT).show()
                                    }else{
                                        Toast.makeText(activity!!,"Hata",Toast.LENGTH_SHORT).show()
                                        println(p0)
                                    }
                                }
                        }
                    }
                })
        }
        return view
    }



    var watcher: TextWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (s!!.length > 3){

                if (etAdSoyad.text.toString().length > 3  && etKullaniciAdi.text.toString().length > 3){
                    btnGiris.isEnabled =true
                    btnGiris.setTextColor(ContextCompat.getColor(activity!!,R.color.beyaz))
                    btnGiris.setBackgroundResource(R.drawable.button_active)
                }

            }else{
                btnGiris.isEnabled =false
                btnGiris.setTextColor(ContextCompat.getColor(activity!!,R.color.sonukmavi))
                btnGiris.setBackgroundResource(R.drawable.button_inactive)
                Toast.makeText(activity!!,"Her iki şifre alanı için en az 4 karakter giriniz", Toast.LENGTH_SHORT).show()
            }
        }
    }

    //Registeractivity ile gelen bilgilerin alındığı fonksiyon yapısıdır.

    @Subscribe(sticky = true)
    internal fun onKayitEvent(kayitBilgileri: EventbusDataEvents.KayitBilgileriniGonder){

            gelenSifre = kayitBilgileri.sifre!!
            gelenEmail = kayitBilgileri.email!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        EventBus.getDefault().register(this)
    }

    override fun onDetach() {
        super.onDetach()
        EventBus.getDefault().unregister(this)
    }


}
