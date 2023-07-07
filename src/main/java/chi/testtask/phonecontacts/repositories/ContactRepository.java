package chi.testtask.phonecontacts.repositories;

import chi.testtask.phonecontacts.models.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
