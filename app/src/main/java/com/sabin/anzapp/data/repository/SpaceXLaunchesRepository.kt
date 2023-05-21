package com.sabin.anzapp.data.repository

import com.sabin.anzapp.data.api.GetSpaceXApiService
import com.sabin.anzapp.data.model.LaunchFailureDetails
import com.sabin.anzapp.data.model.LaunchSite
import com.sabin.anzapp.data.model.Rocket
import com.sabin.anzapp.data.model.SpaceXLaunchesModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

// Define the repository interface
interface SpaceXLaunchesRepository {
    fun getLaunches(limit: String): Flow<List<SpaceXLaunchesModel>>
}

// Implement the real repository using the remote API service
@Singleton
class SpaceXLaunchesRepositoryImpl @Inject constructor(private val apiService: GetSpaceXApiService) :
    SpaceXLaunchesRepository {

    override fun getLaunches(limit: String): Flow<List<SpaceXLaunchesModel>> {
        return flow {
                emit(apiService.getAllLaunches(limit))
        }
    }
}

// Implement a fake repository for testing
class FakeSpaceXLaunchesRepository : SpaceXLaunchesRepository {
    private val fakeData = listOf(
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

    override fun getLaunches(limit: String): Flow<List<SpaceXLaunchesModel>> {
        return flow {
            emit(fakeData)
        }
    }
}