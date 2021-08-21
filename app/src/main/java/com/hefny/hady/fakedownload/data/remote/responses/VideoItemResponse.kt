package com.hefny.hady.fakedownload.data.remote.responses


data class VideoItemResponse(
    val name: String,
    val id: Int,
    val type: String,
    val url: String,
    var downloaded: Int = -1,
    var totalItemSizeInMegaBytes: Int = 5
)