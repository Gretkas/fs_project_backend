package fs_project.mapping.mappers;


import fs_project.mapping.dto.reservations.*;

import fs_project.model.dataEntity.Item;
import fs_project.model.dataEntity.Reservation;
import fs_project.model.dataEntity.Room;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * The interface Reservation mapper. Used to map the the reservation data-entity to the correct reservation dto
 */
@Mapper(
        /*
         * Policy for each unmapped target's field(s) in any of
         * the class's methods.
         */

        unmappedTargetPolicy = ReportingPolicy.WARN, // todo change to ignore in production stage,
        uses = {RoomMapper.class, UserMapper.class, ItemMapper.class}




)
public interface ReservationMapper {

    /**
     * Reservation page to reservation response model page page.
     *
     * @param reservations the reservations
     * @return the page
     */
    default Page<ReservationResponseModel> reservationPageToReservationResponseModelPage(Page<Reservation> reservations){
        return reservations.map(this::reservationToReservationResponseModel);
    };

    /**
     * Reservation to reservation response model reservation response model.
     *
     * @param reservation the reservation
     * @return the reservation response model
     */
    public default ReservationResponseModel reservationToReservationResponseModel(Reservation reservation) {
        if ( reservation == null ) {
            return null;
        }

        ReservationResponseModel reservationResponseModel = new ReservationResponseModel(reservation);


        return reservationResponseModel;
    }


    /**
     * Reservation request to reservation reservation.
     *
     * @param reservationRequest the reservation request
     * @return the reservation
     */
    @Mappings({
            // whole source obj is available for expression
            @Mapping(target = "user", ignore = true),
            @Mapping(target = "id", ignore = true) //
    })
    public abstract Reservation reservationRequestToReservation(
            ReservationRequestDto reservationRequest);

    /**
     * Reservation to maintenance response maintenance reservation response.
     *
     * @param reservation the reservation
     * @param result      the result
     * @return the maintenance reservation response
     */
// TODO implement mapstruct decorator for different reservation response types
    @Mapping(target = "roomName", ignore = true) // todo implement
    public abstract MaintenanceReservationResponse reservationToMaintenanceResponse
            (Reservation reservation, @NotNull @NotEmpty @Context String result);

    /**
     * Reservation to standard response standard reservation response.
     *
     * @param reservation the reservation
     * @return the standard reservation response
     */
    @Mapping(target = "roomName", ignore = true) // todo implement
    public abstract StandardReservationResponse reservationToStandardResponse
            (Reservation reservation);

    /**
     * Item reservation dto to item item.
     *
     * @param itemReservationDto the item reservation dto
     * @return the item
     */
    public abstract Item itemReservationDtoToItem(ItemReservationDto itemReservationDto);


    /**
     * Room to room id long.
     *
     * @param room the room
     * @return the long
     */
    public default Long roomToRoomId(Room room) {
        if (room == null) return null;

        return room.getId() != -1 ? room.getId() : null;
    }

    /**
     * Item reservation set to item set list.
     *
     * @param itemReservationSet the item reservation set
     * @return the list
     */
    public abstract List<Item> itemReservationSetToItemSet(List<ItemReservationDto> itemReservationSet);

    /**
     * Item to item reservation dto item reservation dto.
     *
     * @param item the item
     * @return the item reservation dto
     */
    public abstract ItemReservationDto itemToItemReservationDto(Item item);

    /**
     * Item set to item reservation set list.
     *
     * @param itemSet the item set
     * @return the list
     */
    public abstract List<ItemReservationDto> itemSetToItemReservationSet(List<Item> itemSet);

    /**
     * Sets maintenance reservation result.
     *
     * @param maintenanceReservation the maintenance reservation
     * @param result                 the result
     */
    @AfterMapping
    public default void setMaintenanceReservationResult(
            @MappingTarget MaintenanceReservationResponse maintenanceReservation,
            // NB! mapstruct does perform null checks on context
            @NotNull @NotEmpty @Context String result
    ) {
        maintenanceReservation.setResult(result);
    }
}
