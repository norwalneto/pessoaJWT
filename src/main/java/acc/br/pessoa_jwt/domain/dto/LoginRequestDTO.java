package acc.br.pessoa_jwt.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
        @NotBlank(message = "Email obrigatório")
        String email,

        @NotBlank(message = "Senha obrigatória")
        String password
) {
}
