package todolist.persistence.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import todolist.domain.Item;
import todolist.exceptions.ItemNotExistsException;
import todolist.persistence.ItemRepositoryImpl;
import todolist.persistence.item.datarepo.DBItemRepository;
import todolist.repositories.ItemRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Sql({
    "classpath:db/data.sql"
})
@DataJdbcTest
@ActiveProfiles("test")
public class ItemRepositoryTest {

    @Autowired
    private DBItemRepository dbItemRepository;

    private ItemRepository itemRepository;

    @BeforeEach
    void setup() {
        itemRepository = new ItemRepositoryImpl(dbItemRepository);
    }


    @Test
    @DisplayName("List of all items is loaded")
    void test_0() {
        LocalDateTime due1 = LocalDateTime.of(2022, 3, 26, 16, 0);
        LocalDateTime due2 = LocalDateTime.of(2022, 3, 26, 19, 0);
        LocalDateTime due3 = LocalDateTime.of(2022, 3, 14, 8, 0);
        List<Item> items = List.of(
            new Item(1L, "Walk the dog", due1, false, 2),
            new Item(2L, "Feed the cat", due2, false, 1),
            new Item(3L, "Take a walk", due3, false, 5)
        );

        List<Item> result = itemRepository.getAllItems();

        assertThat(result).containsExactlyInAnyOrderElementsOf(items);
    }

    @Test
    @DisplayName("Get item by id 1")
    void test_1() {
        LocalDateTime due = LocalDateTime.of(2022, 3, 26, 16, 0);
        Item item = new Item(1L, "Feed the cat", due, false, 4);

        Optional<Item> result = itemRepository.getItemById(1L);

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(item);
    }

    @Test
    @DisplayName("Item with id 3 can be deleted")
    void test_2() {
        Optional<Item> item = itemRepository.getItemById(3L);

        if (item.isPresent()) {
            itemRepository.deleteById(item.get().getId());
        } else throw new ItemNotExistsException(3L);

        Optional<Item> deletedItem = itemRepository.getItemById(3L);

        // Damit bewiesen ist, dass Item vorher in der DB war.
        // Sonst kann Test durchlaufen, obwohl es das Item nie gab.
        assertThat(item).isPresent();
        assertThat(deletedItem).isEmpty();
    }

    @Test
    @DisplayName("An item can be saved")
    void test_3() {
        LocalDateTime due = LocalDateTime.of(2022, 3, 27, 18, 59);
        Item item = new Item(null, "Workout", due, false, 3);

        Item result = itemRepository.save(item);

        assertThat(result).isNotNull();
        assertThat(itemRepository.getItemById(result.getId())).isPresent();
    }

}
