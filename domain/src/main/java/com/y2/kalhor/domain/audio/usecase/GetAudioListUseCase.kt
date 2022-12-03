package com.y2.kalhor.domain.audio.usecase

import com.y2.kalhor.domain.base.BaseUseCase
import com.y2.kalhor.domain.audio.AudioRepository
import com.y2.kalhor.domain.audio.result.Audio
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