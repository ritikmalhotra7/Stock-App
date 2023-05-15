package com.example.stockapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.stockapp.data.local.StockDao
import com.example.stockapp.data.local.StockDatabase
import com.example.stockapp.data.remote.StockApi
import com.example.stockapp.data.remote.StockApi.Companion.BASE_URL
import com.example.stockapp.data.repository.StockRepositoryImpl
import com.example.stockapp.domain.repository.StockRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StockModule {
    @Provides
    @Singleton
    fun provideApi(): StockApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create()

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext ctx: Context): StockDatabase = Room.databaseBuilder(
        ctx,StockDatabase::class.java,"stockdb.db"
    ).build()
}