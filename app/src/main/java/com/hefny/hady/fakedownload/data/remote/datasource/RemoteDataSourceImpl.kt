package com.hefny.hady.fakedownload.data.remote.datasource

import com.hefny.hady.fakedownload.data.remote.FakeDownloadApi
import com.hefny.hady.fakedownload.utils.Constants
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSourceImpl @Inject constructor(fakeDownloadApi: FakeDownloadApi) :
    RemoteDataSource {
    override fun getFakeVideos(): Observable<Int> {
        return Observable.create { emitter ->
            for (i in 0..Constants.FAKE_VIDEO_SIZE_IN_MEGA_BYTES) {
                emitter?.onNext(i)
                Thread.sleep(1000)
            }
            emitter.onComplete()
        }
    }
}