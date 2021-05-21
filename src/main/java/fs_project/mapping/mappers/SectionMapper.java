package fs_project.mapping.mappers;

import fs_project.mapping.dto.SectionDTO;
import fs_project.model.dataEntity.Section;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * The type Section mapper. Used to map the the section data-entity to the correct section dto
 */
@Mapper(
        /*
         * Policy for each unmapped target's field(s) in any of
         * the class's methods.
         */
        unmappedTargetPolicy = ReportingPolicy.WARN, // todo change to ignore in production stage
        uses = {ItemMapper.class}
)
public abstract class SectionMapper {


    /**
     * Section to section dto section dto.
     *
     * @param section the section
     * @return the section dto
     */
    public abstract SectionDTO SectionToSectionDTO(Section section);

    /**
     * Rooms to section dto list.
     *
     * @param sections the sections
     * @return the list
     */
    public abstract List<SectionDTO> RoomsToSectionDTO(List<Section> sections);
}
