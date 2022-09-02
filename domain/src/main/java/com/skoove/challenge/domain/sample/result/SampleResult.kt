package com.skoove.challenge.domain.sample.result

import android.os.Parcelable

import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * Sample result
 *
 * @property sampleList
 * @constructor Create empty Sample result
 */
@Parcelize
@Serializable
data class SampleResult(
    @SerialName("sample")
    val sampleList: List<SampleModel> = listOf(),
) : Parcelable {

    /**
     * Sample model
     *
     * @property sample
     * @constructor Create empty Sample model
     */
    @Parcelize
    @Serializable
    data class SampleModel(
        val sample: Int?,
    ) : Parcelable
}