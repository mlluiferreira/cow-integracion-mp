package org.example.learnspark.services

import org.example.learnspark.common.BaseMPTest
import org.example.learnspark.model.common.PaymentMethodDTO

class PaymentMethodServiceTest extends BaseMPTest {

    private final static String GET_ALL_PAYMENT_METHODS_RESPONSE = "get_all_payment_methods_mock_mp_response.json";

    def "should retrieve all payment methods"() {
        given:
            dummyHttpClient.mock(200, GET_ALL_PAYMENT_METHODS_RESPONSE)
        when:
            List<PaymentMethodDTO> response = PaymentMethodService.getPaymentMethods()
        then:
            response !== null
            response.size() == expectedListSize
        where:
            expectedListSize | _
            14               | 1
    }
}
