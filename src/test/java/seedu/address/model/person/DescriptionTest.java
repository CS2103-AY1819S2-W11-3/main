package seedu.address.model.person;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class DescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Description(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidEmail = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Description(invalidEmail));
    }

    @Test
    public void isValidDescription() {
        // null email
        Assert.assertThrows(NullPointerException.class, () -> Description.isValidDescription(null));

        // blank email
        assertFalse(Description.isValidDescription("")); // empty string
        assertFalse(Description.isValidDescription(" ")); // spaces only

        // missing parts
        assertFalse(Description.isValidDescription("@example.com")); // missing local part
        assertFalse(Description.isValidDescription("peterjackexample.com")); // missing '@' symbol
        assertFalse(Description.isValidDescription("peterjack@")); // missing domain name

        // invalid parts
        assertFalse(Description.isValidDescription("peterjack@-")); // invalid domain name
        assertFalse(Description.isValidDescription("peterjack@exam_ple.com")); // underscore in domain name
        assertFalse(Description.isValidDescription("peter jack@example.com")); // spaces in local part
        assertFalse(Description.isValidDescription("peterjack@exam ple.com")); // spaces in domain name
        assertFalse(Description.isValidDescription(" peterjack@example.com")); // leading space
        assertFalse(Description.isValidDescription("peterjack@example.com ")); // trailing space
        assertFalse(Description.isValidDescription("peterjack@@example.com")); // double '@' symbol
        assertFalse(Description.isValidDescription("peter@jack@example.com")); // '@' symbol in local part
        assertFalse(Description.isValidDescription("peterjack@example@com")); // '@' symbol in domain name
        assertFalse(Description.isValidDescription("peterjack@.example.com")); // domain name starts with a period
        assertFalse(Description.isValidDescription("peterjack@example.com.")); // domain name ends with a period
        assertFalse(Description.isValidDescription("peterjack@-example.com")); // domain name starts with a hyphen
        assertFalse(Description.isValidDescription("peterjack@example.com-")); // domain name ends with a hyphen

        // valid description
        assertTrue(Description.isValidDescription("PeterJack_1190@example.com"));
        assertTrue(Description.isValidDescription("a@bc"));
        assertTrue(Description.isValidDescription("test@localhost"));
        assertTrue(Description.isValidDescription("!#$%&'*+/=?`{|}~^.-@example.org"));
        assertTrue(Description.isValidDescription("123@145"));
        assertTrue(Description.isValidDescription("a1+be!@example1.com"));
        assertTrue(Description.isValidDescription("peter_jack@very-very-very-long-example.com"));
        assertTrue(Description.isValidDescription("if.you.dream.it_you.can.do.it@example.com"));
    }
}
