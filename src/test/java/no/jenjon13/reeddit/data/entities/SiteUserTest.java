package no.jenjon13.reeddit.data.entities;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class SiteUserTest {
    private Validator validator;

    @Before
    public void setUp() throws Exception {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
        validatorFactory.close();
    }

    @Test
    public void testDefaultCustomerShouldValidate() throws Exception {
        final SiteUser siteUser = ValidTestUserFactory.create();
        final Set<ConstraintViolation<SiteUser>> violationSet = validator.validate(siteUser);
        Assert.assertEquals(0, violationSet.size());
    }

    @Test
    public void testCustomerWithNonNullableNullValuesShouldFail() throws Exception {
        final SiteUser siteUser = new SiteUser();
        final Set<ConstraintViolation<SiteUser>> violations = validator.validate(siteUser);

        Assert.assertTrue(violations.size() > 0);
    }

}
