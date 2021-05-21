package fs_project.repo;

import fs_project.model.dataEntity.Item;
import fs_project.model.dataEntity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

/**
 * The interface Item repo. Implements default db operations through JpaRepository.
 */
public interface ItemRepo extends JpaRepository<Item, Long> {
    /**
     * Gets sections by item lists.
     *
     * @param items the items
     * @return the sections by item lists
     */
    @Query(
            value="SELECT DISTINCT s FROM section s INNER JOIN s.items i WHERE i.itemId IN (?1)",
            nativeQuery = true
    )
    Set<Section> getSectionsByItemLists(List<Item> items);

    /**
     * Gets items by room id.
     *
     * @param roomId the room id
     * @return the items by room id
     */
    @Query(
            value="SELECT * FROM Item WHERE room_id = ?1",
            nativeQuery = true
    )
    List<Item> getItemsByRoomId(long roomId);
}
