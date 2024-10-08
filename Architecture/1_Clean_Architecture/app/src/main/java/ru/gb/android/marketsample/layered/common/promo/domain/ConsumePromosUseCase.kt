package ru.gb.android.marketsample.layered.common.promo.domain

import kotlinx.coroutines.flow.Flow
import ru.gb.android.marketsample.layered.common.promo.data.PromoDomainMapper


class ConsumePromosUseCase(
    private val promoRepository: PromoRepository,
    promoDomainMapper: PromoDomainMapper

) {
    operator fun invoke(): Flow<List<Promo>> {
        return promoRepository.consumePromos()

    }
}