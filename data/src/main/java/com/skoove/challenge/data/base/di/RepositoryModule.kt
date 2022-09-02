package com.skoove.challenge.data.base.di

import com.skoove.challenge.data.audio.AudioRepositoryImpl
import com.skoove.challenge.domain.audio.AudioRepository
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
     * Bind audio repository
     *
     * @param repository
     * @return
     */
    @Binds
    abstract fun bindAudioRepository(repository: AudioRepositoryImpl): AudioRepository

}