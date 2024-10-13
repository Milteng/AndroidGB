package ru.gb.android.homework3.data.product

import javax.inject.Inject

class ProductDataMapper @Inject constructor() {
    fun toEntity(productDto: ProductDto): ProductEntity {
        return ProductEntity(
            id = productDto.id,
            name = productDto.name,
            image = productDto.image,
            price = productDto.price
        )
    }
}
