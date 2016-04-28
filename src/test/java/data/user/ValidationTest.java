package data.user;

import data.Address;
import data.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;

public class ValidationTest {
    private Validator validator;
    private User testUser;

    @Before
    public void setUp() throws Exception {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
        testUser = ValidTestUserFactory.create();
        validatorFactory.close();
    }

    @Test
    public void testDefaultCustomerShouldValidate() throws Exception {
        final User user = ValidTestUserFactory.create();
        final Set<ConstraintViolation<User>> violationSet = validator.validate(user);
        Assert.assertEquals(0, violationSet.size());
    }

    @Test
    public void testCustomerWithNonNullableNullValuesShouldFail() throws Exception {
        final User user = new User();
        final Set<ConstraintViolation<User>> violations = validator.validate(user);

        Assert.assertEquals(8, violations.size());
    }

    @Test
    public void testCustomerDatesCannotBeSetInFuture() throws Exception {
        final Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(3000, Calendar.MARCH, 1);
        testUser.setDateOfRegistration(calendar.getTime());
        testUser.setDateOfBirth(calendar.getTime());

        final Set<ConstraintViolation<User>> violations = validator.validate(testUser);
        Assert.assertEquals(3, violations.size());
    }

    @Test
    public void testDefaultCustomerAgeIsOver18() throws Exception {
        final Set<ConstraintViolation<User>> violations = validator.validate(testUser);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void testCustomerUnder18IsRejected() throws Exception {
        final Calendar calendar = GregorianCalendar.getInstance();
        calendar.add(Calendar.YEAR, -17);
        testUser.setDateOfBirth(calendar.getTime());

        final Set<ConstraintViolation<User>> violations = validator.validate(testUser);
        Assert.assertEquals(1, violations.size());
    }

    @Test
    public void testNoFieldOver64CharsAllowed() throws Exception {
        final String tooLongString = "THIS_FIELD_IS_OVER_SIXTY-FOUR_CHARACTERS_LONG_AND_THAT_IS_SIMPLY_UNACCEPTABLE";

        final Address customerAddress = new Address();
        customerAddress.setCountry(tooLongString);
        customerAddress.setState(tooLongString);
        customerAddress.setCity(tooLongString);
        customerAddress.setStreet(tooLongString);
        testUser.setAddress(customerAddress);

        testUser.setFirstName(tooLongString);
        testUser.setMiddleName(tooLongString);
        testUser.setSurname(tooLongString);

        final Set<ConstraintViolation<User>> violations = validator.validate(testUser);
        Assert.assertEquals(7, violations.size());
        violations.forEach(v -> Assert.assertEquals("size must be between 0 and 64", v.getMessage()));
    }
}
