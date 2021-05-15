package fs_project.mapping.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AvailableItemsResponse {

    @NotNull
    private boolean[][] timetable;
}
