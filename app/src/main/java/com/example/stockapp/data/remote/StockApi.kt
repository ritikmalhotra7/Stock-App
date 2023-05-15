package com.example.stockapp.data.remote

import com.example.stockapp.data.remote.dto.CompanyInfoDto
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface StockApi {
    @GET("query?function=LISTING_STATUS")
    suspend fun getListing(
        @Query("apikey") apiKey: String = API_KEY,
    ): ResponseBody

    @GET("query?function=TIME_SERIES_INTRADAY&interval=60min&datatype=csv")
    suspend fun getIntradayInfo(
        @Query("symbol") symbol: String,
        @Query("apikey") key: String = API_KEY
    ):ResponseBody
    @GET("query?function=OVERVIEW")
    suspend fun getCompanyInfo(
        @Query("symbol") symbol:String,
        @Query("apikey") apiKey:String = API_KEY
    ):CompanyInfoDto

    companion object {
        const val API_KEY = "TCVY9XXMU4HO7WT4"
        const val BASE_URL = "https://www.alphavantage.co"
    }
}