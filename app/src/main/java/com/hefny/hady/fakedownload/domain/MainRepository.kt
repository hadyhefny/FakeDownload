package com.hefny.hady.fakedownload.domain

import io.reactivex.rxjava3.core.Observable

interface MainRepository {
    fun getFakeVideos(): Observable<Int>
}