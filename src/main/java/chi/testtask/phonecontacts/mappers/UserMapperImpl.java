package chi.testtask.phonecontacts.mappers;

import chi.testtask.phonecontacts.models.dtos.UserDTO;
import chi.testtask.phonecontacts.models.entities.Contact;
import chi.testtask.phonecontacts.models.entities.User;

import java.util.List;

public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO userToUserDTO(User user) {
        if (user == null) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());

        List<Long> contactIds = user.getContacts()
                .stream()
                .map(Contact::getId)
                .toList();
        userDTO.setContactIds(contactIds);

        return userDTO;
    }
}
