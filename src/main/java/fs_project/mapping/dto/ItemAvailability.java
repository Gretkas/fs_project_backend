package fs_project.mapping.dto;

import fs_project.model.dataEntity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemAvailability {

    @NotNull
    @Positive
    private Long itemId;

    private Set<Reservation> itemReservations = new HashSet<>();

}
