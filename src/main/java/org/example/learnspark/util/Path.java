package org.example.learnspark.util;

public class Path {
    private Path() { }

    public static class Web {

        private Web() { }

        public static final String PING = "/ping";

        // routes to obtain payment methods
        public static final String GET_PAYMENT_METHODS = "/payment_methods";
        public static final String GET_PAYMENT_METHODS_BY_TYPE = "/payment_methods/:type";

        // routes to obtain identification types
        public static final String GET_IDENTIFICATION_TYPES = "/identification";
        public static final String GET_IDENTIFICATION_TYPES_BY_ID = "/identification/:id";

        // routes for payment CRUD
        public static final String CREATE_PAYMENT = "/payment";
        public static final String GET_PAYMENT_BY_ID = "/payment/:id";
        public static final String PAY_PAYMENT_BY_ID = "/payment/:id/pay";

        // routes for preference CRUD
        public static final String CREATE_PREFERENCE = "preference";
        public static final String GET_PREFERENCE_BY_ID = "preference/:id";
        public static final String PAY_PREFERENCE_BY_ID = "preference/:id/pay";
    }

    public static class MercadoPago {

        private MercadoPago() { }

        private static final String ENV_ACCESS_TOKEN = "ENV_ACCESS_TOKEN";
        private static final String PUBLIC_KEY = "PUBLIC_KEY";

        public static String getEnvAccessToken() {
            return System.getenv(ENV_ACCESS_TOKEN);
        }

        public static String getPublicKey() {
            return System.getenv(PUBLIC_KEY);
        }
    }
}
