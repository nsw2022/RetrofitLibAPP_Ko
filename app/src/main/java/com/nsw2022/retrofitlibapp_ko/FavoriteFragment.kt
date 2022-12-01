package com.nsw2022.retrofitlibapp_ko

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.nsw2022.retrofitlibapp_ko.databinding.FragmentFavBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


class FavoriteFragment : Fragment() {
    lateinit var binding: FragmentFavBinding
    val items: MutableList<ReviewItem> = mutableListOf()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerFav.adapter = FavAdapter(requireContext(), items)

        binding.swipeRefreshLayout.setOnRefreshListener {

            loadData()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    fun loadData() {
//        items.add(FavClassItems("노승우","","도서관이름","가나다라마바사아자차카타파하","2022-10-10"))
//        items.add(FavClassItems("노승우","https://k.kakaocdn.net/dn/dpmv3K/btrMjCMZK7j/NNNiZiiGENm1IcweFJzwFk/img_640x640.jpg","도서관이름","가나다라마바사아자차카타파하","2022-10-10"))
        //Toast.makeText(requireContext(), "lodData반응", Toast.LENGTH_SHORT).show()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://tmddn34101.dothome.co.kr/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitService = retrofit.create(ReviewRetrofit::class.java)

        retrofitService.loadDataFromServer()
            .enqueue(object : Callback<MutableList<ReviewItem>> {
                override fun onResponse(
                    call: Call<MutableList<ReviewItem>>,
                    response: Response<MutableList<ReviewItem>>
                ) {
                    Toast.makeText(requireContext(), "레트로핏 시작", Toast.LENGTH_SHORT).show()
                    items.clear()
                    binding.recyclerFav.adapter?.notifyDataSetChanged()

                    var list: MutableList<ReviewItem>? = response.body()
                    list?.forEach {
                        items.add(0, it)
                        binding.recyclerFav.adapter?.notifyItemInserted(0)

                        Toast.makeText(requireContext(), "레트로핏 성공시", Toast.LENGTH_SHORT).show()
                    }
                    Log.d("SS", "$list")

                }

                override fun onFailure(call: Call<MutableList<ReviewItem>>, t: Throwable) {
                    Log.d("SS", "${t.message}")
                    Toast.makeText(requireContext(), "레트로핏 실패시", Toast.LENGTH_SHORT).show()
                }

            })
    }


}