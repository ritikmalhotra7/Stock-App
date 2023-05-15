package com.example.stockapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StockDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompanyListing(companyListingEntity: List<CompanyListingEntity>)

    @Query("DELETE FROM companylistingentity")
    suspend fun clearCompanyListing()

    @Query(
        """
        SELECT *
        FROM  companylistingentity
        WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%' 
        OR UPPER(:query) == symbol  
    """
    )
    //check query=tes and company name is tesla then % would check for tes in between every company name if % is at very end then it will check tes starting names
    suspend fun searchCompanyListing(query: String): List<CompanyListingEntity>
}