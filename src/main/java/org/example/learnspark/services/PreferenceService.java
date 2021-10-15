package org.example.learnspark.services;

import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import org.example.learnspark.exception.ErrorCreatePaymentException;
import org.example.learnspark.model.mapper.PreferenceMapper;
import org.example.learnspark.model.preference.CreatePreferenceDTO;

public class PreferenceService {

    private PreferenceService() { }

    public static Object createPreference(CreatePreferenceDTO createPreferenceDTO) {
        Preference preference = PreferenceMapper.INSTANCE.createPreferenceDTOToPreference(createPreferenceDTO);
        Preference createdPreference = null;

        try {
            createdPreference = preference.save();
            if(createdPreference.getSandboxInitPoint() == null)
                throw new ErrorCreatePaymentException(createdPreference.getLastApiResponse().getStringResponse(), createPreferenceDTO);
        } catch (MPException e) {
            e.printStackTrace();
        }

        return createdPreference;
    }
}
