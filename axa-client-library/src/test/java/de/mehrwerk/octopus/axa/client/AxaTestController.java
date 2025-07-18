package de.mehrwerk.octopus.axa.client;

import de.mehrwerk.octopus.axa.model.cases.home.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/axa-test")
public class AxaTestController {

    @Autowired
    private AxaApiClient axaApiClient;

    @GetMapping
    public ResponseEntity<HomeCaseResponseDto> homeCaseCreate() {
        HomeCaseRequestDto request = (HomeCaseRequestDto) new HomeCaseRequestDto()
                .setIncident(new IncidentDto()
                        .setCause(new CauseDto()
                                .setCode("BLOCKAGE"))
                        .setLocation(new LocationDto()
                                .setAddress(new AddressDto()
                                        .setCountry("DE")
                                        .setLocality("Frankfurt (Oder)")
                                        .setPostalCode("15230")
                                        .setStreetAddress("Grosse Scharrnstrasse 36"))
                                .setAdditionalInformation(""))
                        .setOccurredAt(OffsetDateTime.parse("2022-11-29T15:28:05+01:00")))
                .setContact(new ContactDto()
                        .setPreferredLanguage("de")
                        .setPhone(new PhoneDto()
                                .setInternationalPrefix("+49")
                                .setNumber("7111111111")))
                .setPolicy(new PolicyPartnerReferenceDto()
                        .setPartnerReference("146")
                        .setExternalPolicyNumber("ACTIVE1")
                        .setPackageId("06.92707")
                        .setPolicyHolder(new PolicyHolderPersonalDto()
                                .setAddressPostalCode("15230")
                                .setFirstName("Kloster")
                                .setLastName("Dirk")));

        return ResponseEntity.ok(axaApiClient.createHomeCase(request));
    }
}
