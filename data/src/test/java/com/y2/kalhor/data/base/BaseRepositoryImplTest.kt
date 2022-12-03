package com.y2.kalhor.data.base

import com.y2.kalhor.data.helper.FakeApi
import com.y2.kalhor.data.helper.createRetrofit
import com.y2.kalhor.data.helper.setBodyFromFile
import junit.framework.Assert
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class BaseRepositoryImplTest {

    private val mockWebServer = MockWebServer()
    private lateinit var baseRepositoryImpl: BaseRepositoryImpl

    @Before
    fun setup() {
        baseRepositoryImpl = BaseRepositoryImpl()
    }

    @After
    fun clean() {
        mockWebServer.shutdown()
    }

    @Test
    fun getResult_responseIsSuccessful_callsDoOnSuccessAndReturnsSuccessResult() {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBodyFromFile("base_response.json")
        )
        val retrofit = createRetrofit(mockWebServer)
        var isSuccess = false
        runTest {
            val result = baseRepositoryImpl.getResult(doOnSuccess = {
                isSuccess = true
            }) {
                retrofit.create(FakeApi::class.java).fakeCall()
            }
            Assert.assertTrue(isSuccess)
            Assert.assertTrue(result.isSuccess)
        }
    }

    @Test
    fun getResult_responseIsSuccessfulButJsonIsNotValid_returnsFailedResult() {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody("{}")
        )
        val retrofit = createRetrofit(mockWebServer)
        runTest {
            val result = baseRepositoryImpl.getResult {
                retrofit.create(FakeApi::class.java).fakeCall()
            }
            Assert.assertTrue(result.isFailure)
        }
    }
}