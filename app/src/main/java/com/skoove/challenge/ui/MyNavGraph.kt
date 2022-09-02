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
import com.skoove.challenge.ui.Destinations.SPLASH_ROUTE
import com.skoove.challenge.ui.splash.SplashScreen
import kotlinx.coroutines.InternalCoroutinesApi

/**
 * Destinations used in the ([App]).
 */
const val deeplinkUri = "https://www.skoove.com"

object Destinations {
    const val SPLASH_ROUTE = "splash"
}


@SuppressLint("UnrememberedGetBackStackEntry")
@InternalCoroutinesApi
@Composable
fun MyNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = SPLASH_ROUTE,
) {

    val actions = remember(navController) { MainActions(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(
            Destinations.SPLASH_ROUTE,
            deepLinks = listOf(navDeepLink { uriPattern = deeplinkUri }),

            ) {
            SplashScreen(
                splashViewModel = hiltViewModel(),
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

