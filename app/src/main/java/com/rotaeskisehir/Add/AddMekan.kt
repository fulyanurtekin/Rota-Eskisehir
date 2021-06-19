package com.rotaeskisehir.Add

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.PopupMenu
import android.widget.Toast
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.text.SimpleDateFormat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*


class AddFragment : Fragment() {


    //haritadan seçilecek olan mekan ve mekanın lokasyon bilgilerine ulaşılması için bu map layout alanlarını ekledim.
    <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:map="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <LinearLayout
    android:orientation="horizontal"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:id="@+id/lineer"
    android:layout_alignParentTop="true"
    android:layout_alignParentLeft="true">

    <EditText
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_weight="1"
    android:id="@+id/et_location" />

    <Button
    android:text="Go"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:id="@+id/btn_Go"
    />
    </LinearLayout>

    <Button
    android:text="Uydu"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_alignParentBottom="true"
    android:layout_alignParentStart="true"
    android:id="@+id/btn_Sat" />

    <fragment
    android:id="@+id/map"
    android:name="com.google.android.gms.maps.SupportMapFragment"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.example.hasibezafer.googlemapsapp.MapsActivity"
    android:layout_below="@+id/lineer"/>

    <ZoomControls
    android:layout_alignParentEnd="true"
    android:layout_alignParentBottom="true"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:id="@+id/zoom"></ZoomControls>

    </RelativeLayout>


    //harita üzerindeki mekanlar eklendi.
    markerLocationList.add(new MarkerOptions()
    .position(new LatLng(39.7648235,30.5202948))
    .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin)).clusterable(true));

    markerLocationList.add(new MarkerOptions()
    .position(new LatLng(39.7649595,30.522696))
    .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin2)).clusterable(true));

    markerLocationList.add(new MarkerOptions()
    .position(new LatLng(39.7650957,30.5209901))
    .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin3)).clusterable(true));

    markerLocationList.add(new MarkerOptions()
    .position(new LatLng(39.7642274,30.5221833)
    .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin2)).clusterable(true));

    markerLocationList.add(new MarkerOptions()
    .position(new LatLng(39.7640331,30.5215262))
    .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin)).clusterable(true));

    markerLocationList.add(new MarkerOptions()
    .position(new LatLng(39.7826023,30.5082429))
    .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin)).clusterable(true));

    markerLocationList.add(new MarkerOptions()
    .position(new LatLng(39.7814645,30.5108822))
    .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin)).clusterable(true));

    markerLocationList.add(new MarkerOptions()
    .position(new LatLng(39.7773996,30.5160964))
    .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin)).clusterable(true));

    markerLocationList.add(new MarkerOptions()
    .position(new LatLng(39.7791628,30.5100156))
    .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin)).clusterable(true));

    markerLocationList.add(new MarkerOptions()
    .position(new LatLng(39.7584032,30.4694937))
    .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin)).clusterable(true));

    markerLocationList.add(new MarkerOptions()
    .position(new LatLng(39.7752064,30.5484201))
    .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin)).clusterable(true));

    markerLocationList.add(new MarkerOptions()
    .position(new LatLng(39.7565924,30.5312393))
    .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin)).clusterable(true));

    markerLocationList.add(new MarkerOptions()
    .position(new LatLng(39.7739261,30.5181529))
    .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin)).clusterable(true));

    markerLocationList.add(new MarkerOptions()
    .position(new LatLng(39.7739261,30.5181529))
    .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin)).clusterable(true));

    markerLocationList.add(new MarkerOptions()
    .position(new LatLng(39.7892967,30.4890469))
    .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin)).clusterable(true));

    markerLocationList.add(new MarkerOptions()
    .position(new LatLng(39.7854048,30.5000224))
    .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin)).clusterable(true));
    }
    }
}
