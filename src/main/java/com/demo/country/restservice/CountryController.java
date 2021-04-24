package com.demo.country.restservice;

import com.demo.country.exception.CountryNotFoundException;
import com.demo.country.repository.CountryRepository;
import com.demo.country.model.Countries;
import com.demo.country.model.CountryMoreInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api")
public class CountryController {

    private CountryRepository countryRepository;

    public CountryController(final CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @GetMapping(path ="/countries")
    Mono<Countries> all() {
        Countries val = new Countries();
        val.setCountries(countryRepository.findAllCountries().collectList().block());
        return Mono.just(val);
    }

    @GetMapping(path = "/countries/{name}")
    Mono<CountryMoreInfo> getCountryByName(@PathVariable String name) {
        return countryRepository.findCountryByName(name);
    }

    @ExceptionHandler(CountryNotFoundException.class)
    public ResponseEntity<?> handleCountryNotFound(CountryNotFoundException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
