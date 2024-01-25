package ru.ryabson.atm.dto.responses;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ErrorResponse {

    private final LocalDateTime errorTimeStamp;
    private final String errorMessage;

    public ErrorResponse(String errorMessage) {
        this.errorTimeStamp = LocalDateTime.now();
        this.errorMessage = errorMessage;
    }
}
