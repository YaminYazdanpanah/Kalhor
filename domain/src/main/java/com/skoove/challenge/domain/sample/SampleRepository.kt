package com.skoove.challenge.domain.sample

import com.skoove.challenge.domain.sample.result.SampleResult


/**
 * Sample repository
 *
 * @constructor Create empty Sample repository
 */
interface SampleRepository {

    /**
     * Sample
     *
     * @return
     */
    suspend fun sampleGet(sample: String): Result<List<SampleResult>>
}