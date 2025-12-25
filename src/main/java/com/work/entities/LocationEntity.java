package com.work.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Table(name = "locations")
@Data
public class LocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id", updatable = false, nullable = false)
    private Long id;
    @Column(name = "street_address")
    private String streetAddress;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "city")
    private String city;

    @Column(name = "state_province")
    private String stateProvince;

    @Column(name = "country_name")
    private String countryName;

}
