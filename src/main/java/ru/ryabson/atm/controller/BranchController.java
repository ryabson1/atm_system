package ru.ryabson.atm.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ryabson.atm.dto.requests.BranchRequest;
import ru.ryabson.atm.dto.responses.BranchResponse;
import ru.ryabson.atm.service.BranchService;

@RestController
@RequestMapping("api/v1/admin/branches")
@AllArgsConstructor
public class BranchController {

    private final BranchService branchService;

    @PostMapping("/create")
    public ResponseEntity<BranchResponse> createNewBranch(@RequestBody BranchRequest request) {
        return new ResponseEntity<>(branchService.createNewBranch(request), HttpStatus.CREATED);
    }


}
