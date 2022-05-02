package todolist.appservices.item;

import todolist.domain.Item;
import todolist.exceptions.ItemNotExistsException;
import todolist.repositories.ItemRepository;
import todolist.stereotypes.ApplicationService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ApplicationService
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository todolistItemRepository) {
        this.itemRepository = todolistItemRepository;
    }

    public List<Item> getAllItems() {
        return itemRepository.getAllItems();
    }

    public Item createItem(String description, LocalDateTime due, Integer priority) {
        Item item = new Item(null, description, due, false, priority);
        // Item returnen, was save zurückliefert, da sonst ID immer null
        return itemRepository.save(item);
    }

    public Optional<Item> getItemById(long id) {
        return itemRepository.getItemById(id);
    }

    public Optional<Item> patchItem(long id) {
        Optional<Item> item = itemRepository.getItemById(id);
        if (item.isPresent()) {
            Item temp = item.get();
            temp.setDone(true);
            return Optional.ofNullable(itemRepository.save(temp));
        } else throw new ItemNotExistsException(id);
    }

    public void deleteItem(long id) {
        itemRepository.deleteById(id);
    }

}
