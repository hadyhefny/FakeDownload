package com.hefny.hady.fakedownload.data.remote

import com.hefny.hady.fakedownload.data.remote.responses.VideoItemResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface FakeDownloadApi {
    @GET("{id}")
    fun downloadFakeVideo(
        @Path("id") id: Int
    ): Observable<VideoItemResponse>

    @GET("movies")
    fun getFakeVideos(): Single<ArrayList<VideoItemResponse>>
}