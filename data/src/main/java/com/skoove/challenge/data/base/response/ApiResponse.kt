package com.skoove.challenge.data.base.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Wrapper object for all api requests
 */
@Serializable
data class ApiResponse<T>(
    val data: T?,
    val meta: Meta?,
    val errors: List<String> = emptyList(),
    val status: Boolean = false
) {
    @Serializable
    class Meta {
        @SerialName("current_page")
        var currentPage: Int? = null

        @SerialName("total_pages")
        var totalPages: Int? = null

        @SerialName("total_count")
        var totalCount: Int? = null

        @SerialName("has_next")
        var hasNext: Boolean? = null

        @SerialName("has_previous")
        var hasPrevious: Boolean? = null
    }
}
