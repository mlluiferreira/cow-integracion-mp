package org.example.learnspark.common

import com.mercadopago.MercadoPago
import spock.lang.Specification

class BaseMPTest extends Specification {
    protected DummyHttpClient dummyHttpClient

    public static String accessToken = "fakeToken"

    def setup() {
        dummyHttpClient = new DummyHttpClient()
        MercadoPago.SDK.cleanConfiguration()
        MercadoPago.SDK.setAccessToken(accessToken)
        MercadoPago.SDK.setHttpClient(dummyHttpClient)
    }
}
