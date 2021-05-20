package fs_project.mapping.section;

import fs_project.mapping.dto.RoomDTO;
import fs_project.mapping.dto.SectionDTO;
import fs_project.mapping.item.ItemMapper;
import fs_project.model.dataEntity.Room;
import fs_project.model.dataEntity.Section;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        /*
         * Policy for each unmapped target's field(s) in any of
         * the class's methods.
         */
        unmappedTargetPolicy = ReportingPolicy.WARN, // todo change to ignore in production stage
        uses = {ItemMapper.class}
)
public abstract class SectionMapper {


    public abstract SectionDTO SectionToSectionDTO(Section section);
    public abstract List<SectionDTO> RoomsToSectionDTO(List<Section> sections);
}
