package com.hefny.hady.fakedownload.domain

import com.hefny.hady.fakedownload.domain.models.VideoItem
import io.reactivex.Single
import javax.inject.Inject

class GetFakeVideosUseCase @Inject constructor(private val mainRepository: MainRepository) {
    fun getFakeVideos(): Single<ArrayList<VideoItem>> {
        return mainRepository.getFakeVideos()
    }
}