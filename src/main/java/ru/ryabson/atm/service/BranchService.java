package ru.ryabson.atm.service;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.ryabson.atm.dto.requests.BranchRequest;
import ru.ryabson.atm.dto.responses.BranchResponse;
import ru.ryabson.atm.dto.responses.DeleteObjectResponse;
import ru.ryabson.atm.entity.Branch;
import ru.ryabson.atm.exception.DuplicateNameException;
import ru.ryabson.atm.exception.EmptyListException;
import ru.ryabson.atm.exception.EmptyObjectException;
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

    public List<BranchResponse> getBranchesList() {
        if (branchRepository.findAll().isEmpty()) {
            logger.error("Branch List is empty");
            throw new EmptyListException("There is no any records in Branch view");
        }
        List<BranchResponse> responseList = new ArrayList<>();
        branchRepository.findAll().forEach(
                (branch) -> responseList.add(branchMapper.mapBranchToBranchResponse(branch)));
        return responseList;
    }

    public BranchResponse updateExistedBranch(String branchName, BranchRequest branchRequest) {
        System.out.println(branchName);
        if (branchRepository.findBranchByBranchName(branchName) == null) {
            logger.error("There is no such Branch like " + branchName);
            throw new EmptyObjectException("Please, enter the right type of branch");
        }
        Branch branch = branchRepository.findBranchByBranchName(branchName);
        branch.setBranchName(branchRequest.getBranchName());
        branchRepository.save(branch);
        logger.info("Branch name " + branchName + " was changed successfully on " + branch
                .getBranchName());
        return branchMapper.mapBranchToBranchResponse(branch);
    }

    public DeleteObjectResponse deleteBranch(String branchName) {
        if (branchRepository.findBranchByBranchName(branchName) == null) {
            logger.error("branch with name " + branchName + " does not exist");
            throw new EmptyObjectException("branch with name " + branchName + " does not exist");
        }
        branchRepository.deleteBranchByBranchName(branchName);
        DeleteObjectResponse response = new DeleteObjectResponse();
        response.setObjectName(branchName);
        return response;
    }


}
