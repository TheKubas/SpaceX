package cz.vlossak.spacex.ui.crewdetailscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import cz.vlossak.spacex.R
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import cz.vlossak.spacex.ui.errorscreen.ErrorScreen
import cz.vlossak.spacex.ui.loadingScreen.LoadingScreen
import cz.vlossak.spacex.ui.theme.Typography

@Composable
fun CrewDetailScreen(
    viewModel: CrewDetailViewModel = hiltViewModel()
) {
    val viewState by viewModel.viewState.collectAsState()
    val isLoading = viewState.loading

    if (isLoading) {
        LoadingScreen()
    } else if (viewState.error != "") {
        ErrorScreen(errorMessage = viewState.error)
    } else {
        DetailScreen(viewState)
    }
}

@Composable
private fun DetailScreen(viewState: CrewDetailViewState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp, 60.dp, 10.dp, 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = viewState.data.image,
            contentDescription = "Image",
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = viewState.data.name, style = Typography.headlineMedium)
        Row(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {
            Column(horizontalAlignment = Alignment.Start) {
                Text(
                    text = stringResource(id = R.string.Agency),
                    style = Typography.bodyMedium
                )
                Text(
                    text = stringResource(id = R.string.Status),
                    style = Typography.bodyMedium
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = viewState.data.agency,
                    style = Typography.bodyMedium
                )
                Text(
                    text = viewState.data.status,
                    style = Typography.bodyMedium
                )
            }
        }
    }
}