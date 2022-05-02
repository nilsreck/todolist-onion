package todolist.persistence.item.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("ITEM")
public record ItemDTO(
    @Id
    Long id,
    String description,
    LocalDateTime due,
    Boolean done,
    Integer priority
) {

    public todolist.domain.Item createItem() {
        return new todolist.domain.Item(
            id,
            description,
            due,
            done,
            priority
        );
    }
}
