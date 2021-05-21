package fs_project.mapping.dto;

import fs_project.model.Attributes.ReservationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequestDto {

    @NotNull
    @FutureOrPresent
    private LocalDateTime startTime;

    @NotNull
    @FutureOrPresent
    private LocalDateTime endTime;

    @NotNull
    private String title;

    @NotNull
    @NotEmpty
    private List<SingleItemDTO> items;

    @NotNull
    private ReservationType type;



    @AssertTrue
    private boolean isValidDateRange() {
        return startTime != null &&
                endTime != null &&
                startTime.isBefore(endTime);
    }
}
