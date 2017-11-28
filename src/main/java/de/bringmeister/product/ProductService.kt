package de.bringmeister.product


import de.bringmeister.util.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ProductService () {

    @Autowired
    lateinit var productDao: ProductDao

    fun findProductById(id: String): ProductEto {
        for (product in productDao.getAllProducts())
            if (product.id == id) return product
        throw NotFoundException()
    }

    fun getAllProducts(): List<ProductEto> {
        return productDao.getAllProducts()
    }

}
