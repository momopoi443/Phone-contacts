package chi.testtask.phonecontacts.mappers;

import chi.testtask.phonecontacts.models.dtos.ContactDTO;
import chi.testtask.phonecontacts.models.entities.Contact;
import chi.testtask.phonecontacts.models.entities.Email;
import chi.testtask.phonecontacts.models.entities.PhoneNumber;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContactMapperImpl implements ContactMapper {

    @Override
    public ContactDTO contactToContactDTO(Contact contact) {
        if (contact == null) {
            return null;
        }

        ContactDTO contactDTO = new ContactDTO();

        contactDTO.setId(contact.getId());
        contactDTO.setName(contact.getName());
        contactDTO.setOwnerId(contact.getOwner().getId());

        List<String> emails = contact.getEmails()
                .stream()
                .map(Email::getEmailAddress)
                .toList();
        contactDTO.setEmails(emails);

        List<String> phoneNumbers = contact.getPhoneNumbers()
                .stream()
                .map(PhoneNumber::getPhoneNumber)
                .toList();
        contactDTO.setEmails(phoneNumbers);

        return contactDTO;
    }
}
