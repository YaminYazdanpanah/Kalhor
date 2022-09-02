package com.skoove.challenge.data.base.di

import com.skoove.challenge.data.sample.SampleApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


/**
 * Api module
 *
 * @constructor Create empty Api module
 */
@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    /**
     * Provide sample
     *
     * @param retrofit
     * @return
     */
    @Provides
    @Singleton
    fun provideSample(retrofit: Retrofit): SampleApi = retrofit.create(SampleApi::class.java)

}