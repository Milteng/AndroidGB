package ru.gb.android.marketsample.layered.features.products.domain

import kotlinx.coroutines.flow.Flow
import ru.gb.android.marketsample.layered.features.products.data.ProductDomainMapper

class ConsumeProductsUseCase(
    private val productRepository: ProductRepository,
    productDomainMapper: ProductDomainMapper,

    ) {
    operator fun invoke(): Flow<List<Product>> {
        return productRepository.consumeProducts()

    }
}