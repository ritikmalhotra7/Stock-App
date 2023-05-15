package com.example.stockapp.presentation.company_listing

import com.example.stockapp.domain.model.CompanyListingModel

data class CompanyListingState(
    val companies: List<CompanyListingModel> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
)