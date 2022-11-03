package com.nsw2022.retrofitlibapp_ko

data class RecyclerItem constructor(
    var LBRRY_NAME:String,
    var CODE_VALUE:String,
    var ADRES:String,
    var FDRM_CLOSE_DATE:String,
    var TEL_NOL:String
)

//json 데이터 정보
//LBRRY_SEQ_NO	도서관 일련번호
//LBRRY_NAME	도서관명
//GU_CODE 	구 코드	//<-필요없음
//CODE_VALUE	구명
//ADRES	    	주소
//FDRM_CLOSE_DATE	정기 휴관일
//TEL_NO		전화번호
//XCNTS	위도
//YDNTS	경도