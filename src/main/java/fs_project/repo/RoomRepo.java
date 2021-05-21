package fs_project.repo;

import fs_project.model.dataEntity.Reservation;
import fs_project.model.dataEntity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepo extends JpaRepository<Room, Long> {
}
