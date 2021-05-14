package fs_project.repo;

import fs_project.model.dataEntity.Reservation;
import fs_project.model.dataEntity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface ReservationRepo extends JpaRepository<Reservation, Long> {

    @Query(value = "SELECT * FROM reservation WHERE end_time > CURRENT_TIMESTAMP AND user_id = ?1 ORDER BY start_time",
            nativeQuery = true)

    Set<Reservation> getUpcomingReservationsByUser(long id);

    @Query(value = "SELECT * FROM reservation WHERE end_time < CURRENT_TIMESTAMP AND user_id = ?1 ORDER BY start_time",
            nativeQuery = true)
    Set<Reservation> getReservationHistoryByUser(long id);

    @Query(value = "SELECT * FROM reservation WHERE start_time BETWEEN GETDATE() AND DateAdd(DD,7,GETDATE() AND reservation_id IN(SELECT reservation_id FROM reservation_item WHERE item_id = ?1) ORDER BY start_time)",
            nativeQuery = true)
    Set<Reservation> getItemReservationsNextSevenDays(long itemId);

    Set<Reservation> getReservationsByUser(User user);
}
