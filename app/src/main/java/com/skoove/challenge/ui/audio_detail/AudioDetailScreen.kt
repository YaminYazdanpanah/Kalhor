package com.skoove.challenge.ui.audio_detail

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.skoove.challenge.component.AppTopBar
import com.skoove.challenge.component.TopBarNavigationType
import com.skoove.challenge.domain.audio.result.Audio
import com.skoove.challenge.ui.theme.red
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AudioDetailScreen(
    audio: Audio,
    audioDetailViewModel: AudioDetailViewModel,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
) {

    Scaffold(
        scaffoldState = scaffoldState,
        // top app bar content section, here showing sync with server and logout part
        topBar = {
            AppTopBar(
                navigationType = TopBarNavigationType.BACK,
                title = audio.title
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
                    .background(MaterialTheme.colors.surface),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
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
                        .padding(16.dp)
                        .fillMaxWidth()
                )


            }


        }
    }
}