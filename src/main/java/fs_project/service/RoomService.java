package fs_project.service;

import fs_project.mapping.dto.RoomDTO;
import fs_project.mapping.room.RoomMapper;
import fs_project.model.dataEntity.Room;
import fs_project.repo.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RoomService {
    @Autowired
    RoomRepo roomRepo;
    @Autowired
    RoomMapper roomMapper;

    public RoomDTO getRoom(long id) {
        return roomMapper.RoomToRoomDTO(roomRepo.getOne(id));
    }

    public Room createRoom(Room reservation) {
        return null;
    }

    public Room updateRoom(Room reservation) {
        return null;
    }

    public Room deleteRoom(long id) {
        return null;
    }

    public List<RoomDTO> getRooms() {
        return roomMapper.RoomsToRoomDTO(roomRepo.findAll());
    }
    
}
