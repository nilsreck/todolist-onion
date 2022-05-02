package todolist.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Item {

    private final Long id;
    private final String description;
    private final LocalDateTime due;
    private Boolean done;
    private final Integer priority;

    public Item(Long id, String description, LocalDateTime due, Boolean done, Integer priority) {
        this.id = id;
        this.description = description;
        this.due = due;
        this.done = done;
        this.priority = priority;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDue() {
        return due;
    }

    public Boolean getDone() {
        return done;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item that = (Item) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Item{" +
            "id=" + id +
            ", description='" + description + '\'' +
            ", due=" + due +
            ", done=" + done +
            ", priority=" + priority +
            '}';
    }
}
