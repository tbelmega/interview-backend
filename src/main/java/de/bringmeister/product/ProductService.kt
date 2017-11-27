package de.bringmeister.product


import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Component

@Component
class ProductService {

    fun readProductList(): Array<ProductTto> {
        val mapper = XmlMapper()
        return mapper.readValue(
                productsFile(),
                mapper.typeFactory.constructArrayType(ProductTto::class.javaObjectType)
        )
    }

    private fun productsFile() = javaClass.classLoader.getResource("products/products.xml")

    fun getAllProducts(): Array<ProductTto> {
        return readProductList()
    }

}
