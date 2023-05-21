import com.google.common.truth.Truth.assertThat
import com.sabin.anzapp.data.api.GetSpaceXApiService
import com.sabin.anzapp.data.model.LaunchFailureDetails
import com.sabin.anzapp.data.model.LaunchSite
import com.sabin.anzapp.data.model.Rocket
import com.sabin.anzapp.data.model.SpaceXLaunchesModel
import com.sabin.anzapp.data.repository.SpaceXLaunchesRepository
import com.sabin.anzapp.framework.MainDispatcherRule
import com.sabin.anzapp.ui.base.UiState
import com.sabin.anzapp.utils.AppConstant
import com.sabin.anzapp.viewmodel.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val apiService = mock(GetSpaceXApiService::class.java)

    private val repository = SpaceXLaunchesRepository(apiService)

    private lateinit var mainViewModel: MainViewModel


    @Test
    fun `fetchAllLaunches should update uiState with Success when launches are retrieved successfully`() = runTest {

            mainViewModel = MainViewModel(repository)

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

        // Given
            val mockFlow = flowOf(expectedLaunches)

            // Mock the repository's getLaunches function
            `when`(repository.getLaunches(AppConstant.LIMIT)).thenReturn(mockFlow)

            // When
            mainViewModel.fetchAllLaunches()

            // Advance until Idle
            advanceUntilIdle()

            // Then
            val uiState = mainViewModel.uiState.value
            assertThat(uiState).isEqualTo(UiState.Success(expectedLaunches))
        }

    @Test
    fun `fetchAllLaunches should update uiState with Error when an exception occurs`() = runTest {
        // Given
        val errorMessage = "An error occurred"
        val mockFlow = flow<List<SpaceXLaunchesModel>> {
            throw Exception(errorMessage)
        }

        // Mock the repository's getLaunches function
        `when`(repository.getLaunches(AppConstant.LIMIT)).thenReturn(mockFlow)

        // When
        mainViewModel.fetchAllLaunches()

        // Then
        val uiState = mainViewModel.uiState.value
        assertThat(uiState).isEqualTo(UiState.Error(errorMessage))
    }
}
