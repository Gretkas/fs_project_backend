package fs_project.mapping.room;


import fs_project.mapping.dto.RoomDTO;
import fs_project.mapping.item.ItemMapper;
import fs_project.mapping.section.SectionMapper;
import fs_project.model.dataEntity.Room;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(
        /*
         * Policy for each unmapped target's field(s) in any of
         * the class's methods.
         */
        unmappedTargetPolicy = ReportingPolicy.WARN, // todo change to ignore in production stage
        uses = {ItemMapper.class, SectionMapper.class}
)
public interface RoomMapper {


    public abstract RoomDTO roomToRoomDTO(Room room);
    default Page<RoomDTO> roomPageToRoomDTOPage(Page<Room> rooms){
        return rooms.map(this::roomToRoomDTO);
    };
    public abstract List<RoomDTO> roomListToRoomDTOList(List<Room> rooms);

    @Mapping(target = "id", source = ".")
    public abstract Room roomIdToRoom(Long roomId);

    Room roomDTOToRoom(RoomDTO roomDTO);
}
