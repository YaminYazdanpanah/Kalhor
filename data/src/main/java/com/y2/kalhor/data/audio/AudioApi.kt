package com.y2.kalhor.data.audio

import com.y2.kalhor.data.base.response.ApiResponse
import com.y2.kalhor.domain.audio.result.Audio
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