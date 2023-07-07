package chi.testtask.phonecontacts.mappers;

import chi.testtask.phonecontacts.models.dtos.ContactDTO;
import chi.testtask.phonecontacts.models.entities.Contact;

public interface ContactMapper {

    ContactDTO contactToContactDTO(Contact contact);
}
