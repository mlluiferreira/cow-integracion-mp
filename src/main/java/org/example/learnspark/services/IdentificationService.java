package org.example.learnspark.services;

import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.IdentificationType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class IdentificationService {

    private static List<IdentificationType> identificationTypeCache = new ArrayList<>();

    private IdentificationService() { }

    public static List<IdentificationType> getIdentificationTypes() {
        if(!identificationTypeCache.isEmpty()) return identificationTypeCache;
        try {
            identificationTypeCache = IdentificationType.all().resources();
        } catch (MPException e) {
            e.printStackTrace();
        }
        return identificationTypeCache;
    }

    public static List<IdentificationType> getIdentificationById(String id) {
        if(identificationTypeCache.isEmpty()) getIdentificationTypes();
        return identificationTypeCache.stream().filter(i -> i.getId().equalsIgnoreCase(id))
                .collect(Collectors.toList());
    }

    public static void clearCache() {
        identificationTypeCache.clear();
    }
}
