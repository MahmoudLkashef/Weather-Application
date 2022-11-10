package com.example.weatherapp.di

import android.app.Application
import android.content.Context
import android.provider.DocumentsContract
import androidx.room.Room
import com.example.weatherapp.data.local.WeatherDatabase
import com.example.weatherapp.data.network.WeatherAPI
import com.example.weatherapp.domain.repository.RemoteRepository
import com.example.weatherapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

    @Singleton
    @Provides
    fun provideDatabaseObject(application: Application) =
        Room.databaseBuilder(
            application,
            WeatherDatabase::class.java,
            "weather_database"
        ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideWeatherDao(database: WeatherDatabase) = database.weatherDao

    @Singleton
    @Provides
    fun provideBaseURl() = Constants.BASE_URL

    @Singleton
    @Provides
    fun provideRetrofit(BASE_URL: String) =
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit)=retrofit.create(WeatherAPI::class.java)
}