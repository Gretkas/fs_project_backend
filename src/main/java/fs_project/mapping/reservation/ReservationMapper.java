package fs_project.mapping.reservation;

import fs_project.exceptions.FatalException;
import fs_project.exceptions.ResponseErrStatus;


import fs_project.mapping.item.ItemMapper;
import fs_project.mapping.room.RoomMapper;

import fs_project.mapping.dto.*;
import fs_project.mapping.user.UserMapper;

import fs_project.model.Attributes.ReservationType;
import fs_project.model.dataEntity.Item;
import fs_project.model.dataEntity.Reservation;
import fs_project.model.dataEntity.Room;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Mapper(
        /*
         * Policy for each unmapped target's field(s) in any of
         * the class's methods.
         */

        unmappedTargetPolicy = ReportingPolicy.WARN, // todo change to ignore in production stage,
        uses = {RoomMapper.class, UserMapper.class, ItemMapper.class}




)
public interface ReservationMapper {

    default Page<ReservationResponseModel> reservationPageToReservationResponseModelPage(Page<Reservation> reservations){
        return reservations.map(this::reservationToReservationResponseModel);
    };

    public default ReservationResponseModel reservationToReservationResponseModel(Reservation reservation) {
        if ( reservation == null ) {
            return null;
        }

        ReservationResponseModel reservationResponseModel = new ReservationResponseModel(reservation);


        return reservationResponseModel;
    }


    @Mappings({
            // whole source obj is available for expression
            @Mapping(target = "user", ignore = true),
            @Mapping(target = "id", ignore = true) //
    })
    public abstract Reservation reservationRequestToReservation(
            ReservationRequestDto reservationRequest);

    // TODO implement mapstruct decorator for different reservation response types
    @Mapping(target = "roomName", ignore = true) // todo implement
    public abstract MaintenanceReservationResponse reservationToMaintenanceResponse
            (Reservation reservation, @NotNull @NotEmpty @Context String result);

    @Mapping(target = "roomName", ignore = true) // todo implement
    public abstract StandardReservationResponse reservationToStandardResponse
            (Reservation reservation);

    public abstract Item itemReservationDtoToItem(ItemReservationDto itemReservationDto);


    public default Long roomToRoomId(Room room) {
        if (room == null) return null;

        return room.getId() != -1 ? room.getId() : null;
    }

    public abstract List<Item> itemReservationSetToItemSet(List<ItemReservationDto> itemReservationSet);

    public abstract ItemReservationDto itemToItemReservationDto(Item item);

    public abstract List<ItemReservationDto> itemSetToItemReservationSet(List<Item> itemSet);

//    /**
//     * Method that is called whenever mapping to Reservation,
//     * but only in mapping methods of {@code this} that have {@code reservationType}
//     * as a parameter.
//     * @param reservation target
//     * @param reservationType if provided, this method will be called
//     */
//    @AfterMapping
//    public void finalizeReservation(
//            @MappingTarget Reservation reservation,
//            @Context ReservationType reservationType
//            ) {
//        if (reservation.getUser() == null)
//            throw new FatalException(ResponseErrStatus.USER_NOT_FOUND, "User not found");
//
//        reservation.setType(reservationType);
//    }

    @AfterMapping
    public default void setMaintenanceReservationResult(
            @MappingTarget MaintenanceReservationResponse maintenanceReservation,
            // NB! mapstruct does perform null checks on context
            @NotNull @NotEmpty @Context String result
    ) {
        maintenanceReservation.setResult(result);
    }
}
