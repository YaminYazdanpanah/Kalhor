package com.y2.kalhor.ui

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.y2.kalhor.domain.audio.result.Audio
import com.y2.kalhor.ui.Destinations.ALERT_LIST_ROUTE
import com.y2.kalhor.ui.audio.AudioListScreen
import com.y2.kalhor.ui.audio.AudioListViewModel
import com.y2.kalhor.ui.audio_detail.AudioDetailScreen
import kotlinx.coroutines.InternalCoroutinesApi

/**
 * Destinations used in the ([App]).
 */
const val deeplinkUri = "https://www.kalhor.com"

object Destinations {
    const val ALERT_LIST_ROUTE = "alert_list"
    const val AUDIO_DETAIL_ROUTE = "audio_detail"

}


@SuppressLint("UnrememberedGetBackStackEntry")
@InternalCoroutinesApi
@Composable
fun MyNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = ALERT_LIST_ROUTE,
) {

    val actions = remember(navController) { MainActions(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(
            ALERT_LIST_ROUTE,
            deepLinks = listOf(navDeepLink { uriPattern = deeplinkUri }),

            ) {
            AudioListScreen(
                audioListViewModel = hiltViewModel(),
                navigateToDetail = actions.navigateToAudioDetail,
            )
        }
        composable(
            Destinations.AUDIO_DETAIL_ROUTE,
            deepLinks = listOf(navDeepLink { uriPattern = "$deeplinkUri/audio" }),
            arguments = listOf(
                navArgument("audio") {
                    nullable = true
                    type = NavType.ParcelableType(Audio::class.java)
                },
            )

        ) {
            val audioListViewModel: AudioListViewModel? = navController
                .previousBackStackEntry?.let {
                    hiltViewModel(it)
                }

            AudioDetailScreen(
                audio = navController.previousBackStackEntry?.savedStateHandle?.get<Audio>(
                    "audio"
                ) ?: Audio(),
                returnToAlertListScreen = { audio, state , rate ->
                    audioListViewModel?.updateAudioItemFavoriteState(audio, state)
                    audioListViewModel?.updateAudioItemRating(audio, rate)
                    navController.navigateUp()
                },
                audioDetailViewModel = hiltViewModel(),
            )
        }
    }
}

/**
 * Models the navigation actions in the app.
 */
class MainActions(navController: NavHostController) {

    val navigateToAudioDetail: (audio: Audio) -> Unit = { audio ->
        navController.currentBackStackEntry?.savedStateHandle?.apply {
            set("audio", audio)
        }
        navController.navigate(Destinations.AUDIO_DETAIL_ROUTE)
    }

}

