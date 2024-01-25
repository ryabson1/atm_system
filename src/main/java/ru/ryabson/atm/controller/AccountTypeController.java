package ru.ryabson.atm.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ryabson.atm.dto.requests.AccountTypeRequest;
import ru.ryabson.atm.dto.responses.AccountTypeResponse;
import ru.ryabson.atm.dto.responses.DeleteObjectResponse;
import ru.ryabson.atm.service.AccountTypeService;

@RestController
@RequestMapping("api/v1/admin/account_types")
@AllArgsConstructor
public class AccountTypeController {

    private final AccountTypeService accountTypeService;

    @PostMapping("/create")
    public ResponseEntity<AccountTypeResponse> createNewAccountType(
            @RequestBody AccountTypeRequest request) {
        return new ResponseEntity<>(accountTypeService.createNewAccountType(request),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AccountTypeResponse>> getAllExistAccountTypes() {
        return new ResponseEntity<>(accountTypeService.getAllAccountTypes(), HttpStatus.OK);
    }

    @PutMapping("/{accountTypeName}")
    public ResponseEntity<AccountTypeResponse> editExistAccountType(
            @PathVariable String accountTypeName,
            @RequestBody AccountTypeRequest request) {
        return new ResponseEntity<>(accountTypeService.editAccountType(accountTypeName, request),
                HttpStatus.OK);
    }

    @DeleteMapping("/{accountTypeName}")
    public ResponseEntity<DeleteObjectResponse> deleteExistedAccountType(
            @PathVariable String accountTypeName) {
        return new ResponseEntity<>(accountTypeService.deleteAccountType(accountTypeName),
                HttpStatus.OK);
    }

}
