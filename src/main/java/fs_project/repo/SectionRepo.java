package fs_project.repo;

import fs_project.model.dataEntity.Section;
import fs_project.model.dataEntity.User;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * The interface Section repo. Implements default db operations through JpaRepository.
 */
public interface SectionRepo extends JpaRepository<Section, Long> {
}
