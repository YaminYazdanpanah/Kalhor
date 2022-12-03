package com.y2.kalhor.domain.base


/**
 * Base use case
 *
 * @param ParamType
 * @param ReturnType
 * @constructor Create empty Base use case
 */
abstract class BaseUseCase<ParamType, ReturnType> {
    /**
     * Invoke
     *
     * @param param
     * @return
     */
    abstract suspend operator fun invoke(param: ParamType): ReturnType
}