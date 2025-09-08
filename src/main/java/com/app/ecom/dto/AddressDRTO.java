package com.app.ecom.dto;

import lombok.Data;

@Data
public class AddressDRTO {


    private String street;
    private String zipCode;
    private String city;
    private String state;
    private String country;
}
