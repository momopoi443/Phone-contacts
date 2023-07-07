package chi.testtask.phonecontacts.services;

import chi.testtask.phonecontacts.mappers.UserMapper;
import chi.testtask.phonecontacts.models.dtos.UserDTO;
import chi.testtask.phonecontacts.models.entities.User;
import chi.testtask.phonecontacts.repositories.UserRepository;
import chi.testtask.phonecontacts.services.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
@Slf4j
@Validated
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User getUserEntityById(Long userId) {
        return userRepository
                .findById(userId)
                .orElseThrow(() -> {
                    var ex = new NotFoundException("No user with such id");
                    log.error(ex.getLocalizedMessage());
                    return ex;
                });
    }

    @Override
    public UserDTO getUserById(Long userId) {
        return userMapper.userToUserDTO(userRepository
                .findById(userId)
                .orElseThrow(() -> {
                    var ex = new NotFoundException("No user with such id");
                    log.error(ex.getLocalizedMessage());
                    return ex;
                }));
    }
}
