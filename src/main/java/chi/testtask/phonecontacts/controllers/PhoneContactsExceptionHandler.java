package chi.testtask.phonecontacts.controllers;

import chi.testtask.phonecontacts.services.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class PhoneContactsExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiErrorInfo> handleNotFoundException(NotFoundException ex) {
        ApiErrorInfo errorInfo = ApiErrorInfo.builder()
                .title(ex.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .build();

        return new ResponseEntity<>(errorInfo, errorInfo.getStatus());
    }

    //Validation exceptions

    //
}
