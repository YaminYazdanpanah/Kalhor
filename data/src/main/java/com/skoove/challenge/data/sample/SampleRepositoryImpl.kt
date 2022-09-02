package com.skoove.challenge.data.sample

import com.skoove.challenge.data.base.BaseRepositoryImpl
import com.skoove.challenge.domain.sample.SampleRepository
import com.skoove.challenge.domain.sample.result.SampleResult
import javax.inject.Inject


/**
 * Sample repository impl
 *
 * @property api
 * @constructor Create empty Sample repository impl
 */
class SampleRepositoryImpl @Inject constructor(
    private val api: SampleApi
) : SampleRepository, BaseRepositoryImpl() {

    override suspend fun sampleGet(sample: String): Result<List<SampleResult>> = getResult {
        api.sampleGet(sample)
    }
}