package de.bringmeister.product

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductsController {

    @Autowired
    lateinit var productService: ProductService

    @GetMapping("/products", produces = arrayOf("application/json", "application/xml"))
    fun getAllProducts(): Array<ProductTto> {
        return productService.getAllProducts()
    }


    @GetMapping("/products/{id}", produces = arrayOf("application/json", "application/xml"))
    fun getProductById(@PathVariable id: String): ProductTto {
        return productService.findProductById(id)
    }



}