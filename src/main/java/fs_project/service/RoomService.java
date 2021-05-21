package fs_project.service;

import fs_project.mapping.dto.RoomDTO;
import fs_project.mapping.mappers.RoomMapper;
import fs_project.model.dataEntity.Item;
import fs_project.model.dataEntity.Room;
import fs_project.model.dataEntity.User;
import fs_project.model.filter.RoomFilter;

import fs_project.model.filter.RoomPage;
import fs_project.model.filter.RoomSearchCriteria;
import fs_project.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.Set;


/**
 * The type Room service.
 */
@Service
public class RoomService {
    /**
     * The Room repo.
     */
    @Autowired
    RoomRepo roomRepo;
    /**
     * The Room criteria repo.
     */
    @Autowired
    RoomCriteriaRepo roomCriteriaRepo;
    /**
     * The Room mapper.
     */
    @Autowired
    RoomMapper roomMapper;
    @Autowired
    private ItemRepo itemRepo;
    @Autowired
    private ReservationRepo reservationRepo;
    @Autowired
    private SectionRepo sectionRepo;


    /**
     * Gets room.
     *
     * @param id the id
     * @return the room
     */
    public RoomDTO getRoom(long id) {
        return roomMapper.roomToRoomDTO(roomRepo.getOne(id));
    }


    public RoomDTO createRoom(RoomDTO roomDTO) {
        Room room = roomMapper.roomDTOToRoom(roomDTO);
        itemRepo.saveAll(room.getItems());
        if(room.getSections() != null && room.getSections().size() > 0){
            sectionRepo.saveAll(room.getSections());
        }
        roomRepo.save(room);
        return roomDTO;
    }

    public RoomDTO updateRoom(@NotNull Long id, @NotNull @Valid RoomDTO roomDTO) {
        Room room = roomMapper.roomDTOToRoom(roomDTO);
        @Valid @NotNull Room currentRoom = Optional.of(roomRepo.findRoomById(id).get()).orElse(null);
        if (currentRoom != null) {
            room.setId(currentRoom.getId());
        }

        itemRepo.saveAll(room.getItems());
        if(room.getSections() != null && room.getSections().size() > 0){
            room.getSections().forEach(s -> s.getItems().forEach(itemRepo::save));
            sectionRepo.saveAll(room.getSections());
        }
        room = roomRepo.save(room);
        RoomDTO response = roomMapper.roomToRoomDTO(room);

        return response;


    }

    /**
     * Deletes room with given id. Returns true if deleted.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean deleteRoom(long id) {
        List<Item> items = itemRepo.getItemsByRoomId(id);
        items.forEach(item -> reservationRepo.deleteAll(item.getReservations()));
        roomRepo.deleteById(id);
        return true;
    }

    /**
     * Gets rooms with filters.
     *
     * @param roomPage           the room page
     * @param roomSearchCriteria the room search criteria
     * @return the rooms with filters
     */
    public Page<RoomDTO> getRoomsWithFilters(RoomPage roomPage, RoomSearchCriteria roomSearchCriteria) {
        return roomMapper.roomPageToRoomDTOPage(roomCriteriaRepo.findAllWithFilters(roomPage, roomSearchCriteria));
    }

    /**
     * Gets rooms.
     *
     * @return the rooms
     */
    public List<RoomDTO> getRooms() {
        List<RoomDTO> test = roomMapper.roomListToRoomDTOList(roomRepo.findAll());
        List<Room> rooms = roomRepo.findAll();
        rooms.forEach(room -> System.out.println(room.toString()));
        test.forEach(System.out::println);
        return test;
    }
}
