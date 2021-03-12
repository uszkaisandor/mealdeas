package com.uszkai.mealdeas.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.uszkai.mealdeas.business.data.network.abstraction.SimpleNetworkDataSource
import com.uszkai.mealdeas.business.data.network.implementation.SimpleNetworkDataSourceImpl
import com.uszkai.mealdeas.framework.datasource.network.abstarction.SampleNetworkService
import com.uszkai.mealdeas.framework.datasource.network.api.SampleApiService
import com.uszkai.mealdeas.framework.datasource.network.implementation.SampleNetworkServiceImpl
import com.uszkai.mealdeas.framework.datasource.network.mapper.SampleNetworkMapper
import com.uszkai.mealdeas.util.Constants.NetworkConstants.Companion.API_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .create()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor(
        okHttpClient: OkHttpClient.Builder
    ): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        gson: Gson,
        okHttpClient: OkHttpClient.Builder,
        loggingInterceptor: HttpLoggingInterceptor
    ): Retrofit.Builder {

        okHttpClient.addInterceptor(loggingInterceptor)

        return Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient.build())
    }

    @Singleton
    @Provides
    fun provideSampleApiService(
        retrofit: Retrofit.Builder
    ): SampleApiService {
        return retrofit
            .build()
            .create(SampleApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideSampleNetworkService(
        sampleApiService: SampleApiService,
        sampleNetworkMapper: SampleNetworkMapper
    ): SampleNetworkService {
        return SampleNetworkServiceImpl(
            sampleApiService = sampleApiService,
            sampleNetworkMapper = sampleNetworkMapper
        )
    }

    @Singleton
    @Provides
    fun provideSampleNetworkDataSource(
        sampleNetworkService: SampleNetworkService
    ): SimpleNetworkDataSource {
        return SimpleNetworkDataSourceImpl(
            sampleNetworkService = sampleNetworkService
        )
    }
}