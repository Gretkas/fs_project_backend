package fs_project.mapping.dto;

import fs_project.model.Attributes.ReservationType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public abstract class ReservationResponse {

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
    private UserDescription user;

//    @NotNull
    private String roomName;

    @NotNull
    private ReservationType type;

    @AssertTrue
    final public boolean isValidDateRange() {
        return startTime.isBefore(endTime) || startTime.isEqual(endTime);
    }
}
