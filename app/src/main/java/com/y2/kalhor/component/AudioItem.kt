package com.y2.kalhor.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.y2.kalhor.domain.audio.result.Audio
import com.y2.kalhor.ui.theme.appTypography
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage

/**
 * Audio item
 *
 * @param audio
 */
@Composable
fun AudioItem(audio: Audio, onFavoriteClicked: (newState: Boolean) -> Unit , onItemClicked: () -> Unit) {

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .border(1.dp, MaterialTheme.colors.onBackground)
            .background(MaterialTheme.colors.surface)
            .clickable{
                onItemClicked()
            }
    ) {
        Box() {
            // audio cover image
            CoilImage(
                imageModel = audio.cover,
                contentDescription = stringResource(id = R.string.contentDescription_audio_cover),
                shimmerParams = ShimmerParams(
                    baseColor = MaterialTheme.colors.background,
                    highlightColor = MaterialTheme.colors.surface
                ),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(3f / 2f)
                    .fillMaxWidth()
            )
            // show audio rating by stars
            RatingStars(modifier = Modifier.padding(8.dp), audio.rate , onStarClicked = {})
        }
        // title and favorite section
        Row(
            modifier = Modifier
                .fillMaxWidth(0.65f)
                .align(Alignment.End)
                .padding(horizontal = 8.dp)
                .heightIn(64.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // audio title
            Text(
                modifier = Modifier
                    .wrapContentWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.appTypography.body1,
                text = audio.title.toString(),
                color = MaterialTheme.colors.onSurface
            )
            // audio favorite status element
            FavoriteElement(modifier = Modifier , state = audio.isFavorite, onFavoriteClicked = { onFavoriteClicked(it) })
        }
    }
}

