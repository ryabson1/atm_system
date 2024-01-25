package ru.ryabson.atm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity
@Table(name = "RBS_ACCOUNT_TYPE")
public class AccountType {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID accountTypeId;

    @Column(name = "sysCreateTime")
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd-HH:mm:ss")
    private LocalDate sysCreateTime;

    @Column(name = "type_name", unique = true)
    private String accountTypeName;

    public AccountType() {
        this.sysCreateTime = LocalDate.now();
    }
}
