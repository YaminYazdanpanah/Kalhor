package com.skoove.challenge.data.base.di

import com.skoove.challenge.data.sample.SampleRepositoryImpl
import com.skoove.challenge.domain.sample.SampleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


/**
 * Repository module
 *
 * @constructor Create empty Repository module
 */
@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    /**
     * Bind sample repository
     *
     * @param repository
     * @return
     */
    @Binds
    abstract fun bindSampleRepository(repository: SampleRepositoryImpl): SampleRepository

}