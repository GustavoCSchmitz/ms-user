package br.com.user.dto;

import br.com.user.model.User;

public record UserDto(String id, String name) {

    public UserDto (User user){
        this(
                user.getId(),
                user.getName()
            );
    }
}
