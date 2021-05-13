package fs_project.repo;


import fs_project.model.dataEntity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    @Query(
            value="SELECT * FROM User WHERE user_name = ?1",
            nativeQuery = true
    )
    Optional<User> getUserByName(String userName);
}