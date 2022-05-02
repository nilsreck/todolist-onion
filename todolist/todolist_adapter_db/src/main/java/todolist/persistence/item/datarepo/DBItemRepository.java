package todolist.persistence.item.datarepo;

import org.springframework.data.repository.CrudRepository;
import todolist.persistence.item.dto.ItemDTO;

import java.util.List;

public interface DBItemRepository extends CrudRepository<ItemDTO, Long> {

    @Override
    List<ItemDTO> findAll();

}
