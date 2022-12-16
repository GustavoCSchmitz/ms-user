package br.com.user.service;

import br.com.user.api.form.UserForm;
import br.com.user.api.form.UserFormPut;
import br.com.user.dto.UserDto;
import br.com.user.model.User;
import br.com.user.model.enums.Status;
import br.com.user.repository.UserRepository;
import br.com.user.util.CopyPropertiesUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Transactional
    public UserDto createUser(@Valid UserForm form) {
        try{
            User user = form.toUser(form);
            User savedUser = saveUser(user);
            UserDto userDto = new UserDto(savedUser);
            log.info("User created sucessfully");
            return userDto;
        }catch (Exception e){
            log.error("Cannot save user");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    private UserDto getUserDTO(User user) {
        UserDto userDto = new UserDto(user);
        log.info("User returned");
        return userDto;
    }

    @Transactional
    public UserDto updateUser(String id, UserFormPut form) {
        User user = findUserById(id);
        User userUpdated = updateUser(form, user);

        UserDto userDto = new UserDto(userUpdated);
        log.info("User updated successfully");

        return userDto;
    }

    private User updateUser(UserFormPut form, User user) {
        try{
            User userUpdated = form.toUser();
            CopyPropertiesUtils.copyFieldsNotNull(user, userUpdated);
            return saveUser(user);
        }catch (Exception e){
            log.error("Cannot update user");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity deleteUser(String id) {
        try {
            repository.deleteById(id);
            log.info("User deleted successfully");
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            log.error("Cannot delete user");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
