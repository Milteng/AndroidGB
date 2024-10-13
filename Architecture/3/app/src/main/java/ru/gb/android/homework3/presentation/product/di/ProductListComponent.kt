package ru.gb.android.homework3.presentation.product.di

import dagger.Subcomponent
import ru.gb.android.homework3.presentation.product.ProductListFragment

@Subcomponent
interface ProductListComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create():ProductListComponent//.Factory
    }

    fun inject(fragment: ProductListFragment)
}