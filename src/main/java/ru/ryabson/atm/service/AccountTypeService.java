package ru.ryabson.atm.service;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.ryabson.atm.dto.requests.AccountTypeRequest;
import ru.ryabson.atm.dto.responses.AccountTypeResponse;
import ru.ryabson.atm.dto.responses.DeleteObjectResponse;
import ru.ryabson.atm.entity.AccountType;
import ru.ryabson.atm.exception.DuplicateNameException;
import ru.ryabson.atm.exception.EmptyListException;
import ru.ryabson.atm.exception.EmptyObjectException;
import ru.ryabson.atm.mapper.AccountTypeMapper;
import ru.ryabson.atm.repository.AccountTypeRepository;

@Service
@AllArgsConstructor
public class AccountTypeService {

    private final AccountTypeRepository accountTypeRepository;
    private final AccountTypeMapper accountTypeMapper;

    private static final Logger logger = LoggerFactory.getLogger(AccountTypeService.class);

    public AccountTypeResponse createNewAccountType(AccountTypeRequest request) {
        String accountTypeName = request.getAccountTypeName();
        if (accountTypeRepository.findAccountTypeByAccountTypeName(accountTypeName) != null) {
            logger.error("Account type with name: " + request.getAccountTypeName()
                    + " couldn't be created");
            throw new DuplicateNameException("This type is already exists");
        }
        AccountType accountType = accountTypeMapper.mapAccountTypeRequestToAccountType(request);
        return accountTypeMapper
                .mapAccountTypeToAccountTypeResponse(accountTypeRepository.save(accountType));
    }

    public List<AccountTypeResponse> getAllAccountTypes() {
        if (accountTypeRepository.findAll().isEmpty()) {
            logger.error("Account Type List is empty");
            throw new EmptyListException("There is no any records in Account Type view");
        }
        List<AccountTypeResponse> resultList = new ArrayList<>();
        accountTypeRepository.findAll().forEach(
                (o) -> resultList.add(accountTypeMapper.mapAccountTypeToAccountTypeResponse(o)));
        return resultList;
    }

    public AccountTypeResponse editAccountType(String accountType, AccountTypeRequest request) {
        if (accountTypeRepository.findAccountTypeByAccountTypeName(accountType) == null) {
            logger.error("There is no such Account Type like " + accountType);
            throw new EmptyObjectException("Please, enter the right type of account");
        }
        AccountType _accountType = accountTypeRepository
                .findAccountTypeByAccountTypeName(accountType);
        _accountType.setAccountTypeName(request.getAccountTypeName());
        return accountTypeMapper
                .mapAccountTypeToAccountTypeResponse(accountTypeRepository.save(_accountType));
    }

    public DeleteObjectResponse deleteAccountType(String accountTypeName) {
        if (accountTypeRepository.findAccountTypeByAccountTypeName(accountTypeName) == null) {
            logger.error("There is no such Account Type like " + accountTypeName);
            throw new EmptyObjectException("Please, enter the right type of account");
        }
        accountTypeRepository.deleteAccountTypeByAccountTypeName(accountTypeName);
        DeleteObjectResponse response = new DeleteObjectResponse();
        response.setObjectName(accountTypeName);
        return response;
    }


}
