package acc.br.pessoa_jwt.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreatePessoaDTO(
        @NotNull Integer age,
        @NotBlank String name,
        @Email @NotBlank String email
) {
}

