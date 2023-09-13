package cz.vlossak.spacex.ui.companyscreen

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import cz.vlossak.spacex.ui.errorscreen.ErrorScreen
import cz.vlossak.spacex.ui.loadingScreen.LoadingScreen
import cz.vlossak.spacex.ui.theme.Typography

@Composable
fun CompanyDetailsScreen(
    viewModel: CompanyDetailsScreenViewModel = hiltViewModel()
) {

    val viewState by viewModel.viewState.collectAsState()
    val isLoading = viewState.loading

    if (isLoading) {
        LoadingScreen()
    } else if (viewState.error != "") {
        ErrorScreen(errorMessage = viewState.error)
    } else {

        CompanyDetailContent(viewState)

    }
}

@Composable
private fun CompanyDetailContent(viewState: HomeScreenViewState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = viewState.data.name, style = Typography.displayLarge)
        LeadersInfo(viewState)
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Text(text = viewState.data.summary, style = Typography.bodyMedium)
        }


    }
}

@Composable
private fun LeadersInfo(viewState: HomeScreenViewState) {
    Row {
        Column(horizontalAlignment = Alignment.Start) {
            Text(text = "CEO", style = Typography.bodyLarge)
            Text(text = "COO", style = Typography.bodyLarge)
            Text(text = "CTO", style = Typography.bodyLarge)
            Text(text = "Propulsion CTO", style = Typography.bodyLarge)
        }
        Spacer(modifier = Modifier.width(40.dp))
        Column(horizontalAlignment = Alignment.End) {
            Text(text = viewState.data.ceo, style = Typography.bodyLarge)
            Text(text = viewState.data.coo, style = Typography.bodyLarge)
            Text(text = viewState.data.cto, style = Typography.bodyLarge)
            Text(text = viewState.data.cto_propulsion, style = Typography.bodyLarge)
        }
    }
}