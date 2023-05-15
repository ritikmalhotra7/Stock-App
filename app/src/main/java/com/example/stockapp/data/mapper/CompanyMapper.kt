package com.example.stockapp.data.mapper

import com.example.stockapp.data.local.CompanyListingEntity
import com.example.stockapp.data.remote.dto.CompanyInfoDto
import com.example.stockapp.domain.model.CompanyInfo
import com.example.stockapp.domain.model.CompanyListingModel

fun CompanyListingEntity.toCompnayListingModel(): CompanyListingModel {
    return CompanyListingModel(
        symbol, name, exchange
    )
}

fun CompanyListingModel.toCompanyListingEntity(): CompanyListingEntity {
    return CompanyListingEntity(
        symbol = symbol, name = name, exchange = exchange
    )
}
fun CompanyInfoDto.toCompanyInfo():CompanyInfo{
    return CompanyInfo(
        symbol = symbol?:"",
        description = description?:"",
        name = name?:"",
        country = country?:"",
        industry = industry?:""
    )
}