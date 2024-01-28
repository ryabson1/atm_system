package ru.ryabson.atm.dto.requests;

import lombok.Data;

@Data
public class CurrencyCodeRequest {

    private String currencyIsoCode;
    private int currencyCode;
    private String currencyDescription;

}
