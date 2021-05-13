package fs_project.service;

import fs_project.model.dataEntity.Room;
import fs_project.repo.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoomService {
    @Autowired
    RoomRepo roomRepo;

    public Room getRoom(long id) {
        return roomRepo.getOne(id);
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

    public Set<Room> getRooms() {
        return null;
    }
    
}
