package com.example.yemeksiparisapp.di

import com.example.yemeksiparisapp.BuildConfig
import com.example.yemeksiparisapp.data.remote.FoodApiService
import com.example.yemeksiparisapp.data.remote.RemoteDataSource
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun provideFoodApiService(retrofit: Retrofit) : FoodApiService{
        return retrofit.create(FoodApiService::class.java)
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(" ")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.interceptors().add(HttpLoggingInterceptor().apply {
            level = if(BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        })
        return builder.build()
    }

    /*@Provides
    fun provideEndpoint(): Endpoint {

        return Endpoint("https://dist-learn.herokuapp.com/")
    }*/

    @Provides
    fun provideRemoteDataSource(
        apiService: FoodApiService,
    ): RemoteDataSource {
        return RemoteDataSource(apiService)
    }
}