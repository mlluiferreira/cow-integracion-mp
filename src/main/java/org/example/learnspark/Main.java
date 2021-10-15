package org.example.learnspark;

import com.mercadopago.exceptions.MPConfException;
import org.example.learnspark.controller.ExceptionController;
import org.example.learnspark.controller.IdentificationController;
import org.example.learnspark.controller.PaymentController;
import org.example.learnspark.controller.PaymentMethodController;
import org.example.learnspark.db.DatabaseHelper;
import org.example.learnspark.exception.AbstractGeneralException;
import org.example.learnspark.util.JsonTransformer;
import org.example.learnspark.util.MPSdk;
import org.example.learnspark.util.Path;
import static spark.Spark.*;

public class Main {

    public static void setupExceptionHandler() {
        exception(AbstractGeneralException.class, ExceptionController::handler);
    }

    public static void setupBeforeFilters() {
        before((request, response) -> response.type("application/json"));
    }

    public static void setupRoutes() {
        // ping endpoint
        get(Path.Web.PING, (req, res) -> "pong");

        // handle route to get payment methods
        get(Path.Web.GET_PAYMENT_METHODS, PaymentMethodController::getPaymentMethods, new JsonTransformer());
        get(Path.Web.GET_PAYMENT_METHODS_BY_TYPE, PaymentMethodController::getPaymentMethodsByType, new JsonTransformer());

        // handle route to get identification types
        get(Path.Web.GET_IDENTIFICATION_TYPES, IdentificationController::getIdentificationsTypes, new JsonTransformer());
        get(Path.Web.GET_IDENTIFICATION_TYPES_BY_ID, IdentificationController::getIdentificationsById, new JsonTransformer());

        // handle route for payment CRUD
        post(Path.Web.CREATE_PAYMENT, PaymentController::createPayment, new JsonTransformer());
    }

    public static void setupAfterFilters() {

    }

    public static void initialize() throws MPConfException {
        MPSdk.setup();

        setupBeforeFilters();

        setupRoutes();

        setupAfterFilters();

        setupExceptionHandler();
    }

    public static void main(String[] args) throws MPConfException {
        initialize();
    }
}
