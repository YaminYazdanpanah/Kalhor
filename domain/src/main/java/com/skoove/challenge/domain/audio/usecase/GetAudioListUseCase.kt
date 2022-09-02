package com.skoove.challenge.domain.audio.usecase

import com.skoove.challenge.domain.base.BaseUseCase
import com.skoove.challenge.domain.audio.AudioRepository
import com.skoove.challenge.domain.audio.result.Audio
import javax.inject.Inject


/**
 * Get audio list use case
 *
 * @property repository AudioRepository to connect to Data Layer and get response from Data Sources
 * @constructor Create empty Get audio list use case
 */
class GetAudioListUseCase @Inject constructor(private val repository: AudioRepository) :
    BaseUseCase<Unit, Result<List<Audio>>>() {
    override suspend fun invoke(param: Unit): Result<List<Audio>> = repository.getAudioList()
}