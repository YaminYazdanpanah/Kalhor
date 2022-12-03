package com.y2.kalhor.data.helper


import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.y2.kalhor.data.base.utils.Const
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit


/**
 * In cases we do not want to handle request using [MockWebServer] and instead we want to handle it
 * through interceptors
 */
internal fun createRetrofitObject(vararg interceptor: Interceptor): Retrofit = Retrofit.Builder()
    .baseUrl("https://test.com")
    .client(
        OkHttpClient.Builder()
            .apply {
                interceptor.forEach {
                    this.addInterceptor(it)
                }
            }.build()
    ).addConverterFactory(
        Const.AppJson.asConverterFactory("application/json".toMediaType())
    ).build()



fun readFileFromResources(clazz: Class<*>, name: String): String =
    clazz.classLoader!!.getResource(name).readText()


