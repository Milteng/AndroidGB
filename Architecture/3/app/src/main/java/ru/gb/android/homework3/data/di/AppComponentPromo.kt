package ru.gb.android.homework3.data.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.gb.android.homework3.data.promo.PromoRepository
import ru.gb.android.homework3.domain.promo.PromoDomainMapper
import ru.gb.android.homework3.presentation.promo.di.PromoCompanentDependencies

@Component(modules = [
    NetworkModule::class,
    PersistenceModule::class,
    CoreModule::class,

])
interface AppComponentPromo: PromoCompanentDependencies {
    override fun getPromoRepository(): PromoRepository
    override fun getPromoDomainMapper(): PromoDomainMapper

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponentPromo
    }
}