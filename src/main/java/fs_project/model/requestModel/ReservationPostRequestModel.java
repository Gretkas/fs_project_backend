package fs_project.model.requestModel;

import fs_project.mapping.dto.ItemReservationDto;
import fs_project.model.Attributes.ReservationType;
import fs_project.model.dataEntity.Item;
import fs_project.model.dataEntity.Reservation;
import fs_project.model.dataEntity.User;
import javassist.tools.web.BadHttpRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class ReservationPostRequestModel {

    @NotNull
    @FutureOrPresent
    private LocalDateTime startTime;

    @NotNull
    @FutureOrPresent
    private LocalDateTime endTime;

    @NotNull
    @NotEmpty
    private List<ItemReservationDto> items;

    @NotNull
    private ReservationType type;

    @AssertTrue
    final public boolean isValidDateRange() {
        return startTime.isBefore(endTime);
    }

//    private void validate() throws BadHttpRequest {
//        if(startTime.isAfter(endTime) || items.size() <= 0) throw new BadHttpRequest();
//    }
}
