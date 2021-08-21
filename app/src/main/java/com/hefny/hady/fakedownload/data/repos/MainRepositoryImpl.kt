package com.hefny.hady.fakedownload.data.repos

import com.hefny.hady.fakedownload.data.remote.datasource.RemoteDataSource
import com.hefny.hady.fakedownload.data.remote.responses.VideoItemResponse
import com.hefny.hady.fakedownload.data.remote.responses.VideosListResponse
import com.hefny.hady.fakedownload.data.toVideoItem
import com.hefny.hady.fakedownload.domain.MainRepository
import com.hefny.hady.fakedownload.domain.models.VideoItem
import com.hefny.hady.fakedownload.utils.Constants
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    MainRepository {
    override fun downloadFakeVideo(id: Int): Observable<VideoItem> {
        return Observable.create { emitter ->
            val videoItemResponse: VideoItemResponse =
                VideosListResponse().videoResponses.first { it.id == id }
            for (i in 0..videoItemResponse.totalItemSizeInMegaBytes) {
                videoItemResponse.downloaded = i
                emitter?.onNext(videoItemResponse.toVideoItem())
                Thread.sleep(1000)
            }
            emitter.onComplete()
        }
    }
}