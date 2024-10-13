package ru.gb.android.homework3.presentation.promo.di
import dagger.Component
import dagger.Subcomponent
import ru.gb.android.homework3.data.di.AppComponent
import ru.gb.android.homework3.data.promo.PromoRepository
import ru.gb.android.homework3.domain.promo.PromoDomainMapper
import ru.gb.android.homework3.presentation.promo.PromoListFragment

interface PromoCompanentDependencies{
    fun getPromoRepository(): PromoRepository
    fun getPromoDomainMapper(): PromoDomainMapper
}


@Component(dependencies = [PromoCompanentDependencies::class])
    interface PromoListComponent {
        @Component.Factory
        interface Factory {
            fun create(dependencies:PromoCompanentDependencies):PromoListComponent//.Factory
        }

        fun inject(fragment: PromoListFragment)

}



