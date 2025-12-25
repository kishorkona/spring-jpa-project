package com.work.data;

import lombok.Data;

@Data
public class Location {

    private Long id;
    private String streetAddress;
    private String postalCode;
    private String city;
    private String stateProvince;
    private String countryName;

}
