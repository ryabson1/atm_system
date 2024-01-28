package ru.ryabson.atm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity
@Table(name = "RBS_CURR_CODE")
public class CurrencyCode {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID currencyCodeId;

    @Column(name = "sysCreateTime")
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd-HH:mm:ss")
    private LocalDate sysCreateTime;

    @Column(name = "iso_code")
    @Size(max = 3, message = "ISO CODE cannot be more than 3 symbols")
    private String currencyIsoCode;

    @Column(name = "code")
    private int currencyCode;

    @Column(name = "description")
    @Size(max = 100, message = "description cannot be more than 100 symbols")
    private String currencyDescription;

    public CurrencyCode() {
        this.sysCreateTime = LocalDate.now();
    }
}
