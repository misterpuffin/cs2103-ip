package handlers;

import entities.Task;
import entities.Todo;
import exceptions.DukeException;
import service.Service;

import static utils.Utils.addToList;

public class TodoHandler implements IHandler{
    private String todoName;

    public TodoHandler(HandlerFactory factory) {
        this.todoName = factory.taskName;
    }

    @Override
    public void handle(Service s) throws DukeException {
        if (this.todoName == null) {
            throw new DukeException("Please enter a task name!");
        }
        Task todo = new Todo(this.todoName);
        s.list.add(todo);
    }
}
