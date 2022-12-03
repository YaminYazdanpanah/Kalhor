package com.y2.kalhor.domain.audio.result

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable


/**
 * Audio object
 *
 * @property Audio
 * @constructor Create empty Audio object
 */
@Parcelize
@Serializable
data class Audio(
    val title: String? = "title",
    val audio: String? = "url",
    val cover: String? = "cover",
    val totalDurationMs: Int = 0,
    var rate: Int = 0,
    var isFavorite: Boolean = false,
) : Parcelable
