package com.example.stockapp.domain.repository

import com.example.stockapp.domain.model.CompanyInfo
import com.example.stockapp.domain.model.CompanyListingModel
import com.example.stockapp.domain.model.IntradayInfo
import com.example.stockapp.utils.ResponseState
import kotlinx.coroutines.flow.Flow

interface StockRepository {
    suspend fun getCompanyListing(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<ResponseState<List<CompanyListingModel>>>

    suspend fun getIntradayInfo(
        symbol: String
    ): ResponseState<List<IntradayInfo>>

    suspend fun getCompanyInfo(symbol: String): ResponseState<CompanyInfo>
}