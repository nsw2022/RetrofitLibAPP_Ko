package com.nsw2022.retrofitlibapp_ko


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {

    val bnv:BottomNavigationView by lazy { findViewById(R.id.bnv) }

    lateinit var fragmentManager:FragmentManager
    var fragments:MutableList<Fragment?> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //처음에 보여질 Fragment 를 1개 만들어서 붙이기
        fragments.add(HomeFragment())
        fragments.add(null)
        fragments.add(null)

        //프래그먼트를 동적제어 하기 위해 관리가에게 요청
        fragmentManager=supportFragmentManager

        // id가 fragment_container 인 ViewGroup에 HomeFragemnet를 붙이기
        fragmentManager.beginTransaction().add(R.id.fragment_container, fragments[0]!!).commit()

        bnv.setOnItemSelectedListener(object :NavigationBarView.OnItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                val tran:FragmentTransaction = fragmentManager.beginTransaction()

                if (fragments[0] != null )tran.hide(fragments[0]!!)
                if (fragments[1] != null )tran.hide(fragments[1]!!)
                if (fragments[2] != null )tran.hide(fragments[2]!!)
                when(item.itemId){
                    R.id.menu_bnv_home->tran.show(fragments[0]!!)

                    R.id.menu_bnv_favorite-> {
                        if (fragments[1] == null) {
                            fragments.set(1, FavoriteFragment())
                            tran.add(R.id.fragment_container, fragments[1]!!)
                        }
                        tran.show(fragments[1]!!)
                    }

                    R.id.menu_bnv_location->{
                        if (fragments[2]==null){
                            fragments.set(2,LocationFragment())
                            tran.add(R.id.fragment_container,fragments[2]!!)
                        }
                        tran.show(fragments[2]!!)
                    }

                }//when
                tran.commit()//작업완료
                return true
            }
        })
    }
}


