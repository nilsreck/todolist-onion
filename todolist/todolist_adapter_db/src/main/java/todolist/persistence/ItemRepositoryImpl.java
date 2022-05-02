package todolist.persistence;

import org.springframework.stereotype.Repository;
import todolist.domain.Item;
import todolist.persistence.item.datarepo.DBItemRepository;
import todolist.repositories.ItemRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ItemRepositoryImpl implements ItemRepository {

    private final DBItemRepository dbItemRepository;

    public ItemRepositoryImpl(DBItemRepository itemRepository) {
        this.dbItemRepository = itemRepository;
    }

    @Override
    public List<Item> getAllItems() {
        return dbItemRepository.findAll().stream()
            .map(todolist.persistence.item.dto.ItemDTO::createItem)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<Item> getItemById(Long id) {
        return dbItemRepository.findById(id)
            .map(todolist.persistence.item.dto.ItemDTO::createItem);
    }

    @Override
    public Item save(Item item) {
        return dbItemRepository.save(
            new todolist.persistence.item.dto.ItemDTO(
                item.getId(),
                item.getDescription(),
                item.getDue(),
                item.getDone(),
                item.getPriority()
            )
        ).createItem();
    }

    @Override
    public void deleteById(Long id) {
        dbItemRepository.deleteById(id);
    }
}
