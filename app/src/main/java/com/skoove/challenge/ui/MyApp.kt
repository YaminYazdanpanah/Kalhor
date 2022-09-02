package com.skoove.challenge.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.skoove.challenge.ui.theme.AppTheme
import com.skoove.challenge.ui.theme.LocalUiMode
import com.skoove.challenge.ui.theme.UiMode
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@Composable
fun MyApp() {

    AppTheme {
        ProvideWindowInsets {
            val systemUiController = rememberSystemUiController()
            var uiMode by LocalUiMode.current
            val statusBarColor = MaterialTheme.colors.surface

            SideEffect {
                when (uiMode) {
                    UiMode.Default -> systemUiController.setSystemBarsColor(
                        statusBarColor,
                        darkIcons = true
                    ) {
                        Color.Black
                    }
                    UiMode.Dark ->
                        systemUiController.setSystemBarsColor(
                            statusBarColor,
                            darkIcons = false
                        )
                }
            }

            val navController = rememberNavController()
            val scaffoldState = rememberScaffoldState()


            Scaffold(
                scaffoldState = scaffoldState,
            ) { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding))
                {
                    MyNavGraph(
                        navController = navController,
                    )
                }

            }
        }
    }
}
