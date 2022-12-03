package com.y2.kalhor.data.base.di

import com.y2.kalhor.data.audio.AudioRepositoryImpl
import com.y2.kalhor.domain.audio.AudioRepository
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