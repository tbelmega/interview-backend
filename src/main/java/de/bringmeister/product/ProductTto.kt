package de.bringmeister.product

import com.fasterxml.jackson.annotation.JsonProperty

data class ProductTto(
        @JsonProperty("id") val id: String,
        @JsonProperty("Name") val name: String,
        @JsonProperty("Description") val description: String,
        @JsonProperty("sku") val sku: String
) {

    /** Empty constructor for use by Jackson only, hence private */
    private constructor(): this("", "", "", "")

}
