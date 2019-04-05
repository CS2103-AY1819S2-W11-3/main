package seedu.travel.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.travel.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.travel.testutil.TypicalPlaces.DANIEL;
import static seedu.travel.testutil.TypicalPlaces.ELLE;
import static seedu.travel.testutil.TypicalPlaces.FIONA;
import static seedu.travel.testutil.TypicalPlaces.getTypicalTravelBuddy;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

import seedu.travel.commons.core.Messages;
import seedu.travel.logic.CommandHistory;
import seedu.travel.model.Model;
import seedu.travel.model.ModelManager;
import seedu.travel.model.UserPrefs;
import seedu.travel.model.place.NameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code SearchCommand}.
 */
public class SearchCommandTest {
    private Model model = new ModelManager(getTypicalTravelBuddy(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTravelBuddy(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        SearchCommand searchFirstCommand = new SearchCommand(firstPredicate);
        SearchCommand searchSecondCommand = new SearchCommand(secondPredicate);

        // same object -> returns true
        assertTrue(searchFirstCommand.equals(searchFirstCommand));

        // same values -> returns true
        SearchCommand searchFirstCommandCopy = new SearchCommand(firstPredicate);
        assertTrue(searchFirstCommand.equals(searchFirstCommandCopy));

        // different types -> returns false
        assertFalse(searchFirstCommand.equals(1));

        // null -> returns false
        assertFalse(searchFirstCommand.equals(null));

        // different place -> returns false
        assertFalse(searchFirstCommand.equals(searchSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPlaceFound() {
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        String expectedMessage = constructExpectedMessage(predicate, 0);
        SearchCommand command = new SearchCommand(predicate);
        expectedModel.updateFilteredPlaceList(predicate);
        assertCommandSuccess(command, model, commandHistory, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPlaceList());
    }

    @Test
    public void execute_multipleKeywords_multiplePlacesFound() {
        NameContainsKeywordsPredicate predicate = preparePredicate("Cemetery University Zoo");
        String expectedMessage = constructExpectedMessage(predicate, 3);
        SearchCommand command = new SearchCommand(predicate);
        expectedModel.updateFilteredPlaceList(predicate);
        assertCommandSuccess(command, model, commandHistory, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DANIEL, ELLE, FIONA), model.getFilteredPlaceList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Constructs the expected message from running SearchCommand
     * @param predicate the keywords used in the query
     * @param expectedNumberOfPlaces expected number of places that will be returned
     * @return A string that contains the expected message upon running SearchCommand
     */
    private String constructExpectedMessage(NameContainsKeywordsPredicate predicate, int expectedNumberOfPlaces) {
        StringBuilder expectedMessage = new StringBuilder();
        expectedMessage.append(SearchCommand.COMMAND_WORD);
        expectedMessage.append(" ");
        expectedMessage.append(predicate.getKeywords());
        expectedMessage.append(": ");
        expectedMessage.append(String.format(Messages.MESSAGE_PLACES_LISTED_OVERVIEW,
                expectedNumberOfPlaces));
        return expectedMessage.toString();
    }
}
