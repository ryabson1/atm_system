package ru.ryabson.atm.repository;

import jakarta.transaction.Transactional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.ryabson.atm.entity.AccountType;

public interface AccountTypeRepository extends JpaRepository<AccountType, UUID> {

    AccountType findAccountTypeByAccountTypeName(String accountTypeName);

    @Transactional
    void deleteAccountTypeByAccountTypeName(String accountTypeName);



}
