package com.nsw2022.retrofitlibapp_ko

import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.loader.content.CursorLoader
import com.bumptech.glide.Glide
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk.keyHash
import com.kakao.sdk.user.UserApiClient
import com.kakao.util.maps.helper.Utility
import com.nsw2022.retrofitlibapp_ko.G.Companion.userName
import com.nsw2022.retrofitlibapp_ko.databinding.ActivityIntroBinding
import retrofit2.http.Url
import java.net.URL

class IntroActivity : AppCompatActivity() {

    val binding: ActivityIntroBinding by lazy { ActivityIntroBinding.inflate(layoutInflater) }
    var count:Int=0

    var isFirst = true
    var isChanged = false

    lateinit var profileImage:String
    lateinit var userName:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val keyHashLogin = com.kakao.sdk.common.util.Utility.getKeyHash(this)
        Log.i("Login", "  $keyHashLogin")
        // F1DGuO2pyL2bd9F1oX78E7bsplU=

        val keyHashMap = Utility.getKeyHash(this)
        Log.i("MAP", "   $keyHashMap")
        // F1DGuO2pyL2bd9F1oX78E7bsplU=

        val permissionResult = TedPermission.create().setPermissions(
            android.Manifest.permission.INTERNET,
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        ).setPermissionListener(object : PermissionListener{
            override fun onPermissionGranted() {
                checkUserGPS()
                binding.btnLogin.setOnClickListener { clickLogin() }
                binding.btn.setOnClickListener { clickNext() }
            }

            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                Toast.makeText(this@IntroActivity, "권한 거절", Toast.LENGTH_SHORT).show()
            }


        })
            .setDeniedMessage("[설정] -> [권한]에서 권한 변경이 가능합니다.")
            .setDeniedCloseButtonText("닫기")
            .setGotoSettingButtonText("설정")
            .check()

        loadData()

    }//onCreate

    fun clickNext(){
        when (count) {
            0 -> {
                Toast.makeText(this@IntroActivity, "카카오톡에 로그인해주세요", Toast.LENGTH_SHORT).show()
            }

            1 -> {
                val intent = Intent(this@IntroActivity, MainActivity::class.java)
                saveData()
                startActivity(intent)
                count = 0
            }

        }
    }

    lateinit var imgPath:String
    fun saveData(){
        userName=binding.tvUserId.text.toString()
        profileImage=profileImage

        var pref:SharedPreferences = getSharedPreferences("account", MODE_PRIVATE)
        var editor:SharedPreferences.Editor = pref.edit()

        editor.putString("porfile",profileImage)
        editor.putString("username",userName)

        editor.commit()


    }

    fun loadData(){
        var pref:SharedPreferences=getSharedPreferences("account", MODE_PRIVATE)
        profileImage= pref.getString("porfile","")!!
        userName = pref.getString("username","")!!

        Glide.with(this).load(profileImage).error(R.drawable.profle).into(binding.civ)
        binding.tvUserId.text=userName
        count=1
    }

    fun clickLogin() {
        // 권장 : 카카오톡 로그인을 먼저 시도하고 불가능할 경우 카카오계정 로그인을 시도.

        // 로그인 요청결과에 반응하는 콜백함수
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (token != null) {
                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                loadUserInfo() //사용자 정보 읽어오기
                count=1
            }
        }
        if (UserApiClient.instance
                .isKakaoTalkLoginAvailable(this)
        ) {
            //카카오톡 로그인 요청
            UserApiClient.instance.loginWithKakaoTalk(this, callback = callback)
        } else {
            //카카오계정 로그인 요청
            UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
        }

    }


    // 로그인 성공했을때 사용자 정보 얻어오는 기능
    fun loadUserInfo() {
        UserApiClient.instance.me { user, error ->
            if (user != null) {
                binding.tvUserId.text = user.kakaoAccount?.profile?.nickname
                profileImage=user.kakaoAccount?.profile?.profileImageUrl!!
                Glide.with(this).load(user.kakaoAccount?.profile?.profileImageUrl).into(binding.civ)
            }
        }
    }


    fun checkUserGPS() {
        // 사용자의 GPS가 꺼져있는 경우
        val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(this, "GPS가 꺼져있으십니다 설정 화면으로 이동합니다", Toast.LENGTH_SHORT).show()
            startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
        }
    }

    fun getRealPathFromUri(uri: Uri?): String? {
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val loader = CursorLoader(
            this,
            uri!!, proj, null, null, null
        )
        val cursor = loader.loadInBackground()
        val column_index = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        val result = cursor.getString(column_index)
        cursor.close()
        return result
    }
}