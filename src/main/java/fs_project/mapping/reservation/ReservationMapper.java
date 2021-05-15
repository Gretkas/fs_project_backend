package fs_project.mapping.reservation;

import fs_project.exceptions.FatalException;
import fs_project.exceptions.ResponseErrStatus;
import fs_project.mapping.dto.ItemReservationDto;
import fs_project.mapping.dto.ReservationRequestDto;
import fs_project.model.Attributes.ReservationType;
import fs_project.model.dataEntity.Item;
import fs_project.model.dataEntity.Reservation;
import fs_project.model.dataEntity.Room;
import fs_project.service.UserService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

@Mapper(
        /*
         * Policy for each unmapped target's field(s) in any of
         * the class's methods.
         */
        unmappedTargetPolicy = ReportingPolicy.WARN // todo change to ignore in production stage
)
public abstract class ReservationMapper {

    @Autowired
    protected UserService userService;

    @Mappings({
            // whole source obj is available for expression
            @Mapping(target = "user", expression = "java(userService.getThisUser())"),
            @Mapping(target = "id", ignore = true) //
    })
    public abstract Reservation reservationRequestToReservation(
            ReservationRequestDto reservationRequest, @Context ReservationType reservationType);

    public abstract Item itemReservationDtoToItem(ItemReservationDto itemReservationDto);

    @Mapping(target = "id", source = ".")
    public abstract Room roomIdToRoom(Long roomId);

    public abstract Set<Item> itemReservationSetToItemSet(Set<ItemReservationDto> itemReservationSet);

    public abstract ItemReservationDto itemToItemReservationDto(Item item);

    public abstract Set<ItemReservationDto> itemSetToItemReservationSet(Set<Item> itemSet);

    /**
     * Method that is called whenever mapping to Reservation,
     * but only in mapping methods of {@code this} that have {@code reservationType}
     * as a parameter.
     * @param reservation target
     * @param reservationType if provided, this method will be called
     */
    @AfterMapping
    public void finalizeReservation(
            @MappingTarget Reservation reservation,
            @Context ReservationType reservationType
            ) {
        if (reservation.getUser() == null)
            throw new FatalException(ResponseErrStatus.USER_NOT_FOUND, "User not found");

        reservation.setType(reservationType);
    }


}
