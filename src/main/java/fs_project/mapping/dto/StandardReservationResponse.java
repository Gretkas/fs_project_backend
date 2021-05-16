package fs_project.mapping.dto;

import fs_project.model.Attributes.ReservationType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class StandardReservationResponse extends ReservationResponse{

    @NotNull
    @Positive
    private Long id;

    @NotNull
    @Positive
    private Long userId;

    @NotNull
    private ReservationType type;
}
