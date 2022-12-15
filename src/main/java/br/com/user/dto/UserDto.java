package br.com.user.dto;

import br.com.user.model.User;
import br.com.user.model.enums.Status;

import java.util.Collection;
import java.util.List;

public record UserDto(String id,
                      String name,
                      Status status) {

    public UserDto (User user){
        this(
                user.getId(),
                user.getName(),
                user.getStatus()
            );
    }

    public static List<UserDto> convert(Collection<User> users) {
        return users.stream()
                .map(UserDto::new)
                .toList();
    }
}
