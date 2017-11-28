package de.bringmeister.product

import de.bringmeister.pricing.PricePerUnitEto

/**
 * Composite transport object for a ProductEto and its PricePerUnitEto objects.
 */
data class PricedProductCto(val product: ProductEto, val prices: List<PricePerUnitEto>) {

}
