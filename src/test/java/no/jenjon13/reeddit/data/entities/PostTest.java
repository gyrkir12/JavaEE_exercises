package no.jenjon13.reeddit.data.entities;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class PostTest {
    private Validator validator;
    private Post testPost;

    @Before
    public void setUp() throws Exception {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
        validatorFactory.close();

        final SiteUser testAuthor = ValidTestUserFactory.create();
        testPost = ValidTestPostFactory.create(testAuthor);
    }

    @Test
    public void testDefaultCommentShouldValidate() throws Exception {
        final Set<ConstraintViolation<Post>> violations = validator.validate(testPost);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void testSizeConstraintsShouldBeHonored() throws Exception {
        String tooLongString = new String(new char[501]);
        testPost.setTitle(tooLongString);
        testPost.setContent(tooLongString);

        final Set<ConstraintViolation<Post>> violations = validator.validate(testPost);
        Assert.assertEquals(2, violations.size());
    }
}
