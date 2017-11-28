package de.bringmeister.product

import com.fasterxml.jackson.dataformat.xml.XmlMapper
import org.springframework.stereotype.Component

/**
 * Data access object for products.
 * Usually Daos query a database, but in this assignment the data are read from a resource file.
 */
@Component
class ProductDao {

    /** Read resource file and parse to ProductEto with Jackson Mapper. */
    fun readProductList(): Array<ProductEto> {
        val mapper = XmlMapper()
        return mapper.readValue(
                productsFile(),
                mapper.typeFactory.constructArrayType(ProductEto::class.javaObjectType)
        )
    }

    /** Find the products resource file on the class path. */
    private fun productsFile() = javaClass.classLoader.getResource("products/products.xml")

    fun getAllProducts(): List<ProductEto> {
        return readProductList().asList()
    }


}