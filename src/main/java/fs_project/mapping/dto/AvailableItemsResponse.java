package fs_project.mapping.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableItemsResponse {

    @NotNull
    private boolean[][] timetable;
}
