package com.nsw2022.retrofitlibapp_ko

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.nsw2022.retrofitlibapp_ko.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {

    val binding:ActivityIntroBinding by lazy { ActivityIntroBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        checkUserGPS()

        binding.btn.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                val intent= Intent(this@IntroActivity,MainActivity::class.java)
                startActivity(intent)
            }

        })




    }//onCreate

    fun checkUserGPS(){
        // 사용자의 GPS가 꺼져있는 경우
        val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            Toast.makeText(this, "GPS가 꺼져있으십니다 설정 화면으로 이동합니다", Toast.LENGTH_SHORT).show()
            startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
        }
    }
}