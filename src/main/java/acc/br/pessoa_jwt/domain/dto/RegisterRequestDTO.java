package acc.br.pessoa_jwt.domain.dto;

import acc.br.pessoa_jwt.domain.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequestDTO(

        @NotBlank
        String name,

        @Email
        @NotBlank
        String email,

        @Size(min = 6)
        String password,

        Role role

) {
}
