package com.hefny.hady.fakedownload.di

import com.hefny.hady.fakedownload.data.remote.FakeDownloadApi
import com.hefny.hady.fakedownload.data.repos.MainRepositoryImpl
import com.hefny.hady.fakedownload.domain.MainRepository
import com.hefny.hady.fakedownload.utils.Constants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.HEADERS
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(loggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideGsonConverter(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRxJavaCallAdapterFactory(): RxJava3CallAdapterFactory {
        return RxJava3CallAdapterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
        rxJava3CallAdapterFactory: RxJava3CallAdapterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJava3CallAdapterFactory)
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideFakeDownloadApi(
        retrofit: Retrofit
    ): FakeDownloadApi {
        return retrofit.create(FakeDownloadApi::class.java)
    }

    @Singleton
    @Provides
    fun provideMainRepository(fakeDownloadApi: FakeDownloadApi): MainRepository {
        return MainRepositoryImpl(fakeDownloadApi)
    }
}