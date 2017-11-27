package de.bringmeister.product

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductsController {

    @Autowired
    lateinit var productService: ProductService

    @GetMapping("/products", produces = arrayOf("application/json", "application/xml"))
    fun getAllProducts(): Array<ProductTto> {
        return productService.getAllProducts()
    }

}