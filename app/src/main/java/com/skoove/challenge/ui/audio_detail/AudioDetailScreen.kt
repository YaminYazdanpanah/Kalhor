package com.skoove.challenge.ui.audio_detail

import android.annotation.SuppressLint
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
import androidx.compose.ui.unit.dp
import com.skoove.challenge.R
import com.skoove.challenge.base.ModelWrapper
import com.skoove.challenge.component.AppTopBar
import com.skoove.challenge.component.TopBarNavigationType
import com.skoove.challenge.domain.audio.result.Audio
import com.skoove.challenge.ui.MediaPlayerState
import com.skoove.challenge.ui.theme.red
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AudioDetailScreen(
    audio: Audio,
    audioDetailViewModel: AudioDetailViewModel,
    navigateBack: () -> Unit,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
) {

    //Load the media player with given url
    LaunchedEffect(Unit) {
        audio.audio?.let { audioDetailViewModel.initializeMediaPlayer(it) }
    }

    //Handle different states of media player controller
    val mediaPlayerState by audioDetailViewModel.mediaPlayerState.collectAsState()
    var showPlayButton by remember {
        mutableStateOf(true)
    }
    LaunchedEffect(mediaPlayerState) {
        mediaPlayerState.let {
            showPlayButton = when (it) {
                MediaPlayerState.Started -> false
                else -> true
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        // top app bar content section, here showing sync with server and logout part
        topBar = {
            AppTopBar(
                navigationType = TopBarNavigationType.BACK,
                title = audio.title,
                onNavigationClick = {
                    navigateBack()
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
                    .clickable {
                        audio.audio?.let { url -> audioDetailViewModel.mediaPlayerClickHandler(url) }
                    },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(contentAlignment = Alignment.Center){
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
                            .padding(16.dp)
                            .fillMaxWidth()
                    )
                    //Media Player Controller Icons
                    Image(
                        painter = painterResource(
                            id = if (showPlayButton) R.drawable.ic_play else R.drawable.ic_pause
                        ),
                        contentDescription = stringResource(id = R.string.contentDescription_audio_is_favorite),
                        modifier = Modifier
                            .size(120.dp)

                    )
                }
            }

        }
    }
}