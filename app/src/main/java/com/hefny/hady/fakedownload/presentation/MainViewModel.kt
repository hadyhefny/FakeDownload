package com.hefny.hady.fakedownload.presentation

import androidx.lifecycle.ViewModel
import com.hefny.hady.fakedownload.domain.MainRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {
}