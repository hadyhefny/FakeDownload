package com.hefny.hady.fakedownload.data.repos

import com.hefny.hady.fakedownload.data.remote.datasource.RemoteDataSource
import com.hefny.hady.fakedownload.domain.MainRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    MainRepository {
    override fun getFakeVideos(): Observable<Int> {
        return remoteDataSource.getFakeVideos()
    }
}