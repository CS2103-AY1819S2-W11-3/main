package systemtests;

import static org.junit.Assert.assertFalse;
import static seedu.travel.commons.core.Messages.MESSAGE_PLACES_LISTED_OVERVIEW;
import static seedu.travel.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.travel.testutil.TypicalPlaces.ALICE;
import static seedu.travel.testutil.TypicalPlaces.BENSON;
import static seedu.travel.testutil.TypicalPlaces.CARL;
import static seedu.travel.testutil.TypicalPlaces.ELLE;
import static seedu.travel.testutil.TypicalPlaces.FIONA;
import static seedu.travel.testutil.TypicalPlaces.KEYWORD_MATCHING_SHOPPING_MALL;

import org.junit.Test;

import seedu.travel.commons.core.index.Index;
import seedu.travel.logic.commands.DeleteCommand;
import seedu.travel.logic.commands.RedoCommand;
import seedu.travel.logic.commands.SearchTagsCommand;
import seedu.travel.logic.commands.UndoCommand;
import seedu.travel.model.Model;

public class SearchTagsCommandSystemTest extends TravelBuddySystemTest {

    @Test
    public void find() {
        /* Case: find multiple places in travel book, command with leading spaces and trailing spaces
         * -> 2 places found
         */
        String command = "   " + SearchTagsCommand.COMMAND_WORD + " " + KEYWORD_MATCHING_SHOPPING_MALL + "   ";
        Model expectedModel = getModel();
        ModelHelper.setFilteredList(expectedModel, ALICE, BENSON);
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: repeat previous find command where place list is displaying the places we are finding
         * -> 2 places found
         */
        command = SearchTagsCommand.COMMAND_WORD + " " + KEYWORD_MATCHING_SHOPPING_MALL;
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: find place where place list is not displaying the place we are finding -> 1 place found */
        command = SearchTagsCommand.COMMAND_WORD + " school";
        ModelHelper.setFilteredList(expectedModel, ELLE);
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: find multiple places in travel book, 2 keywords -> 2 places found */
        command = SearchTagsCommand.COMMAND_WORD + " school zoo";
        ModelHelper.setFilteredList(expectedModel, ELLE, FIONA);
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: find multiple places in travel book, 2 keywords in reversed order -> 2 places found */
        command = SearchTagsCommand.COMMAND_WORD + " zoo school";
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: find multiple places in travel book, 2 keywords with 1 repeat -> 2 places found */
        command = SearchTagsCommand.COMMAND_WORD + " zoo school zoo";
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: find multiple places in travel book, 2 matching keywords and 1 non-matching keyword
         * -> 2 places found
         */
        command = SearchTagsCommand.COMMAND_WORD + " zoo school NonMatchingKeyword";
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: undo previous find command -> rejected */
        command = UndoCommand.COMMAND_WORD;
        String expectedResultMessage = UndoCommand.MESSAGE_FAILURE;
        assertCommandFailure(command, expectedResultMessage);

        /* Case: redo previous find command -> rejected */
        command = RedoCommand.COMMAND_WORD;
        expectedResultMessage = RedoCommand.MESSAGE_FAILURE;
        assertCommandFailure(command, expectedResultMessage);

        /* Case: find same places in travel book after deleting 1 of them -> 0 places found */
        executeCommand(DeleteCommand.COMMAND_WORD + " 1");
        assertFalse(getModel().getTravelBuddy().getPlaceList().contains(ELLE));
        command = SearchTagsCommand.COMMAND_WORD + " " + "school";
        expectedModel = getModel();
        ModelHelper.setFilteredList(expectedModel);
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: find place in travel book, keyword is same as tags but of different case -> 2 places found */
        command = SearchTagsCommand.COMMAND_WORD + " sHopPinGmALL";
        ModelHelper.setFilteredList(expectedModel, ALICE, BENSON);
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: find place in travel book, keyword is substring of name -> 0 places found */
        command = SearchTagsCommand.COMMAND_WORD + " temp";
        ModelHelper.setFilteredList(expectedModel);
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: find place in travel book, name is substring of keyword -> 0 places found */
        command = SearchTagsCommand.COMMAND_WORD + " temples";
        ModelHelper.setFilteredList(expectedModel);
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: find place not in travel book -> 0 places found */
        command = SearchTagsCommand.COMMAND_WORD + " mosque";
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: find name of place in travel book -> 0 places found */
        command = SearchTagsCommand.COMMAND_WORD + " " + CARL.getName().fullName;
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: find rating of place in travel book -> 0 places found */
        command = SearchTagsCommand.COMMAND_WORD + " " + CARL.getRating().value;
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: find travel of place in travel book -> 0 places found */
        command = SearchTagsCommand.COMMAND_WORD + " " + CARL.getAddress().value;
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: find description of place in travel book -> 0 places found */
        command = SearchTagsCommand.COMMAND_WORD + " " + CARL.getDescription().value;
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: find while a place is selected -> selected card deselected */
        showAllPlaces();
        selectPlace(Index.fromOneBased(1));
        assertFalse(getPlaceListPanel().getHandleToSelectedCard().getName().equals(CARL.getName().fullName));
        command = SearchTagsCommand.COMMAND_WORD + " amusementPark";
        ModelHelper.setFilteredList(expectedModel, CARL);
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardDeselected();

        /* Case: find place in empty travel book -> 0 places found */
        deleteAllPlaces();
        command = SearchTagsCommand.COMMAND_WORD + " " + KEYWORD_MATCHING_SHOPPING_MALL;
        expectedModel = getModel();
        ModelHelper.setFilteredList(expectedModel, CARL);
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: mixed case command word -> rejected */
        command = "sEaRcHt Temple";
        assertCommandFailure(command, MESSAGE_UNKNOWN_COMMAND);
    }

    /**
     * Executes {@code command} and verifies that the command box displays an empty string, the result display
     * box displays {@code Messages#MESSAGE_PLACES_LISTED_OVERVIEW} with the number of people in the filtered list,
     * and the model related components equal to {@code expectedModel}.
     * These verifications are done by
     * {@code TravelBuddySystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
     * Also verifies that the status bar remains unchanged, and the command box has the default style class, and the
     * selected card updated accordingly, depending on {@code cardStatus}.
     * @see TravelBuddySystemTest#assertApplicationDisplaysExpected(String, String, Model)
     */
    private void assertCommandSuccess(String command, Model expectedModel) {
        String expectedResultMessage = command.trim().concat(": ").concat(String.format(
                MESSAGE_PLACES_LISTED_OVERVIEW, expectedModel.getFilteredPlaceList().size()));

        executeCommand(command);
        assertApplicationDisplaysExpected("", expectedResultMessage, expectedModel);
        assertCommandBoxShowsDefaultStyle();
        assertStatusBarUnchanged();
    }

    /**
     * Executes {@code command} and verifies that the command box displays {@code command}, the result display
     * box displays {@code expectedResultMessage} and the model related components equal to the current model.
     * These verifications are done by
     * {@code TravelBuddySystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
     * Also verifies that the browser url, selected card and status bar remain unchanged, and the command box has the
     * error style.
     * @see TravelBuddySystemTest#assertApplicationDisplaysExpected(String, String, Model)
     */
    private void assertCommandFailure(String command, String expectedResultMessage) {
        Model expectedModel = getModel();

        executeCommand(command);
        assertApplicationDisplaysExpected(command, expectedResultMessage, expectedModel);
        assertSelectedCardUnchanged();
        assertCommandBoxShowsErrorStyle();
        assertStatusBarUnchanged();
    }
}
