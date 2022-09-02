package com.skoove.challenge.data.audio

import com.skoove.challenge.data.base.BaseRepositoryImpl
import com.skoove.challenge.domain.audio.AudioRepository
import com.skoove.challenge.domain.audio.result.Audio
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