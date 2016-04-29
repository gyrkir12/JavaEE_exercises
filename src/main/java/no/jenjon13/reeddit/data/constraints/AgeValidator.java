package no.jenjon13.reeddit.data.constraints;

import no.jenjon13.reeddit.data.entities.SiteUser;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AgeValidator implements ConstraintValidator<Age, SiteUser> {
    private int min;

    @Override
    public void initialize(Age age) {
        min = age.min();
    }

    @Override
    public boolean isValid(SiteUser siteUser, ConstraintValidatorContext context) {
        if (siteUser.getDateOfBirth() == null) return false;

        final Calendar calendar = GregorianCalendar.getInstance();
        calendar.add(Calendar.YEAR, -18);
        return calendar.toInstant().isAfter(siteUser.getDateOfBirth().toInstant());
    }
}
