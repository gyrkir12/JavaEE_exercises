package constraints;

import data.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AgeValidator implements ConstraintValidator<Age, User> {
    private int min;

    @Override
    public void initialize(Age age) {
        min = age.min();
    }

    @Override
    public boolean isValid(User user, ConstraintValidatorContext context) {
        if (user.getDateOfBirth() == null) return false;

        final Calendar calendar = GregorianCalendar.getInstance();
        calendar.add(Calendar.YEAR, -18);
        return calendar.toInstant().isAfter(user.getDateOfBirth().toInstant());
    }
}
