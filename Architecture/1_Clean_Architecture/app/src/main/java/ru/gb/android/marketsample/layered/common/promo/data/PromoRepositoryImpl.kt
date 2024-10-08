package ru.gb.android.marketsample.layered.common.promo.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.gb.android.marketsample.layered.common.promo.domain.Promo
import ru.gb.android.marketsample.layered.common.promo.domain.PromoRepository

class PromoRepositoryImpl(
    private val promoLocalDataSource: PromoLocalDataSource,
    private val promoRemoteDataSource: PromoRemoteDataSource,
    private val promoDataMapper: PromoDataMapper,
    private val coroutineDispatcher: CoroutineDispatcher,
    private val promoDomainMapper:PromoDomainMapper
) : PromoRepository {
    private val scope = CoroutineScope(SupervisorJob() + coroutineDispatcher)

    override fun consumePromos(): Flow<List<Promo>> {
        scope.launch {
            val promos = promoRemoteDataSource.getPromos()
            promoLocalDataSource.savePromos(
                promos.map(promoDataMapper::toEntity)
            )
        }

        return promoLocalDataSource.consumeProducts()
            .flowOn(coroutineDispatcher)
            .map{ promos -> promos.map(promoDomainMapper::fromEntity)}
    }
}
