package org.example.learnspark.services;

import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import org.apache.commons.lang3.StringUtils;
import org.example.learnspark.exception.DataNotFoundException;
import org.example.learnspark.exception.ErrorCreatePreferenceException;
import org.example.learnspark.model.mapper.PreferenceMapper;
import org.example.learnspark.model.preference.CreatePreferenceDTO;

public class PreferenceService {

    private PreferenceService() { }

    public static Preference createPreference(CreatePreferenceDTO createPreferenceDTO) {
        Preference preference = PreferenceMapper.INSTANCE.createPreferenceDTOToPreference(createPreferenceDTO);
        Preference createdPreference = null;

        try {
            createdPreference = preference.save();
            if(createdPreference.getSandboxInitPoint() == null)
                throw new ErrorCreatePreferenceException(createdPreference.getLastApiResponse().getStringResponse(), createPreferenceDTO);
        } catch (MPException e) {
            e.printStackTrace();
        }

        return createdPreference;
    }

    public static Preference getPreferenceById(String id) {
        Preference preference = null;

        if(StringUtils.isBlank(id)) throw new DataNotFoundException("preference not found", id);

        try {
            preference = Preference.findById(id);
        } catch (MPException e) {
            e.printStackTrace();
        }

        if(preference != null && preference.getId() == null)
            throw new DataNotFoundException(preference.getLastApiResponse().getStringResponse(), id);

        return preference;
    }
}
