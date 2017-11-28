package de.bringmeister.pricing

import com.fasterxml.jackson.databind.ObjectMapper
import de.bringmeister.util.NotFoundException
import org.springframework.stereotype.Component

@Component
class PricingService {


    fun parsePrice(priceString: String): PricePerUnitEto {
        val productPriceTto = ObjectMapper().readValue(priceString, PricePerUnitEto::class.javaObjectType)
        return productPriceTto
    }

    fun readPriceList(): Array<PricePerUnitEto> {
        val mapper = ObjectMapper()
        return mapper.readValue(
                pricesFile(),
                mapper.typeFactory.constructArrayType(PricePerUnitEto::class.javaObjectType)
        )
    }

    private fun pricesFile() = javaClass.classLoader.getResource("products/prices.json")


    fun getPricesBySku(sku: String): List<PricePerUnitEto> {
        val result = ArrayList<PricePerUnitEto>();

        readPriceList().filterTo(result) { it.id == sku }

        return result
    }

    fun findPriceBySkuAndUnit(sku: String, unit: String): PricePerUnitEto {
        for (price in getPricesBySku(sku))
            if (matches(price, unit)) return price
        throw NotFoundException()
    }

    private fun matches(price: PricePerUnitEto, unit: String) =
            price.unit.name.toLowerCase() == unit.toLowerCase()

}

