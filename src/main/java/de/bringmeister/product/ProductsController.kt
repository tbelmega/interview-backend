package de.bringmeister.product

import de.bringmeister.pricing.PricePerUnitEto
import de.bringmeister.pricing.PricingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductsController {

    @Autowired
    lateinit var productService: ProductService

    @Autowired
    lateinit var pricingService: PricingService


    @GetMapping("/products", produces = arrayOf("application/json", "application/xml"))
    fun getAllProducts(): List<ProductEto> {
        return productService.getAllProducts()
    }


    @GetMapping("/products/{id}", produces = arrayOf("application/json", "application/xml"))
    fun getProductById(@PathVariable id: String): PricedProductCto {

        val product = productService.findProductById(id)
        val prices : List<PricePerUnitEto> = pricingService.getPricesBySku(product.sku)

        return PricedProductCto(product, prices)
    }

    @GetMapping("/products/{id}/prices/{unit}", produces = arrayOf("application/json", "application/xml"))
    fun getProductPriceByIdAndUnit(@PathVariable id: String, @PathVariable unit: String): PricePerUnitEto {

        val productEto = productService.findProductById(id)
        val findPriceBySkuAndUnit = pricingService.findPriceBySkuAndUnit(productEto.sku, unit)

        return findPriceBySkuAndUnit
    }



}

