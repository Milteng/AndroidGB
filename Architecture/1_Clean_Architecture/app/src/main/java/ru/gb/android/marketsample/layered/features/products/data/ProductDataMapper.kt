package ru.gb.android.marketsample.layered.features.products.data

class ProductDataMapper {
    fun toEntity(productDto: ProductDto): ProductEntity {
        return ProductEntity(
            id = productDto.id,
            name = productDto.name,
            image = productDto.image,
            price = productDto.price
        )
    }
}
