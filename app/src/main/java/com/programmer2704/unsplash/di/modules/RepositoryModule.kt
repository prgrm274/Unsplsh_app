package com.programmer2704.unsplash.di.modules

import android.app.Application
import com.programmer2704.unsplash.data.remote.UnsplashApiService
import com.programmer2704.unsplash.data.repository.ImagineRepository
import com.programmer2704.unsplash.data.repository.ImagineRepositoryImpl
import com.programmer2704.unsplash.utils.StringUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideStringUtils(app: Application): StringUtils {
        return StringUtils(app)
    }

    @Singleton
    @Provides
    fun provideImagineRepository(stringUtils: StringUtils, apiService: UnsplashApiService): ImagineRepository {
        return ImagineRepositoryImpl(stringUtils, apiService)
    }
}