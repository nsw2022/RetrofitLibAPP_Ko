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
import com.nsw2022.retrofitlibapp_ko.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HomeFragment: Fragment() {

    lateinit var btn:Button
    lateinit var recyclerView:RecyclerView
    var items:MutableList<RecyclerItem> = mutableListOf()
    lateinit var adapter: RecyclerAdapter


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
//        inflater.inflate(R.layout.fragment_home,container,false)
        val view = inflater.inflate(R.layout.fragment_home,container,false)


        items.add( RecyclerItem("도서관","강남구","서울시 강남구","2022-01-02","02-1234-5678") )
        items.add( RecyclerItem("홍길동","동대문구","서울시 강남구","2022-01-02","02-1234-5678") )
        items.add( RecyclerItem("글자수가몇개까지일까요제한을뒀답니다~","강서구","서울시 강남구","2022-01-02","02-1234-5678") )
        items.add( RecyclerItem("짜파게티","강남구","서울시 강남구","2022-01-02","02-1234-5678") )
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_home)
        val adapter = RecyclerAdapter(requireContext(),items)
        recyclerView.adapter=adapter

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn=view.findViewById(R.id.btn_serach)
        recyclerView=view.findViewById(R.id.recycler_home)
        adapter=RecyclerAdapter(requireContext(),items)



    }//onViewCreated




}




