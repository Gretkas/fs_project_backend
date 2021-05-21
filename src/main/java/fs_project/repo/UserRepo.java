package fs_project.repo;


import fs_project.model.dataEntity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * The interface User repo. Implements default db operations through JpaRepository.
 */
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    /**
     * Gets user by name.
     *
     * @param userName the user name
     * @return the user by name
     */
    @Query(
            value="SELECT * FROM User WHERE user_name = ?1",
            nativeQuery = true
    )
    Optional<User> getUserByName(String userName);

    /**
     * Find user by email optional.
     *
     * @param email the email
     * @return the optional
     */
    Optional<User> findUserByEmail(String email);

    /**
     * Find user by id optional.
     *
     * @param id the id
     * @return the optional
     */
    Optional<User> findUserById(Long id);
}