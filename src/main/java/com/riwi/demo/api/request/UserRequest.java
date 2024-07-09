package com.riwi.demo.api.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @Size(min = 15, max = 50)
    private String userName;
    @Size(min = 15, max = 100)
    private String password;
    @Email
    @NotBlank(message = "El correo electrónico es requerido")
    private String email;
    @NotBlank(message = "El nombre completo es requerido")
    private String fullName;
    @NotBlank(message = "El rol del usuario es requerido")
    private String role;

}
