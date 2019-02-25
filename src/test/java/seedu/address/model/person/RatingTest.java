package seedu.address.model.person;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class RatingTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Rating(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidRating = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Rating(invalidRating));
    }

    @Test
    public void isValidRating() {
        // null phone number
        Assert.assertThrows(NullPointerException.class, () -> Rating.isValidRating(null));

        // invalid phone numbers
        assertFalse(Rating.isValidRating("")); // empty string
        assertFalse(Rating.isValidRating(" ")); // spaces only
        assertFalse(Rating.isValidRating("0")); // less than 1
        assertFalse(Rating.isValidRating("6")); // greater than 5
        assertFalse(Rating.isValidRating("rating")); // non-numeric
        assertFalse(Rating.isValidRating("3a")); // alphabets with digits
        assertFalse(Rating.isValidRating(" 4")); // spaces before digits

        // valid phone numbers
        assertTrue(Rating.isValidRating("2")); // exactly 1 number within 1-5
        assertTrue(Rating.isValidRating("1"));
        assertTrue(Rating.isValidRating("5"));
    }
}
