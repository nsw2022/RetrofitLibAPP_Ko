package com.nsw2022.retrofitlibapp_ko


import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.nsw2022.retrofitlibapp_ko.databinding.ActivityMainBinding
import kotlin.reflect.KProperty

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding

    var homeFragment:HomeFragment?=null
    var favFragment:FavoriteFragment?=null
    var locationFragment:LocationFragment?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBottomNavigation()

    }

    fun initBottomNavigation(){
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
    }
}



