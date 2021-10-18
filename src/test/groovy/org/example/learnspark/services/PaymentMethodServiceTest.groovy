package org.example.learnspark.services

import org.example.learnspark.common.BaseMPTest
import org.example.learnspark.model.common.PaymentMethodDTO

class PaymentMethodServiceTest extends BaseMPTest {

    private final static String GET_ALL_PAYMENT_METHODS_RESPONSE = "get_all_payment_methods_mock_mp_response.json";

    def setup() {
        PaymentMethodService.clearCache();
    }

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
            14               | 0
    }

    def "should retrieve all payment methods using cache"() {
        given:
            dummyHttpClient.mock(200, GET_ALL_PAYMENT_METHODS_RESPONSE)
        when:
        PaymentMethodService.getPaymentMethods();
            List<PaymentMethodDTO> response = PaymentMethodService.getPaymentMethods()
        then:
            response !== null
            response.size() == expectedListSize
        where:
            expectedListSize | _
            14               | 0
    }

    def "should return empty payment methods if occur exception"() {
        given:
            dummyHttpClient.mock(RuntimeException.class)
        when:
            List<PaymentMethodDTO> response = PaymentMethodService.getPaymentMethods()
        then:
            response !== null
            response.size() == expectedListSize
        where:
            expectedListSize | _
            0                | 0
    }

    def "should retrieve payment by type"() {
        given:
            dummyHttpClient.mock(200, GET_ALL_PAYMENT_METHODS_RESPONSE)
        when:
            List<PaymentMethodDTO> response = PaymentMethodService.getPaymentMethodsByType(type)
        then:
            response !== null
            response.size() == expectedListSize
        where:
            type            | expectedListSize
            "credit_card"   | 6
            "debit_card"    | 4
            "account_money" | 1
            "ticket"        | 3
            "not_exist"     | 0
    }

    def "should retrieve payment by type using cache"() {
        given:
            dummyHttpClient.mock(200, GET_ALL_PAYMENT_METHODS_RESPONSE)
        when:
            PaymentMethodService.getPaymentMethodsByType(type)
            List<PaymentMethodDTO> response = PaymentMethodService.getPaymentMethodsByType(type)
        then:
            response !== null
            response.size() == expectedListSize
        where:
            type            | expectedListSize
            "credit_card"   | 6
            "debit_card"    | 4
            "account_money" | 1
            "ticket"        | 3
            "not_exist"     | 0
    }
}
