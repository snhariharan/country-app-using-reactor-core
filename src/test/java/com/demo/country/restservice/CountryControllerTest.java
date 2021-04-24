package com.demo.country.restservice;

import com.demo.country.model.CountryMoreInfo;
import com.demo.country.repository.CountryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = CountryController.class)
public class CountryControllerTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private CountryRepository countryRepository;

    @Test
    public void testCountryByName() {
        CountryMoreInfo countryMoreInfo = new CountryMoreInfo();
        countryMoreInfo.setName("india");
        countryMoreInfo.setCountryCode("in");
        countryMoreInfo.setPopulation(1000);
        countryMoreInfo.setCapital("test");
        countryMoreInfo.setFlag("test");

        Mockito.when(countryRepository.findCountryByName("china")).thenReturn(Mono.just(countryMoreInfo));


        webClient.get().uri("/api/countries/{name}", "china")
                .exchange()
                .expectStatus().isOk()
                .expectBody(CountryMoreInfo.class)
                .isEqualTo(countryMoreInfo);
    }

}
