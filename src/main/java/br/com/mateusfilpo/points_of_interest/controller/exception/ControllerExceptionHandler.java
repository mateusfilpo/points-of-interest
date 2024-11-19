package br.com.mateusfilpo.points_of_interest.controller.exception;

import br.com.mateusfilpo.points_of_interest.service.exception.InvalidDataException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<StandardError> objectNotFound(InvalidDataException e, HttpServletRequest request){

        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Dado inválido", request.getRequestURI());
        err.getErrors().addAll(e.getMsgs());

        return ResponseEntity.status(status).body(err);
    }
}
