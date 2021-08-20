package com.hefny.hady.fakedownload.data.remote

import com.hefny.hady.fakedownload.data.remote.responses.VideosListResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface FakeDownloadApi {
    @GET("videos")
    fun getFakeVideos(): Observable<VideosListResponse>
}