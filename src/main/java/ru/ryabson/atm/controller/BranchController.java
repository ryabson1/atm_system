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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ryabson.atm.dto.requests.BranchRequest;
import ru.ryabson.atm.dto.responses.BranchResponse;
import ru.ryabson.atm.dto.responses.DeleteObjectResponse;
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

    @GetMapping
    public ResponseEntity<List<BranchResponse>> getAllBranches() {
        return new ResponseEntity<>(branchService.getBranchesList(), HttpStatus.OK);
    }

    @PutMapping("/{branchName}")
    public ResponseEntity<BranchResponse> updateBranch(@PathVariable String branchName,
            @RequestBody BranchRequest branchRequest) {
        System.out.println(branchName);
        return new ResponseEntity<>(branchService.updateExistedBranch(branchName, branchRequest),
                HttpStatus.OK);
    }

    @DeleteMapping("/{branchName}")
    public ResponseEntity<DeleteObjectResponse> deleteBranch(@PathVariable String branchName) {
        return new ResponseEntity<>(branchService.deleteBranch(branchName), HttpStatus.OK);
    }
}
