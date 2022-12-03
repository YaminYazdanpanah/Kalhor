package com.y2.kalhor.data.helper

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.y2.kalhor.data.base.response.ApiResponse
import com.y2.kalhor.data.base.utils.Const
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import retrofit2.Retrofit
import retrofit2.http.GET
import java.nio.charset.StandardCharsets
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalSerializationApi::class)
internal fun createRetrofit(webServer: MockWebServer): Retrofit = Retrofit.Builder()
    .baseUrl(webServer.url("/"))
    .client(
        OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.SECONDS)
            .writeTimeout(1, TimeUnit.SECONDS)
            .build()
    ).addConverterFactory(
        Const.AppJson.asConverterFactory("application/json".toMediaType())
    ).build()

fun MockResponse.setBodyFromFile(name: String): MockResponse {
    javaClass.classLoader?.getResourceAsStream(name)?.also {
        setBody(it.source().buffer().readString(StandardCharsets.UTF_8))
    }
    return this
}

interface FakeApi {
    @GET("/fake")
    suspend fun fakeCall(): ApiResponse<Unit>

}
