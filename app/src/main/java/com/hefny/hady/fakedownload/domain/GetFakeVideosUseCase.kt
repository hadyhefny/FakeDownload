package com.hefny.hady.fakedownload.domain

import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetFakeVideosUseCase @Inject constructor(private val mainRepository: MainRepository) {
    fun getFakeVideos(): Observable<Int> {
        return mainRepository.getFakeVideos()
    }
}