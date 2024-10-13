package ru.gb.android.homework3.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import ru.gb.android.homework3.data.product.ProductApiService
import ru.gb.android.homework3.data.product.ProductRemoteDataSource

@Module
object PersistenceModule {

    private val Context.appDataStore: DataStore<Preferences> by preferencesDataStore(name = "app")


    @Provides
    fun provideProductLocalDataSource(
        context: Context
    ): DataStore<Preferences> {
        return context.appDataStore

    }

    /*@Provides
    fun provideProductRemoteDataSource(productApiService: ProductApiService): ProductRemoteDataSource {
        return ProductRemoteDataSource(
            productApiService = productApiService
        )
    }*/

}