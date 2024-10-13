package ru.gb.android.homework3.data.di

import dagger.Module
import ru.gb.android.homework3.presentation.product.di.ProductListComponent
import ru.gb.android.homework3.presentation.promo.di.PromoListComponent


@Module(subcomponents = [
    ProductListComponent::class,
    //PromoListComponent::class,
])
object SubComponentsModule {
}