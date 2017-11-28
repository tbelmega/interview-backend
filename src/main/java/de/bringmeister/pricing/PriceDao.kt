package de.bringmeister.pricing

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component

/**
 * Data access object for prices.
 * Usually Daos query a database, but in this assignment the data are read from a resource file.
 */
@Component
class PriceDao {

    fun parsePrice(priceString: String): PricePerUnitEto {
        val productPriceTto = ObjectMapper().readValue(priceString, PricePerUnitEto::class.javaObjectType)
        return productPriceTto
    }

    fun getAllPrices(): List<PricePerUnitEto> {
        return readPricesList().asList()
    }

    private fun readPricesList(): Array<PricePerUnitEto> {
        val mapper = ObjectMapper()
        return mapper.readValue(
                pricesFile(),
                mapper.typeFactory.constructArrayType(PricePerUnitEto::class.javaObjectType)
        )
    }

    private fun pricesFile() = javaClass.classLoader.getResource("products/prices.json")

}