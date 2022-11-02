package com.nsw2022.retrofitlibapp_ko

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HomeFragment: Fragment() {

    lateinit var btn:Button
    lateinit var recyclerView:RecyclerView


    lateinit var home_spinner:Spinner
    lateinit var gu_arrays:Array<String>
    var gu:String? = null
    var key:String="4d6e42445a746d6437354e65737162"

    //화면을 만들기 전에 보여줄 데이터를 읽어오는 등의 작업을 수행하는 메소드
   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    // 뷰가 만들어질때
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inflater.inflate(R.layout.fragment_home,container,false)
        return inflater.inflate(R.layout.fragment_home,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        btn=view.findViewById(R.id.btn_serach)
    }//onViewCreated




}




