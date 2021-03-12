package com.uszkai.mealdeas.di

import android.content.Context
import androidx.room.Room
import com.uszkai.mealdeas.business.data.cache.abstraction.SampleCacheDataSource
import com.uszkai.mealdeas.business.data.cache.implementation.SampleCacheDataSourceImpl
import com.uszkai.mealdeas.framework.datasource.cache.abstraction.SampleDaoService
import com.uszkai.mealdeas.framework.datasource.cache.database.AppDatabase
import com.uszkai.mealdeas.framework.datasource.cache.database.SampleDao
import com.uszkai.mealdeas.framework.datasource.cache.implementation.SampleDaoServiceImpl
import com.uszkai.mealdeas.framework.datasource.cache.mapper.SampleCacheMapper
import com.uszkai.mealdeas.util.Constants.AppConstants.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideAppDb(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideSampleDao(appDatabase: AppDatabase): SampleDao {
        return appDatabase.sampleDao()
    }

    @Singleton
    @Provides
    fun provideSampleDaoService(
        sampleDao: SampleDao,
        sampleCacheMapper: SampleCacheMapper
    ): SampleDaoService {
        return SampleDaoServiceImpl(
            sampleDao = sampleDao,
            sampleCacheMapper = sampleCacheMapper
        )
    }

    @Singleton
    @Provides
    fun provideSampleCacheDataSource(
        sampleDaoService: SampleDaoService
    ): SampleCacheDataSource {
        return SampleCacheDataSourceImpl(sampleDaoService)
    }
}