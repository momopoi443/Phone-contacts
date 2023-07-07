package chi.testtask.phonecontacts.services;

import chi.testtask.phonecontacts.mappers.ContactMapper;
import chi.testtask.phonecontacts.models.dtos.ContactCreationDTO;
import chi.testtask.phonecontacts.models.dtos.ContactDTO;
import chi.testtask.phonecontacts.models.entities.*;
import chi.testtask.phonecontacts.repositories.ContactRepository;
import chi.testtask.phonecontacts.services.exceptions.InvalidArgumentException;
import chi.testtask.phonecontacts.services.exceptions.NotFoundException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Validated
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    private final UserService userService;

    @Override
    public List<ContactDTO> getAllContactsForUser(Long ownerId) {
        User owner = userService.getUserEntityById(ownerId);
        return owner.getContacts()
                .stream()
                .map(contactMapper::contactToContactDTO)
                .toList();
    }

    @Override
    public ContactDTO getContactById(@NotNull Long contactId) {
        return contactMapper.contactToContactDTO(contactRepository
                .findById(contactId)
                .orElseThrow(() -> {
                    var ex = new NotFoundException("No contact with such id");
                    log.error(ex.getLocalizedMessage());
                    return ex;
                }));
    }

    @Override
    public ContactDTO createContact(@NotNull Long ownerId, @Valid ContactCreationDTO creationInfo) {
        User owner = userService.getUserEntityById(ownerId);
        validateContactNameForUniqueness(creationInfo.getName(), owner);

        Contact contactToSave = new Contact();
        mapCreationDTOToContact(creationInfo, contactToSave);
        owner.addContact(contactToSave);

        contactToSave = contactRepository.save(contactToSave);
        log.info("Created new contact entity with id:" + contactToSave.getId());

        return ContactDTO.builder()
                .id(contactToSave.getId())
                .emails(creationInfo.getEmails())
                .name(creationInfo.getName())
                .ownerId(contactToSave.getOwner().getId())
                .phoneNumbers(creationInfo.getPhoneNumbers())
                .build();
    }

    @Override
    public void updateContactById(@NotNull Long contactId, @Valid ContactCreationDTO updateInfo) {
        Contact contactToUpdate = contactRepository
                .findById(contactId)
                .orElseThrow(() -> {
                    var ex = new NotFoundException("No contact with such id");
                    log.error(ex.getLocalizedMessage());
                    return ex;
                });
        validateContactNameForUniqueness(updateInfo.getName(), contactToUpdate.getOwner());
        mapCreationDTOToContact(updateInfo, contactToUpdate);

        contactRepository.save(contactToUpdate);
        log.info("Updated contact entity with id:" + contactId);
    }

    @Override
    public void deleteContactById(@NotNull Long contactId) {
        if (!contactRepository.existsById(contactId)) {
            var ex = new NotFoundException("No contact with such id");
            log.error(ex.getLocalizedMessage());
            throw ex;
        }

        contactRepository.deleteById(contactId);
        log.info("Deleted contact entity with id:" + contactId);
    }


    public void validateContactNameForUniqueness(String contactName, User owner) {
        if (owner.getContacts()
                .stream()
                .anyMatch(contact -> contact.getName().equals(contactName))
        ) {
            throw new InvalidArgumentException("Contact names must be unique");
        }
    }

    private void mapCreationDTOToContact(ContactCreationDTO updateInfo, Contact contact) {
        contact.setName(updateInfo.getName());
        updateInfo.getEmails()
                .forEach(emailAddress -> {
                    contact.addEmail(new Email(null, emailAddress, null));
                });
        updateInfo.getPhoneNumbers()
                .forEach(phoneNumber -> {
                    contact.addPhoneNumber(new PhoneNumber(null, phoneNumber, null));
                });
    }
}
