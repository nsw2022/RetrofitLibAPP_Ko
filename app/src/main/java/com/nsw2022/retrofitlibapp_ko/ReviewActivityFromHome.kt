package com.nsw2022.retrofitlibapp_ko

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nsw2022.retrofitlibapp_ko.databinding.ActivityReviewFromHomeBinding

class ReviewActivityFromHome : AppCompatActivity() {
    lateinit var binding: ActivityReviewFromHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityReviewFromHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent

        binding.tvNameLibReview.text=intent.getStringExtra("title")
        binding.tvGuLibReview.text=intent.getStringExtra("gu")
        binding.tvHolidayReview.text=intent.getStringExtra("holiday")
        binding.tvTelLibReview.text=intent.getStringExtra("tel")

        binding.btnBackPress.setOnClickListener { onBackPressed() }

        binding.btnSave.setOnClickListener { onBackPressed() }



    }//OnCrate
}