package com.demo.country.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CountryDto {
    private String name;
    private String alpha2Code;
    private String capital;
    private String population;
    private String flag;
}
