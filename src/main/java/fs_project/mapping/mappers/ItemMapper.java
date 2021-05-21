package fs_project.mapping.mappers;


import fs_project.mapping.dto.SingleItemDTO;
import fs_project.model.dataEntity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * The type Item mapper. Used to map the the item data-entity to the correct item dto
 */
@Mapper(
        /*
         * Policy for each unmapped target's field(s) in any of
         * the class's methods.
         */
        unmappedTargetPolicy = ReportingPolicy.WARN // todo change to ignore in production stage
)
public abstract class ItemMapper {


    /**
     * Item list to item dto list list.
     *
     * @param items the items
     * @return the list
     */
    public abstract List<SingleItemDTO> itemListToItemDTOList(List<Item> items);

    /**
     * Item dto list to item list list.
     *
     * @param items the items
     * @return the list
     */
    public abstract List<Item> itemDTOListToItemList(List<SingleItemDTO> items);

    /**
     * Item to single item dto single item dto.
     *
     * @param item the item
     * @return the single item dto
     */
    public abstract SingleItemDTO itemToSingleItemDTO(Item item);

    /**
     * Item dto to single item item.
     *
     * @param item the item
     * @return the item
     */
    public abstract Item itemDTOToSingleItem(SingleItemDTO item);
}
