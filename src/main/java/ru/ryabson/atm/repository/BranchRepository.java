package ru.ryabson.atm.repository;

import jakarta.transaction.Transactional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.ryabson.atm.entity.Branch;

public interface BranchRepository extends JpaRepository<Branch, UUID> {

    Branch findBranchByBranchName(String branchName);

    @Transactional
    void deleteBranchByBranchName(String branchName);

}
