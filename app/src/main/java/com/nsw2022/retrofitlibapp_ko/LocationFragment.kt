package com.nsw2022.retrofitlibapp_ko


import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.app.ProgressDialog.show
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.PermissionChecker
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import com.gun0912.tedpermission.provider.TedPermissionProvider.context
import com.nsw2022.retrofitlibapp_ko.databinding.FragmentLocationBinding
import net.daum.mf.map.api.MapCircle
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import okhttp3.internal.Util
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.jar.Manifest
import java.util.prefs.Preferences

class LocationFragment : Fragment() {

    lateinit var binding: FragmentLocationBinding
    private val ACCESS_FINE_LOCATION = 1000 // Request Code
    var aBoolean = true
    var userCount: Int = 0
    var circleCount: Int = 0
    lateinit var mapView : MapView
    var marker = MapPOIItem()


    //val eventListener = MarkerEventListener(requireContext())
//    var itemListener = object : MapView.POIItemEventListener {
//        override fun onPOIItemSelected(p0: MapView?, p1: MapPOIItem?) {
//            Toast.makeText(requireContext(), "나는 아이템이 선택됐을때야!${p1?.itemName}", Toast.LENGTH_SHORT)
//                .show()
//            Log.d("ITEM", "${p1?.itemName}")
//        }
//
//        override fun onCalloutBalloonOfPOIItemTouched(
//            p0: MapView?,
//            p1: MapPOIItem?
//        ) {
//            Toast.makeText(requireContext(), "나는 벌룬? 선택됐을때야!${p1?.itemName}", Toast.LENGTH_SHORT)
//                .show()
//            Log.d("Ballo", "${p1?.itemName}")
//        }
//
//        override fun onCalloutBalloonOfPOIItemTouched(
//            p0: MapView?,
//            p1: MapPOIItem?,
//            p2: MapPOIItem.CalloutBalloonButtonType?
//        ) {
//            Toast.makeText(
//                requireContext(),
//                "벌룬인데 메소드가 하나 더 많아!{${p1?.itemName}}",
//                Toast.LENGTH_SHORT
//            ).show()
//            Log.d("SAME", "${p1?.itemName}")
//        }
//
//        override fun onDraggablePOIItemMoved(
//            p0: MapView?,
//            p1: MapPOIItem?,
//            p2: MapPoint?
//        ) {
//            Toast.makeText(requireContext(), "드래그라는거보니아닌듯${p1?.itemName}}", Toast.LENGTH_SHORT)
//                .show()
//            Log.d("SAME2", "${p1?.itemName}")
//        }
//    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLocationBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var mapView = MapView(requireContext())

        binding.fabAdd.setOnClickListener { fabAddBtn() }

        var mapContainer = view.findViewById<ViewGroup>(R.id.map_container)
        mapContainer.addView(mapView)

        //mapView.setPOIItemEventListener(    MarkerEventListener(requireContext())   )

        var marker = MapPOIItem()

        // 열린데이터광장 데이터 파싱
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(SeoulOpenApi.DOMAIN)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitService = retrofit.create(LibApiRetrofitService::class.java)

        retrofitService.getAPILibDatas(SeoulOpenApi.KEY)
            .enqueue(object : Callback<LibApiItem> {
                override fun onResponse(
                    call: Call<LibApiItem>,
                    response: Response<LibApiItem>
                ) {
                    val apiReonse: LibApiItem? = response.body()

                    apiReonse?.SeoulLibraryTimeInfo?.row?.forEach {
                        marker.itemName = "${it.LBRRY_NAME}\n${it.FDRM_CLOSE_DATE}"
                        marker.mapPoint = MapPoint.mapPointWithGeoCoord(
                            it.XCNTS.toDouble(),
                            it.YDNTS.toDouble()
                        )
                        marker.markerType = MapPOIItem.MarkerType.RedPin
                        marker.selectedMarkerType = MapPOIItem.MarkerType.YellowPin

//                  val url:String="kakaomap://route?sp=37.537229,127.005515&ep=37.4979502,127.0276368&by=PUBLICTRANSIT"
//                  var intent= Intent(Intent.ACTION_VIEW, Uri.parse(url))
//                  startActivity(intent)
                        mapView.addPOIItem(marker)
                    }
                }

                override fun onFailure(call: Call<LibApiItem>, t: Throwable) {
                    AlertDialog.Builder(requireContext()).setMessage("${t.message}")
                        .create().show()
                }

            })

