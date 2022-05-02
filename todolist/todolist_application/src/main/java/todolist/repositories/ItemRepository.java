package todolist.repositories;

import todolist.domain.Item;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {

    List<Item> getAllItems();

    Optional<Item> getItemById(Long id);

    Item save(Item item);

    void deleteById(Long id);
}
