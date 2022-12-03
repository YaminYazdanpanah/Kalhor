package com.y2.kalhor.domain.audio

import com.y2.kalhor.domain.audio.result.Audio


/**
 * Audio repository
 */
interface AudioRepository {

    /**
     * Get Audio List content from remote Data Source
     *
     * @return List of Audio objects
     */
    suspend fun getAudioList(): Result<List<Audio>>

}