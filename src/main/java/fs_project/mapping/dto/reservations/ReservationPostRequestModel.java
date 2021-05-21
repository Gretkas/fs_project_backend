package fs_project.mapping.dto.reservations;

import fs_project.model.Attributes.ReservationType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;


/**
 * The type Reservation post request model. Used to create reservations.
 */
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
  
    @NotNull
    private String title;

    /**
     * Is valid date range boolean.
     *
     * @return the boolean
     */
    @AssertTrue
    final public boolean isValidDateRange() {
        return startTime.isBefore(endTime);
    }
}
