package fs_project.mapping.dto.reservations;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

/**
 * The type Maintenance reservation response. Used to respond to maintenance reservations.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MaintenanceReservationResponse extends ReservationResponse{

    @NotNull
    @NotBlank
    private String result;
}
