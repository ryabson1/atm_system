package ru.ryabson.atm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity
@Table(name = "RBS_ACCOUNT")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID accountId;

    @Column(name = "create_date")
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd-HH:mm:ss")
    private LocalDate sysCreateTime;

    @Column(name = "number", nullable = false, unique = true)
    @Pattern(regexp = "\\d{20}", message = "field number must contain 20 symbols")
    private String accountNumber;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private AccountType accountType;

    @Column(name = "balance")
    private double accountBalance;

    @Column(name = "active")
    private boolean accountIsActive;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @ManyToOne
    @JoinColumn(name = "currency_type_id")
    private CurrencyCode currencyCode;

    public Account() {
        this.accountBalance = 0.0;
        this.sysCreateTime = LocalDate.now();
        this.accountIsActive = true;
    }

    @PrePersist
    public void prePersist() {
        if (accountNumber == null || accountNumber.isEmpty()) {
            accountNumber = "40702" + generateUniqueNumber();
        }
    }

    private String generateUniqueNumber() {
        return String.format("%015d", System.currentTimeMillis() % 1000000000000000L);
    }


}
