package org.example.learnspark.services

import com.mercadopago.resources.Preference
import org.example.learnspark.common.BaseMPTest
import org.example.learnspark.exception.DataNotFoundException
import org.example.learnspark.exception.ErrorCreatePreferenceException
import org.example.learnspark.model.preference.CreatePreferenceDTO
import org.example.learnspark.model.preference.ItemDTO
import org.example.learnspark.util.Parser

class PreferenceServiceTest extends BaseMPTest {

    def "should create preference"() {
        given:
            CreatePreferenceDTO createPreferenceDTO = createPreference()
            dummyHttpClient.mock(200, "preference_mock_mp_response.json")
        when:
            Preference preference = PreferenceService.createPreference(createPreferenceDTO)
        then:
            preference != null
            preference.getId() != null
    }

    def "should throw ErrorCreatePreferenceException if unable to create preference"() {
        given:
            CreatePreferenceDTO createPreferenceDTO = createPreference()
            dummyHttpClient.mock(400, "create_preference_error_mock_mp_response.json")
        when:
            Preference preference = PreferenceService.createPreference(createPreferenceDTO)
        then:
            def ex = thrown(expectedException)
            def dictionary = Parser.toObj(ex.getMessage(), Map.class)
            dictionary.get("message") == expectedMessage
            dictionary.get("error") == expectedError
            dictionary.get("status") == expectedStatus
        where:
            expectedException               | expectedStatus | expectedMessage      | expectedError
            ErrorCreatePreferenceException  | 400            | "unit_price invalid" | "invalid_items"
    }

    def "should return null if occur unexpected exception"() {
        given:
            CreatePreferenceDTO createPreferenceDTO = createPreference()
            dummyHttpClient.mock(RuntimeException.class)
        when:
            Preference preference = PreferenceService.createPreference(createPreferenceDTO)
        then:
            preference == null
    }

    def "should retrieve preference by id"() {
        given:
            dummyHttpClient.mock(200, "preference_mock_mp_response.json")
        when:
            Preference preference = PreferenceService.getPreferenceById("1")
        then:
            preference != null
            preference.getId() != null
            preference.getSandboxInitPoint() != null
    }

    def "should throw DataNotFoundException if id is empty or null"() {
        when:
            Preference preference = PreferenceService.getPreferenceById(preferenceId)
        then:
            def ex = thrown(expectedException)
            ex.getMessage() == expectedMessage
        where:
            preferenceId    | expectedException     | expectedMessage
            ""              | DataNotFoundException | "preference not found"
            null            | DataNotFoundException | "preference not found"
    }

    def "should throw DataNotFoundException if not found preference"() {
        given:
            CreatePreferenceDTO createPreferenceDTO = createPreference()
            dummyHttpClient.mock(404, "get_preference_by_id_error_mock_mp_response.json")
        when:
            Preference preference = PreferenceService.getPreferenceById("1")
        then:
            def ex = thrown(expectedException)
            def dictionary = Parser.toObj(ex.getMessage(), Map.class)
            dictionary.get("message") == expectedMessage
            dictionary.get("error") == expectedError
            dictionary.get("status") == expectedStatus
        where:
            expectedException      | expectedStatus | expectedMessage                                 | expectedError
            DataNotFoundException  | 404            | "The preference with identifier 1 was not found" | "not_found"
    }

    def "should return null if unable to found preference"() {
        given:
            dummyHttpClient.mock(RuntimeException.class)
        when:
            Preference preference = PreferenceService.getPreferenceById("1")
        then:
            preference == null
    }

    static def CreatePreferenceDTO createPreference() {
        List<ItemDTO> items = new ArrayList<>();
        items.add(new ItemDTO("dummy", "dummy", "", "", 1, "BRL", 10.0));
        return new CreatePreferenceDTO(items);
    }
}
