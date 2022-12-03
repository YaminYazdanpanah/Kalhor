package com.y2.kalhor.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.y2.kalhor.domain.audio.result.Audio
import com.y2.kalhor.ui.audio_detail.AudioDetailViewModel
import com.y2.kalhor.ui.theme.appTypography
import com.y2.kalhor.utils.extension.timeStampToDuration
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun AudioDetailItem(
    audio: Audio,
    audioDetailViewModel: AudioDetailViewModel,
    isAudioPlaying: Boolean,
    isFavorite: Boolean,
    playingTime: Float,
    duration: Int,
    rate: Int,
    onStarClicked: (rate: Int) -> Unit,
    onSliderValueChanged: (value: Float) -> Unit,
    onFavoriteClicked: (newState: Boolean) -> Unit,
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
                    onFavoriteClicked(!isFavorite)
                })
        }

        Spacer(modifier = Modifier.size(32.dp))

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
            onValueChange = {
                onSliderValueChanged(it)
            },
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
        Spacer(modifier = Modifier.size(32.dp))

        // show audio rating by stars
        RatingStars(
            modifier = Modifier.padding(8.dp),
            rate = rate,
            starSize = 64,
            onStarClicked = { index ->
                onStarClicked(index + 1)
            }
        )

    }
}
