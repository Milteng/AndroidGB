package ru.gb.android.homework3.presentation.common

import javax.inject.Inject

class DiscountFormatter @Inject constructor() {
    fun format(discount: Int): String {
        return String.format("%d %%", discount)
    }
}