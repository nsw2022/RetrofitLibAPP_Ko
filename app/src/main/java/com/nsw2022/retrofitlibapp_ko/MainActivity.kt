package com.nsw2022.retrofitlibapp_ko


import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.kakao.util.maps.helper.Utility
import com.nsw2022.retrofitlibapp_ko.databinding.ActivityMainBinding
import kotlin.reflect.KProperty

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var fragments : Array<Fragment?> = arrayOf(HomeFragment(),null,null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val db: SQLiteDatabase = FavDB(this).writableDatabase


        supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragments[0]!!)
            .commit()

        binding.bnv.setOnItemSelectedListener(object : NavigationBarView.OnItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                var trans =supportFragmentManager.beginTransaction()
                if (fragments[0]!=null) trans.hide(fragments[0]!!)
                if (fragments[1]!=null) trans.hide(fragments[1]!!)
                if (fragments[2]!=null) trans.hide(fragments[2]!!)

                when (item.itemId) {
                    R.id.menu_bnv_home -> {
                        trans.show(fragments[0]!!)
                    }
                    R.id.menu_bnv_favorite -> {
                        if (fragments[1] == null){
                            fragments[1]=FavoriteFragment()
                            trans.add(R.id.fragment_container,fragments[1]!!)
                        }
                        trans.show(fragments[1]!!)
                    }
                    R.id.menu_bnv_location -> {
                        if (fragments[2] == null){
                            fragments[2]=LocationFragment()
                            trans.add(R.id.fragment_container,fragments[2]!!)
                        }
                        trans.show(fragments[2]!!)
                    }
                }
                trans.commit()
                return true
            }
        })
    }////////create



}



/*
supportFragmentManager.beginTransaction().add(R.id.fragment_container,HomeFragment()).commit()

binding.bnv.setOnItemSelectedListener { item ->
    when (item.itemId) {
        R.id.menu_bnv_home -> {
            var homeFragment = HomeFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, homeFragment).commit()
        }
        R.id.menu_bnv_favorite -> {
            var favoriteFragment = FavoriteFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, favoriteFragment).commit()
        }
        R.id.menu_bnv_location -> {
            var locationFragment = LocationFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, locationFragment).commit()
        }
    }
    true
}

 */





