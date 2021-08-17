package com.example.yemeksiparisapp.di

import android.content.Context
import com.example.yemeksiparisapp.data.local.LocaleDataSource
import com.example.yemeksiparisapp.data.local.SharedPrefManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ActivityRetainedComponent::class)
class LocalModule {
    @Provides
    fun sharedPrefManager(@ApplicationContext context: Context): SharedPrefManager {
        return SharedPrefManager(context)
    }

    @Provides
    fun localDataSource(sharedPrefManager: SharedPrefManager): LocaleDataSource {
        return LocaleDataSource(sharedPrefManager)
    }
}