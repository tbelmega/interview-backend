package de.bringmeister.product


import com.fasterxml.jackson.dataformat.xml.XmlMapper;

class ProductService {

    fun readProductList(): Array<ProductTto> {
        val mapper = XmlMapper()
        return mapper.readValue(
                productsFile(),
                mapper.typeFactory.constructArrayType(ProductTto::class.javaObjectType)
        )
    }

    private fun productsFile() = javaClass.classLoader.getResource("products/products.xml")

}
