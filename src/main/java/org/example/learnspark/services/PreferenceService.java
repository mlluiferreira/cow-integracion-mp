package org.example.learnspark.services;

import com.mercadopago.resources.Preference;
import org.example.learnspark.model.preference.CreatePreferenceDTO;

public class PreferenceService {

    private PreferenceService() { }

    public static Object createPreference(CreatePreferenceDTO createPreferenceDTO) {
        Preference preference = new Preference();
        return null;
    }
}
