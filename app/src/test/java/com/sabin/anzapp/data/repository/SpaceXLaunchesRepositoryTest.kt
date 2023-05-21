package com.sabin.anzapp.data.repository

import com.google.common.truth.Truth.assertThat
import com.sabin.anzapp.data.api.GetSpaceXApiService
import com.sabin.anzapp.data.model.LaunchFailureDetails
import com.sabin.anzapp.data.model.LaunchSite
import com.sabin.anzapp.data.model.Rocket
import com.sabin.anzapp.data.model.SpaceXLaunchesModel
import com.sabin.anzapp.utils.AppConstant
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever


@ExperimentalCoroutinesApi
class SpaceXLaunchesRepositoryTest {

    private val apiService = mock(GetSpaceXApiService::class.java)

    private val repository = SpaceXLaunchesRepository(apiService)

    @Test
    fun getLaunches_returnsListOfLaunches() = runTest {
        // Given
        val expectedLaunches = listOf(
            SpaceXLaunchesModel(
                mission_name = "Mission 1",
                launch_date_local = "2023-05-21",
                rocket = Rocket("Falcon1"),
                launch_site = LaunchSite("Launch Site 1"),
                launch_success = true,
                launch_failure_details = LaunchFailureDetails("Ignition failure"),
                details = ""
            ),
            SpaceXLaunchesModel(
                mission_name = "Mission 2",
                launch_date_local = "2023-05-21",
                rocket = Rocket("Falcon1"),
                launch_site = LaunchSite("Launch Site 1"),
                launch_success = true,
                launch_failure_details = LaunchFailureDetails("Ignition failure"),
                details = ""
            )
        )

        whenever(apiService.getAllLaunches(AppConstant.LIMIT)).thenReturn(expectedLaunches)

        // When
        val actualLaunches = repository.getLaunches(AppConstant.LIMIT).first() // Convert SafeFlow to a list

        // Then
        assertThat(actualLaunches).isEqualTo(expectedLaunches)
    }
}