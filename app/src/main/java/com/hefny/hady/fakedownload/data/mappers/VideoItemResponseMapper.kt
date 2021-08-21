package com.hefny.hady.fakedownload.data

import com.hefny.hady.fakedownload.data.remote.responses.VideoItemResponse
import com.hefny.hady.fakedownload.domain.models.VideoItem

fun VideoItemResponse.toVideoItem(): VideoItem {
    return VideoItem(
        this.name,
        this.id,
        this.type,
        this.url,
        this.downloaded,
        this.totalItemSizeInMegaBytes
    )
}