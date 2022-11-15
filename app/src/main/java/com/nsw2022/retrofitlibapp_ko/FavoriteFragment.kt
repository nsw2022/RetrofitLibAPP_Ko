package com.nsw2022.retrofitlibapp_ko

import android.os.Binder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nsw2022.retrofitlibapp_ko.databinding.FragmentFavBinding
import com.nsw2022.retrofitlibapp_ko.databinding.FragmentHomeBinding

class FavoriteFragment:Fragment() {
    lateinit var binding: FragmentFavBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentFavBinding.inflate(inflater,container,false)

        return inflater.inflate(R.layout.fragment_fav,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}