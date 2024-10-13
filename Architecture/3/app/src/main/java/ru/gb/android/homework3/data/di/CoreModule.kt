package ru.gb.android.homework3.data.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers


@Module
object CoreModule {

    @Provides
    fun provideCoroutineDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }
}