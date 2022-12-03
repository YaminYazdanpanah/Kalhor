package com.y2.kalhor.data.base.response

import kotlinx.serialization.Serializable

/**
 * Wrapper object for all api requests
 */
@Serializable
data class ApiResponse<T>(
    val data: T?,
)

