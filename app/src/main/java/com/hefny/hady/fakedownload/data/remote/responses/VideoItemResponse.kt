package com.hefny.hady.fakedownload.data.remote.responses


data class VideoItemResponse(
    val name: String,
    val id: Int,
    val type: String,
    val url: String,
    var downloadedPercentage: Int = 0,
)