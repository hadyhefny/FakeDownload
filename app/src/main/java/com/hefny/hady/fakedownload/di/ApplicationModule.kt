package com.hefny.hady.fakedownload.di

import com.hefny.hady.fakedownload.data.remote.FakeDownloadApi
import com.hefny.hady.fakedownload.data.remote.datasource.RemoteDataSource
import com.hefny.hady.fakedownload.data.remote.datasource.RemoteDataSourceImpl
import com.hefny.hady.fakedownload.data.repos.MainRepositoryImpl
import com.hefny.hady.fakedownload.domain.DownloadFakeVideoUseCase
import com.hefny.hady.fakedownload.domain.GetFakeVideosUseCase
import com.hefny.hady.fakedownload.domain.MainRepository
import com.hefny.hady.fakedownload.utils.Constants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
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
    fun provideRxJavaCallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
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
    fun provideRemoteDataSource(fakeDownloadApi: FakeDownloadApi): RemoteDataSource {
        return RemoteDataSourceImpl(fakeDownloadApi)
    }

    @Singleton
    @Provides
    fun provideMainRepository(remoteDataSource: RemoteDataSource): MainRepository {
        return MainRepositoryImpl(remoteDataSource)
    }

    @Singleton
    @Provides
    fun provideGetFakeVideosUseCase(mainRepository: MainRepository): GetFakeVideosUseCase {
        return GetFakeVideosUseCase(mainRepository)
    }

    @Singleton
    @Provides
    fun provideDownloadFakeVideoUseCase(mainRepository: MainRepository): DownloadFakeVideoUseCase {
        return DownloadFakeVideoUseCase(mainRepository)
    }
}