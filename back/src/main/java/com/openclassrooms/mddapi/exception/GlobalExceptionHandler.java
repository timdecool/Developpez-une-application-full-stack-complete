/**
 * Class handling various exceptions that may occur in the application and returning appropriate responses to the client.
 */

package com.openclassrooms.mddapi.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles errors that occur when invalid credentials are provided.
     * @param ex: InvalidCredentialsException
     * @return ResponseEntity (401)
     */
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<String> handleInvalidCredentials(InvalidCredentialsException ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ex.getMessage());
    }

    /**
     * Handles errors that occur when provided data is not unique and should be, such as in user creation operations.
     * @param ex : NotUniqueException
     * @return ResponseEntity (400)
     */
    @ExceptionHandler(NotUniqueException.class)
    public ResponseEntity<String> handleNotUnique(NotUniqueException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }


    /**
     * Handles errors that occur when required resource does not exist.
     * @param ex: NotSuchElementException
     * @return ResponseEntity (404)
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNotFound(NoSuchElementException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    /**
     * Handles errors that occur when any illegal argument is given from the client.
     * @param ex: IllegalArgumentException
     * @return ResponseEntity(400)
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleBadRequest(IllegalArgumentException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    /**
     * Handles errors that occur when request body is not readable.
     * @param ex: HttpMessageNotReadableException
     * @return ResponseEntity (400)
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleJsonParseException(HttpMessageNotReadableException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Request body is either invalid or incomplete");
    }

    /**
     * Handles errors that are raised when request body contains invalid values.
     * @param ex: MethodArgumentNotValidException
     * @return ResponseEntity (400)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("; "));

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorMessage);
    }

    /**
     * Handles any other errors that are not supported by other handlers.
     * @param ex: Exception
     * @return ResponseEntity (500)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneric(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An unexpected error occurred");
    }
}