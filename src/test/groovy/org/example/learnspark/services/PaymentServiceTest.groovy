package org.example.learnspark.services

import com.mercadopago.resources.Payment
import org.example.learnspark.common.BaseMPTest
import org.example.learnspark.exception.DataNotFoundException
import org.example.learnspark.exception.ErrorCreatePaymentException
import org.example.learnspark.model.common.AddressDTO
import org.example.learnspark.model.common.PayerDTO
import org.example.learnspark.model.payment.CreatePaymentDTO
import org.example.learnspark.util.Parser

class PaymentServiceTest extends BaseMPTest {
    def "should create an payment"() {
        given:
            dummyHttpClient.mock(200, "payment_mock_mp_response.json")
            AddressDTO addressDTO = new AddressDTO("dummy", "dummy", 1, "dummy", "dummy", "dummy");
            PayerDTO payerDTO = new PayerDTO("dummy@dummy.com", "dummy", "dummy", "00000000000", addressDTO);
            CreatePaymentDTO createPaymentDTO = new CreatePaymentDTO(10.0, "dummy", "bolbradesco", payerDTO);
        when:
            Payment payment = PaymentService.createPayment(createPaymentDTO)
        then:
            payment.getStatus().name().equalsIgnoreCase(expectedStatus)
            payment.getPayer().getEmail().equalsIgnoreCase(expectedEmail)
            payment.getPaymentMethodId().equalsIgnoreCase(expectedPaymentMethod)
            payment !== null
        where:
            expectedStatus | expectedEmail      | expectedPaymentMethod
            "pending"      | "dummy@dummy.com"  | "bolbradesco"
    }

    def "should throw exception if occur error when save payment"() {
        given:
            dummyHttpClient.mock(400, "create_payment_error_mock_mp_response.json")
            AddressDTO addressDTO = new AddressDTO("dummy", "dummy", 1, "dummy", "dummy", "dummy");
            PayerDTO payerDTO = new PayerDTO("dummy", "dummy", "dummy", "00000000000", addressDTO);
            CreatePaymentDTO createPaymentDTO = new CreatePaymentDTO(10.0, "dummy", "bolbradesco", payerDTO);
        when:
            Payment payment = PaymentService.createPayment(createPaymentDTO)
        then:
            def ex = thrown(expectedException)
            def dictionary = Parser.toObj(ex.getMessage(), Map.class)
            dictionary.get("message") == expectedMessage
            dictionary.get("error") == expectedError
            dictionary.get("status") == expectedStatus
        where:
            expectedException           | expectedMessage                       | expectedError | expectedStatus
            ErrorCreatePaymentException | "payer.email must be a valid email"   | "bad_request" | 400.0
    }

    def "should return null if return unexpected error when create user"() {
        given:
            dummyHttpClient.mock(RuntimeException.class)
            AddressDTO addressDTO = new AddressDTO("dummy", "dummy", 1, "dummy", "dummy", "dummy");
            PayerDTO payerDTO = new PayerDTO("dummy@dummy.com", "dummy", "dummy", "00000000000", addressDTO);
            CreatePaymentDTO createPaymentDTO = new CreatePaymentDTO(10.0, "dummy", "bolbradesco", payerDTO);
        when:
            Payment payment = PaymentService.createPayment(createPaymentDTO)
        then:
            payment == null
    }

    def "should retrieve an payment by id"() {
        given:
            dummyHttpClient.mock(200, "payment_mock_mp_response.json")
            String paymentId = "1242461237"
        when:
            Payment payment = PaymentService.getPaymentById(paymentId)
        then:
            payment.getStatus().name().equalsIgnoreCase(expectedStatus)
            payment.getPayer().getEmail().equalsIgnoreCase(expectedEmail)
            payment.getPaymentMethodId().equalsIgnoreCase(expectedPaymentMethod)
            payment !== null
        where:
            expectedStatus | expectedEmail      | expectedPaymentMethod
            "pending"      | "dummy@dummy.com"  | "bolbradesco"
    }

    def "should throw DataNotFoundException if id is invalid while get by id"() {
        given:
            dummyHttpClient.mock(200, "payment_mock_mp_response.json")
        when:
            Payment payment = PaymentService.getPaymentById(paymentId)
        then:
            def ex = thrown(expectedException)
            ex.getMessage() == expectedMessage
        where:
            paymentId | expectedException      | expectedMessage
            ""        | DataNotFoundException  | "payment not found"
            null      | DataNotFoundException  | "payment not found"
    }

    def "should throw DataNotFoundException if not found payment"() {
        given:
            dummyHttpClient.mock(404, "get_payment_by_id_mock_mp_response.json")
        when:
            Payment payment = PaymentService.getPaymentById(paymentId)
        then:
            def ex = thrown(expectedException)
            def dictionary = Parser.toObj(ex.getMessage(), Map.class)
            def message = Parser.toObj(dictionary.get("message") as String, Map.class)
            message.get("message") == expectedMessage
            message.get("error") == expectedError
            message.get("status") == expectedStatus
        where:
            paymentId       | expectedException     | expectedMessage        | expectedError | expectedStatus
            "00"            | DataNotFoundException | "Payment not found"    | "not_found"   | 404.0
    }

    def "should return null if unexpected error happen while find payment"() {
        given:
            dummyHttpClient.mock(RuntimeException.class)
        when:
            Payment payment = PaymentService.getPaymentById("1")
        then:
            payment == null
    }
}
