package duke.handlers;

import java.time.format.DateTimeParseException;

import duke.entities.Deadline;
import duke.entities.Task;
import duke.exceptions.DukeException;
import duke.service.Service;


/**
 * Handles the user command for creating a new Deadline.
 */
public class DeadlineHandler implements IHandler {
    private String deadlineName;
    private String flag;
    private String flagOption;

    /**
     * Constructs new DeadlineHandler from a HandlerFactory.
     * @param factory
     */
    public DeadlineHandler(HandlerFactory factory) {
        this.deadlineName = factory.getTaskName();
        this.flag = factory.getFlag();
        this.flagOption = factory.getFlagOption();
    }

    /**
     * Handles the "deadline" command by adding a new deadline to service's tasklist
     *
     * @param s Service object of the application
     * @throws DukeException
     */
    @Override
    public void handle(Service s) throws DukeException {
        if (this.deadlineName == null) {
            throw new DukeException("Please enter a task name!");
        }
        // TODO: Refactor to enum
        if (!flag.equals("by")) {
            throw new DukeException("Incorrect option flag!\nUsage:`deadline return book /by Sunday`");
        }
        if (this.flagOption == null) {
            throw new DukeException("Please enter a deadline!");
        }
        try {
            Task deadline = new Deadline(this.deadlineName, this.flagOption);
            s.addToList(deadline);
        } catch (DateTimeParseException ex) {
            throw new DukeException("Invalid Date/Time!\nUsage: `deadline return book /at 2/12/2019 1800`");
        }
    }
}
