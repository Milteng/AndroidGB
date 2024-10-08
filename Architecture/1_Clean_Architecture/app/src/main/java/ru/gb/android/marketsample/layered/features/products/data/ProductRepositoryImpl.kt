package ru.gb.android.marketsample.layered.features.products.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.gb.android.marketsample.layered.features.products.domain.Product
import ru.gb.android.marketsample.layered.features.products.domain.ProductRepository

class ProductRepositoryImpl(
    private val productLocalDataSource: ProductLocalDataSource,
    private val productRemoteDataSource: ProductRemoteDataSource,
    private val productDataMapper: ProductDataMapper,
    private val coroutineDispatcher: CoroutineDispatcher,
    private val productDomainMapper: ProductDomainMapper
): ProductRepository {
    private val scope = CoroutineScope(SupervisorJob() + coroutineDispatcher)

    override fun consumeProducts(): Flow<List<Product>> {
        scope.launch {
            val products = productRemoteDataSource.getProducts()
            productLocalDataSource.saveProducts(
                products.map(productDataMapper::toEntity)
            )
        }

        return productLocalDataSource.consumeProducts()
            .flowOn(coroutineDispatcher)
            .map{ products -> products.map(productDomainMapper::fromEntity) }
    }
}
