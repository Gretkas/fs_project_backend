package fs_project.repo;


import fs_project.model.dataEntity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {


    @Query(
            value="SELECT * FROM User WHERE user_id = ?1",
            nativeQuery = true
    )
    User getUser(long id);

    @Query(
            value="SELECT * FROM User",
            nativeQuery = true
    )
    Set<User> getUsers();
}