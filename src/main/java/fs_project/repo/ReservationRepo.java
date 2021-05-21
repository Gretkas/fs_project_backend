package fs_project.repo;

import fs_project.model.Attributes.ReservationType;
import fs_project.model.dataEntity.Item;
import fs_project.model.dataEntity.Reservation;
import fs_project.model.dataEntity.Section;
import fs_project.model.dataEntity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface ReservationRepo extends JpaRepository<Reservation, Long> {

    @Query(value = "SELECT * FROM reservation WHERE end_time > CURRENT_TIMESTAMP AND user_id = ?1 ORDER BY start_time",
            nativeQuery = true)

    Set<Reservation> getUpcomingReservationsByUser(long id);

    @Query(value = "SELECT * FROM reservation WHERE end_time < CURRENT_TIMESTAMP AND user_id = ?1 ORDER BY start_time",
            nativeQuery = true)
    Set<Reservation> getReservationHistoryByUser(long id);

    @Query(value = "SELECT * FROM reservation WHERE end_time > CURRENT_TIMESTAMP AND reservation_id IN(SELECT reservation_id FROM reservation_item WHERE item_id = ?1) ORDER BY start_time",
            nativeQuery = true)
    Set<Reservation> getItemReservationsNextSevenDays(long itemId);

    Set<Reservation> getReservationsByUser(User user);

    @Query(
            value = "SELECT r " +
                    "FROM Reservation r " +
                    "WHERE r.type = '0' " +
                    "AND r.id <> :affectedBy " +
                    "AND (r.endTime BETWEEN :maintenanceStart AND :maintenanceEnd  " +
                    "OR r.startTime BETWEEN :maintenanceStart AND :maintenanceEnd) " +
                    "AND r IN (SELECT r FROM Reservation r INNER JOIN r.items i WHERE i IN(:items))"
    )
    Set<Reservation> findAffectedReservations
            (LocalDateTime maintenanceStart, LocalDateTime maintenanceEnd,
             Long affectedBy, List<Item> items);
}
