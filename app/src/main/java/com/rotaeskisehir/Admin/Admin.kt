package com.rotaeskisehir.Admin


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import android.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.rotaeskisehir.Model.Users
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.nostra13.universalimageloader.core.ImageLoader
import kotlinx.android.synthetic.main.activity_admin.*


//Tüm kullanıcılar firebase bağlantısında kaydedildi ve buradan çekilip uygulamada aktif edildi.

class AdminActivity : AppCompatActivity() {

    lateinit var mRef : FirebaseDatabaseReference
    var userList:ArrayList<Users> = ArrayList()
    var qUserList:ArrayList<Users> = ArrayList()

    var searchItem:MenuItem? = null
    var searcView:SearchView? = null

//Firebase Users alanından veriler çekiliyor.
     mRef = FirebaseDatabase.getInstance().reference
        initImageLoader()
        mRef.child("users").addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                println(p0.message)
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()){
                    for (user in p0.children){
                        userList.add(user.getValue(Users::class.java)!!)
                    }
            }

        })


    }

    //Kullanıcı çıkışı için menü çıkış sekmesi eklendi.
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.admin_menu,menu)
        searchItem = menu!!.findItem(R.id.action_search)
        searcView = menu.findItem(R.id.action_search).actionView as SearchView

        searcView!!.setOnQueryTextListener(object:SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query:String):Boolean {
                // Toast like print
                return false
            }
            override fun onQueryTextChange(s:String):Boolean {

                // println(s)
                return false
            }
        })
    }

    // Menü çıkış butonuna tıklandığında aktif olup user çıkış yapacaktır.
    override fun onOptionsItemSelected(item: MenuItem)= when (item.itemId) {

        R.id.cikis -> {
            FirebaseAuth.getInstance().signOut()

            AuthUI.getInstance().signOut(this)
                    .addOnCompleteListener {
                        startActivity(Intent(this, FirstActivity::class.java))
                        finish()
                    }
            true
        }
    }
}
