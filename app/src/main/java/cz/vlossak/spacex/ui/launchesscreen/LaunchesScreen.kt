package cz.vlossak.spacex.ui.launchesscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import cz.vlossak.spacex.R
import cz.vlossak.spacex.extension.formatLocalDateTime
import cz.vlossak.spacex.model.LaunchesDetail
import cz.vlossak.spacex.ui.errorscreen.ErrorScreen
import cz.vlossak.spacex.ui.loadingScreen.LoadingScreen
import cz.vlossak.spacex.ui.theme.Typography
import androidx.compose.foundation.lazy.items
import androidx.navigation.NavController

@Composable
fun LaunchesScreen(
    viewModel: LaunchesScreenViewModel = hiltViewModel(),
    navigateToDetail: (id: String) -> Unit,
    navController: NavController
) {
    val viewState by viewModel.viewState.collectAsState()
    val isLoading = viewState.loading

    if (isLoading) {
        LoadingScreen()
    } else if (viewState.error != "") {
        ErrorScreen(errorMessage = viewState.error)
    } else {
        LaunchesList(viewState, navigateToDetail)
    }
}

@Composable
private fun LaunchesList(viewState: LaunchesScreenViewState, navigateToDetail: (id: String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 60.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
            Row(modifier = Modifier.width(150.dp), horizontalArrangement = Arrangement.Center) {
            }
        }
        LazyColumn() {
            items(viewState.data) { item ->
                CustomItem(item, navigateToDetail)
            }
        }
    }
}

@Composable
private fun CustomItem(launches: LaunchesDetail, navigateToDetail: (id: String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(0.dp)
            .clickable {
                navigateToDetail(launches.id)
            },
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier
                .weight(1f)
                .aspectRatio(1f)
                .padding(15.dp),
            //shape = CircleShape,
        ) {
            if (launches.patchSmall == "") {
                Image(
                    painterResource(id = R.drawable.spacex),
                    contentDescription = "image_placeholder",
                )
            } else {
                AsyncImage(
                    contentScale = ContentScale.FillBounds,
                    model = launches.patchSmall,
                    contentDescription = "obrazek"
                )
            }


        }
        Spacer(modifier = Modifier.width(5.dp))
        Column(modifier = Modifier.weight(2f)) {
            Text(
                text = launches.name,
                style = Typography.bodyLarge
            )
            Text(
                text = formatLocalDateTime(dateTime = launches.date_utc),
                style = Typography.bodySmall
            )
        }
        Spacer(modifier = Modifier.width(5.dp))
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "#${launches.flight_number}")
        }
    }
    Divider()
}
