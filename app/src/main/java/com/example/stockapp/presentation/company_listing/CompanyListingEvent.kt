package com.example.stockapp.presentation.company_listing

sealed class CompanyListingEvent {
    object Refresh : CompanyListingEvent()
    data class OnSearchedQueryChange(val query: String) : CompanyListingEvent()

}