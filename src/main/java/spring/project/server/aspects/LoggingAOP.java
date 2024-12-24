/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.project.server.aspects;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import spring.project.server.exceptions.handler.ApiException;

/**
 * @author Veljko
 */
@Aspect
@Component
public class LoggingAOP {

    public static final Logger LOGGER = LoggerFactory.getLogger(LoggingAOP.class);

    @Pointcut("execution(public * spring.project.server.services.*.*(..))")
    public void servicesClasses() {
    }

    @Before("servicesClasses()")
    public void beforeServiceClassesLogger(final JoinPoint joinPoint) {
        LOGGER.info(
                "Calling service method {}() in service class {}",
                joinPoint.getSignature().getName(),
                joinPoint.getTarget().getClass().getSimpleName()
        );
    }

    @AfterReturning("servicesClasses()")
    public void afterReturnServiceClassesLogger(final JoinPoint joinPoint) {
        LOGGER.info(
                "Successfully called service {}() in service class {}",
                joinPoint.getSignature().getName(),
                joinPoint.getTarget().getClass().getSimpleName()
        );
    }

    @AfterThrowing(pointcut = "servicesClasses()", throwing = "ex")
    public void afterThrowServiceClassesLogger(final JoinPoint joinPoint, final Exception ex) {
        LOGGER.warn(
                "Unsuccessfully called service {}() in class {}. Reason:",
                joinPoint.getSignature().getName(),
                joinPoint.getTarget().getClass().getName(),
                ex
        );
    }

    @Pointcut("execution(public * spring.project.server.controllers.*.*(..))")
    public void controllersClasses() {
    }

    @AfterThrowing(pointcut = "controllersClasses()", throwing = "ex")
    public void afterThrowControllersClassesLogger(final JoinPoint joinPoint, final Exception ex) {
        LOGGER.warn(
                "Unsuccessfully called service {}() in class {}. Reason:",
                joinPoint.getSignature().getName(),
                joinPoint.getTarget().getClass().getName(),
                ex
        );
    }

    @Pointcut("execution(protected org.springframework.http" +
            ".ResponseEntity spring.project.server.exceptions.handler.RestExceptionHandler.handleMethodArgumentNotValid(..))")
    public void validationException() {
    }

    @AfterReturning(pointcut = "validationException()", returning = "result")
    public void afterValidationException(final ResponseEntity<ApiValidationException> result) {
        final ApiValidationException apiValidationException = result.getBody();
        LOGGER.info(
                "{}: {}",
                apiValidationException.getMessage(),
                apiValidationException.getDetails()
        );
    }

    @Pointcut("execution(protected org.springframework.http" +
            ".ResponseEntity spring.project.server.exceptions.handler.RestExceptionHandler.handleDatabaseError(..))")
    public void databaseException() {
    }

    @AfterReturning(pointcut = "databaseException()", returning = "result")
    public void afterFatalDatabaseException(final ResponseEntity<ApiException> result) {
        final ApiException apiException = result.getBody();
        LOGGER.error(
                "Cannot create database connection.", apiException
        );
    }
}
