package de.bringmeister.pricing

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal
import java.util.*


/**
 * Entity transport object for a PricePerUnit entity.
 */
data class PricePerUnitEto(val id: String, val price: Price, val unit: PricingUnit) {

    /** Empty constructor for use by Jackson only, hence private */
    private constructor() : this("", Price(), PricingUnit.PIECE)

}

data class Price(val value: BigDecimal, val currency: Currency) {

    /** Empty constructor for use by Jackson only, hence private */
    constructor() : this(BigDecimal.ZERO, Currency.getInstance(Locale.GERMANY))

}

enum class PricingUnit {

    @JsonProperty("piece")
    PIECE,

    @JsonProperty("package")
    PACKAGE;
}
