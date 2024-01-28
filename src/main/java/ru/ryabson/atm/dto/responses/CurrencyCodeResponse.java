package ru.ryabson.atm.dto.responses;

import java.time.LocalDate;
import java.util.UUID;
import lombok.Data;

@Data
public class CurrencyCodeResponse {

    private UUID currencyCodeId;
    private LocalDate sysCreateTime;
    private String currencyIsoCode;
    private int currencyCode;
    private String currencyDescription;

}
