package fs_project.mapping.room;


import fs_project.mapping.dto.RoomDTO;
import fs_project.mapping.item.ItemMapper;
import fs_project.model.dataEntity.Room;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        /*
         * Policy for each unmapped target's field(s) in any of
         * the class's methods.
         */
        unmappedTargetPolicy = ReportingPolicy.WARN, // todo change to ignore in production stage
        uses = {ItemMapper.class}
)
public abstract class RoomMapper {


    public abstract RoomDTO RoomToRoomDTO(Room room);
    public abstract List<RoomDTO> RoomsToRoomDTO(List<Room> rooms);

    @Mapping(target = "id", source = ".")
    public abstract Room roomIdToRoom(Long roomId);
}
