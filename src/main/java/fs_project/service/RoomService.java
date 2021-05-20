package fs_project.service;

import fs_project.mapping.dto.RoomDTO;
import fs_project.mapping.room.RoomMapper;
import fs_project.model.dataEntity.Item;
import fs_project.model.dataEntity.Room;
import fs_project.model.filter.RoomFilter;
import fs_project.model.filter.RoomPage;
import fs_project.model.filter.RoomSearchCriteria;
import fs_project.repo.ItemRepo;
import fs_project.repo.ReservationRepo;
import fs_project.repo.RoomCriteriaRepo;
import fs_project.repo.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RoomService {
    @Autowired
    RoomRepo roomRepo;
    @Autowired
    RoomCriteriaRepo roomCriteriaRepo;
    @Autowired
    RoomMapper roomMapper;
    @Autowired
    private ItemRepo itemRepo;
    @Autowired
    private ReservationRepo reservationRepo;


    public RoomDTO getRoom(long id) {
        return roomMapper.roomToRoomDTO(roomRepo.getOne(id));
    }

    public Room createRoom(Room reservation) {
        return null;
    }

    public Room updateRoom(Room reservation) {
        return null;
    }

    public boolean deleteRoom(long id) {
        List<Item> items = itemRepo.getItemsByRoomId(id);
        items.forEach(item -> reservationRepo.deleteAll(item.getReservations()));
       // itemRepo.deleteAll(items);
        roomRepo.deleteById(id);
        return true;
    }

    public Page<RoomDTO> getRoomsWithFilters(RoomPage roomPage, RoomSearchCriteria roomSearchCriteria) {
        return roomMapper.roomPageToRoomDTOPage(roomCriteriaRepo.findAllWithFilters(roomPage, roomSearchCriteria));
    }

    public List<RoomDTO> getRooms() {
        List<RoomDTO> test = roomMapper.roomListToRoomDTOList(roomRepo.findAll());
        List<Room> rooms = roomRepo.findAll();
        rooms.forEach(room -> System.out.println(room.toString()));
        test.forEach(System.out::println);
        return test;
    }
}
