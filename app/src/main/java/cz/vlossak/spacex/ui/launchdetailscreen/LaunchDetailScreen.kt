package cz.vlossak.spacex.ui.launchdetailscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import cz.vlossak.spacex.R
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
        ) {
            Row {
                Text(text = viewState.data.name, style = Typography.headlineMedium)
            }
            DetailBlock(
                text = viewState.data.flightNumber.toString(),
                string = R.string.Flight_number
            )
            DetailBlock(
                text = viewState.data.launchpad,
                string = R.string.Launchpad
            )
            DetailBlock(
                text = viewState.data.rocket,
                string = R.string.Rocket
            )
            DetailBlock(
                text = viewState.data.details,
                string = R.string.Details
            )
        }

    }
}

@Composable
private fun DetailBlock(text: String, string: Int) {
    Column(modifier = Modifier.padding(5.dp)) {
        Divider()
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = stringResource(id = string), style = Typography.bodySmall)
        Text(text = text, style = Typography.bodyMedium)
    }
}