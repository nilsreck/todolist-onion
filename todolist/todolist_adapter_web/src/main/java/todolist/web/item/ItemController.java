package todolist.web.item;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import todolist.appservices.item.ItemService;
import todolist.domain.Item;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService todolistItemService) {
        this.itemService = todolistItemService;
    }

    @GetMapping("/todo")
    public List<Item> showItems() {
        return itemService.getAllItems();
    }

    @PostMapping(
        path = "/todo",
        consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public Item addItem(@RequestPart String description,
                        @RequestPart(name = "due-date", required = false) LocalDateTime due,
                        @RequestPart(required = false) Integer priority) {
        return itemService.createItem(description, due, priority);
    }

    @GetMapping("/todo/{id}")
    public Optional<Item> showSingleItem(@PathVariable("id") int id) {
        return itemService.getItemById(id);
    }

    @PatchMapping("/todo/{id}")
    public Optional<Item> patch(@PathVariable("id") int id) {
        return itemService.patchItem(id);
    }

    @DeleteMapping("/todo/{id}")
    public void delete(@PathVariable("id") int id) {
        itemService.deleteItem(id);
    }
}
