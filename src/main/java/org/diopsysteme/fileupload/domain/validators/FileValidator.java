package org.diopsysteme.fileupload.domain.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FileSizeValidator.class)
public @interface FileValidator {

    String message() default "le fichier depasse la taille acceptee";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

//    long maxSize() default 10 * 1024 * 1024;
}