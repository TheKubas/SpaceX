package cz.vlossak.spacex.ui.crewsrceen

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import cz.vlossak.spacex.R
import cz.vlossak.spacex.model.Crew
import cz.vlossak.spacex.ui.errorscreen.ErrorScreen
import cz.vlossak.spacex.ui.launchesscreen.LaunchesScreenViewModel
import cz.vlossak.spacex.ui.loadingScreen.LoadingScreen
import cz.vlossak.spacex.ui.theme.Typography

@Composable
fun CrewScreen(
    viewModel: CrewScreenViewModel = hiltViewModel(),
    navigateToDetail: (id: String) -> Unit
) {
    val viewState by viewModel.viewState.collectAsState()
    val isLoading = viewState.loading

    if (isLoading) {
        LoadingScreen()
    } else if (viewState.error != "") {
        ErrorScreen(errorMessage = viewState.error)
    } else {
        CrewList(
            viewState = viewState,
            navigateToDetail = navigateToDetail
        )
    }
}

@Composable
private fun CrewList(
    navigateToDetail: (id: String) -> Unit,
    viewState: CrewScreenViewState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 60.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = R.string.Crew), style = Typography.headlineMedium)
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(end = 10.dp, top = 10.dp)
        ) {
            Spacer(modifier = Modifier.weight(1f))
        }
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
            Row(modifier = Modifier.width(150.dp), horizontalArrangement = Arrangement.Center) {
            }
        }
        LazyColumn {
            items(viewState.data) { item ->
                CustomItem(
                    item,
                    navigateToDetail
                )
            }
        }
    }
}

@Composable
private fun CustomItem(crew: Crew, navigateToDetail: (id: String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(0.dp)
            .clickable {
                navigateToDetail(crew.id)
            },
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier
                .weight(1f)
                .aspectRatio(1f)
                .padding(15.dp)
        ) {
            Column(modifier = Modifier.padding(5.dp)) {
                AsyncImage(
                    contentScale = ContentScale.Fit,
                    model = crew.image,
                    contentDescription = "image",
                    error = painterResource(id = R.drawable.spacex)
                )
            }
        }
        Spacer(modifier = Modifier.width(5.dp))
        Column(modifier = Modifier.weight(2f)) {
            Text(
                text = crew.name,
                style = Typography.bodyLarge
            )
            Text(
                text = crew.agency,
                style = Typography.bodySmall
            )
        }
    }
    Divider()
}