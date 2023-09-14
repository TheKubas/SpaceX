package cz.vlossak.spacex.ui.launchdetailscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import cz.vlossak.spacex.ui.errorscreen.ErrorScreen
import cz.vlossak.spacex.ui.loadingScreen.LoadingScreen
import cz.vlossak.spacex.ui.theme.Typography

@Composable
fun LaunchDetailScreen(
    viewModel: LaunchDetailScreenViewModel = hiltViewModel()
) {
    val viewState by viewModel.viewState.collectAsState()
    val isLoading = viewState.loading

    if (isLoading) {
        LoadingScreen()
    } else if (viewState.error != "") {
        ErrorScreen(errorMessage = viewState.error)
    } else {
        LaunchDetailContent(viewState = viewState)
    }
}

@Composable
private fun LaunchDetailContent(viewState: LaunchDetailScreenViewState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp, 70.dp, 10.dp, 10.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .weight(1f)
        ) {
            AsyncImage(model = viewState.data.large, contentDescription = "image")
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Text(text = viewState.data.name, style = Typography.headlineMedium)
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(text = "Flight number:")
                    Text(text = "Launchpad id:")
                    Text(text = "Rocket id:")

                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(text = viewState.data.flight_number.toString())
                    Text(text = viewState.data.launchpad)
                    Text(text = viewState.data.rocket)
                }
            }
            Row {
                Text(text = viewState.data.details)
            }
        }

    }
}