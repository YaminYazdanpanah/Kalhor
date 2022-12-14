package com.y2.kalhor.ui

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * Media player controller
 *
 * @constructor Create empty Media player controller
 */
abstract class MediaPlayerController : ViewModel() {

    // object of media player
    private val mediaPlayer = MediaPlayer()

    //sealed class for handling different media player states
    private val _mediaPlayerState =
        MutableStateFlow<MediaPlayerState>(MediaPlayerState.None)
    val mediaPlayerState = _mediaPlayerState.asStateFlow()

    /**
     * Media player click handler
     *
     * @param url
     */
    fun mediaPlayerClickHandler(url: String) {
        when (mediaPlayerState.value) {
            MediaPlayerState.Started -> pauseMediaPlayer()
            MediaPlayerState.Paused, MediaPlayerState.Initialized, MediaPlayerState.Finished -> startMediaPlayer()
            else -> {
                initializeMediaPlayer(url)
            }
        }
    }

    /**
     * Initialize media player
     *
     * @param url
     */
    fun initializeMediaPlayer(url: String) {
        try {
            mediaPlayer.setAudioAttributes(
                AudioAttributes
                    .Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()
            )
            // on below line we are setting audio source
            // as audio url on below line.
            mediaPlayer.setDataSource(url)

            // on below line we are preparing
            // our media player.
            mediaPlayer.prepare()

            _mediaPlayerState.update { MediaPlayerState.Initialized }

        } catch (e: Exception) {

            // on below line we are
            // handling our exception.
            e.printStackTrace()
        }
    }

    /**
     * Start media player
     *
     */
    fun startMediaPlayer() {
        try {
            // on below line we are starting
            // our media player.
            mediaPlayer.start()
            _mediaPlayerState.update { MediaPlayerState.Started }
        } catch (e: Exception) {
            // on below line we are
            // handling our exception.
            e.printStackTrace()
        }
    }

    /**
     * Pause media player
     *
     */
    fun pauseMediaPlayer() {
        // on below line we are pausing
        // our media player.
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            _mediaPlayerState.update { MediaPlayerState.Paused }
        }
    }


    /**
     * Release media player
     *
     */
    fun releaseMediaPlayer() {
        // on below line we are stopping and releasing
        // our media player.
        mediaPlayer.stop()
        mediaPlayer.release()
        _mediaPlayerState.update { MediaPlayerState.None }

    }

    /**
     * Reset media player
     *
     */
    fun resetMediaPlayer() {
        // on below line we are stopping and resetting
        // our media player.
        _mediaPlayerState.update { MediaPlayerState.Finished }
    }

    /**
     * Seek media player
     *
     * @param newPosition
     */
    fun seekMediaPlayer(newPosition: Int) {
        mediaPlayer.seekTo(newPosition)
    }
}

/**
 * Media player state
 *
 * @constructor Create empty Media player state
 */
sealed class MediaPlayerState() {
    object None : MediaPlayerState()
    object Initialized : MediaPlayerState()
    object Started : MediaPlayerState()
    object Paused : MediaPlayerState()
    object Finished : MediaPlayerState()
}