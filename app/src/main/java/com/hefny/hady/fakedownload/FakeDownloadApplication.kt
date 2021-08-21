package com.hefny.hady.fakedownload

import android.app.Application
import com.hefny.hady.fakedownload.di.DaggerApplicationComponent

class FakeDownloadApplication : Application() {
    val appComponent = DaggerApplicationComponent.create()
}