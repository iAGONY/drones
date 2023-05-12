package com.musala.drones.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.ConstraintViolation;
import lombok.*;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ApiError {

    private HttpStatus httpStatus;
    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss a")
    private LocalDateTime timestamp;
    private List<ApiSubError> apiErrors;

    public ApiError() {
        timestamp = LocalDateTime.now();
    }

    public ApiError(HttpStatus status) {
        this();
        this.httpStatus = status;
    }

    private void addSubError(ApiSubError subError) {
        if (apiErrors == null) {
            apiErrors = new ArrayList<>();
        }
        apiErrors.add(subError);
    }

    public void addValidationError(String field, Object rejectedValue, String message) {
        addSubError(new ApiValidationError(field, rejectedValue, message));
    }

    private void addValidationError(ConstraintViolation<?> cv) {
        this.addValidationError(((PathImpl) cv.getPropertyPath()).getLeafNode().asString(),
                cv.getInvalidValue(), cv.getMessage());
    }

    public void addValidationErrors(Set<ConstraintViolation<?>> constraintViolations) {
        constraintViolations.forEach(this::addValidationError);
    }
    public void addValidationErrors(List<FieldError> fieldErrors) {
        fieldErrors.forEach(this::addValidationError);
    }

    private void addValidationError(FieldError fieldError) {
        this.addValidationError(fieldError.getField(), fieldError.getRejectedValue(),
                fieldError.getDefaultMessage());
    }

    public void addValidationError(List<ObjectError> globalErrors) {
        globalErrors.forEach(this::addValidationError);
    }

    private void addValidationError(ObjectError objectError) {
        this.addValidationError(objectError.getObjectName(), objectError.getDefaultMessage());
    }

    private void addValidationError(String object, String message) {
        addSubError(new ApiValidationError(object, message));
    }

    public abstract class ApiSubError {

    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    @AllArgsConstructor
    public class ApiValidationError extends ApiSubError {

        private String field;
        private Object rejectedValue;
        private String message;

        public ApiValidationError(String object, String message) {
            this.message = message;
        }
    }
}
