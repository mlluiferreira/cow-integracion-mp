package org.example.learnspark.model.mapper;

import com.mercadopago.resources.Preference;
import org.example.learnspark.model.preference.CreatePreferenceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PreferenceMapper {

    PreferenceMapper INSTANCE = Mappers.getMapper(PreferenceMapper.class);

    Preference createPreferenceDTOToPreference(CreatePreferenceDTO createPreferenceDTO);
}
