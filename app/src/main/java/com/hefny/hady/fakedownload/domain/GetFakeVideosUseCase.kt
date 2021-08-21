package com.hefny.hady.fakedownload.domain

import com.hefny.hady.fakedownload.domain.models.VideoItem
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetFakeVideosUseCase @Inject constructor(private val mainRepository: MainRepository) {
    fun downloadFakeVideo(id: Int): Observable<VideoItem> {
        return mainRepository.downloadFakeVideo(id)
    }
}