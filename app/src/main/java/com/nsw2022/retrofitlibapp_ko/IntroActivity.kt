package com.nsw2022.retrofitlibapp_ko

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.nsw2022.retrofitlibapp_ko.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {

    val binding:ActivityIntroBinding by lazy { ActivityIntroBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btn.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                val intent= Intent(this@IntroActivity,MainActivity::class.java)
                startActivity(intent)
            }

        })




    }
}