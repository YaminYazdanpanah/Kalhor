package com.y2.kalhor.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.y2.kalhor.R
import com.y2.kalhor.ui.theme.red

/**
 * Favorite element to show status of audio item
 *
 * @param state
 * @param onFavoriteClicked
 */
@Composable
fun FavoriteElement(
    modifier: Modifier,
    state: Boolean,
    onFavoriteClicked: (newState: Boolean) -> Unit
) {
    Icon(
        painter = painterResource(
            id =
            if (state) R.drawable.ic_heart_filled else R.drawable.ic_heart
        ),
        tint = red,
        contentDescription = stringResource(id = R.string.contentDescription_audio_is_favorite),
        modifier = modifier
            .size(32.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = false),
            ) {
                onFavoriteClicked(!state)
            }
    )
}