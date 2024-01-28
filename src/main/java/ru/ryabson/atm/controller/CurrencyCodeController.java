package ru.ryabson.atm.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ryabson.atm.dto.requests.CurrencyCodeRequest;
import ru.ryabson.atm.dto.responses.CurrencyCodeResponse;
import ru.ryabson.atm.service.CurrencyCodeService;

@RestController
@RequestMapping("api/v1/admin/currencies")
@AllArgsConstructor
public class CurrencyCodeController {

    private final CurrencyCodeService currencyCodeService;

    @PostMapping("/create")
    public ResponseEntity<CurrencyCodeResponse> addCurrencyCode(
            @RequestBody CurrencyCodeRequest currencyCodeRequest) {
        return new ResponseEntity<>(currencyCodeService.createCurrencyCode(currencyCodeRequest),
                HttpStatus.CREATED);
    }
    
}
