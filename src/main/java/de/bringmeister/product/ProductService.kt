package de.bringmeister.product


import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import de.bringmeister.util.NotFoundException
import org.springframework.stereotype.Component

@Component
class ProductService {

    /** Read resource file and parse to ProductTto with Jackson Mapper. */
    fun readProductList(): Array<ProductTto> {
        val mapper = XmlMapper()
        return mapper.readValue(
                productsFile(),
                mapper.typeFactory.constructArrayType(ProductTto::class.javaObjectType)
        )
    }

    /** Find the products resource file on the class path. */
    private fun productsFile() = javaClass.classLoader.getResource("products/products.xml")

    fun getAllProducts(): Array<ProductTto> {
        return readProductList()
    }


    fun findProductById(id: String): ProductTto {
        for (product in readProductList())
            if (product.id == id) return product
        throw NotFoundException()
    }

}
