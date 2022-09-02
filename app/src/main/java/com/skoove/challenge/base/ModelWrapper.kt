package com.skoove.challenge.base

sealed class ModelWrapper<T> {
    class Loading<T> : ModelWrapper<T>()
    data class Success<T>(val result: T) : ModelWrapper<T>() {
        private var isHandled = false
        fun getResultIfNotHandled(): T? {
            if (isHandled) return null
            isHandled = true
            return result
        }
    }
    data class Failure<T>(val info: Any? = null, val throwable: Throwable) : ModelWrapper<T>() {
        private var isHandled = false
        fun getResultIfNotHandled() : Boolean? {
            if (isHandled) return null
            isHandled = true
            return true
        }
    }
    class None<T> : ModelWrapper<T>()
}