package com.skoove.challenge.ui

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.skoove.challenge.ui.Destinations.ALERT_LIST_ROUTE
import com.skoove.challenge.ui.audio.AudioListScreen
import kotlinx.coroutines.InternalCoroutinesApi

/**
 * Destinations used in the ([App]).
 */
const val deeplinkUri = "https://www.skoove.com"

object Destinations {
    const val ALERT_LIST_ROUTE = "alert_list"
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
            )
        }
    }
}

/**
 * Models the navigation actions in the app.
 */
class MainActions(navController: NavHostController) {

    val upPress: () -> Unit = {
        navController.navigateUp()
    }

}

