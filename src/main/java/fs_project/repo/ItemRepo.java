package fs_project.repo;

import fs_project.model.dataEntity.Item;
import fs_project.model.dataEntity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface ItemRepo extends JpaRepository<Item, Long> {
    @Query(
            value="SELECT DISTINCT s FROM section s INNER JOIN s.items i WHERE i.itemId IN (?1)",
            nativeQuery = true
    )
    Set<Section> getSectionsByItemLists(List<Item> items);
}
