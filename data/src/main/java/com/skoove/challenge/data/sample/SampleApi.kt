package com.skoove.challenge.data.sample

import com.skoove.challenge.data.base.response.ApiResponse
import com.skoove.challenge.domain.sample.param.SampleParam
import com.skoove.challenge.domain.sample.result.SampleResult
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


/**
 * Sample api
 *
 * @constructor Create empty Sample api
 */
interface SampleApi {

    /**
     * Sample get
     *
     * @param sample
     * @return
     */
    @GET("../{sample}")
    suspend fun sampleGet(
        @Path("sample") sample: String,
    ): ApiResponse<List<SampleResult>>

    /**
     * Sample post
     *
     * @param body
     * @return
     */
    @POST("..")
    suspend fun samplePost(
        @Body body: SampleParam
    ): ApiResponse<SampleResult>

}