package com.hefny.hady.fakedownload.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hefny.hady.fakedownload.domain.GetFakeVideosUseCase
import com.hefny.hady.fakedownload.utils.Constants
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

    private val _videosMutableLiveData = MutableLiveData<Resource<Int>>()
    val videosLiveData: LiveData<Resource<Int>>
        get() = _videosMutableLiveData

    fun downloadVideo(id: Int) {
        _videosMutableLiveData.value = Resource.loading(true)
        getFakeVideosUseCase.getFakeVideos()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable?) {
                    disposable.add(d)
                }

                override fun onNext(t: Int?) {
                    _videosMutableLiveData.value = Resource.data(t, id = id)
                }

                override fun onError(e: Throwable?) {
                    _videosMutableLiveData.value = Resource.error(e?.message)
                }

                override fun onComplete() {
                    _videosMutableLiveData.value = Resource.data(
                        Constants.FAKE_VIDEO_SIZE_IN_MEGA_BYTES,
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