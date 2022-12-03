package com.y2.kalhor.ui.audio_detail

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import com.y2.kalhor.component.*
import com.y2.kalhor.domain.audio.result.Audio
import com.y2.kalhor.ui.MediaPlayerState
import com.y2.kalhor.ui.theme.red
import kotlinx.coroutines.delay

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AudioDetailScreen(
    audio: Audio,
    audioDetailViewModel: AudioDetailViewModel,
    returnToAlertListScreen: (audio: Audio, state: Boolean, rate: Int) -> Unit,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
) {

    //Load the media player with given url
    LaunchedEffect(Unit) {
        audio.audio?.let { audioDetailViewModel.initializeMediaPlayer(it) }
    }

    //Handle different states of media player controller
    val mediaPlayerState by audioDetailViewModel.mediaPlayerState.collectAsState()
    var isAudioPlaying by remember {
        mutableStateOf(true)
    }
    LaunchedEffect(mediaPlayerState) {
        mediaPlayerState.let {
            isAudioPlaying = when (it) {
                MediaPlayerState.Started -> true
                else -> false
            }
        }
    }

    // Audio duration time
    val duration by remember { mutableStateOf(audio.totalDurationMs / 1000) }
    // Audio playing Time
    var playingTime by remember {
        mutableStateOf(0f)
    }
    LaunchedEffect(key1 = playingTime, key2 = isAudioPlaying) {
        if (isAudioPlaying) {
            if (playingTime <= (duration - 1)) {
                delay(1000)
                playingTime += 1
            } else {
                audioDetailViewModel.resetMediaPlayer()
                playingTime = 0f
            }
        }

    }

    // handle media player on different compose lifecycle states
    ComposableLifecycle { _, event ->
        if (event == Lifecycle.Event.ON_PAUSE) {
            audioDetailViewModel.pauseMediaPlayer()
        } else if (event == Lifecycle.Event.ON_DESTROY) {
            audioDetailViewModel.releaseMediaPlayer()
        }
    }

    //handle is Favorite status of audio item
    var isFavorite by remember {
        mutableStateOf(audio.isFavorite)
    }

    //handle rate stars of audio item
    var rate by remember {
        mutableStateOf(audio.rate)
    }

    // handle back button of phone
    BackHandler {
        returnToAlertListScreen(audio, isFavorite, rate)
    }

    Scaffold(
        scaffoldState = scaffoldState,
        // top app bar content section, here showing sync with server and logout part
        topBar = {
            AppTopBar(
                navigationType = TopBarNavigationType.BACK,
                title = audio.title,
                onNavigationClick = {
                    returnToAlertListScreen(audio, isFavorite, rate)
                },

                )
        },
        // Customizing snack bar Host to for error messages
        snackbarHost = {
            // reuse default SnackHost to have default animation and timing handling
            SnackbarHost(it) { data ->
                // custom snack with the custom colors
                Snackbar(
                    backgroundColor = red,
                    contentColor = MaterialTheme.colors.onError,
                    snackbarData = data
                )
            }
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface)
        ) {
            // audio detail item
            AudioDetailItem(
                audio = audio,
                audioDetailViewModel = audioDetailViewModel,
                isAudioPlaying = isAudioPlaying,
                isFavorite = isFavorite,
                playingTime = playingTime,
                duration = duration,
                rate = rate,
                onStarClicked = { newRate ->
                    rate = newRate
                },
                onSliderValueChanged = { value ->
                    playingTime = value
                },
                onFavoriteClicked = { newState ->
                    isFavorite = newState
                }
            )

        }
    }
}