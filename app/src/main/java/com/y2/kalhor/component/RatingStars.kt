package com.y2.kalhor.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.y2.kalhor.R
import com.y2.kalhor.ui.theme.yellow


/**
 * Rating stars Component to show audio rate by filled stars
 *
 * @param modifier
 * @param rate
 * @param maxRate
 */
@Composable
fun RatingStars(
    modifier: Modifier,
    rate: Int,
    maxRate: Int = 5,
    starSize: Int = 24,
    onStarClicked: (index: Int) -> Unit
) {
    Row(
        modifier = modifier
            .testTag("RatingStars")
        ,
    ) {
        repeat(maxRate) { index ->
            Icon(
                painter = painterResource(id = if (index >= rate) R.drawable.ic_star else R.drawable.ic_star_filled),
                tint = yellow,
                contentDescription = stringResource(id = R.string.contentDescription_audio_rating_start),
                modifier = Modifier
                    .size(starSize.dp)
                    .testTag("star")
                    .padding(horizontal = 1.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = false),
                    ) {
                        onStarClicked(index)
                    }
            )
        }
    }
}