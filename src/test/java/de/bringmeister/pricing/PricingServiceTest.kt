package de.bringmeister.pricing

import org.junit.Test
import java.math.BigDecimal
import java.util.*

class PricingServiceTest {

    val priceString1 = "{\n" +
            "    \"id\": \"BA-01\",\n" +
            "    \"price\": {\n" +
            "      \"value\": 2.45,\n" +
            "      \"currency\": \"EUR\"\n" +
            "    },\n" +
            "    \"unit\": \"piece\"\n" +
            "  }"

    val priceString2 = "{\n" +
            "    \"id\": \"TO-02\",\n" +
            "    \"price\": {\n" +
            "      \"value\": 4.01,\n" +
            "      \"currency\": \"EUR\"\n" +
            "    },\n" +
            "    \"unit\": \"package\"\n" +
            "  }"

    @Test
    fun testThat_Price1_IsParsedCorrectly() {
        // act
        val price1 = PricingService().parsePrice(priceString1)

        // assert
        assert(price1.id == "BA-01")
        assert(price1.unit == PricingUnit.PIECE)
        assert(price1.price.value == BigDecimal.valueOf(2.45))
        assert(price1.price.currency == Currency.getInstance("EUR"))
    }

    @Test
    fun testThat_Price2_HasId_TO02() {
        // act
        val price2 = PricingService().parsePrice(priceString2)

        // assert
        assert(price2.id == "TO-02")
        assert(price2.unit == PricingUnit.PACKAGE)
        assert(price2.price.value == BigDecimal.valueOf(4.01))
        assert(price2.price.currency == Currency.getInstance("EUR"))
    }


}