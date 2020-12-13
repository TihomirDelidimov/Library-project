package deltasource.eu.libraryproject.book;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class IsbnValidator implements ConstraintValidator<IsbnConstraint, String> {

    @Override
    public void initialize(IsbnConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(String isbn, ConstraintValidatorContext constraintValidatorContext) {
        if (isbn == null || isbn.trim().isEmpty()) {
            return false;
        }
        isbn = isbn.replaceAll("-", "");
        if (isbn.length() == 10) {
            return validateIsbn10(isbn);
        } else if (isbn.length() == 13) {
            return validateIsbn13(isbn);
        } else {
            return false;
        }
    }

    private boolean validateIsbn13( String isbn ) {
        try {
            int sum = 0;
            for ( int i = 0; i < 12; i++ ) {
                int digit = Integer.parseInt( isbn.substring( i, i + 1 ) );
                sum += (i % 2 == 0) ? digit * 1 : digit * 3;
            }
            int checksum = 10 - (sum % 10);
            if ( checksum == 10 ) {
                checksum = 0;
            }
            return checksum == Integer.parseInt( isbn.substring( 12 ) );
        }
        catch ( NumberFormatException nfe ) {
            return false;
        }
    }

    private boolean validateIsbn10( String isbn ) {
        try {
            int sum = 0;
            for ( int i = 0; i < 9; i++ ) {
                int digit = Integer.parseInt( isbn.substring( i, i + 1 ) );
                sum += ((10 - i) * digit);
            }
            String checksum = Integer.toString( (11 - (sum % 11)) % 11 );
            if ( "10".equals( checksum ) )
            {
                checksum = "X";
            }
            return checksum.equals( isbn.substring( 9 ) );
        }
        catch ( NumberFormatException nfe )
        {
            return false;
        }
    }
}
