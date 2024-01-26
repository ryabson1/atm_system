package ru.ryabson.atm.dto.responses;

import java.time.LocalDate;
import java.util.UUID;
import lombok.Data;

@Data
public class BranchResponse {

    private UUID branchId;
    private LocalDate sysCreateTime;
    private String branchName;
    private Long branchExtId;
    private boolean branchIsActive;

}
