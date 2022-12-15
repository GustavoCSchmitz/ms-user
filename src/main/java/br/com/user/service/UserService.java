package br.com.user.service;

import br.com.user.api.form.UserForm;
import br.com.user.dto.UserDto;
import br.com.user.model.User;
import br.com.user.model.enums.Status;
import br.com.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Transactional
    public UserDto createUser(@Valid UserForm form) {
        try{
            User user = getUser(form);
            User savedUser = saveUser(user);
            UserDto userDto = new UserDto(savedUser);
            return userDto;
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public List<UserDto> getAllUsers() {
        List<User> users = getAllUsersList();
        List<UserDto> usersDTOList = UserDto.convert(users);
        return usersDTOList;
    }

    private List<User> getAllUsersList() {
        return repository.findAll();
    }

    private User saveUser(User user) {
        return repository.save(user);
    }

    private User getUser(UserForm form) {
        return User.builder()
                .name(form.name())
                .status(Status.ACTIVE)
                .build();
    }

}
