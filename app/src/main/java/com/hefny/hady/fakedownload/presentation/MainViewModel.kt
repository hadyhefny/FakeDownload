package com.hefny.hady.fakedownload.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hefny.hady.fakedownload.domain.DownloadFakeVideoUseCase
import com.hefny.hady.fakedownload.domain.GetFakeVideosUseCase
import com.hefny.hady.fakedownload.domain.models.VideoItem
import com.hefny.hady.fakedownload.utils.Resource
import io.reactivex.Observer
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainViewModel @Inject constructor(
    private val getFakeVideosUseCase: GetFakeVideosUseCase,
    private val downloadFakeVideoUseCase: DownloadFakeVideoUseCase
) : ViewModel() {

    private val disposable = CompositeDisposable()
    private val _downloadVideoMutableLiveData = MutableLiveData<Resource<VideoItem>>()
    val downloadVideoLiveData: LiveData<Resource<VideoItem>>
        get() = _downloadVideoMutableLiveData
    private val _fakeVideosMutableLiveData = MutableLiveData<Resource<ArrayList<VideoItem>>>()
    val fakeVideosLiveData: LiveData<Resource<ArrayList<VideoItem>>>
        get() = _fakeVideosMutableLiveData

    fun downloadVideo(id: Int) {
        _downloadVideoMutableLiveData.value = Resource.loading(true)
        downloadFakeVideoUseCase.downloadFakeVideo(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<VideoItem> {
                override fun onSubscribe(d: Disposable) {
                    disposable.add(d)
                }

                override fun onNext(t: VideoItem) {
                    _downloadVideoMutableLiveData.value = Resource.data(t, id = id)
                }

                override fun onError(e: Throwable) {
                    _downloadVideoMutableLiveData.value = Resource.error(e.message)
                }

                override fun onComplete() {
                    _downloadVideoMutableLiveData.value = Resource.data(
                        null,
                        "Video is downloaded",
                        id
                    )
                }
            })
    }

    fun getFakeVideos() {
        _fakeVideosMutableLiveData.value = Resource.loading(true)
        getFakeVideosUseCase.getFakeVideos()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<ArrayList<VideoItem>> {
                override fun onSubscribe(d: Disposable) {
                    disposable.add(d)
                }

                override fun onSuccess(t: ArrayList<VideoItem>) {
                    _fakeVideosMutableLiveData.value = Resource.data(t)
                }

                override fun onError(e: Throwable) {
                    _fakeVideosMutableLiveData.value = Resource.error(e.message)
                }
            })
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}