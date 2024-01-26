package ru.ryabson.atm.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.ryabson.atm.dto.requests.BranchRequest;
import ru.ryabson.atm.dto.responses.BranchResponse;
import ru.ryabson.atm.entity.Branch;
import ru.ryabson.atm.exception.DuplicateNameException;
import ru.ryabson.atm.mapper.BranchMapper;
import ru.ryabson.atm.repository.BranchRepository;

@Service
@AllArgsConstructor
public class BranchService {

    private static final Logger logger = LoggerFactory.getLogger(BranchService.class);

    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper;

    public BranchResponse createNewBranch(BranchRequest request) {

        String branchName = request.getBranchName();

        if (branchRepository.findBranchByBranchName(branchName) != null) {
            logger.error("branch with name " + branchName + " exist already");
            throw new DuplicateNameException("branch with name " + branchName + " exist already");
        }

        Branch branch = branchMapper.mapBranchRequestToBranch(request);

        return branchMapper.mapBranchToBranchResponse(branchRepository.save(branch));
    }

}
