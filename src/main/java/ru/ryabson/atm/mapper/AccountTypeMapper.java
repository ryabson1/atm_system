package ru.ryabson.atm.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ryabson.atm.dto.requests.AccountTypeRequest;
import ru.ryabson.atm.dto.responses.AccountTypeResponse;
import ru.ryabson.atm.entity.AccountType;

@Mapper(componentModel = "spring")
public interface AccountTypeMapper {

    @Mapping(target = "accountTypeId", ignore = true)
    AccountType mapAccountTypeRequestToAccountType(AccountTypeRequest request);

    @Mapping(target = "accountTypeId", ignore = false)
    AccountTypeResponse mapAccountTypeToAccountTypeResponse(AccountType accountType);

}
