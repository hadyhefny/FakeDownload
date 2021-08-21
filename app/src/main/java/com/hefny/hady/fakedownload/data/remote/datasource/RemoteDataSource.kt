package com.hefny.hady.fakedownload.data.remote.datasource

import com.hefny.hady.fakedownload.data.remote.responses.VideoItemResponse
import io.reactivex.Observable
import io.reactivex.Single

interface RemoteDataSource {
    fun downloadFakeVideo(id: Int): Observable<VideoItemResponse>
    fun getFakeVideos(): Single<ArrayList<VideoItemResponse>>
}