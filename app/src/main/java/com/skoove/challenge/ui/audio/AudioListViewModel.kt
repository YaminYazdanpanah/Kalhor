package com.skoove.challenge.ui.audio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skoove.challenge.base.ModelMutableStateFlow
import com.skoove.challenge.domain.audio.result.Audio
import com.skoove.challenge.domain.audio.usecase.GetAudioListUseCase
import com.skoove.challenge.utils.extension.failure
import com.skoove.challenge.utils.extension.loading
import com.skoove.challenge.utils.extension.success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Audio list view model
 *
 * @property getAudioListUseCase use case for getting list of audios
 * @constructor Create empty Audio list view model
 */
@HiltViewModel
class AudioListViewModel @Inject constructor(
    private val getAudioListUseCase: GetAudioListUseCase,
) : ViewModel() {

    // Mutable and Immutable variables to observe GetAudioList state
    private val _audioListState = ModelMutableStateFlow<List<Audio>>()
    val audioListState = _audioListState.asStateFlow()

    init {
        getAudioList()
    }

    /**
     * Get audio list using getAudioListUseCase and fold function
     *
     */
    fun getAudioList() {
        _audioListState.loading()
        viewModelScope.launch {
            getAudioListUseCase(Unit).fold({
                _audioListState.success(it)
            }) {
                _audioListState.failure(it)
            }
        }
    }

}
