package com.nsw2022.retrofitlibapp_ko

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.nsw2022.retrofitlibapp_ko.databinding.FragmentFavBinding

class FavoriteFragment:Fragment() {
    lateinit var binding: FragmentFavBinding
    val items:MutableList<FavClassItems> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentFavBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
        }
        binding.recyclerFav.adapter = FavAdapter(requireContext(), items )

        binding.swipeRefreshLayout.setOnRefreshListener {
            items.clear()
             loadData()
            binding.swipeRefreshLayout.isRefreshing = false
        }

    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    fun loadData(){
//        items.add(FavClassItems("노승우","","도서관이름","가나다라마바사아자차카타파하","2022-10-10"))
//        items.add(FavClassItems("노승우","https://k.kakaocdn.net/dn/dpmv3K/btrMjCMZK7j/NNNiZiiGENm1IcweFJzwFk/img_640x640.jpg","도서관이름","가나다라마바사아자차카타파하","2022-10-10"))

    }



}