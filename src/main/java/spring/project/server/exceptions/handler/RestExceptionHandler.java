/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.project.server.exceptions.handler;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import spring.project.server.exceptions.BusyUsernameException;
import spring.project.server.exceptions.MatchNotFound;
import spring.project.server.exceptions.SelectionNotFoundException;
import spring.project.server.exceptions.UserNotFoundException;
import spring.project.server.exceptions.WrongCredentials;

import java.time.ZonedDateTime;

/**
 * @author Veljko
 */
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {SelectionNotFoundException.class})
    public ResponseEntity<Object> handleSelectionNotFound(final SelectionNotFoundException ex) {
        final ApiException exception = new ApiException(ex.getMessage(), HttpStatus.NOT_FOUND, ZonedDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleDataIntegrityViolation(final DataIntegrityViolationException ex) {
        final String bodyOfResponse = "Data integrity violation exception";
        final ApiException exception = new ApiException(bodyOfResponse + ":" + ex.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {EmptyResultDataAccessException.class})
    public ResponseEntity<Object> handleEmptyResultDataAccess(final EmptyResultDataAccessException ex) {
        final ApiException exception = new ApiException(ex.getMessage(), HttpStatus.NOT_FOUND, ZonedDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<Object> handleUserNotFound(final UserNotFoundException ex) {
        final ApiException exception = new ApiException(ex.getMessage(), HttpStatus.NOT_FOUND, ZonedDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {WrongCredentials.class})
    public ResponseEntity<Object> handleWrongCredentials(final WrongCredentials ex) {
        final ApiException exception = new ApiException(ex.getMessage(), HttpStatus.UNAUTHORIZED, ZonedDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {CannotCreateTransactionException.class})
    public ResponseEntity<Object> handleDatabaseError() {
        final String bodyOfResponse = "Server side error.";
        final ApiException exception = new ApiException(bodyOfResponse, HttpStatus.SERVICE_UNAVAILABLE, ZonedDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(value = {MatchNotFound.class})
    public ResponseEntity<Object> handleMatchNotFound(final MatchNotFound ex) {
        final ApiException exception = new ApiException(ex.getMessage(), HttpStatus.NOT_FOUND, ZonedDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<Object> handleEntityNotFoundException(final EntityNotFoundException ex) {
        final ApiException exception = new ApiException(ex.getMessage(), HttpStatus.NOT_FOUND, ZonedDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {BusyUsernameException.class})
    public ResponseEntity<Object> handleBusyUsernameException(final BusyUsernameException ex) {
        final ApiException exception = new ApiException(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE, ZonedDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.NOT_ACCEPTABLE);
    }

}