        binding.fabUserLocation.setOnClickListener {

            when (userCount) {
                0 -> {
                    mapView.currentLocationTrackingMode =
                        MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading
                    userCount++
                }
                1 -> {
                    mapView.currentLocationTrackingMode =
                        MapView.CurrentLocationTrackingMode.TrackingModeOnWithHeading
                    userCount++
                }
                2 -> {
                    mapView.currentLocationTrackingMode =
                        MapView.CurrentLocationTrackingMode.TrackingModeOff
                    userCount = 0
                }
            }
        }

        binding.fabTwo.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {

                when (circleCount) {
                    0 -> {
                        mapView.currentLocationTrackingMode =
                            MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading
                        val locationManager: LocationManager =
                            context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
                        if (ActivityCompat.checkSelfPermission(
                                requireContext(),
                                android.Manifest.permission.ACCESS_FINE_LOCATION
                            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                                requireContext(),
                                android.Manifest.permission.ACCESS_COARSE_LOCATION
                            ) != PackageManager.PERMISSION_GRANTED
                        ) {
                            return
                        }
                        //위치정보 객체 참조변수
                        var location: Location? = null
                        if (locationManager!!.isProviderEnabled("gps")) {
                            location = locationManager!!.getLastKnownLocation("gps")
                        } else if (locationManager!!.isProviderEnabled("network")) {
                            location = locationManager!!.getLastKnownLocation("network")
                        }
                        if (location == null) {
                            Toast.makeText(
                                requireContext(),
                                "Error 네트워크 GPS 확인요망",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            // 위도,경도 얻어내기
                            val latitude = location.latitude
                            val longitude = location.longitude
                            //Toast.makeText(requireContext(), "나는 위도$latitude\n나는 경도 $longitude", Toast.LENGTH_SHORT).show()
                            val circle = MapCircle(
                                MapPoint.mapPointWithGeoCoord(latitude, longitude), 2000,
                                Color.argb(128, 255, 0, 0),//strokeColor
                                Color.argb(128, 0, 255, 0) //fillColor
                            )
                            mapView.addCircle(circle)
                            Toast.makeText(requireContext(), "반경 2km 입니다.", Toast.LENGTH_SHORT).show()
                            circleCount++
                        }
                    }
                    1 -> {
                        mapView.removeAllCircles()
                        circleCount = 0
                    }
                }//when
            }
        })//clickListener

    }//////////////onCreate

    fun fabAddBtn() {
        if (aBoolean) {
            binding.fabUserLocation.show()
            binding.fabTwo.show()
            aBoolean = false
        } else {
            binding.fabUserLocation.hide()
            binding.fabTwo.hide()
            aBoolean = true
        }
    }

    class MarkerEventListener(val context: Context):MapView.POIItemEventListener{
        override fun onPOIItemSelected(p0: MapView?, p1: MapPOIItem?) {
            Toast.makeText(context, "나는 아이템이 선택됐을때야!${p1?.itemName}", Toast.LENGTH_SHORT)
                .show()
            Log.d("ITEM", "${p1?.itemName}")
        }

        override fun onCalloutBalloonOfPOIItemTouched(
            p0: MapView?,
            p1: MapPOIItem?
        ) {
            Toast.makeText(context, "나는 벌룬? 선택됐을때야!${p1?.itemName}", Toast.LENGTH_SHORT)
                .show()
            Log.d("Ballo", "${p1?.itemName}")
        }

        override fun onCalloutBalloonOfPOIItemTouched(
            p0: MapView?,
            p1: MapPOIItem?,
            p2: MapPOIItem.CalloutBalloonButtonType?
        ) {
            Toast.makeText(
                context,
                "벌룬인데 메소드가 하나 더 많아!{${p1?.itemName}}",
                Toast.LENGTH_SHORT
            ).show()
            Log.d("SAME", "${p1?.itemName}")
        }

        override fun onDraggablePOIItemMoved(
            p0: MapView?,
            p1: MapPOIItem?,
            p2: MapPoint?
        ) {
            Toast.makeText(context, "드래그라는거보니아닌듯${p1?.itemName}}", Toast.LENGTH_SHORT)
                .show()
            Log.d("SAME2", "${p1?.itemName}")
        }

    }
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

