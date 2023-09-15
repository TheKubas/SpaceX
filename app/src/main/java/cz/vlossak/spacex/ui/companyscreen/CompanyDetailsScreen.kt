package cz.vlossak.spacex.ui.companyscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import cz.vlossak.spacex.R
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
private fun CompanyDetailContent(viewState: CompanyDetailViewState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = viewState.data.name, style = Typography.displayLarge)
        DetailBlock(
            text = viewState.data.ceo,
            string = R.string.CEO
        )
        DetailBlock(
            text = viewState.data.coo,
            string = R.string.COO
        )
        DetailBlock(
            text = viewState.data.cto,
            string = R.string.CTO
        )
        DetailBlock(
            text = viewState.data.ctoPropulsion,
            string = R.string.Propulsion_CTO
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row {
            Text(text = viewState.data.summary, style = Typography.bodyMedium)
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