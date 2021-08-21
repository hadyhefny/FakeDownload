package com.hefny.hady.fakedownload.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hefny.hady.fakedownload.domain.GetFakeVideosUseCase
import com.hefny.hady.fakedownload.domain.models.VideoItem
import com.hefny.hady.fakedownload.utils.Resource
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainViewModel @Inject constructor(private val getFakeVideosUseCase: GetFakeVideosUseCase) :
    ViewModel() {

    private val disposable = CompositeDisposable()

    private val _downloadVideoMutableLiveData = MutableLiveData<Resource<VideoItem>>()
    val downloadVideoLiveData: LiveData<Resource<VideoItem>>
        get() = _downloadVideoMutableLiveData

    fun downloadVideo(id: Int) {
        _downloadVideoMutableLiveData.value = Resource.loading(true)
        getFakeVideosUseCase.downloadFakeVideo(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<VideoItem> {
                override fun onSubscribe(d: Disposable?) {
                    disposable.add(d)
                }

                override fun onNext(t: VideoItem?) {
                    _downloadVideoMutableLiveData.value = Resource.data(t, id = id)
                }

                override fun onError(e: Throwable?) {
                    _downloadVideoMutableLiveData.value = Resource.error(e?.message)
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

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}