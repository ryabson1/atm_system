package ru.ryabson.atm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity
@Table(name = "RBS_BRANCH")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID branchId;

    @Column(name = "create_date")
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd-HH:mm:ss")
    private LocalDate sysCreateTime;

    @Column(name = "name")
    @Size(max = 100, message = "message cannot be more than 100 symbols")
    private String branchName;

    @Column(name = "ext_id", unique = true)
    private Long branchExtId;

    @Column(name = "is_active")
    private boolean branchIsActive;

    public Branch() {
        this.sysCreateTime = LocalDate.now();
        this.branchIsActive = true;
    }

    @PrePersist
    public void prePersist() {
        if (branchExtId == 0) {
            branchExtId = generateUniqueNumber();
        }
    }

    private Long generateUniqueNumber() {
        return System.currentTimeMillis();
    }
}
