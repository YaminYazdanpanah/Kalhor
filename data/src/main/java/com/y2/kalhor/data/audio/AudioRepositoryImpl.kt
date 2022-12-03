package com.y2.kalhor.data.audio

import com.y2.kalhor.data.base.BaseRepositoryImpl
import com.y2.kalhor.domain.audio.AudioRepository
import com.y2.kalhor.domain.audio.result.Audio
import javax.inject.Inject


/**
 * Sample repository impl
 *
 * @property api
 * @constructor Create empty Sample repository impl
 */
class AudioRepositoryImpl @Inject constructor(
    private val api: AudioApi
) : AudioRepository, BaseRepositoryImpl() {

    override suspend fun getAudioList(): Result<List<Audio>> =
        getResult { api.getAudioList() }
}