package com.skoove.challenge.ui.audio

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.skoove.challenge.R
import com.skoove.challenge.base.ModelWrapper
import com.skoove.challenge.component.AppTopBar
import com.skoove.challenge.component.AudioItem
import com.skoove.challenge.component.TopBarNavigationType
import com.skoove.challenge.domain.audio.result.Audio
import com.skoove.challenge.ui.theme.red


/**
 * Screen to show list of content retrieved from api
 *
 * @param audioListViewModel
 */
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AudioListScreen(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    audioListViewModel: AudioListViewModel,
    navigateToDetail: (audio: Audio) -> Unit
) {

    // Observe state of get audio list
    val audioListState by audioListViewModel.audioListState.collectAsState()

    // error message to show when cannot fetch data
    val errorMessage = stringResource(id = R.string.error)

    //Get Audio list on page initialized
    LaunchedEffect(Unit) {
        audioListViewModel.getAudioList()
    }

    // run suspend functions in the scope of a composable and handle states
    LaunchedEffect(audioListState) {
        (audioListState as? ModelWrapper.Success)?.let {
            (audioListState as? ModelWrapper.Success)?.result?.let { list ->
                // populate audio item list with content received from data layer
                audioListViewModel.updateAudioItems(list)
            }
        }
        (audioListState as? ModelWrapper.Failure)?.let { result ->
            // show error as an snack bar in case of error
            scaffoldState.snackbarHostState.showSnackbar(
                message = errorMessage,
            )
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        // top app bar content section, here showing sync with server and logout part
        topBar = {
            AppTopBar(
                navigationType = TopBarNavigationType.NONE,
                title = stringResource(id = R.string.audio_list_screen_app_top_bar_title)
            )
        },
        // Customizing snack bar Host to for error messages
        snackbarHost = {
            // reuse default SnackHost to have default animation and timing handling
            SnackbarHost(it) { data ->
                // custom snack with the custom colors
                Snackbar(
                    backgroundColor = red,
                    contentColor = MaterialTheme.colors.onError,
                    snackbarData = data
                )
            }
        },
    ) {
        val swipeRefreshState = rememberSwipeRefreshState(false)
        SwipeRefresh(state = swipeRefreshState, onRefresh = {
            // on wipe refresh, read the audio content
            audioListViewModel.getAudioList()
        }) {
            //Scrollable column
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.surface)
            ) {
                // show Linear Progress Indicator when audio List state is in Loading state
                item {
                    if (audioListState is ModelWrapper.Loading) {
                        LinearProgressIndicator(
                            modifier = Modifier.fillMaxWidth(),
                            color = MaterialTheme.colors.primary
                        )
                    }
                }
                // show list of Audio items fetched
                items(audioListViewModel.audioItems) { audio ->
                    // show Audio item
                    AudioItem(
                        audio = audio,
                        // handle on favorite clicked, make item favorite and all other items non-favorite
                        onFavoriteClicked = { state ->
                            audioListViewModel.updateAudioItemFavoriteState(
                                audio,
                                state
                            )
                        },
                        // open audio detail screen on audio item clicked
                        onItemClicked = {
                            navigateToDetail(audio)
                        }
                    )
                }
            }
        }
    }
}
