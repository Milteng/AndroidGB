package ru.gb.android.marketsample.layered.common.promo.domain

import kotlinx.coroutines.flow.Flow

interface PromoRepository {
     fun consumePromos(): Flow<List<Promo>>
}