package fs_project.mapping.dto;

import fs_project.model.dataEntity.Section;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

/**
 * The type Room dto. Contains relevant information about a room.
 */
@Data
@NoArgsConstructor
public class RoomDTO {

    @Positive
    private Long id;

    @NotNull
    @Positive
    private int maxNumber;

    @NotNull
    @NotEmpty
    private List<SingleItemDTO> items;

    private List<SectionDTO> sections;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String location;

    @NotNull
    @NotBlank
    private String description;
}
