package com.rotaeskisehir.Home

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rotaeskisehir.Admin
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.nostra13.universalimageloader.core.ImageLoader
import kotlinx.android.synthetic.main.activity_main.*
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener



//Çok sayfalı yapısı gereği UI oluşturmak için ve birden çok parçayı tek bir activity'de bu sayfada birleştirdim.
public class MainActivity extends AppCompatActivity

    val value: Any = protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        value
        val currentUserId = FirebaseAuth.getInstance().currentUser!!.uid
       }

public class MainActivity extends ListActivity
    {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        value
        val currentUserId = FirebaseAuth.getInstance().currentUser!!.uid
    }

        //Admin kullanıcı girişi doğrulanmıştır.
        if (FirebaseAuth.getInstance().currentUser!!.email.equals("rotaeskisehir26@gmail.com"))
        {
            startActivity(Intent(this,AdminActivity::class.java))
            finish()
        }
        else{
                            activeLayout.visibility = View.VISIBLE
                            inactiveLayout.visibility = View.GONE
                        }

                    }
                }

            })

        }

        CikisYap.setOnClickListener{
            FirebaseAuth.getInstance().signOut()

            AuthUI.getInstance().signOut(this)
                    .addOnCompleteListener {
                        startActivity(Intent(this, FirstActivity::class.java))
                        finish()
                    }
        }

        setupNavigationView()
        initImageLoader()

    }

    //Fotoğrafların yüklenmesi için ImageLoader kullanılmıştır. LazyLoader ile yükleme hızları da düşürülmüştür.
    private fun initImageLoader(){
        val universalImageLoader = UniversalImageLoader(this)
        ImageLoader.getInstance().init(universalImageLoader.config)
    }

    @Override
      override fun onStart() {
         super.onStart();
         active = true;
      }

      @Override
      override fun onStop() {
         super.onStop();
         active = false;
      }

}
