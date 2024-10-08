package ru.gb.android.marketsample.layered.features.products.domain

import kotlinx.coroutines.flow.Flow


interface ProductRepository {
    fun consumeProducts(): Flow<List<Product>>
}