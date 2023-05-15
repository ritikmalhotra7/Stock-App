package com.example.stockapp.data.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "companylistingentity")
data class CompanyListingEntity(
    @ColumnInfo(name = "symbol") val symbol: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "exchange") val exchange: String,
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) val id: Int? = null
)
