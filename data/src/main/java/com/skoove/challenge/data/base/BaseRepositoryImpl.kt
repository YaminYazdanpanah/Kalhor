package com.skoove.challenge.data.base

import com.skoove.challenge.data.base.response.exception.AppException
import com.skoove.challenge.data.base.response.ApiResponse
import com.skoove.challenge.data.base.response.exception.NoInternetException
import java.lang.Exception

open class BaseRepositoryImpl {

    /**
     * Calls the server and returns result
     *
     * @param doOnSuccess will be called before returning success result. Pass this parameter when
     * you need run some tasks when api call is successful
     * @param info is information of the api call when an error occurs
     * @param call is the api call
     */
    suspend fun <T> getResult(
        doOnSuccess: (suspend () -> Unit)? = null,
        info: Any? = null,
        call: suspend () -> ApiResponse<T>
    ): Result<T> {
        return try {
            val apiResponse = call.invoke()
            if (apiResponse.status && apiResponse.data != null) {
                doOnSuccess?.invoke()
                Result.success(apiResponse.data)
            } else {
                throw AppException(apiResponse.errors, info)
            }
        } catch (e: Exception) {
            when (e) {
                is AppException -> {
                    Result.failure(e)
                }
                is NoInternetException -> {
                    Result.failure(e)
                }
                else -> {
                    Result.failure(e)
                }
            }
        }
    }

}