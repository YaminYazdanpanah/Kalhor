package com.skoove.challenge.ui.audio

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue


/**
 * Screen to show list of content retrieved from api
 *
 * @param audioListViewModel
 */
@Composable
fun AudioListScreen(
    audioListViewModel: AudioListViewModel,
) {

    // Observe state of get audio list
    val audioListState by audioListViewModel.audioListState.collectAsState()

}
