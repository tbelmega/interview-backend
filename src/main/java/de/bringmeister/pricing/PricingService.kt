package de.bringmeister.pricing

import com.fasterxml.jackson.databind.ObjectMapper


class PricingService {


    fun parsePrice(priceString: String): ProductPriceTto {
        val productPriceTto = ObjectMapper().readValue(priceString, ProductPriceTto::class.javaObjectType)
        return productPriceTto
    }

    fun readPriceList(): Array<ProductPriceTto> {
        val mapper = ObjectMapper()
        return mapper.readValue(
                pricesFile(),
                mapper.typeFactory.constructArrayType(ProductPriceTto::class.javaObjectType)
        )
    }

    private fun pricesFile() = javaClass.classLoader.getResource("products/prices.json")

}

