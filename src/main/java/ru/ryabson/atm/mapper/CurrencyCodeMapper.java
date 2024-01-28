package ru.ryabson.atm.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ryabson.atm.dto.requests.CurrencyCodeRequest;
import ru.ryabson.atm.dto.responses.CurrencyCodeResponse;
import ru.ryabson.atm.entity.CurrencyCode;

@Mapper(componentModel = "spring")
public interface CurrencyCodeMapper {

    @Mapping(target = "currencyCodeId", ignore = true)
    CurrencyCode mapCurrencyCodeRequestToCurrencyCode(CurrencyCodeRequest request);

    @Mapping(target = "currencyCodeId", ignore = false)
    CurrencyCodeResponse mapCurrencyCodeToCurrencyCodeResponse(CurrencyCode currencyCode);

}
