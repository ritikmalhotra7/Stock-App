package com.example.stockapp.di

import com.example.stockapp.data.csv.CompanyListingParser
import com.example.stockapp.data.csv.CsvParser
import com.example.stockapp.data.csv.IntradayInfoParser
import com.example.stockapp.data.repository.StockRepositoryImpl
import com.example.stockapp.domain.model.CompanyListingModel
import com.example.stockapp.domain.model.IntradayInfo
import com.example.stockapp.domain.repository.StockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsCompanyListingParser(
        companyListingParser: CompanyListingParser
    ): CsvParser<CompanyListingModel>
    @Binds
    @Singleton
    abstract fun bindsStockRepository(
        repositoryImpl: StockRepositoryImpl
    ):StockRepository

    @Binds
    @Singleton
    abstract fun bindsIntradayInfoParser(
        intradayInfoParser: IntradayInfoParser
    ):CsvParser<IntradayInfo>
}