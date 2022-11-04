package com.nsw2022.retrofitlibapp_ko


data class LibApiItem constructor(
    val SeoulLibraryTimeInfo: LibApiItemList
)
data class LibApiItemList constructor(
    val row:MutableList<Row>
)

data class Row constructor(
    val ADRES: String,
    val CODE_VALUE: String,
    val FDRM_CLOSE_DATE: String,
    val GU_CODE: String,
    val LBRRY_NAME: String,
    val TEL_NO: String,
    val XCNTS: String,
    val YDNTS: String
)