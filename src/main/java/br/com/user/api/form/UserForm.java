package br.com.user.api.form;

import br.com.user.model.User;
import br.com.user.model.enums.Status;

import javax.validation.constraints.NotBlank;

public record UserForm(
        @NotBlank(message = "name must not be blank")
        String name
) {
    public User toUser(UserForm form) {
        return new User(form.name(), Status.ACTIVE);
    }

}
