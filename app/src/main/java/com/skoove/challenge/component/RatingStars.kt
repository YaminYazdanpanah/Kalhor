package com.skoove.challenge.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.skoove.challenge.R
import com.skoove.challenge.ui.theme.yellow


/**
 * Rating stars Component to show audio rate by filled stars
 *
 * @param modifier
 * @param rate
 * @param maxRate
 */
@Composable
fun RatingStars(modifier: Modifier, rate: Int, maxRate: Int = 5) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Start
    ) {
        repeat(maxRate) { index ->
            Icon(
                painter = painterResource(id = if (index >= rate) R.drawable.ic_star else R.drawable.ic_star_filled),
                tint = yellow,
                contentDescription = stringResource(id = R.string.contentDescription_audio_rating_start),
                modifier = Modifier
                    .size(24.dp)
                    .padding(horizontal = 1.dp)
            )
        }
    }
}