package fs_project.mapping.availability;

import fs_project.mapping.dto.AvailableItemsRequest;
import fs_project.mapping.dto.ItemAvailability;
import fs_project.mapping.dto.ItemReservationDto;
import fs_project.model.responseModel.ReservationAvailabilityResponseModel;
import org.mapstruct.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN) // todo change to ignore in production stage
public abstract class AvailabilityMapper {

    @Mapping(target = ".", source = "items")
    abstract Set<ItemAvailability> availabilityRequestToItemsAvailability(AvailableItemsRequest request);

    @Mapping(target = "itemReservations", ignore = true)
    abstract ItemAvailability itemToItemAvailability(ItemReservationDto item);

    @Mapping(target = "timetable", qualifiedByName = "timetableMapper")
    abstract ReservationAvailabilityResponseModel populatedItemAvailabilitySetToAvailabilityResponse
            (Set<ItemAvailability> itemAvailabilitySet);

    @Named("timetableMapper")
    protected boolean[][] itemsSetToTimetable(@NotNull Set<ItemAvailability> itemAvailabilitySet) {
        boolean[][] res = new boolean[7][10];

        if (itemAvailabilitySet.isEmpty()) return res;

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
        return res;
    }

}
