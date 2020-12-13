package deltasource.eu.libraryproject.book;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Annotation to check for valid book ISBN. The ISBN can be 10 or 13 digit long.
 */
@Documented
@Constraint(validatedBy = IsbnValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsbnConstraint {
    String message() default "Invalid ISBN!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
