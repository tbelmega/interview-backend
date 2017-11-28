package de.bringmeister.product

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Entity transport object for a Product entity.
 * (Persistence is out of scope for this assessment, so there are no entity classes.
 *  Nevertheless, a product is logically an entity of this system.)
 */
data class ProductEto(
        @JsonProperty("id") val id: String,
        @JsonProperty("Name") val name: String,
        @JsonProperty("Description") val description: String,
        @JsonProperty("sku") val sku: String
) {

    /** Empty constructor for use by Jackson only, hence private */
    private constructor(): this("", "", "", "")

}
