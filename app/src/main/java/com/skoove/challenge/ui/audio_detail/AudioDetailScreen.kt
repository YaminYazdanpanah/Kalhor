package com.skoove.challenge.ui.audio_detail

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import com.skoove.challenge.R
import com.skoove.challenge.base.ModelWrapper
import com.skoove.challenge.component.AppTopBar
import com.skoove.challenge.component.ComposableLifecycle
import com.skoove.challenge.component.FavoriteElement
import com.skoove.challenge.component.TopBarNavigationType
import com.skoove.challenge.domain.audio.result.Audio
import com.skoove.challenge.ui.MediaPlayerState
import com.skoove.challenge.ui.theme.appTypography
import com.skoove.challenge.ui.theme.red
import com.skoove.challenge.utils.extension.timeStampToDuration
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage
import kotlinx.coroutines.delay

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AudioDetailScreen(
    audio: Audio,
    audioDetailViewModel: AudioDetailViewModel,
    returnToAlertListScreen: (audio: Audio, state: Boolean) -> Unit,
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

    // handle back button of phone
    BackHandler {
        returnToAlertListScreen(audio, isFavorite)
    }

    Scaffold(
        scaffoldState = scaffoldState,
        // top app bar content section, here showing sync with server and logout part
        topBar = {
            AppTopBar(
                navigationType = TopBarNavigationType.BACK,
                title = audio.title,
                onNavigationClick = {
                    returnToAlertListScreen(audio, isFavorite)
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

            Column(
                modifier = Modifier
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            audio.audio?.let { url ->
                                audioDetailViewModel.mediaPlayerClickHandler(
                                    url
                                )
                            }
                        }, contentAlignment = Alignment.Center
                ) {
                    // Audio Cover
                    CoilImage(
                        imageModel = audio.cover,
                        contentDescription = null,
                        shimmerParams = ShimmerParams(
                            baseColor = MaterialTheme.colors.background,
                            highlightColor = MaterialTheme.colors.surface
                        ),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .fillMaxWidth()
                    )
                    //Media Player Controller Icons
                    Image(
                        painter = painterResource(
                            id = if (isAudioPlaying) R.drawable.ic_pause else R.drawable.ic_play
                        ),
                        contentDescription = stringResource(id = R.string.contentDescription_audio_is_favorite),
                        modifier = Modifier
                            .size(120.dp)

                    )

                    // audio favorite status element
                    FavoriteElement(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(16.dp),
                        state = isFavorite,
                        onFavoriteClicked = {
                            isFavorite = !isFavorite
                        })
                }

                Spacer(modifier = Modifier.size(16.dp))

                //TIMER
                Text(
                    modifier = Modifier
                        .wrapContentWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.appTypography.body1,
                    text = "${
                        playingTime.toInt().timeStampToDuration()
                    } / ${duration.timeStampToDuration()}",
                    color = MaterialTheme.colors.onSurface
                )

                //Audio Slider
                Slider(
                    value = playingTime,
                    onValueChange = { playingTime = it },
                    valueRange = 0f..duration.toFloat(),
                    onValueChangeFinished = {
                        audioDetailViewModel.seekMediaPlayer((playingTime * 1000).toInt())
                    },
                    steps = 1000,
                    colors = SliderDefaults.colors(
                        thumbColor = MaterialTheme.colors.secondary,
                        activeTickColor = MaterialTheme.colors.secondary,
                        inactiveTickColor = MaterialTheme.colors.onError,
                    )
                )

            }

        }
    }
}