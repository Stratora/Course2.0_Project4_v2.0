package com.course.model.dto;

import com.course.model.dto.validator.ValidEmail;
import com.course.model.dto.validator.ValidPassword;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by fg on 7/26/2016.
 *
 * The class handles transmission of registration user data from view to controller.
 */

@Setter
@Getter
@EqualsAndHashCode(of = {"email", "firstName", "lastName"})
@ToString(of = {"email", "firstName", "lastName"})
@ValidPassword
public class UserRegistrationDTO {
    @NotNull
    @Size(min = 1)
    private String firstName;

    @NotNull
    @Size(min = 1)
    private String lastName;

    @NotNull
    @Size(min = 3, message = "Password to short")
    private String password;

    @NotNull
    @Size(min = 3, message = "Password to short")
    private String matchingPassword;

    @NotNull
    @Size(min = 1)
    @ValidEmail
    private String email;
}
