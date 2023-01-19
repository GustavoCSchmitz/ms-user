package br.com.user.service;

import br.com.user.api.form.UserForm;
import br.com.user.api.form.UserFormPut;
import br.com.user.dto.UserDto;
import br.com.user.exceptions.NotFoundException;
import br.com.user.exceptions.UserException;
import br.com.user.model.User;
import br.com.user.repository.UserRepository;
import br.com.user.util.CopyPropertiesUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private static final String USER_NOT_FOUND_MESSAGE = "User not found";

    private final UserRepository repository;

    @Transactional
    public UserDto createUser(@Valid UserForm form) {
        try{
            User user = form.toUser(form);
            User savedUser = saveUser(user);
            UserDto userDto = new UserDto(savedUser);
            log.info("User created successfully");
            return userDto;
        }catch (Exception e){
            log.error("Cannot save user");
            throw new UserException(e.getMessage());
        }
    }

    public List<UserDto> getAllUsers() {
        List<User> users = getAllUsersList();
        List<UserDto> usersDTOList = UserDto.convert(users);
        log.info("User list returned");

        return usersDTOList;
    }

    public UserDto getUserById(String id) {
        return repository.findById(id)
                .map(this::getUserDTO)
                .orElseThrow(this::handleNotFoundException);
    }

    @Transactional
    public UserDto updateUser(String id, UserFormPut form) {
        User user = findUserById(id);
        User userUpdated = updateUser(form, user);

        UserDto userDto = new UserDto(userUpdated);
        log.info("User updated successfully");

        return userDto;
    }

    public void deleteUser(String id) {
        try {
            repository.deleteById(id);
            log.info("User deleted successfully");
        }catch (Exception e){
            log.error("Cannot delete user");
            throw new UserException(e.getMessage());
        }
    }

    private User updateUser(UserFormPut form, User user) {
        try{
            User userUpdated = form.toUser();
            CopyPropertiesUtils.copyFieldsNotNull(user, userUpdated);
            return saveUser(user);
        }catch (Exception e){
            log.error("Cannot update user");
            throw new UserException(e.getMessage());
        }
    }

    private List<User> getAllUsersList() {
        return repository.findAll();
    }

    private User saveUser(User user) {
        return repository.save(user);
    }

    private User findUserById(String id) {
        return repository.findById(id)
                .orElseThrow(this::handleNotFoundException);
    }

    private NotFoundException handleNotFoundException() {
        log.error(USER_NOT_FOUND_MESSAGE);
        return new NotFoundException(USER_NOT_FOUND_MESSAGE);
    }

    private UserDto getUserDTO(User user) {
        UserDto userDto = new UserDto(user);
        log.info("User returned");
        return userDto;
    }
}
