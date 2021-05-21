package fs_project.repo;

import fs_project.model.dataEntity.Reservation;
import fs_project.model.dataEntity.Room;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;



/**
 * The interface Room repo. Implements default db operations through JpaRepository.
 */

public interface RoomRepo extends JpaRepository<Room, Long> {
    Optional<Room> findRoomById(Long id);
}
