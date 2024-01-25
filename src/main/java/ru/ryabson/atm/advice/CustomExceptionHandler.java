package ru.ryabson.atm.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.ryabson.atm.dto.responses.ErrorResponse;
import ru.ryabson.atm.exception.DuplicateNameException;
import ru.ryabson.atm.exception.EmptyListException;
import ru.ryabson.atm.exception.EmptyObjectException;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(DuplicateNameException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateNameException(
            DuplicateNameException exception) {
        logger.error(exception.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(
                exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyListException.class)
    public ResponseEntity<ErrorResponse> handleEmptyListException(EmptyListException exception) {
        logger.error(exception.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyObjectException.class)
    public ResponseEntity<ErrorResponse> handleEmptyObjectException(
            EmptyObjectException exception) {
        logger.error(exception.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


}
