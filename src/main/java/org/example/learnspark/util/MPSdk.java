package org.example.learnspark.util;

import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPConfException;
import com.mercadopago.resources.IdentificationType;
import com.mercadopago.resources.Payment;
import com.mercadopago.resources.PaymentMethod;
import com.mercadopago.resources.Preference;
import org.apache.http.client.HttpClient;

public class MPSdk {
    public static void setup() throws MPConfException {
        String accessToken = Path.MercadoPago.getEnvAccessToken();
        setup(null, accessToken);
    }

    public static void setup(HttpClient httpClient, String accessToken) throws MPConfException {
        MercadoPago.SDK.cleanConfiguration();
        MercadoPago.SDK.setAccessToken(accessToken);
        MercadoPago.SDK.setHttpClient(httpClient);
        setupDomains();
    }

    private static void setupDomains() {
        new PaymentMethod();
        new IdentificationType();
        new Payment();
        new Preference();
    }
}
