package ru.ryabson.atm.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.ryabson.atm.entity.CurrencyCode;

public interface CurrencyCodeRepository extends JpaRepository<CurrencyCode, UUID> {

    CurrencyCode findCurrencyCodeByCurrencyIsoCode(String currencyIsoCode);

}
