package de.bringmeister.product


import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import de.bringmeister.pricing.PricePerUnitEto
import de.bringmeister.pricing.PricingService
import de.bringmeister.util.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ProductService () {

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

    fun findProductById(id: String): ProductEto {
        for (product in readProductList())
            if (product.id == id) return product
        throw NotFoundException()
    }

}
