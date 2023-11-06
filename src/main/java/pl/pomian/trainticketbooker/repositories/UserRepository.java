package pl.pomian.trainticketbooker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.pomian.trainticketbooker.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Query(
            nativeQuery = true,
            value = "UPDATE users " +
                    "SET password = ? " +
                    "WHERE id = ?;"
    )
    void updatePasswordById(byte[] password, Long id);
}
