package constraints;

import entities.Customer;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AgeValidator implements ConstraintValidator<Age, Customer> {
    private int min;

    @Override
    public void initialize(Age age) {
        min = age.min();
    }

    @Override
    public boolean isValid(Customer customer, ConstraintValidatorContext context) {
        if (customer.getDateOfBirth() == null) return false;

        final Calendar calendar = GregorianCalendar.getInstance();
        calendar.add(Calendar.YEAR, -18);
        return calendar.toInstant().isAfter(customer.getDateOfBirth().toInstant());
    }
}
