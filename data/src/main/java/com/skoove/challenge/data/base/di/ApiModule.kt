package com.skoove.challenge.data.base.di

import com.skoove.challenge.data.audio.AudioApi
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
     * Provide audio api
     *
     * @param retrofit
     * @return
     */
    @Provides
    @Singleton
    fun provideAudioApi(retrofit: Retrofit): AudioApi = retrofit.create(AudioApi::class.java)

}