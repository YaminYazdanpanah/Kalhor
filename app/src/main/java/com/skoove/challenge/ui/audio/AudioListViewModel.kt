package com.skoove.challenge.ui.audio

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.neverEqualPolicy
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skoove.challenge.base.ModelMutableStateFlow
import com.skoove.challenge.domain.audio.result.Audio
import com.skoove.challenge.domain.audio.usecase.GetAudioListUseCase
import com.skoove.challenge.utils.extension.failure
import com.skoove.challenge.utils.extension.loading
import com.skoove.challenge.utils.extension.none
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
@SuppressLint("MutableCollectionMutableState")
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

    // Mutable List to show content on screen
    var audioItems by mutableStateOf<MutableList<Audio>>(
        mutableListOf(),
        policy = neverEqualPolicy()
    )

    // Populate list of audio items
    fun updateAudioItems(list: List<Audio>) {
        audioItems.clear()
        audioItems.addAll(list)
        _audioListState.none()
    }

    //Update Favorite State of audio item and non-favorite other items
    fun updateAudioItemFavoriteState(audio: Audio, state: Boolean) {
        audioItems = audioItems.apply {
            this.forEach {
                it.isFavorite = false
            }
            this[this.indexOf(audio)].isFavorite = state
        }
    }

    //Update rate stars of item
    fun updateAudioItemRating(audio: Audio, rate: Int) {
        audioItems = audioItems.apply {
            this[this.indexOf(audio)].rate = rate
        }
    }

}
