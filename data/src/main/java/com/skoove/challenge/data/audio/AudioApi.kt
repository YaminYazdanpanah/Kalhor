package com.skoove.challenge.data.audio

import com.skoove.challenge.data.base.response.ApiResponse
import com.skoove.challenge.domain.audio.result.Audio
import retrofit2.http.GET


/**
 * Audio api class by Retrofit
 *
 */
interface AudioApi {

    /**
     * Get Audio List content from server
     *
     * @return List of Contents as Audio Objects
     */
    @GET("react native/simple audio player/data/manifest.json")
    suspend fun getAudioList(): ApiResponse<List<Audio>>

}