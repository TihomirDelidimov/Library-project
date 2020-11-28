package deltasource.eu.libraryproject.book;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ISBNValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsbnConstraint {
    String message() default "Invalid ISBN!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
