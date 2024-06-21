package com.ecommerce.ecommercebackend.api.model;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RegistartionBody {
    @NotNull
    private String username;
    @Email
    private String email;
    @NotNull
    @Size(min = 6, max = 20)
    private String password;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
}
