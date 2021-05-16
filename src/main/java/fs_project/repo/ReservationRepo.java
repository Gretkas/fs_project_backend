package fs_project.repo;

import fs_project.model.Attributes.ReservationType;
import fs_project.model.dataEntity.Item;
import fs_project.model.dataEntity.Reservation;
import fs_project.model.dataEntity.Section;
import fs_project.model.dataEntity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

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

    @Query(value = "SELECT * FROM reservation WHERE start_time BETWEEN GETDATE() AND DateAdd(DD,7,GETDATE() AND reservation_id IN(SELECT reservation_id FROM reservation_item WHERE item_id = ?1) ORDER BY start_time)",
            nativeQuery = true)
    Set<Reservation> getItemReservationsNextSevenDays(long itemId);

    Set<Reservation> getReservationsByUser(User user);

    // TODO implement
    @Query(value = "SELECT * from reservation", nativeQuery = true)
    Reservation saveMaintenanceReservation(Reservation reservation);

    @Query(
            value = "UPDATE Reservation r " +
                    "SET r.endTime = :maintenanceStart " +
                    "WHERE r.type = :type " +
                    "AND r.id <> :affectedBy " +
//                    "AND r.startTime BEFORE :maintenanceStart " +
                    "AND r.endTime < :maintenanceStart " +
                    "AND r.items IN :items",
            nativeQuery = true
    )
    Set<Reservation> updateAffectedReservationOfType
            (LocalDateTime maintenanceStart, List<Item> items, Long affectedBy, ReservationType type);

//    Set<Reservation> findAllByEndTimeBeforeAndItemsContains();
//
//    Set<Reservation> findAllByAffectedReservation(Reservation maintenance);
//
//    @Modifying(clearAutomatically = true) // ensures that em will synchronize affected entities
//    @Query(
//            value = "UPDATE ",
//            nativeQuery = true
//    )
//    Set<Reservation> updateAffectedReservation();
//
//    Set<Section> findAllByItemsContains
}
