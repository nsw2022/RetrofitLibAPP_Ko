package com.nsw2022.retrofitlibapp_ko

import android.content.Context
import android.os.Bundle
import android.util.Log
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
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


class HomeFragment: Fragment() {

    lateinit var binding:FragmentHomeBinding





    // 뷰가 만들어질때
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentHomeBinding.inflate(inflater,container,false)


/*
        더미테스트
        items.add( RecyclerItem("도서관","강남구","서울시 강남구","2022-01-02","02-1234-5678") )
        items.add( RecyclerItem("홍길동","동대문구","서울시 강남구","2022-01-02","02-1234-5678") )
        items.add( RecyclerItem("글자수가몇개까지일까요제한을뒀답니다~","강서구","서울시 강남구","2022-01-02","02-1234-5678") )
        items.add( RecyclerItem("짜파게티","강남구","서울시 강남구","2022-01-02","02-1234-5678") )

       더미테스트
       val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_home)
       val adapter = RecyclerAdapter(requireContext(),items)
       recyclerView.adapter=adapter
*/



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        var gu : String = "강남구"
        var gu_arrays:Array<String> = resources.getStringArray(R.array.city)

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                gu=gu_arrays[p2]
                //Toast.makeText(requireContext(), ""+gu, Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }

        binding.btnSerach.setOnClickListener {
            val retrofit:Retrofit=Retrofit.Builder()
                .baseUrl(SeoulOpenApi.DOMAIN)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            // 레트로핏 서비스로부터 원하는 설계(인터페이스) 하고나서 객체 생성
            val retrofitService = retrofit.create(LibApiRetrofitService::class.java)

            retrofitService.getAPILibDatas(SeoulOpenApi.KEY)
                .enqueue(object : Callback<LibApiItem>{
                    override fun onResponse(call: Call<LibApiItem>, response: Response<LibApiItem>) {
                        val apiReonse:LibApiItem?=response.body()
                        //var adapter=RecyclerAdapter(requireContext(),apiReonse!!.SeoulLibraryTimeInfo.row)
                        //binding.recyclerHome.adapter=adapter

                        var items:MutableList<Row> = mutableListOf() //빈 리스트.. 리사이클러뷰가 보여줄 데이터들..

                        apiReonse?.SeoulLibraryTimeInfo?.row?.forEach {
                            Log.i("TAG", it.CODE_VALUE +" , " + gu)
                            if(it.CODE_VALUE == gu) items.add(it)
                        }

                        //Toast.makeText(requireContext(), "$gu", Toast.LENGTH_SHORT).show()
                        Toast.makeText(requireContext(), "${items.size}",
                            Toast.LENGTH_SHORT).show()

                        binding.recyclerHome.adapter= RecyclerAdapter(requireContext(), items)

                    }

                    override fun onFailure(call: Call<LibApiItem>, t: Throwable) {
                        Toast.makeText(requireContext(), "error:${t.message}", Toast.LENGTH_SHORT).show()
                    }

                })
        }



    }
}




