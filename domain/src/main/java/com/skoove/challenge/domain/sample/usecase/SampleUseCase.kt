package com.skoove.challenge.domain.sample.usecase

import com.skoove.challenge.domain.base.BaseUseCase
import com.skoove.challenge.domain.sample.SampleRepository
import com.skoove.challenge.domain.sample.result.SampleResult
import javax.inject.Inject


/**
 * Sample use case
 *
 * @property repository
 * @constructor Create empty Sample use case
 */
class SampleUseCase @Inject constructor(private val repository: SampleRepository) :
    BaseUseCase<String, Result<List<SampleResult>>>() {
    override suspend fun invoke(param: String): Result<List<SampleResult>> = repository.sampleGet(param)
}