package fs_project.mapping.item;


import fs_project.mapping.dto.ItemReservationDto;
import fs_project.mapping.dto.SingleItemDTO;
import fs_project.model.dataEntity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        /*
         * Policy for each unmapped target's field(s) in any of
         * the class's methods.
         */
        unmappedTargetPolicy = ReportingPolicy.WARN // todo change to ignore in production stage
)
public abstract class ItemMapper {


    public abstract List<SingleItemDTO> itemListToItemDTOList(List<Item> items);
    public abstract List<Item> itemDTOListToItemList(List<SingleItemDTO> items);
    public abstract SingleItemDTO itemToSingleItemDTO(Item item);
    public abstract Item itemDTOToSingleItem(SingleItemDTO item);
}
