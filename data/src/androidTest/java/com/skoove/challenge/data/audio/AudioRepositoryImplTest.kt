package com.skoove.challenge.data.audio

import android.content.Context
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.test.core.app.ApplicationProvider
import com.skoove.challenge.data.helper.createRetrofitObject
import com.skoove.challenge.data.helper.readFileFromResources
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Test


/**
 * This test is instrumented because I wanted to showcase Instrument test on Repository
 * It will be use full if we have data store
 * For most of the Repository Impls, local tests are preferred
 */
@OptIn(ExperimentalCoroutinesApi::class)
class AudioRepositoryImplTest {

    companion object {
        private val context: Context = ApplicationProvider.getApplicationContext()
        private val dataStore =
            PreferenceDataStoreFactory.create(
                scope = TestScope(UnconfinedTestDispatcher() + Job()),
                produceFile = { context.preferencesDataStoreFile("TEST_DATA_STORE") }
            )
    }

    @Test
    fun getAudioList_isSuccessful_mustReturnSuccess() {
        runTest {
            val api =
                createRetrofitObject(GenerateGetAudioListFakeInterceptor()).create(AudioApi::class.java)
            val repository = AudioRepositoryImpl(api)
            val result = repository.getAudioList()
            //Testing Response Json Values
            Assert.assertEquals("Oceansound", result.getOrNull()!![0].title)
            Assert.assertNotNull( result.getOrNull()!![0].totalDurationMs)
        }
    }
}

private class GenerateGetAudioListFakeInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = readFileFromResources(javaClass, "get_audio_list_response.json")

        return Response.Builder()
            .code(200)
            .message(response)
            .body(response.toResponseBody())
            .request(chain.request())
            .protocol(Protocol.HTTP_2)
            .addHeader("content-type", "application/json")
            .build()
    }
}