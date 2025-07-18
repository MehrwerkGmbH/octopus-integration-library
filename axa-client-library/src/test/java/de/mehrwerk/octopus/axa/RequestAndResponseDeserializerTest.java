package de.mehrwerk.octopus.axa;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.mehrwerk.octopus.axa.jackson.JacksonConfig;
import de.mehrwerk.octopus.axa.model.cases.home.AbstractHomeCaseDto;
import de.mehrwerk.octopus.axa.model.cases.home.HomeCaseRequestDto;
import de.mehrwerk.octopus.axa.model.cases.home.HomeCaseResponseDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RequestAndResponseDeserializerTest {

    private final ObjectMapper objectMapper = new JacksonConfig().objectMapper();

    @Test
    void testDeserializeResponse() throws Exception {
        String json = """
                {
                    "case_id": "500AW00000SDZB9YAP",
                    "case_reference": "0625800408",
                    "status": "ONGOING",
                    "incident": {
                       "cause": {
                           "code": "BLOCKAGE"
                       },
                       "location": {
                           "address": {
                               "country": "DE",
                               "locality": "Frankfurt (Oder)",
                               "postal_code": "15230",
                               "street_address": "Große Scharrnstraße 36"
                           },
                           "additional_information": ""
                       },
                       "occurred_at": "2022-11-29T15:28:05+01:00"
                   },
                   "contact": {
                       "preferred_language": "de",
                       "phone": {
                           "international_prefix": "+49",
                           "number": "7111111111"
                       }
                   },
                   "policy": {
                       "partner_reference": "146",
                       "external_policy_number" : "ACTIVE1",
                       "package_id" : "06.92707",
                       "policy_holder": {
                           "address_postal_code": "15230",
                           "first_name": "Kloster",
                           "last_name": "Dirk"
                       }
                   },
                   "questions_and_answers": [
                       {
                           "question_id": "CASE_SOURCE",
                           "question_text": "Case Source",
                           "question_type": "ENUM",
                           "priority": -1,
                           "enumeration_answers": [
                               {
                                   "answer_id": "Home manager",
                                   "answer_text": "Home manager",
                                   "is_selected": true
                               },
                               {
                                   "answer_id": "E-Rescue",
                                   "answer_text": "E-Rescue",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "Phone",
                                   "answer_text": "Phone",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "_OTHER",
                                   "answer_text": "Other",
                                   "is_selected": false
                               }
                           ]
                       },
                       {
                           "question_id": "COUNTRIES",
                           "question_text": "Countries",
                           "question_type": "ENUM",
                           "priority": -1,
                           "enumeration_answers": [
                               {
                                   "answer_id": "DE",
                                   "answer_text": "Germany",
                                   "is_selected": true
                               },
                               {
                                   "answer_id": "GG",
                                   "answer_text": "Guernsey",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "IM",
                                   "answer_text": "Isle of Man",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "JE",
                                   "answer_text": "Jersey",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "NORTHERN IRELAND",
                                   "answer_text": "NORTHERN IRELAND",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "_OTHER",
                                   "answer_text": "Other",
                                   "is_selected": false
                               }
                           ]
                       },
                       {
                           "question_id": "OCCURRENCE_DATE",
                           "question_text": "Occurrence date",
                           "question_type": "DATE",
                           "priority": -1,
                           "answered_date": "2022-11-29"
                       },
                       {
                           "question_id": "FIRST_QUESTION",
                           "question_text": "First question",
                           "question_type": "ENUM",
                           "priority": -1,
                           "enumeration_answers": [
                               {
                                   "answer_id": "Electrics",
                                   "answer_text": "Electrics",
                                   "is_selected": true
                               },
                               {
                                   "answer_id": "Bad Smell",
                                   "answer_text": "Bad Smell",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "Blockage",
                                   "answer_text": "Blockage",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "Heating",
                                   "answer_text": "Heating",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "Heating and Hot Water",
                                   "answer_text": "Heating and Hot Water",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "Heating and/or hot water",
                                   "answer_text": "Heating and/or hot water",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "Hot Water",
                                   "answer_text": "Hot Water",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "Leak",
                                   "answer_text": "Leak",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "Other emergency",
                                   "answer_text": "Other emergency",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "Pests",
                                   "answer_text": "Pests",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "Plumbing",
                                   "answer_text": "Plumbing",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "Plumbing and drainage",
                                   "answer_text": "Plumbing and drainage",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "Roofing",
                                   "answer_text": "Roofing",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "Security",
                                   "answer_text": "Security",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "Security and glazing",
                                   "answer_text": "Security and glazing",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "Water Leak",
                                   "answer_text": "Water Leak",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "_OTHER",
                                   "answer_text": "Other",
                                   "is_selected": false
                               }
                           ]
                       },
                       {
                           "question_id": "ELECTRICS_EMERGENCY",
                           "question_text": "Electrics emergency",
                           "question_type": "ENUM",
                           "priority": -1,
                           "enumeration_answers": [
                               {
                                   "answer_id": "Domestic appliance problem",
                                   "answer_text": "Domestic appliance problem",
                                   "is_selected": true
                               },
                               {
                                   "answer_id": "Lighting",
                                   "answer_text": "Lighting",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "Lighting and sockets",
                                   "answer_text": "Lighting and sockets",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "Sockets",
                                   "answer_text": "Sockets",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "_OTHER",
                                   "answer_text": "Other",
                                   "is_selected": false
                               }
                           ]
                       },
                       {
                           "question_id": "DOMESTIC_APPLIANCES",
                           "question_text": "Domestic appliance affected",
                           "question_type": "ENUM",
                           "priority": -1,
                           "enumeration_answers": [
                               {
                                   "answer_id": "Electric shower",
                                   "answer_text": "Electric shower",
                                   "is_selected": true
                               },
                               {
                                   "answer_id": "Cooker/hob",
                                   "answer_text": "Cooker/hob",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "Cooker/Hob",
                                   "answer_text": "Cooker/Hob",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "Dish washer",
                                   "answer_text": "Dish washer",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "Dishwasher",
                                   "answer_text": "Dishwasher",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "Fridge freezer",
                                   "answer_text": "Fridge freezer",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "Macerator",
                                   "answer_text": "Macerator",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "Other appliances",
                                   "answer_text": "Other appliances",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "Washing machine",
                                   "answer_text": "Washing machine",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "_OTHER",
                                   "answer_text": "Other",
                                   "is_selected": false
                               }
                           ]
                       },
                       {
                           "question_id": "DAYS_HOME_UNOCCUPIED",
                           "question_text": "Has your property been unoccupied for 60 or more consecutive days?",
                           "question_type": "BOOLEAN",
                           "priority": -1,
                           "answered_boolean": false
                       },
                       {
                           "question_id": "VULNERABILITY_HM",
                           "question_text": "Vulnerability yes/no HM",
                           "question_type": "BOOLEAN",
                           "priority": -1,
                           "answered_boolean": false
                       },
                       {
                           "question_id": "PROBLEM_DETAIL_HM",
                           "question_text": "Problem detail yes/no HM",
                           "question_type": "BOOLEAN",
                           "priority": -1,
                           "answered_boolean": false
                       },
                       {
                           "question_id": "COVERLEVEL",
                           "question_text": "Coverlevel",
                           "question_type": "ENUM",
                           "priority": -1,
                           "enumeration_answers": [
                               {
                                   "answer_id": "URCA",
                                   "answer_text": "URCA",
                                   "is_selected": true
                               },
                               {
                                   "answer_id": "Premier",
                                   "answer_text": "Premier",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "_OTHER",
                                   "answer_text": "Other",
                                   "is_selected": false
                               }
                           ]
                       }
                   ]
                }
                """;

        AbstractHomeCaseDto dto = objectMapper.readValue(json, AbstractHomeCaseDto.class);
        assertTrue(dto instanceof HomeCaseResponseDto);
        assertEquals("500AW00000SDZB9YAP", ((HomeCaseResponseDto) dto).getCaseId());
    }

    @Test
    void testDeserializeRequest() throws Exception {
        String json = """
                {
                    "incident": {
                       "cause": {
                           "code": "BLOCKAGE"
                       },
                       "location": {
                           "address": {
                               "country": "DE",
                               "locality": "Frankfurt (Oder)",
                               "postal_code": "15230",
                               "street_address": "Große Scharrnstraße 36"
                           },
                           "additional_information": ""
                       },
                       "occurred_at": "2022-11-29T15:28:05+01:00"
                   },
                   "contact": {
                       "preferred_language": "de",
                       "phone": {
                           "international_prefix": "+49",
                           "number": "7111111111"
                       }
                   },
                   "policy": {
                       "partner_reference": "146",
                       "external_policy_number" : "ACTIVE1",
                       "package_id" : "06.92707",
                       "policy_holder": {
                           "address_postal_code": "15230",
                           "first_name": "Kloster",
                           "last_name": "Dirk"
                       }
                   },
                   "questions_and_answers": [
                       {
                           "question_id": "CASE_SOURCE",
                           "question_text": "Case Source",
                           "question_type": "ENUM",
                           "priority": -1,
                           "enumeration_answers": [
                               {
                                   "answer_id": "Home manager",
                                   "answer_text": "Home manager",
                                   "is_selected": true
                               },
                               {
                                   "answer_id": "E-Rescue",
                                   "answer_text": "E-Rescue",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "Phone",
                                   "answer_text": "Phone",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "_OTHER",
                                   "answer_text": "Other",
                                   "is_selected": false
                               }
                           ]
                       },
                       {
                           "question_id": "COUNTRIES",
                           "question_text": "Countries",
                           "question_type": "ENUM",
                           "priority": -1,
                           "enumeration_answers": [
                               {
                                   "answer_id": "DE",
                                   "answer_text": "Germany",
                                   "is_selected": true
                               },
                               {
                                   "answer_id": "GG",
                                   "answer_text": "Guernsey",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "IM",
                                   "answer_text": "Isle of Man",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "JE",
                                   "answer_text": "Jersey",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "NORTHERN IRELAND",
                                   "answer_text": "NORTHERN IRELAND",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "_OTHER",
                                   "answer_text": "Other",
                                   "is_selected": false
                               }
                           ]
                       },
                       {
                           "question_id": "OCCURRENCE_DATE",
                           "question_text": "Occurrence date",
                           "question_type": "DATE",
                           "priority": -1,
                           "answered_date": "2022-11-29"
                       },
                       {
                           "question_id": "FIRST_QUESTION",
                           "question_text": "First question",
                           "question_type": "ENUM",
                           "priority": -1,
                           "enumeration_answers": [
                               {
                                   "answer_id": "Electrics",
                                   "answer_text": "Electrics",
                                   "is_selected": true
                               },
                               {
                                   "answer_id": "Bad Smell",
                                   "answer_text": "Bad Smell",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "Blockage",
                                   "answer_text": "Blockage",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "Heating",
                                   "answer_text": "Heating",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "Heating and Hot Water",
                                   "answer_text": "Heating and Hot Water",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "Heating and/or hot water",
                                   "answer_text": "Heating and/or hot water",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "Hot Water",
                                   "answer_text": "Hot Water",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "Leak",
                                   "answer_text": "Leak",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "Other emergency",
                                   "answer_text": "Other emergency",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "Pests",
                                   "answer_text": "Pests",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "Plumbing",
                                   "answer_text": "Plumbing",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "Plumbing and drainage",
                                   "answer_text": "Plumbing and drainage",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "Roofing",
                                   "answer_text": "Roofing",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "Security",
                                   "answer_text": "Security",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "Security and glazing",
                                   "answer_text": "Security and glazing",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "Water Leak",
                                   "answer_text": "Water Leak",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "_OTHER",
                                   "answer_text": "Other",
                                   "is_selected": false
                               }
                           ]
                       },
                       {
                           "question_id": "ELECTRICS_EMERGENCY",
                           "question_text": "Electrics emergency",
                           "question_type": "ENUM",
                           "priority": -1,
                           "enumeration_answers": [
                               {
                                   "answer_id": "Domestic appliance problem",
                                   "answer_text": "Domestic appliance problem",
                                   "is_selected": true
                               },
                               {
                                   "answer_id": "Lighting",
                                   "answer_text": "Lighting",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "Lighting and sockets",
                                   "answer_text": "Lighting and sockets",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "Sockets",
                                   "answer_text": "Sockets",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "_OTHER",
                                   "answer_text": "Other",
                                   "is_selected": false
                               }
                           ]
                       },
                       {
                           "question_id": "DOMESTIC_APPLIANCES",
                           "question_text": "Domestic appliance affected",
                           "question_type": "ENUM",
                           "priority": -1,
                           "enumeration_answers": [
                               {
                                   "answer_id": "Electric shower",
                                   "answer_text": "Electric shower",
                                   "is_selected": true
                               },
                               {
                                   "answer_id": "Cooker/hob",
                                   "answer_text": "Cooker/hob",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "Cooker/Hob",
                                   "answer_text": "Cooker/Hob",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "Dish washer",
                                   "answer_text": "Dish washer",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "Dishwasher",
                                   "answer_text": "Dishwasher",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "Fridge freezer",
                                   "answer_text": "Fridge freezer",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "Macerator",
                                   "answer_text": "Macerator",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "Other appliances",
                                   "answer_text": "Other appliances",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "Washing machine",
                                   "answer_text": "Washing machine",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "_OTHER",
                                   "answer_text": "Other",
                                   "is_selected": false
                               }
                           ]
                       },
                       {
                           "question_id": "DAYS_HOME_UNOCCUPIED",
                           "question_text": "Has your property been unoccupied for 60 or more consecutive days?",
                           "question_type": "BOOLEAN",
                           "priority": -1,
                           "answered_boolean": false
                       },
                       {
                           "question_id": "VULNERABILITY_HM",
                           "question_text": "Vulnerability yes/no HM",
                           "question_type": "BOOLEAN",
                           "priority": -1,
                           "answered_boolean": false
                       },
                       {
                           "question_id": "PROBLEM_DETAIL_HM",
                           "question_text": "Problem detail yes/no HM",
                           "question_type": "BOOLEAN",
                           "priority": -1,
                           "answered_boolean": false
                       },
                       {
                           "question_id": "COVERLEVEL",
                           "question_text": "Coverlevel",
                           "question_type": "ENUM",
                           "priority": -1,
                           "enumeration_answers": [
                               {
                                   "answer_id": "URCA",
                                   "answer_text": "URCA",
                                   "is_selected": true
                               },
                               {
                                   "answer_id": "Premier",
                                   "answer_text": "Premier",
                                   "is_selected": false
                               },
                               {
                                   "answer_id": "_OTHER",
                                   "answer_text": "Other",
                                   "is_selected": false
                               }
                           ]
                       }
                   ]
                }
                """;

        AbstractHomeCaseDto dto = objectMapper.readValue(json, AbstractHomeCaseDto.class);
        assertTrue(dto instanceof HomeCaseRequestDto);
    }
}