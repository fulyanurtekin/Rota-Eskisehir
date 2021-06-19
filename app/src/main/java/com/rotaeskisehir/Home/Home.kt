package com.rotaeskisehir.Home

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.rotaeskisehir.Mekan
import com.rotaeskisehir.Add
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.greenrobot.eventbus.EventBus


//Rota sayfasına girişte tüm mekanlar bu sayfada görünecektir.

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        mRef = FirebaseDatabase.getInstance().reference

        //Verileri listelemek için recyclerView kullanıldı. RecyclerView yalnızca ekranda görüntülenmekte olan item’leri işlemek veya çizmek için çalışır.
        getMekan()
       val gl = Globals.letsGo
        gl.homeFragment = this

        return view
    }

        //Mekan bilgilerinde güncelleme olduğunda yeniden yükleme (reload) işlemi yapılır.
    fun reload(){
        all.clear()
        getMekan()
    }

 // EventBus kütüphanesi çekilerek bileşenler arasındaki iletişim daha az kod ile çok daha basit hale getirilmiştir.
    override fun onAttach(context: Context) {
         super.onAttach(context)
         EventBus.getDefault().register(this)
     }

     override fun onDetach() {
         super.onDetach()
         EventBus.getDefault().unregister(this)
     }


}
