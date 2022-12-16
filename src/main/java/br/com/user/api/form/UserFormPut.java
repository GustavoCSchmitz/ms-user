package br.com.user.api.form;

import br.com.user.model.User;
import br.com.user.model.enums.Status;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record UserFormPut(
        @NotBlank(message = "name must not be blank")
        String name,
        @NotNull(message = "status must be not null")
        Status status
) {
    public User toUser() {
        return new User(name, status);
    }
}
