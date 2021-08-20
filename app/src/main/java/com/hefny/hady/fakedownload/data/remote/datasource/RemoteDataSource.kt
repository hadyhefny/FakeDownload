package com.hefny.hady.fakedownload.data.remote.datasource

import io.reactivex.rxjava3.core.Observable

interface RemoteDataSource {
    fun getFakeVideos(): Observable<Int>
}