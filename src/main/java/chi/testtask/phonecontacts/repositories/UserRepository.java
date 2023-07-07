package chi.testtask.phonecontacts.repositories;

import chi.testtask.phonecontacts.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
