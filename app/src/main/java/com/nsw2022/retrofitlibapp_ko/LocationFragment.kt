package com.nsw2022.retrofitlibapp_ko


import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.Context.MODE_PRIVATE
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.PermissionChecker
import androidx.fragment.app.Fragment
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import com.nsw2022.retrofitlibapp_ko.databinding.FragmentLocationBinding
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import okhttp3.internal.Util
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.jar.Manifest
import java.util.prefs.Preferences

class LocationFragment: Fragment() {

    lateinit var binding:FragmentLocationBinding
    private val ACCESS_FINE_LOCATION = 1000 // Request Code

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentLocationBinding.inflate(layoutInflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)





        var mapView=MapView(requireContext())
        var mapContainer=view.findViewById<ViewGroup>(R.id.map_container)
        mapContainer.addView(mapView)









    }//////////////onCreate




    /*
        // 중심점 변경 + 줌 레벨 변경
        mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(33.41, 126.52), 8, true)

        // 줌 인
        mapView.zoomIn(true)

        // 줌 아웃
        mapView.zoomOut(true)


        //마커추가
        var marker=MapPOIItem()
        marker.itemName = "미래IT캠퍼스"
        marker.tag = ㄱ0
        marker.mapPoint = MapPoint.mapPointWithGeoCoord(37.5654,127.0253)
        marker.markerType = MapPOIItem.MarkerType.BluePin // 기본으로 제공하는 BluePin 마커 모양.
        marker.selectedMarkerType = MapPOIItem.MarkerType.RedPin // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

        mapView.addPOIItem(marker)

        mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(37.5654,127.0253), 5, true)

         */



    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}