package src.main.java.com.demo.country.mapper;

import com.demo.country.model.Country;
import com.demo.country.model.CountryDto;
import com.demo.country.model.CountryMoreInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CountryMapper {
    @Mappings(value = {
            @Mapping(source = "alpha2Code", target = "countryCode")
    })
    Country getCountry(CountryDto countryDto);

    @Mappings(value = {
            @Mapping(source = "alpha2Code", target = "countryCode")
    })
    CountryMoreInfo getCountryInfo(CountryDto countryDto);
}
