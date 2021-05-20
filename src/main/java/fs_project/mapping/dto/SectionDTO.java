package fs_project.mapping.dto;




import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectionDTO {


    @Positive
    private long id;

    @NotNull
    @NotEmpty
    private List<SingleItemDTO> items;


    @NotNull
    @NotBlank
    private String name;

}
