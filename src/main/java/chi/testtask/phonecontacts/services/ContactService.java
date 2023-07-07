package chi.testtask.phonecontacts.services;

import chi.testtask.phonecontacts.models.dtos.ContactCreationDTO;
import chi.testtask.phonecontacts.models.dtos.ContactDTO;

import java.util.List;

public interface ContactService {

    List<ContactDTO> getAllContactsForUser(Long ownerId);

    ContactDTO getContactById(Long contactId);

    ContactDTO createContact(Long ownerId, ContactCreationDTO creationInfo);

    void updateContactById(Long contactId, ContactCreationDTO updateInfo);

    void deleteContactById(Long contactId);
}
