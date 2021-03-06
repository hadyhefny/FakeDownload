package com.hefny.hady.fakedownload.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hefny.hady.fakedownload.domain.DownloadFakeVideoUseCase
import com.hefny.hady.fakedownload.domain.GetFakeVideosUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
@Singleton
class MainViewModelFactory
@Inject constructor(
    private val getFakeVideosUseCase: GetFakeVideosUseCase,
    private val downloadFakeVideoUseCase: DownloadFakeVideoUseCase
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(getFakeVideosUseCase, downloadFakeVideoUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}