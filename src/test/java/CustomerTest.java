import entities.Address;
import entities.Customer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;

public class CustomerTest {
    private ValidatorFactory validatorFactory;
    private Validator validator;

    @Before
    public void setUp() throws Exception {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @After
    public void tearDown() throws Exception {
        validatorFactory.close();
    }

    private Customer createTestCustomer() {
        final Customer customer = new Customer();
        customer.setFirstName("Jonas");
        customer.setSurname("Jensen");

        final Calendar calendarInstance = GregorianCalendar.getInstance();
        calendarInstance.set(1992, Calendar.DECEMBER, 24);
        customer.setDateOfBirth(calendarInstance.getTime());

        customer.setDateOfRegistration(new Date());

        final Address address = new Address();
        address.setCountry("Norway");
        address.setCity("Oslo");
        address.setStreet("John Colletts Allé 110 PB.150");
        customer.setAddress(address);
        return customer;
    }

    @Test
    public void testNormalCustomerShouldPass() throws Exception {
        final Customer customer = createTestCustomer();
        final Set<ConstraintViolation<Customer>> violationSet = validator.validate(customer);
        Assert.assertEquals(0, violationSet.size());
    }

    @Test
    public void testCustomerWithNonNullableNullValuesShouldFail() throws Exception {
        final Customer customer = new Customer();
        final Set<ConstraintViolation<Customer>> violations = validator.validate(customer);

        Assert.assertEquals(6, violations.size());
    }

    @Test
    public void testCustomerDatesCannotBeSetInFuture() throws Exception {
        final Customer testCustomer = createTestCustomer();

        final Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(3000, Calendar.MARCH, 1);
        testCustomer.setDateOfRegistration(calendar.getTime());
        testCustomer.setDateOfBirth(calendar.getTime());

        final Set<ConstraintViolation<Customer>> violations = validator.validate(testCustomer);
        Assert.assertEquals(3, violations.size());
    }

    @Test
    public void testDefaultCustomerAgeIsOver18() throws Exception {
        final Customer testCustomer = createTestCustomer();
        final Set<ConstraintViolation<Customer>> violations = validator.validate(testCustomer);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void testCustomerUnder18IsRejected() throws Exception {
        final Customer testCustomer = createTestCustomer();

        final Calendar calendar = GregorianCalendar.getInstance();

        calendar.add(Calendar.YEAR, -17);
        testCustomer.setDateOfBirth(calendar.getTime());

        final Set<ConstraintViolation<Customer>> violations = validator.validate(testCustomer);
        Assert.assertEquals(1, violations.size());
    }

    @Test
    public void testNoFieldOver64CharsAllowed() throws Exception {
        final String tooLongString = "THIS_FIELD_IS_OVER_SIXTY-FOUR_CHARACTERS_LONG_AND_THAT_IS_SIMPLY_UNACCEPTABLE";
        final Customer testCustomer = createTestCustomer();

        final Address customerAddress = new Address();
        customerAddress.setCountry(tooLongString);
        customerAddress.setState(tooLongString);
        customerAddress.setCity(tooLongString);
        customerAddress.setStreet(tooLongString);
        testCustomer.setAddress(customerAddress);

        testCustomer.setFirstName(tooLongString);
        testCustomer.setMiddleName(tooLongString);
        testCustomer.setSurname(tooLongString);

        final Set<ConstraintViolation<Customer>> violations = validator.validate(testCustomer);
        Assert.assertEquals(7, violations.size());
        violations.forEach(v -> Assert.assertEquals("size must be between 0 and 64", v.getMessage()));
    }
}
