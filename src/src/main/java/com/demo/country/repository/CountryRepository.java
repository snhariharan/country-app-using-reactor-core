package src.main.java.com.demo.country.repository;

import com.demo.country.exception.CountryNotFoundException;
import com.demo.country.mapper.CountryMapper;
import com.demo.country.model.Country;
import com.demo.country.model.CountryDto;
import com.demo.country.model.CountryMoreInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class CountryRepository {

    private RestTemplateBuilder restTemplateBuilder;

    private String restUrl;

    private CountryMapper countryMapper;

    public CountryRepository(CountryMapper countryMapper, RestTemplateBuilder restTemplateBuilder, @Value("${rest.url}") String restUrl) {
        this.countryMapper = countryMapper;
        this.restTemplateBuilder = restTemplateBuilder;
        this.restUrl = restUrl;
        build();
    }

    Map<String, CountryDto> countryDtoMap;

    private void build() {
        ResponseEntity<Object[]> responseEntity =  this.restTemplateBuilder.build().getForEntity(restUrl, Object[].class);
        Object[] objects = responseEntity.getBody();
        ObjectMapper mapper = new ObjectMapper();
        countryDtoMap = Arrays.stream(objects)
                .map(object -> mapper.convertValue(object, CountryDto.class))
                .collect(Collectors.toMap(dto -> toLowerCase(dto.getName()), dto -> dto));

    }

    private String toLowerCase(String countryName) {
        return countryName.toLowerCase();
    }

    public Mono<CountryMoreInfo> findCountryByName(String name)
    {
        CountryDto countryDto = countryDtoMap.get(name.toLowerCase());
        if (countryDto == null) {
            return Mono.error(new CountryNotFoundException(name));
        }
        return Mono.just(countryMapper.getCountryInfo(countryDto));
    }

    public Flux<Country> findAllCountries()
    {
        List<Country> countries = countryDtoMap.values()
                .stream()
                .map(dto -> countryMapper.getCountry(dto))
                .collect(Collectors.toList());
        return Flux.fromIterable(countries);
    }
}
