package src.main.java.com.demo.country.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@JsonPropertyOrder({"name", "country_code", "capital", "population", "flag_file_url"})
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CountryMoreInfo extends Country {
    private String capital;
    private Integer population;
    @JsonProperty("flag_file_url")
    private String flag;
}
