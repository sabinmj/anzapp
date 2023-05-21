package com.sabin.anzapp.data.repository

import com.google.common.truth.Truth.assertThat
import com.sabin.anzapp.data.model.LaunchFailureDetails
import com.sabin.anzapp.data.model.LaunchSite
import com.sabin.anzapp.data.model.Rocket
import com.sabin.anzapp.data.model.SpaceXLaunchesModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


@ExperimentalCoroutinesApi
class SpaceXLaunchesRepositoryTest {

    private lateinit var repository: SpaceXLaunchesRepository

    @Before
    fun setUp() {
        // Initialize the repository with the fake implementation
        repository = FakeSpaceXLaunchesRepository()
    }

    @Test
    fun `getLaunches returns the expected launches`() = runTest {
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

        // When
        val launches = mutableListOf<SpaceXLaunchesModel>()
        repository.getLaunches("3").collect { data ->
            launches.addAll(data)
        }

        // Then
        assertThat(launches).isEqualTo(expectedLaunches)
    }
}