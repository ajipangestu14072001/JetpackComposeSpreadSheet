package com.example.spreadsheetjetpackcompose.di

import com.example.spreadsheetjetpackcompose.impl.ApiHelperImpl
import com.example.spreadsheetjetpackcompose.network.ApiHelper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepoModule {
    @Binds
    abstract fun bindRemoteDS(remoteDsImpl: ApiHelperImpl): ApiHelper
}
