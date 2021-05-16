package fs_project.mapping.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MaintenanceReservationResponse extends ReservationResponse{

    @NotNull
    @NotBlank
    private String result;
}
