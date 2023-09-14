package cz.vlossak.spacex.ui.companyscreen

import cz.vlossak.spacex.model.CompanyDetails

data class CompanyDetailViewState(
    val data: CompanyDetails = CompanyDetails(),
    val loading: Boolean = true,
    val error: String = ""
)