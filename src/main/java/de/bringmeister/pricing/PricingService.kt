package de.bringmeister.pricing

import com.fasterxml.jackson.databind.ObjectMapper


class PricingService {


    fun parsePrice(priceString: String): ProductPriceTto {
        val productPriceTto = ObjectMapper().readValue(priceString, ProductPriceTto::class.javaObjectType)
        return productPriceTto
    }


}

