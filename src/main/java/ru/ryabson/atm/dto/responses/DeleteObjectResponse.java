package ru.ryabson.atm.dto.responses;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class DeleteObjectResponse {

    private final LocalDateTime errorTimeStamp;
    private String objectName;
    private final String deletedAnswer;

    public DeleteObjectResponse() {
        this.errorTimeStamp = LocalDateTime.now();
        this.deletedAnswer = "deleted succsessfully";
    }
}
