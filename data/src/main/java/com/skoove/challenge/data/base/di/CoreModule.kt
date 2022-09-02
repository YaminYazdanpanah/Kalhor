package com.skoove.challenge.data.base.di

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.skoove.challenge.data.BuildConfig
import com.skoove.challenge.data.base.utils.Const.AppJson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton


/**
 * Core module
 *
 * @constructor Create empty Core module
 */
@Module
@InstallIn(SingletonComponent::class)
class CoreModule {

    /**
     * Provide retrofit
     *
     * @param okHttpClient
     * @return
     */
    @Provides
    @Singleton
    @OptIn(ExperimentalSerializationApi::class)
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.CONNECTION_URL)
            .addConverterFactory(
                AppJson.asConverterFactory("application/json".toMediaType())
            )
            .build()
    }

    /**
     * Provide ok http
     *
     * @param context
     * @return
     */
    @Provides
    @Singleton
    fun provideOkHttp(
        @ApplicationContext context: Context,
    ): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
            .apply {
                if (BuildConfig.BUILD_TYPE != "release") {
                    addInterceptor(HttpLoggingInterceptor().apply {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    })
                }
            }

        return httpClient.build()
    }



}

