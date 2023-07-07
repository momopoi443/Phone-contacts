package chi.testtask.phonecontacts.services;

import chi.testtask.phonecontacts.models.dtos.UserDTO;
import chi.testtask.phonecontacts.models.entities.User;

public interface UserService {

    User getUserEntityById(Long userId);

    UserDTO getUserById(Long userId);
}
