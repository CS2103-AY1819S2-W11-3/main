package seedu.travel.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.travel.logic.CommandHistory;
import seedu.travel.model.Model;

/**
 * Generates a chart.
 */
public class GenerateCommand extends Command {

    public static final String COMMAND_WORD = "generate";
    public static final String COMMAND_ALIAS = "g";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Generates a chart based "
            + "on Country and Rating.\n"
            + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_EMPTY = "List is empty, unable to generate any charts.";
    public static final String MESSAGE_SUCCESS = "Chart has been successfully generated!";

    @Override
    // tag::execute[]
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.setChartDisplayed(true);                  // <1>
        model.commitTravelBuddy();                      // <2>
        if (model.getFilteredPlaceList().isEmpty()) {   // <3>
            return new CommandResult(MESSAGE_EMPTY);
        } else {
            return new CommandResult(MESSAGE_SUCCESS);
        }
    }
    // end::execute[]
}
