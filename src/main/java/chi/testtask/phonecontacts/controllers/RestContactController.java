package chi.testtask.phonecontacts.controllers;

import chi.testtask.phonecontacts.models.dtos.ContactCreationDTO;
import chi.testtask.phonecontacts.models.dtos.ContactDTO;
import chi.testtask.phonecontacts.services.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class RestContactController {
    public static final String CONTACT_PATH = "/api/v1/contacts";
    public static final String CONTACT_PATH_ID = CONTACT_PATH + "/{contactId}";
    public static final String CONTACT_PATH_OWNER_ID = CONTACT_PATH + "/{ownerId}";


    private final ContactService contactService;

    @GetMapping(CONTACT_PATH_OWNER_ID)
    public List<ContactDTO> getAllContacts(
            @PathVariable("ownerId") Long ownerId
    ) {
        return contactService.getAllContactsForUser(ownerId);
    }

    @GetMapping(CONTACT_PATH_ID)
    public ContactDTO getContactById(
            @PathVariable("contactId") Long contactId
    ) {
        return contactService.getContactById(contactId);
    }

    @PostMapping(CONTACT_PATH)
    public ResponseEntity createContact(
            @Valid @RequestBody ContactCreationDTO creationDTO
    ) {
        //some how fetch current logged user id

        ContactDTO savedContact = contactService.createContact(ownerId, creationDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", CONTACT_PATH + "/" + savedContact.getId());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping(CONTACT_PATH_ID)
    public ResponseEntity updateContact(
            @PathVariable("contactId") Long contactId,
            @Valid @RequestBody ContactCreationDTO creationDTO
    ) {
        contactService.updateContactById(contactId, creationDTO);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(CONTACT_PATH_ID)
    public ResponseEntity deleteContact(
            @PathVariable("contactId") Long contactId
    ) {
        contactService.deleteContactById(contactId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
