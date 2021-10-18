package org.example.learnspark.common

import com.mercadopago.MercadoPago
import com.mercadopago.resources.IdentificationType
import com.mercadopago.resources.Payment
import com.mercadopago.resources.PaymentMethod
import com.mercadopago.resources.Preference
import org.example.learnspark.util.MPSdk
import spock.lang.Specification

class BaseMPTest extends Specification {
    protected DummyHttpClient dummyHttpClient

    public static String accessToken = "fakeToken"

    def setup() {
        dummyHttpClient = new DummyHttpClient()
        MPSdk.setup(dummyHttpClient, accessToken);
    }
}
