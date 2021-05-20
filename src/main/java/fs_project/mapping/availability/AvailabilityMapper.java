/*package fs_project.mapping.availability;

import fs_project.mapping.dto.AvailableItemsResponse;
import fs_project.mapping.dto.ItemAvailability;
import fs_project.mapping.dto.ItemReservationDto;
import org.mapstruct.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN) // todo change to ignore in production stage
public abstract class AvailabilityMapper {

    @IterableMapping(elementTargetType = ItemAvailability.class)
    public abstract Set<ItemAvailability> availabilityRequestToItemsAvailability
            (Set<ItemReservationDto> availabilityReqItemSet);

    public Long itemDtoToItemId(ItemReservationDto itemDto) {
        if (itemDto == null) return null;

        return itemDto.getItemId() != null ? itemDto.getItemId() : null;
    }

    @Mapping(target = "itemReservations", ignore = true)
    public abstract ItemAvailability itemToItemAvailability(ItemReservationDto item);

//    @Mapping(target = "timetable", source = ".", qualifiedByName = "timetableMapper")
    public AvailableItemsResponse populatedItemAvailabilitySetToAvailabilityResponse
            (@NotNull Set<ItemAvailability> itemAvailabilitySet) {
        boolean[][] res = new boolean[7][10];
        AvailableItemsResponse availableItemsResponse = new AvailableItemsResponse(res);

        if (itemAvailabilitySet.isEmpty()) return availableItemsResponse;

        itemAvailabilitySet
                .forEach(
                        item -> item.getItemReservations() // todo not null check
                                .forEach(r -> {
                                    if (r != null) {
                                        int day = (int) LocalDate.now().until(r.getStartTime().toLocalDate(), ChronoUnit.DAYS);
                                        boolean[] reservationTimeTable = r.toTimeTable();
                                        for (int i = 0; i < 10; i++) {
                                            res[day][i] |= reservationTimeTable[i];
                                        }
                                    }
                                }));
        availableItemsResponse.setTimetable(res);
        return availableItemsResponse;
    };

//    @Named("timetableMapper")
//    public boolean[][] itemsSetToTimetable(@NotNull Set<ItemAvailability> itemAvailabilitySet) {
//        boolean[][] res = new boolean[7][10];
//
//        if (itemAvailabilitySet.isEmpty()) return res;
//
//        itemAvailabilitySet
//                .forEach(
//                         item -> item.getItemReservations() // todo not null check
//                                .forEach(r -> {
//                                    if (r != null) {
//                                        int day = (int) LocalDate.now().until(r.getStartTime().toLocalDate(), ChronoUnit.DAYS);
//                                        boolean[] reservationTimeTable = r.toTimeTable();
//                                        for (int i = 0; i < 10; i++) {
//                                            res[day][i] |= reservationTimeTable[i];
//                                        }
//                                    }
//                                }));
//        return res;
//    }

}*/
