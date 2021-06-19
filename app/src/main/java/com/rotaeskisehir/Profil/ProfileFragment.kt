package com.rotaeskisehir.Profil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.rotaeskisehir.Model
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
package com.androidhive.sessions;
import java.util.HashMap;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class SessionManager {
    // Gizlilik sekmesi ile herkese açık veya kapalı alanlar düzenlenmiştir.
    SharedPreferences pref;

    // Sadece mekan ekleyenlere gösterim yapar.
    Editor editor;

    Context _context;

    // Yorum ve Puanlamalar sadece kullanıcı tarafından görüntülenir. Gizli moddur.
    int PRIVATE_MODE = 0;

    // Paylaşılan bilgiler
    private static final String PREF_NAME = "AndroidHivePref";

    private static final String IS_LOGIN = "IsLoggedIn";

    // Kullanıcı adı
    public static final String KEY_NAME = "name";

    // Email address
    public static final String KEY_EMAIL = "email";

    // Çıkış yap alanı eylem dosya çekimidir.
    private static final String IS_LOGIN = "IsLoggedIn";

    // Sayfa yapısını oluşturmak için yeniden bir Constructor yapıcı kullanıldı.
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }