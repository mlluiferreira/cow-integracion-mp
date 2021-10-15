package org.example.learnspark.util;

import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPConfException;
import com.mercadopago.resources.IdentificationType;
import com.mercadopago.resources.Payment;
import com.mercadopago.resources.PaymentMethod;
import com.mercadopago.resources.Preference;

public class MPSdk {
    public static void setup() throws MPConfException {
        MercadoPago.SDK.setAccessToken(Path.MercadoPago.getEnvAccessToken());
        new PaymentMethod();
        new IdentificationType();
        new Payment();
        new Preference();
    }
}
