package com.example.stockapp.data.mapper

import com.example.stockapp.data.remote.dto.IntradayInfoDto
import com.example.stockapp.domain.model.IntradayInfo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun IntradayInfoDto.toIntradayInfo():IntradayInfo{
    val pattern = "yyy-MM-dd HH:mm:ss"
    val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
    val localDateAndTiem = LocalDateTime.parse(timestamp,formatter)
    return IntradayInfo(localDateAndTiem,close)
}