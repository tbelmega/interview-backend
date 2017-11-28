package de.bringmeister.pricing

import com.fasterxml.jackson.databind.ObjectMapper


class PricingService {


    fun parsePrice(priceString: String): PricePerUnitTto {
        val productPriceTto = ObjectMapper().readValue(priceString, PricePerUnitTto::class.javaObjectType)
        return productPriceTto
    }

    fun readPriceList(): Array<PricePerUnitTto> {
        val mapper = ObjectMapper()
        return mapper.readValue(
                pricesFile(),
                mapper.typeFactory.constructArrayType(PricePerUnitTto::class.javaObjectType)
        )
    }

    private fun pricesFile() = javaClass.classLoader.getResource("products/prices.json")

}

