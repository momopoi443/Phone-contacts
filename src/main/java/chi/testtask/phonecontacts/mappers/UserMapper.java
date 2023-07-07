package chi.testtask.phonecontacts.mappers;

import chi.testtask.phonecontacts.models.dtos.UserDTO;
import chi.testtask.phonecontacts.models.entities.User;

public interface UserMapper {

    UserDTO userToUserDTO(User user);
}
