package com.skoove.challenge.ui.audio

import com.skoove.challenge.base.ModelWrapper
import com.skoove.challenge.domain.audio.result.Audio
import com.skoove.challenge.domain.audio.usecase.GetAudioListUseCase
import com.skoove.challenge.rule.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class AudioListViewModelTest {


    private lateinit var mockGetAudioListUseCase : GetAudioListUseCase
    private lateinit var audioListViewModel: AudioListViewModel

    @get: Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setup() {
        mockGetAudioListUseCase = mockk(relaxed = true)
        audioListViewModel =
        AudioListViewModel(
            mockGetAudioListUseCase,
        )
    }


    @Test
    fun getAudioList_isSuccess_updatesAudioListStateWithModelWrapperSuccess() = runTest {
        coEvery {
            mockGetAudioListUseCase.invoke(Unit)
        } returns Result.success(
            listOf(
                Audio(
                    title = "Oceansound",
                    audio = "https://raw.githubusercontent.com/Learnfield-GmbH/CodingChallange/master/shared/simple%20audio%20player/data/Oceansound.mp3",
                    cover = "https://raw.githubusercontent.com/Learnfield-GmbH/CodingChallange/master/shared/simple%20audio%20player/data/Oceansound.png",
                    totalDurationMs = 14448
                )
            )
        )
        audioListViewModel.getAudioList()
        val response = audioListViewModel.audioListState.first()

        Assert.assertTrue(response is ModelWrapper.Success)
        Assert.assertNotNull((response as ModelWrapper.Success).result)
    }

}