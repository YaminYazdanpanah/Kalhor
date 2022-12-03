package com.y2.kalhor.data.base.utils

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

object Const {

    @OptIn(ExperimentalSerializationApi::class)
    val AppJson = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
        coerceInputValues = true
    }
}