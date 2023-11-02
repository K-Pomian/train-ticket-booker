package pl.pomian.trainticketbooker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pomian.trainticketbooker.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
