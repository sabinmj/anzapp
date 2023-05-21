package com.sabin.anzapp.ui.launch

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sabin.anzapp.data.model.LaunchFailureDetails
import com.sabin.anzapp.data.model.LaunchSite
import com.sabin.anzapp.data.model.Rocket
import com.sabin.anzapp.data.model.SpaceXLaunchesModel
import com.sabin.anzapp.ui.base.UiState
import com.sabin.anzapp.ui.theme.AnzAppTheme
import com.sabin.anzapp.viewmodel.MainViewModel


@Composable
fun LaunchScreen(viewModel: MainViewModel = hiltViewModel(),onClick: () -> Unit) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        is UiState.Loading -> {
            // Show loading state
            ShowProgressBar()
        }
        is UiState.Success -> {
            val launches = (uiState as UiState.Success<List<SpaceXLaunchesModel>>).data
            // Use the list of launches here
            LaunchList(launches,onClick)
        }
        is UiState.Error -> {
               val error = (uiState as UiState.Error).message
            // Show error state
        }
    }
}

@Composable
fun ShowProgressBar() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator()
    }
}

/**
 * Composable function that displays a list of SpaceX launches.
 *
 * @param launches The list of SpaceX launches to be displayed.
 */
@Composable
fun LaunchList(launches: List<SpaceXLaunchesModel>, onClick: () -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(launches) { launch ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onClick.invoke() }
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    Text(
                        text = launch.mission_name,
                        style = MaterialTheme.typography.h4,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "Launch Date: ${launch.launch_date_local}",
                        style = MaterialTheme.typography.body1
                    )

                    Text(
                        text = "Launch Location: ${launch.launch_site.site_name}",
                        style = MaterialTheme.typography.body1
                    )

                    Text(
                        text = "Status: ${launch.launch_success}",
                        style = MaterialTheme.typography.body1
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun LaunchListPreview() {
    val demoLaunches = listOf(
        SpaceXLaunchesModel(
            mission_name = "Mission 1",
            launch_date_local = "2023-05-21",
            rocket = Rocket("Falcon1"),
            launch_site = LaunchSite("Launch Site 1"),
            launch_success = true,
            launch_failure_details = LaunchFailureDetails("Ignition failure"),
            details = ""
        ),
        // Add more demo launches if needed
    )
    AnzAppTheme {
        LaunchList(launches = demoLaunches, onClick = {})
    }
}

