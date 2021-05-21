package fs_project.mapping.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * The type Single item dto. Contains relevant information about an item.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SingleItemDTO {

    @NotNull
    @Positive
    private long itemId;


    @NotNull
    @NotBlank
    private String name;

}
