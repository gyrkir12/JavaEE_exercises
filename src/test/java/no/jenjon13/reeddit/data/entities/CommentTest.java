package no.jenjon13.reeddit.data.entities;

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

public class CommentTest {
    private Validator validator;
    private Comment testComment;

    @Before
    public void setUp() throws Exception {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
        validatorFactory.close();

        final SiteUser testAuthor = ValidTestUserFactory.create();
        testComment = ValidTestCommentFactory.create(testAuthor);
    }

    @Test
    public void testDefaultCommentShouldValidate() throws Exception {
        final Set<ConstraintViolation<Comment>> violations = validator.validate(testComment);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void testSizeConstraintsShouldBeHonored() throws Exception {
        String tooLongString = new String(new char[501]);
        testComment.setTitle(tooLongString);
        testComment.setContent(tooLongString);

        final Set<ConstraintViolation<Comment>> violations = validator.validate(testComment);
        Assert.assertEquals(2, violations.size());
    }

    @Test
    public void testCommentTimestampCannotBeSetInFuture() throws Exception {
        final Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(3000, Calendar.MARCH, 1);
        testComment.setTimestamp(calendar.getTime());

        final Set<ConstraintViolation<Comment>> violations = validator.validate(testComment);
        Assert.assertEquals(1, violations.size());
    }
}
