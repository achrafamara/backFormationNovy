package org.example.projet.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleDataNotFoundException(DataNotFoundException dataNotFoundException)
    {

        ProblemDetail problemDetail=ProblemDetail.
                forStatus(HttpStatus.NOT_FOUND.value());
        problemDetail.setTitle("Data not found");
        problemDetail.setDetail(dataNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value())
                .body(problemDetail);
    }
}
