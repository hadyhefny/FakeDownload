package com.hefny.hady.fakedownload.data.remote.datasource

import com.hefny.hady.fakedownload.data.remote.FakeDownloadApi
import com.hefny.hady.fakedownload.data.remote.responses.VideoItemResponse
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSourceImpl @Inject constructor(private val fakeDownloadApi: FakeDownloadApi) :
    RemoteDataSource {
    override fun downloadFakeVideo(id: Int): Observable<VideoItemResponse> {
        return fakeDownloadApi.downloadFakeVideo(id)
    }

    override fun getFakeVideos(): Single<ArrayList<VideoItemResponse>> {
        return fakeDownloadApi.getFakeVideos()
    }
}