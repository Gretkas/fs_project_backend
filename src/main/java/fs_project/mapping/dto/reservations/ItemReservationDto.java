package fs_project.mapping.dto.reservations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * The type Item reservation dto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemReservationDto {

    @NotNull
    @Positive
    private Long itemId;

    @NotNull
    @Positive
    private Long roomId;

    @NotNull
    @NotBlank
    private String name;
}
