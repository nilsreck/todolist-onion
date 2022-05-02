package todolist.exceptions;

public class ItemNotExistsException extends RuntimeException {

    public ItemNotExistsException(long id) {
        super("Item with " + id + " does not exist");
    }
}
