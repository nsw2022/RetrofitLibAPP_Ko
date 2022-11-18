package com.nsw2022.retrofitlibapp_ko

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class MyApplication : Application(){

    override fun onCreate() {
        super.onCreate()

        //카카오 SDK 초기화
        KakaoSdk.init(this,"32b8c3f96e3faf087e8147f88fcb0cc4")
    }

}