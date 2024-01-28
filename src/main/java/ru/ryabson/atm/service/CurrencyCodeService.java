package ru.ryabson.atm.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.ryabson.atm.dto.requests.CurrencyCodeRequest;
import ru.ryabson.atm.dto.responses.CurrencyCodeResponse;
import ru.ryabson.atm.entity.CurrencyCode;
import ru.ryabson.atm.exception.DuplicateNameException;
import ru.ryabson.atm.mapper.CurrencyCodeMapper;
import ru.ryabson.atm.repository.CurrencyCodeRepository;

@Service
@AllArgsConstructor
public class CurrencyCodeService {

    private final CurrencyCodeRepository currencyCodeRepository;
    private final CurrencyCodeMapper currencyCodeMapper;

    private final static Logger logger = LoggerFactory.getLogger(CurrencyCodeService.class);

    public CurrencyCodeResponse createCurrencyCode(CurrencyCodeRequest request) {
        String currencyIsoCode = request.getCurrencyIsoCode();
        if (currencyCodeRepository.findCurrencyCodeByCurrencyIsoCode(currencyIsoCode) != null) {
            logger.error("Currency Code with name " + currencyIsoCode + " exist already");
            throw new DuplicateNameException(
                    "Currency Code with name" + currencyIsoCode + " exist already");
        }
        CurrencyCode currencyCode = currencyCodeMapper
                .mapCurrencyCodeRequestToCurrencyCode(request);
        currencyCodeRepository.save(currencyCode);
        logger.info("Iso Code " + currencyIsoCode + " was saved succsessfully");
        return currencyCodeMapper.mapCurrencyCodeToCurrencyCodeResponse(
                currencyCodeRepository.findCurrencyCodeByCurrencyIsoCode(currencyIsoCode));
    }


}
