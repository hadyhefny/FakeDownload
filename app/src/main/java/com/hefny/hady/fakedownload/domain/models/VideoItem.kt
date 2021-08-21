package com.hefny.hady.fakedownload.domain.models

data class VideoItem(
    val name: String,
    val id: Int,
    val type: String,
    val url: String,
    var downloaded: Int = -1,
    var totalItemSizeInMegaBytes: Int
){
    fun getDownloadedPercentage(): Int{
        return (downloaded * 100) / totalItemSizeInMegaBytes
    }
}