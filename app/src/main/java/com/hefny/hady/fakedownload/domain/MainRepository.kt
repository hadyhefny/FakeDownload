package com.hefny.hady.fakedownload.domain

import com.hefny.hady.fakedownload.domain.models.VideoItem
import io.reactivex.rxjava3.core.Observable

interface MainRepository {
    fun downloadFakeVideo(id: Int): Observable<VideoItem>
}