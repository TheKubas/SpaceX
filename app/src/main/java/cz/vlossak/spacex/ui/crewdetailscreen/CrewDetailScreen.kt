package cz.vlossak.spacex.ui.crewdetailscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                modifier = Modifier.clip(shape = RoundedCornerShape(25.dp)),
                model = viewState.data.image,
                contentDescription = "Image",
                contentScale = ContentScale.Fit
            )
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(text = viewState.data.name, style = Typography.headlineMedium)
            DetailBlock(
                text = viewState.data.agency,
                string = R.string.Agency
            )
            DetailBlock(
                text = viewState.data.status,
                string = R.string.Status
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