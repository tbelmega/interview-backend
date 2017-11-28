package de.bringmeister.product

import org.junit.Test

class ProductDaoTest {

    /**
     * This test depends on the data in the resource file products/products.xml
     */
    @Test
    fun testThat_ProductsFile_IsParsedCorrectly() {
        // act
        val products = ProductDao().readProductList()

        // assert
        assert(products.size >= 2)

        val banana = products[0]
        assert(banana.id == "43b105a0-b5da-401b-a91d-32237ae418e4")
        assert(banana.name == "Banana")
        assert(banana.description.contains("<p>The <b>banana</b> is an edible <a href"))
        assert(banana.sku == "BA-01")

    }

}