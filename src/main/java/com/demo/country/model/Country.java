package com.demo.country.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;

@JsonPropertyOrder({"name", "country_code"})
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Country {
    @JsonProperty("name")
    private String name;

    @JsonProperty("country_code")
    private String countryCode;

}
