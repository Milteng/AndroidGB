package ru.gb.android.homework3.data.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.gb.android.homework3.data.promo.PromoRepository
import ru.gb.android.homework3.domain.promo.ConsumePromosUseCase
import ru.gb.android.homework3.domain.promo.PromoDomainMapper
import ru.gb.android.homework3.presentation.product.di.ProductListComponent
import ru.gb.android.homework3.presentation.promo.di.PromoCompanentDependencies
import ru.gb.android.homework3.presentation.promo.di.PromoListComponent


@Component (modules = [
    NetworkModule::class,
    PersistenceModule::class,
    CoreModule::class,
    SubComponentsModule::class,
])
interface AppComponent {

    //связываем subcomponent
    fun productListComponentFactory(): ProductListComponent.Factory
    //fun promoListComponentFactory(): PromoListComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }


}

