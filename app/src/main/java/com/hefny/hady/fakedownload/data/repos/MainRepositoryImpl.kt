package com.hefny.hady.fakedownload.data.repos

import com.hefny.hady.fakedownload.data.remote.FakeDownloadApi
import com.hefny.hady.fakedownload.domain.MainRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepositoryImpl @Inject constructor(private val fakeDownloadApi: FakeDownloadApi) : MainRepository {
}