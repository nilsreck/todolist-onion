package todolist.appservices.todolistitem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import todolist.appservices.item.ItemService;
import todolist.domain.Item;
import todolist.repositories.ItemRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class TodoListItemServiceTest {

    private ItemRepository itemRepository;
    private ItemService itemService;

    @BeforeEach
    void setup() {
        itemRepository = mock(ItemRepository.class);
        itemService = new ItemService(itemRepository);
    }

    @Test
    @DisplayName("A single item can be created")
    void test_0() {
        LocalDateTime due = LocalDateTime.of(2022,3,23,18, 0);
        itemService.createItem("Clean Dishes", due, 3);

        ArgumentCaptor<Item> captor = ArgumentCaptor.forClass(Item.class);
        verify(itemRepository).save(captor.capture());
        assertThat(captor.getValue().getDescription()).isEqualTo("Clean Dishes");
    }

    @Test
    @DisplayName("The status of an item can be changed")
    void test_1() {
        LocalDateTime due = LocalDateTime.of(2022,3,23,18, 0);
        when(itemRepository.getItemById(anyLong())).thenReturn(Optional.of(new Item(
            42L,
            "Groceries",
            due, false,
            3
        )));

        Optional<Item> item = itemService.patchItem(42);

        item.ifPresent(value -> assertThat(value.getDone()).isTrue());
    }
}
