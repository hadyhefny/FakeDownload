package com.hefny.hady.fakedownload.data.remote.datasource

import com.hefny.hady.fakedownload.data.remote.responses.VideoItemResponse
import io.reactivex.rxjava3.core.Observable

interface RemoteDataSource {
    fun downloadFakeVideo(id: Int): Observable<VideoItemResponse>
}