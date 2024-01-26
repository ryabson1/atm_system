package ru.ryabson.atm.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ryabson.atm.dto.requests.BranchRequest;
import ru.ryabson.atm.dto.responses.BranchResponse;
import ru.ryabson.atm.entity.Branch;

@Mapper(componentModel = "spring")
public interface BranchMapper {

    @Mapping(target = "branchId", ignore = true)
    Branch mapBranchRequestToBranch(BranchRequest request);

    @Mapping(target = "branchId", ignore = false)
    BranchResponse mapBranchToBranchResponse(Branch branch);

}
