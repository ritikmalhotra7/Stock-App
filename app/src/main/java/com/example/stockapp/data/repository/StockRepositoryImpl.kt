package com.example.stockapp.data.repository

import com.example.stockapp.data.csv.CsvParser
import com.example.stockapp.data.local.StockDatabase
import com.example.stockapp.data.mapper.toCompanyInfo
import com.example.stockapp.data.mapper.toCompanyListingEntity
import com.example.stockapp.data.mapper.toCompnayListingModel
import com.example.stockapp.data.remote.StockApi
import com.example.stockapp.domain.model.CompanyInfo
import com.example.stockapp.domain.model.CompanyListingModel
import com.example.stockapp.domain.model.IntradayInfo
import com.example.stockapp.domain.repository.StockRepository
import com.example.stockapp.utils.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    private val api: StockApi,
    private val db: StockDatabase,
    private val companyListingParser: CsvParser<CompanyListingModel>,
    private val intradayInfoParser: CsvParser<IntradayInfo>
) : StockRepository {
    var dao = db.getDao
    override suspend fun getCompanyListing(
        shouldFetchFromRemote: Boolean,
        query: String
    ): Flow<ResponseState<List<CompanyListingModel>>> {
        return flow {
            emit(ResponseState.Loading())
            val localList = dao.searchCompanyListing(query)
            emit(ResponseState.Success(localList.map { it.toCompnayListingModel() }))

            val isQueryBlank = query.isBlank()
            val isDbEmpty = localList.isEmpty()
            val shouldJustLoadFromCache = !isDbEmpty && !isQueryBlank && !shouldFetchFromRemote

            if (shouldJustLoadFromCache) {
                return@flow
            }
            val remoteListing = try {
                val response = api.getListing()
                companyListingParser.parse(response.byteStream())
            } catch (e: IOException) {
                e.printStackTrace()
                emit(ResponseState.Error("Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(ResponseState.Error("Couldn't load data"))
                null
            }
            remoteListing?.let {
                dao.clearCompanyListing()
                dao.insertCompanyListing(it.map { it.toCompanyListingEntity() })
                emit(
                    ResponseState.Success(
                        dao.searchCompanyListing("").map { it.toCompnayListingModel() })
                )
            }
        }
    }

    override suspend fun getIntradayInfo(symbol: String): ResponseState<List<IntradayInfo>> {
        return try {
            val response = api.getIntradayInfo(symbol)
            val results = intradayInfoParser.parse(response.byteStream())
            ResponseState.Success(results)
        } catch (e: HttpException) {
            e.printStackTrace()
            ResponseState.Error("Couldn't load intraday data")
        } catch (e: IOException) {
            e.printStackTrace()
            ResponseState.Error("Couldn't load intraday data")
        }
    }

    override suspend fun getCompanyInfo(symbol: String): ResponseState<CompanyInfo> {
        return try {
            val response = api.getCompanyInfo(symbol)
            ResponseState.Success(response.toCompanyInfo())
        } catch (e: HttpException) {
            e.printStackTrace()
            ResponseState.Error("Couldn't load intraday data")
        } catch (e: IOException) {
            e.printStackTrace()
            ResponseState.Error("Couldn't load intraday data")
        }
    }
}