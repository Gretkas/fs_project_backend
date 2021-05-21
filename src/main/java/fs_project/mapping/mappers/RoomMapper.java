package fs_project.mapping.mappers;


import fs_project.mapping.dto.RoomDTO;
import fs_project.model.dataEntity.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * The interface Room mapper. Used to map the the room data-entity to the correct room dto
 */
@Mapper(
        /*
         * Policy for each unmapped target's field(s) in any of
         * the class's methods.
         */
        unmappedTargetPolicy = ReportingPolicy.WARN, // todo change to ignore in production stage
        uses = {ItemMapper.class, SectionMapper.class}
)
public interface RoomMapper {


    /**
     * Room to room dto room dto.
     *
     * @param room the room
     * @return the room dto
     */
    public abstract RoomDTO roomToRoomDTO(Room room);

    /**
     * Room page to room dto page page.
     *
     * @param rooms the rooms
     * @return the page
     */
    default Page<RoomDTO> roomPageToRoomDTOPage(Page<Room> rooms){
        return rooms.map(this::roomToRoomDTO);
    };

    /**
     * Room list to room dto list list.
     *
     * @param rooms the rooms
     * @return the list
     */
    public abstract List<RoomDTO> roomListToRoomDTOList(List<Room> rooms);

    /**
     * Room id to room room.
     *
     * @param roomId the room id
     * @return the room
     */
    @Mapping(target = "id", source = ".")
    public abstract Room roomIdToRoom(Long roomId);
}
