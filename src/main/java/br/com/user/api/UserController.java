package br.com.user.api;

import br.com.user.api.contract.UserApi;
import br.com.user.api.form.UserForm;
import br.com.user.dto.UserDto;
import br.com.user.model.enums.Status;
import br.com.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService service;

    @Override
    public UserDto createUser(UserForm userForm) {
        return service.createUser(userForm);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return service.getAllUsers();
    }

}
