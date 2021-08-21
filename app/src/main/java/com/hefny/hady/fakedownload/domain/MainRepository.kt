package com.hefny.hady.fakedownload.domain

import com.hefny.hady.fakedownload.domain.models.VideoItem
import io.reactivex.Observable
import io.reactivex.Single

interface MainRepository {
    fun downloadFakeVideo(id: Int): Observable<VideoItem>
    fun getFakeVideos(): Single<ArrayList<VideoItem>>
}