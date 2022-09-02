package com.skoove.challenge.base

import kotlinx.coroutines.flow.MutableStateFlow

@Suppress("FunctionName")
fun <T> ModelMutableStateFlow(value: ModelWrapper<T> = ModelWrapper.None()): MutableStateFlow<ModelWrapper<T>> =
    MutableStateFlow(value)



