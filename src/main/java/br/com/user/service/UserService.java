package br.com.user.service;

import br.com.user.dto.UserDto;
import br.com.user.model.User;
import br.com.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void createUser(UserDto userDto) {
        repository.save(new User(userDto.id(), userDto.name()));

    }
}
