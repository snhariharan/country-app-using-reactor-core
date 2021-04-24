package com.demo.country.service;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {
    public long personId = 123L;
    public String  personName = "James Clark";
    @JsonGetter(value = "person-id")
    public long getPersonId() {
        return personId;
    }
    @JsonGetter(value = "person-name")
    public String getPersonName() {
        return personName;
    }

    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(new Test());
        System.out.println(jsonString);
    }
}
