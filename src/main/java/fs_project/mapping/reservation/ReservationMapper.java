package fs_project.mapping.reservation;

import fs_project.exceptions.FatalException;
import fs_project.exceptions.ResponseErrStatus;


import fs_project.mapping.room.RoomMapper;

import fs_project.mapping.dto.*;
import fs_project.mapping.user.UserMapper;

import fs_project.model.Attributes.ReservationType;
import fs_project.model.dataEntity.Item;
import fs_project.model.dataEntity.Reservation;
import fs_project.model.dataEntity.Room;
import fs_project.service.UserService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Mapper(
        /*
         * Policy for each unmapped target's field(s) in any of
         * the class's methods.
         */

        unmappedTargetPolicy = ReportingPolicy.WARN, // todo change to ignore in production stage,
        uses = {RoomMapper.class, UserMapper.class}




)
public abstract class ReservationMapper {

//    @Autowired
//    protected UserService userService;

    @Mappings({
            // whole source obj is available for expression
//            @Mapping(target = "user", expression = "java(userService.getThisUser())"),
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
    public void setMaintenanceReservationResult(
            @MappingTarget MaintenanceReservationResponse maintenanceReservation,
            // NB! mapstruct does perform null checks on context
            @NotNull @NotEmpty @Context String result
    ) {
        maintenanceReservation.setResult(result);
    }
}
