package ru.ryabson.atm.dto.responses;

import java.time.LocalDate;
import java.util.UUID;
import lombok.Data;

@Data
public class AccountTypeResponse {

    private UUID accountTypeId;
    private LocalDate sysCreateTime;
    private String accountTypeName;

}
