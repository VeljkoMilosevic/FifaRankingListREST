/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.project.server.aspects;


import org.slf4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import spring.project.server.FifaRangListServerApplication;
import spring.project.server.exceptions.handler.ApiException;

/**
 * @author Veljko
 */
@Aspect
@Component
public class LoggingAOP {

    public static final Logger LOGGER = LoggerFactory.getLogger(FifaRangListServerApplication.class);

    @Pointcut("execution(public * spring.project.server.services.*.*(..))")
    public void servicesClasses() {}

    @Before("servicesClasses()")
    public void beforeServiceClassesLogger(final JoinPoint joinPoint) {
        LOGGER.info("Calling service method " + joinPoint.getSignature().getName() + "() in service class " + joinPoint.getTarget().getClass().getSimpleName());
    }

    @AfterReturning("servicesClasses()")
    public void afterReturnServiceClassesLogger(final JoinPoint joinPoint) {
        LOGGER.info("Successfully called service " + joinPoint.getSignature().getName()
                + "() in service class " + joinPoint.getTarget().getClass().getSimpleName());
    }

    @AfterThrowing(pointcut = "servicesClasses()", throwing = "ex")
    public void afterThrowServiceClassesLogger(final JoinPoint joinPoint, final Exception ex) {
        LOGGER.error("Unsuccessfully called service " + joinPoint.getSignature().getName() + "() in class "
                + joinPoint.getTarget().getClass().getName() + ". Reason is " + ex);
    }

    @Pointcut("execution(public * spring.project.server.controllers.*.*(..))")
    public void controllersClasses() {
    }

    @AfterThrowing(pointcut = "controllersClasses()", throwing = "ex")
    public void afterThrowControllersClassesLogger(final JoinPoint joinPoint, final Exception ex) {
        LOGGER.info("Exception in REST controller " + joinPoint.getSignature().getName() + "() in class "
                + joinPoint.getTarget().getClass().getName() + ". Reason is " + ex);
    }

    @Pointcut("execution(protected org.springframework.http" +
            ".ResponseEntity spring.project.server.exceptions.handler.RestExceptionHandler.handleMethodArgumentNotValid(..))")
    public void validationException() {
    }

    @AfterReturning(pointcut = "validationException()", returning = "result")
    public void afterValidationException(final JoinPoint joinPoint, final ResponseEntity result) {
        final ApiValidationException apiValidationException = (ApiValidationException) result.getBody();
        LOGGER.info(apiValidationException.getMessage() + ":" + apiValidationException.getDetails());
    }

    @Pointcut("execution(protected org.springframework.http" +
            ".ResponseEntity spring.project.server.exceptions.handler.RestExceptionHandler.handleDatabaseError(..))")
    public void databaseException() {
    }

    @AfterReturning(pointcut = "databaseException()", returning = "result")
    public void afterFatalDatabaseException(final JoinPoint joinPoint, final ResponseEntity result) {
        final ApiException apiException = (ApiException) result.getBody();
        LOGGER.error(apiException.getMessage() + " Cannot create " + "database connection.");
    }
}
