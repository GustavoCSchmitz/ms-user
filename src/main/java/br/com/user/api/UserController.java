package br.com.user.api;

import br.com.user.api.contract.UserApi;
import br.com.user.api.form.UserForm;
import br.com.user.api.form.UserFormPut;
import br.com.user.dto.UserDto;
import br.com.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService service;

    @Override
    public UserDto createUser(@Valid UserForm userForm) {
        log.info("[USER - API] Creating user");
        return service.createUser(userForm);
    }

    @Override
    public List<UserDto> getAllUsers() {
        log.info("[USER - API] List users");
        return service.getAllUsers();
    }

    @Override
    public UserDto getUser(String id) {
        log.info("[USER - API] Get user by id");
        return service.getUserById(id);
    }

    @Override
    public UserDto updateUser(String id, @Valid UserFormPut form) {
        log.info("[USER - API] Updating user");
        return service.updateUser(id, form);
    }

    @Override
    public void deleteUser(String id) {
        log.info("[USER - API] Deleting user");
        service.deleteUser(id);
    }

}
