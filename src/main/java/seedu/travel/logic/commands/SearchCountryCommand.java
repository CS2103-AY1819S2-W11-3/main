package seedu.travel.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.travel.commons.core.Messages;
import seedu.travel.logic.CommandHistory;
import seedu.travel.model.Model;
import seedu.travel.model.place.CountryCodeContainsKeywordsPredicate;

/**
 * Finds and lists all places in travel book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class SearchCountryCommand extends Command {

    public static final String COMMAND_WORD = "searchc";
    public static final String COMMAND_ALIAS = "sc";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all places whose country code contain "
            + "the specified keywords and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " SGP JPN";

    private final CountryCodeContainsKeywordsPredicate predicate;

    public SearchCountryCommand(CountryCodeContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.updateFilteredPlaceList(predicate);
        return new CommandResult(constructFeedbackToUser(model));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SearchCountryCommand // instanceof handles nulls
                && predicate.equals(((SearchCountryCommand) other).predicate)); // state check
    }

    /**
     * Constructs the message to be displayed upon successful SearchCountryCommand
     * @param model Model component
     * @return A string showing a successful search that will be displayed on the GUI
     */
    private String constructFeedbackToUser(Model model) {
        StringBuilder feedbackToUser = new StringBuilder();
        feedbackToUser.append(COMMAND_WORD);
        feedbackToUser.append(" ");
        feedbackToUser.append(predicate.getKeywords());
        feedbackToUser.append(": ");
        feedbackToUser.append(String.format(Messages.MESSAGE_PLACES_LISTED_OVERVIEW,
                model.getFilteredPlaceList().size()));
        return feedbackToUser.toString();
    }
}
