package com.skoove.challenge.ui.component

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.skoove.challenge.component.RatingStars
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class RatingStarsTest {

    @get: Rule
    val composeRule = createComposeRule()

    @Test
    fun mustAddStarsAsNeededAndCheckDefaultStarWidth() {
        val maxRate = 8
        composeRule.setContent {
            RatingStars(
                modifier = Modifier,
                rate = 1,
                maxRate = maxRate,
                onStarClicked = {}
            )
        }
        composeRule.onAllNodesWithTag("star").assertCountEquals(maxRate)
        composeRule.onNodeWithTag("RatingStars").onChildAt(0).assertWidthIsEqualTo(22.dp)

    }
}