package seedu.address.model.person;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class DescriptionTest {

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidEmail = "@";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Description(invalidEmail));
    }

    @Test
    public void isValidDescription() {
        // null email
        Assert.assertThrows(NullPointerException.class, () -> Description.isValidDescription(null));

        // blank email
        assertFalse(Description.isValidDescription("")); // empty string
        assertFalse(Description.isValidDescription(" ")); // spaces only

        // invalid parts
        assertFalse(Description.isValidDescription("@")); // start with non-alphabet
        assertFalse(Description.isValidDescription("1")); // start with non-alphabet
        assertFalse(Description.isValidDescription("")); // start with non-alphabet

        // valid description
        assertTrue(Description.isValidDescription("I love this place.")); // start with uppercase alphabet
        assertTrue(Description.isValidDescription("i love this place.")); // start with lowercase alphabet
        assertTrue(Description.isValidDescription("No description added.")); // default description
    }
}
