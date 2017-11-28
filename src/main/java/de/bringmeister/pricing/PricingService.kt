package de.bringmeister.pricing

import de.bringmeister.util.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PricingService {

    @Autowired
    lateinit var priceDao: PriceDao

    fun getPricesBySku(sku: String): List<PricePerUnitEto> {
        val result = ArrayList<PricePerUnitEto>()

        priceDao.getAllPrices().filterTo(result) { it.id == sku }

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

